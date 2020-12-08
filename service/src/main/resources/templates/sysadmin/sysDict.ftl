<@l.manage tab="sysDict" title="字典管理" import="base,vue,iview,layout" js="" >
    <div id="App" class="reletiveContainer list-panel-container" v-cloak>
        <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
            <div class="reletiveContainer">
                <div class="absoluteArea func-area">
                    <div class="right func-item">
                        <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                    </div>
                </div>
                <div class="absoluteArea list-tree-area" style="">
                    <Tree :data="dict_tree" @on-select-change="treeSelectChange" class="tree-menu"></Tree>
                </div>
                <div class="absoluteArea list-right-area">
                    <i-table border :columns="dictColumn" :data="dictList"></i-table>
                </div>
                <div class="absoluteArea page-area" style="bottom: 0;height: 40px;left:300px;right: 0px;">
                    <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange" :page-size="pageSize"
                          @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
                </div>
            </div>
        </div>
        <Drawer :title="drawerTitle" width="420" :closable="false" v-model="dictDrawer">
            <div class="absoluteArea reset-drawer-body" style="">
                <i-form class="form-reset" ref="dictForm" :rules="dictValidate" :model="dict">
                    <form-item label="字典名称" prop='dictName'>
                        <i-input v-model="dict.dictName"  name="dictName" placeholder=""></i-input>
                    </form-item>
                    <form-item label="字典代码" prop='dictCode'>
                        <i-input v-model="dict.dictCode" name="dictCode" placeholder=""></i-input>
                    </form-item>
                    <form-item label="字典值" prop="dictValue">
                        <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="dict.dictValue" name="dictValue"></i-input>
                    </form-item>
                    <form-item label="排序字段（大于1的整数）" prop="sort">
                        <i-input v-model="dict.sort" name="sort"></i-input>
                    </form-item>
                    <form-item label="父节点" prop="parent">
                        <i-input v-model="currentParentOrg.title" disabled></i-input>
                    </form-item>
                </i-form>
            </div>
            <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
                <i-button style="margin-right: 8px" @click="dictDrawer = false">取消</i-button>
                <i-button type="primary" @click="operate">提交</i-button>
            </div>
        </Drawer>
        <Modal v-model="deleteConfirm" title="确认删除">
            <p slot="header" style="color:#f60;">
                <Icon type="ios-information-circle"></Icon>
                <span>确认删除</span>
            </p>
            <p>此操作将删除字典及关联信息，是否继续操作？</p>
            <div slot="footer">
                <i-button @click="cancelDel" size="large">取消</i-button>
                <i-button @click="confirmDel" size="large">确定</i-button>
            </div>
        </Modal>
    </div>

    <style type="text/css">

    </style>

    <script type="text/javascript">
        var App = {};
        var func = {};
        var pageSizes = 10;
        if (top.document.body.clientWidth > 1900) {
            pageSizes = 20
        }
        func.dictCodeDump = function (rule, dictCode, callback) {
            var url = '<@c.rootPath/>' + api.sysDict_dictCodeDump;
            var formdata = new FormData();
            formdata.append("dictCode", dictCode);
            formdata.append("dictId", appData.dict.id);
            _$.post(App, formdata, url, function (_r, _x) {
                if (_r.success) {
                    var res = _r.data;
                    if (res) {
                        return callback(new Error());
                    }
                    return callback();
                }
            }, function (_ex) {
                _u.error(App, JSON.stringify(_ex));
                return callback();
            });
        };
        var appData = {
            operateType: "add",//展开抽屉的类型
            drawerTitle: "新增字典项",//展开抽屉的标题
            dictDrawer: false,//抽屉指针
            deleteConfirm: false,//删除确认modal
            pageTotal: pageSizes,
            pageSize: pageSizes,
            dict_tree: [],
            currentParentOrg: {},
            dict: {//当前对象
                id: "",
                dictCode: "",
                dictName: "",
                dictValue: "",
                sort: "",
                parentId: ""
            },
            searchParam: {//查询参数
                dictCode: "",
                dictName: "",
                parentId: "1",//parent id
                curPage: 1,
                pageSize: pageSizes,
                containParent: true
            },
            dictColumn: [//list列信息
                {
                    type: 'index',
                    width: 60,
                    align: 'center',
                    indexMethod: function (row) {
                        return (appData.searchParam.curPage - 1) * 10 + row._index + 1;
                    }
                },
                {
                    title: '字典名',
                    align: 'center',
                    tooltip: true,
                    key: 'dictName'
                },
                {
                    title: '字典代码',
                    align: 'center',
                    tooltip: true,
                    key: 'dictCode'
                },
                {
                    title: '字典值',
                    align: 'center',
                    tooltip: true,
                    key: 'dictValue'
                },
                {
                    title: '操作',
                    key: 'action',
                    width: 180,
                    align: 'center',
                    fixed: 'right',
                    render: function (h, params) {
                        return h("div", [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: function () {
                                        func.edit(params.row);
                                    }
                                }
                            }, "修改"),
                            h('Button', {
                                props: {
                                    type: 'error',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: function () {
                                        func.delete(params.row);
                                    }
                                }
                            }, "删除")
                        ])
                    }
                }
            ],
            dictList: [],//list 数据
            dictValidate: {
                dictName: [
                    {required: true, message: '输入字典名不能为空', trigger: 'blur', validator: _u.validate_notNull},
                    {type: 'string', min: 2, message: '单位名必须是2个以上的字符串组成', trigger: 'blur'}
                ],
                dictCode: [
                    {required: true, message: '输入字典代码不能为空', trigger: 'blur', validator: _u.validate_notNull},
                    {type: 'string', min: 2, message: '字典代码必须是2个以上的字符串组成', trigger: 'blur'},
                    {validator: func.dictCodeDump, trigger: 'blur', message: '字典代码不能重复'}
                ],
                dictValue: [
                    {required: true, message: '输入字典值不能为空', trigger: 'blur', validator: _u.validate_notNull},
                    {type: 'string', min: 2, message: '字典值必须是2个以上的字符串组成', trigger: 'blur'}
                ],
                sort: [
                    {validator: _u.validate_int, trigger: 'blur', message: '需填入正整数'}
                ]
            }
        };

        /*页面交互*/
        func.add = function () {
            appData.drawerTitle = "新增字典项";
            appData.operateType = "add";
            for (var key in appData.dict) {
                appData.dict[key] = ""
            }
            appData.dict.parentId = appData.currentParentOrg.id;
            appData.dictDrawer = true;
            App.$nextTick(function () {
                App.$refs['dictForm'].validate()
            })
        };
        func.edit = function (dict) {
            appData.drawerTitle = "字典信息查看编辑";
            App.$refs['dictForm'].resetFields();
            appData.operateType = "edit";
            appData.dict = _$.clone_str(dict);
            appData.dictDrawer = true;
        };
        func.delete = function (dict) {
            appData.deleteConfirm = true;
            appData.dict = _$.clone_str(dict);
        };
        func.drawerClose = function () {
            appData.drawerShow = false;
        };

        /*请求*/
        func.delRequest = function () {
            var url = '<@c.rootPath/>' + api.sysDict_del;
            var data = {id: appData.dict.id};
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    _u.success(App, '字典项删除成功，刷新页面');
                    appData.searchParam.parentId = 1;
                    func.refreshList();
                    func.getTree(true);
                } else {
                    _u.error(App, _r.message);
                }
            }, function (_ex) {
                _u.error(App, JSON.stringify(_ex));
            });
        };
        func.refreshList = function () {
            var url = '<@c.rootPath/>' + api.sysDict_query;
            var data = {
                param: JSON.stringify(appData.searchParam),
                curPage: appData.searchParam.curPage,
                pageSize: appData.searchParam.pageSize
            };
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    var res = _r.data;
                    appData.dictList = res.records;
                    appData.searchParam.pageSize = res.size;
                    appData.searchParam.curPage = res.current;
                    appData.pageTotal = res.total;
                } else {
                    _u.error(App, _r.message);
                }
            }, function (_ex) {
                _u.error(App, JSON.stringify(_ex));
            });
        };
        func.getTree = function (reset) {
            var url = '<@c.rootPath/>' + api.sysDict_tree;
            var data = {id: "1"};
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    appData.dict_tree = [_r.data];
                    if (reset) {
                        appData.currentParentOrg = _r.data;
                    }
                } else {
                    _u.error(App, _r.message);
                }
            }, function (_ex) {
                _u.error(App, JSON.stringify(_ex));
            });
        };
        func.addOrEditRequest = function () {
            var url = "";
            var request = new FormData(document.querySelector('form'));
            if (appData.operateType === 'edit') {
                url = '<@c.rootPath/>' + api.sysDict_edit;
                request.append('id',appData.dict.id);
            } else if (appData.operateType === 'add') {
                url = '<@c.rootPath/>' + api.sysDict_add;
                request.append('parentId',appData.dict.parentId);
            } else {
                _u.error(App, "bug原因：operateType 不在所给范围内");
            }
            App.$refs['dictForm'].validate(function (valid) {
                if (valid) {
                    _$.post(App, request, url, function (_r, _x) {
                        if (_r.success) {
                            _u.success(App, '操作成功，刷新列表');
                            appData.dictDrawer=false;
                            func.refreshList();
                            func.getTree(false);
                        } else {
                            _u.error(App, _r.message);
                        }
                    }, function (_ex) {
                        _u.error(App, JSON.stringify(_ex));
                    });
                } else {
                    _u.error(App, "表单校验失败，请按提示修改");
                }
            });
        };

        window.onload = function () {
            App = new Vue({
                el: '#App',
                data: appData,
                mounted: function () {
                    this.$nextTick(function () {
                        func.refreshList();
                        func.getTree(true);
                    })
                },
                methods: {
                    add: function () {
                        func.add();
                    },
                    dictChange: function () {
                        func.refreshList();
                        console.log("dictChange")
                    },
                    searchName: function () {
                        func.refreshList();
                        console.log("searchName")
                    },
                    cancelDel: function () {
                        this.deleteConfirm = false;
                        console.log("cancelDel")
                    },
                    confirmDel: function () {
                        func.delRequest();
                        this.deleteConfirm = false;
                        console.log("confirmDel")
                    },
                    operate: function () {
                        func.addOrEditRequest();
                    },
                    pageChange: function (page) {
                        this.searchParam.curPage = page;
                        func.refreshList();
                    },
                    pageSizeChange: function (size) {
                        this.searchParam.pageSize = size;
                        func.refreshList();
                    },
                    treeSelectChange: function (node) {
                        if (node.length > 0) {
                            this.currentParentOrg = _$.clone_str(node[0]);
                            this.searchParam.parentId = this.currentParentOrg.id;//查询以选中项作为根节点
                            this.searchParam.containParent = true;
                            func.refreshList();
                        }
                    }
                }
            });
        };
    </script>
</@l.manage>

<@l.manage tab="sysArea" title="区域管理" import="base,vue,iview,layout" js="" >
    <div id="appArea" class="reletiveContainer list-panel-container" v-cloak>
        <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
            <div class="reletiveContainer">
                <div class="absoluteArea func-area">
                    <div class="right func-item">
                        <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                    </div>
                </div>
                <div class="absoluteArea list-tree-area" style="">
                    <Tree :data="area_tree" @on-select-change="treeSelectChange" class="tree-menu"></Tree>
                </div>
                <div class="absoluteArea list-right-area">
                    <i-table border :columns="areaColumn" :data="areaList"></i-table>
                </div>
                <div class="absoluteArea page-area" style="bottom: 0;height: 40px;left:300px;right: 0px;">
                    <Page :total="pageTotal" :page-size-opts="[10,20]" :page-size="pageSize" @on-change="pageChange"
                          @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
                </div>
            </div>
        </div>
        <Drawer :title="drawerTitle" width="420" :closable="false" v-model="areaDrawer">
            <div class="absoluteArea reset-drawer-body" style="">
                <i-form class="form-reset" ref="areaForm" :rules="areaValidate" :model="area">
                    <form-item label="区域代码" prop='areaCode'>
                        <i-input v-model="area.areaCode" name="areaCode" placeholder=""></i-input>
                    </form-item>
                    <form-item label="区域名称" prop='areaName'>
                        <i-input v-model="area.areaName" name="areaName" placeholder=""></i-input>
                    </form-item>
                    <form-item label="排序字段（大于1的整数）" prop="sort">
                        <i-input v-model="area.sort" name="sort"></i-input>
                    </form-item>
                    <form-item label="父节点" prop="parent">
                        <i-input v-model="currentParentArea.title" disabled></i-input>
                    </form-item>
                </i-form>
            </div>
            <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
                <i-button style="margin-right: 8px" @click="areaDrawer = false">取消</i-button>
                <i-button type="primary" @click="operate">提交</i-button>
            </div>
        </Drawer>
        <Modal v-model="deleteConfirm" title="确认删除">
            <p slot="header" style="color:#f60;">
                <Icon type="ios-information-circle"></Icon>
                <span>确认删除</span>
            </p>
            <p>此操作将删除单位及关联信息，是否继续操作？</p>
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
        func.areaCodeDump = function (rule, areaCode, callback) {
            var url = '<@c.rootPath/>' + api.sysarea_areaCodeDump;
            var formdata = new FormData();
            formdata.append("areaCode", areaCode);
            formdata.append("areaId", appData.area.id);
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
            drawerTitle: "新增用户",//展开抽屉的标题
            areaDrawer: false,//抽屉指针
            deleteConfirm: false,//删除确认modal
            pageTotal: pageSizes,
            area_tree: [],
            currentParentArea: {},
            pageSize: pageSizes,
            area: {//当前对象
                id: "",
                areaName: "",
                sort: "",
                parentId: ""
            },
            searchParam: {//查询参数
                areaName: "",
                parentId: "1",//parent id
                curPage: 1,
                pageSize: pageSizes,
                containParent: true
            },
            areaColumn: [//list列信息
                {
                    type: 'index',
                    width: 60,
                    align: 'center',
                    indexMethod: function (row) {
                        return (appData.searchParam.curPage - 1) * 10 + row._index + 1;
                    }
                },
                {
                    title: '区域代码',
                    align: 'center',
                    tooltip: true,
                    key: 'areaCode'
                },
                {
                    title: '区域名称',
                    align: 'center',
                    tooltip: true,
                    key: 'areaName'
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
            areaList: [],//list 数据
            areaValidate: {
                areaCode: [
                    {required: true, message: '输入区域代码不能为空', trigger: 'blur', validator: _u.validate_notNull},
                    {type: 'string', min: 2, message: '必须是2个以上的字符串组成', trigger: 'blur'},
                    {type: 'string', max: 15, message: '长度不能超过15', trigger: 'blur'},
                    {validator: func.areaCodeDump, trigger: 'blur', message: '区域代码不能重复'}
                ],
                areaName: [
                    {required: true, message: '输入区域名称不能为空', trigger: 'blur', validator: _u.validate_notNull},
                    {type: 'string', min: 2, message: '必须是2个以上的字符串组成', trigger: 'blur'},
                    {type: 'string', max: 30, message: '长度不能超过30', trigger: 'blur'}
                ],
                sort: [
                    {validator: _u.validate_int, trigger: 'blur', message: '需填入正整数'}
                ]

            }
        };

        /*页面交互*/
        func.add = function () {
            appData.drawerTitle = "区域新增";
            appData.operateType = "add";
            for (var key in appData.area) {
                appData.area[key] = ""
            }
            appData.area.parentId = appData.currentParentArea.id;
            appData.areaDrawer = true;
            App.$nextTick(function () {
                App.$refs['areaForm'].validate()
            })
        };
        func.edit = function (area) {
            appData.drawerTitle = "区域信息查看编辑";
            App.$refs['areaForm'].resetFields();
            appData.operateType = "edit";
            appData.area = _$.clone_str(area);
            appData.areaDrawer = true;
        };
        func.delete = function (area) {
            appData.deleteConfirm = true;
            appData.area = _$.clone_str(area);
        };
        func.drawerClose = function () {
            appData.drawerShow = false;
        }

        /*请求*/
        func.delRequest = function () {
            _$.ajax({
                url: '<@c.rootPath/>' + api.sysarea_del,
                dataType: 'json',
                type: 'POST',
                data: {id: appData.area.id},
                success: function (_r, _s) {
                    if (_r.success) {
                        _u.success(App, '删除成功，刷新页面');
                        appData.searchParam.parentId = 1;
                        func.refreshList();
                        func.getTree(true);
                    } else {
                        _u.error(App, _r.message);
                    }
                },
                fail: function (_ex) {
                    _u.error(App, JSON.stringify(_ex));
                }
            });
        };
        func.refreshList = function () {
            var url = '<@c.rootPath/>' + api.sysarea_query;
            var data = {
                param: JSON.stringify(appData.searchParam),
                curPage: appData.searchParam.curPage,
                pageSize: appData.searchParam.pageSize
            };
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    var res = _r.data;
                    appData.areaList = res.records;
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
            _u.getAreaTree(App, function (data) {
                appData.area_tree = [data];
                if (reset) {
                    appData.currentParentArea = data;
                }
            })
        };
        func.addOrEditRequest = function () {
            var url = "";
            var request = new FormData(document.querySelector('form'));
            if (appData.operateType === 'edit') {
                url = '<@c.rootPath/>' + api.sysarea_edit;
                request.append('id',appData.area.id);
            } else if (appData.operateType === 'add') {
                url = '<@c.rootPath/>' + api.sysarea_add;
                request.append('parentId',appData.area.parentId);
            } else {
                _u.error(App, "bug原因：operateType 不在所给范围内");
            }
            App.$refs['areaForm'].validate(function (valid) {
                if (valid) {
                    _$.post(App, request, url, function (_r, _x) {
                        if (_r.success) {
                            _u.success(App,'操作成功，刷新列表');
                            appData.areaDrawer=false;
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
                el: '#appArea',
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
                    searchName: function () {
                        func.refreshList();
                        console.log("searchName")
                    },
                    confirmPass: function () {
                        func.confirmPass();
                        console.log("confirmPass")
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
                            this.currentParentArea = _$.clone_str(node[0]);
                            this.searchParam.parentId = this.currentParentArea.id;//查询以选中项作为根节点
                            this.searchParam.containParent = true;
                            func.refreshList();
                        }
                    }
                }
            });
        };
    </script>
</@l.manage>

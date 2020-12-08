<@l.manage tab="sysMenu" title="菜单管理" import="base,vue,iview,layout" js="" >
    <div id="appMenu" class="reletiveContainer list-panel-container" v-cloak>
        <div class="absoluteArea panel-body" style="top: 40px;bottom: 0;width: 100%">
            <div class="reletiveContainer">
                <div class="absoluteArea func-area">
                    <div class="right func-item">
                        <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                    </div>
                </div>
                <div class="absoluteArea list-tree-area" style="">
                    <Tree :data="menu_tree" @on-select-change="treeSelectChange" class="tree-menu"></Tree>
                </div>
                <div class="absoluteArea list-right-area">
                    <i-table border :columns="menuColumn" :data="menuList"></i-table>
                </div>
                <div class="absoluteArea page-area" style="bottom: 0;height: 40px;left:300px;right: 0px;">
                    <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange"
                          @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
                </div>
            </div>
        </div>
        <Drawer :title="drawerTitle" width="420" :closable="false" v-model="menuDrawer">
            <div class="absoluteArea reset-drawer-body" style="">
                <i-form class="form-reset" ref="menuForm" :rules="menuValidate" :model="menu">
                    <form-item label="菜单地址" prop='menuUrl'>
                        <i-input v-model="menu.menuUrl" placeholder="" name="menuUrl"></i-input>
                    </form-item>
                    <form-item label="菜单名称" prop="menuName">
                        <i-input v-model="menu.menuName" placeholder="" name="menuName"></i-input>
                    </form-item>
                    <form-item label="菜单编号" prop="menuCode">
                        <i-input v-model="menu.menuCode" placeholder="" name="menuCode"></i-input>
                    </form-item>
                    <form-item label="前端路由" prop='menuRoute'>
                        <i-input v-model="menu.menuRoute" placeholder="" name="menuRoute"></i-input>
                    </form-item>
                    <form-item label="菜单图标" prop="menuIcon">
                        <i-input v-model="menu.menuIcon" name="menuIcon"></i-input>
                    </form-item>
                    <form-item label="排序字段（大于1的整数）" prop="sort">
                        <i-input v-model="menu.sort" name="sort"></i-input>
                    </form-item>
                    <form-item label="父节点" prop="parent">
                        <i-input v-model="currentParentMenu.title" disabled></i-input>
                    </form-item>
                </i-form>
            </div>
            <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
                <i-button style="margin-right: 8px" @click="menuDrawer = false">取消</i-button>
                <i-button type="primary" @click="operate">提交</i-button>
            </div>
        </Drawer>
        <Modal v-model="deleteConfirm" title="确认删除">
            <p slot="header" style="color:#f60;">
                <Icon type="ios-information-circle"></Icon>
                <span>确认删除</span>
            </p>
            <p>此操作将删除菜单项及关联信息，是否继续操作？</p>
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
        func.childNameDump = function (rule, menuName, callback) {
            var parentId = "";
            if (appData.operateType === 'edit') {
                parentId = appData.menu.parentId;
            } else if (appData.operateType === 'add') {
                parentId = appData.currentParentMenu.id;
            }
            var url = '<@c.rootPath/>' + api.sysmenu_childNameDump;
            var formdata = new FormData();
            formdata.append("parentId", parentId);
            formdata.append("menuName", menuName);
            formdata.append("menuId", appData.menu.id);
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
            drawerTitle: "新增菜单",//展开抽屉的标题
            menuDrawer: false,//抽屉指针
            deleteConfirm: false,//删除确认modal
            pageTotal: 10,
            menu_tree: [],
            currentParentMenu: {},
            menu: {//当前对象
                id: "",
                menuName: "",
                areaName: "",
                sort: "",
                parentId: ""
            },
            searchParam: {//查询参数
                menuName: "",
                parentId: "1",//parent id
                curPage: 1,
                pageSize: 10,
                containParent: true
            },
            menuColumn: [//list列信息
                {
                    type: 'index',
                    width: 60,
                    align: 'center',
                    indexMethod: function (row) {
                        return (appData.searchParam.curPage - 1) * 10 + row._index + 1;
                    }
                },
                {
                    title: 'parentId',
                    align: 'center',
                    tooltip: true,
                    key: 'parentId'
                },
                {
                    title: '菜单地址',
                    align: 'center',
                    tooltip: true,
                    key: 'menuUrl'
                },
                {
                    title: '前端路由',
                    align: 'center',
                    tooltip: true,
                    key: 'menuRoute'
                },
                {
                    title: '菜单名称',
                    align: 'center',
                    tooltip: true,
                    key: 'menuName'
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
            menuList: [],//list 数据
            menuValidate: {
                menuUrl: [
                    {required: true, message: '菜单地址不能为空', trigger: 'blur'},
                    {type: 'string', min: 2, message: '必须是2个以上的字符组成', trigger: 'blur'},
                    {type: 'string', max: 30, message: '长度不能超过30个字', trigger: 'blur'},
                ],
                menuRoute: [
                    {type: 'string', min: 2, message: '必须是2个以上的字符组成', trigger: 'blur'},
                    {type: 'string', max: 30, message: '长度不能超过30个字', trigger: 'blur'},
                ],
                menuName: [
                    {required: true, message: '菜单名称不能为空', trigger: 'blur'},
                    {type: 'string', min: 2, message: '必须是2个以上的字符组成', trigger: 'blur'},
                    {type: 'string', max: 20, message: '长度不能超过20个字', trigger: 'blur'},
                    {validator: func.childNameDump, trigger: 'blur', message: '菜单名称不能与同级菜单名称重复'}
                ],
                sort: [
                    {validator: _u.validate_int, trigger: 'blur', message: '需填入正整数'}
                ]
            }
        };

        /*页面交互*/
        func.add = function () {
            appData.drawerTitle = "新增菜单";
            appData.operateType = "add";
            for (var key in appData.menu) {
                appData.menu[key] = ""
            }
            appData.menu.parentId = appData.currentParentMenu.id;
            appData.menuDrawer = true;
            App.$nextTick(function () {
                App.$refs['menuForm'].resetFields()
            })
        };
        func.edit = function (menu) {
            appData.drawerTitle = "信息查看编辑";
            App.$refs['menuForm'].resetFields();
            appData.operateType = "edit";
            appData.menu = _$.clone_str(menu);
            appData.menuDrawer = true;
        };
        func.delete = function (menu) {
            appData.deleteConfirm = true;
            appData.menu = _$.clone_str(menu);
        };
        func.drawrClose = function () {
            appData.drawerShow = false;
        }

        /*请求*/
        func.delRequest = function () {
            _$.ajax({
                url: '<@c.rootPath/>' + api.sysmenu_del,
                dataType: 'json',
                type: 'POST',
                data: {id: appData.menu.id},
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
            var url = '<@c.rootPath/>' + api.sysmenu_query;
            var data = {
                param: JSON.stringify(appData.searchParam),
                curPage: appData.searchParam.curPage,
                pageSize: appData.searchParam.pageSize
            };
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    var res = _r.data;
                    appData.menuList = res.records;
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
            _$.ajax({
                url: '<@c.rootPath/>' + api.sysmenu_tree,
                dataType: 'json',
                type: 'POST',
                data: {id: "1"},
                success: function (_r, _s) {
                    if (_r.success) {
                        appData.menu_tree = [_r.data];
                        if (reset) {
                            appData.currentParentMenu = _r.data;
                        }
                    } else {
                        _u.error(App, _r.message);
                    }
                },
                fail: function (_ex) {
                    _u.error(App, JSON.stringify(_ex));
                }
            });
        };
        func.addOrEditRequest = function () {
            var url = "";
            var request = new FormData(document.querySelector('form'));
            if (appData.operateType === 'edit') {
                url = '<@c.rootPath/>' + api.sysmenu_edit;
                request.append('id', appData.menu.id);
            } else if (appData.operateType === 'add') {
                url = '<@c.rootPath/>' + api.sysmenu_add;
                request.append('parentId', appData.menu.parentId);
            } else {
                _u.error(App, "bug原因：operateType 不在所给范围内");
            }
            var menu = _$.clone_str(appData.menu);
            App.$refs['menuForm'].validate(function (valid) {
                if (valid) {
                    _$.post(App, request, url, function (_r, _x) {
                        if (_r.success) {
                            _u.success(App, '操作成功，刷新列表');
                            appData.menuDrawer = false;
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
                el: '#appMenu',
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
                    orgChange: function () {
                        func.refreshList();
                        console.log("orgChange")
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
                            this.currentParentMenu = _$.clone_str(node[0]);
                            this.searchParam.parentId = this.currentParentMenu.id;//查询以选中项作为根节点
                            this.searchParam.containParent = true;
                            func.refreshList();
                            /*alert(this.currentParentMenu.parentId);*///父级id
                        }
                    }
                }
            });
        };
    </script>
</@l.manage>

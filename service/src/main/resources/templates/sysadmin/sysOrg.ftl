<@l.manage tab="sysOrg" title="单位管理" import="base,vue,iview,layout" js="" >
    <div id="appOrg" class="reletiveContainer list-panel-container" v-cloak>
        <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
            <div class="reletiveContainer">
                <div class="absoluteArea func-area">
                    <div class="right func-item">
                        <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                    </div>
                </div>
                <div class="absoluteArea list-tree-area" style="">
                    <Tree :data="org_tree" @on-select-change="treeSelectChange" class="tree-menu"></Tree>
                </div>
                <div class="absoluteArea list-right-area">
                    <i-table border :columns="orgColumn" :data="orgList"></i-table>
                </div>
                <div class="absoluteArea page-area" style="bottom: 0;height: 40px;left:300px;right: 0px;">
                    <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange" :page-size="pageSize"
                          @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
                </div>
            </div>
        </div>
        <Drawer :title="drawerTitle" width="420" :closable="false" v-model="orgDrawer">
            <div class="absoluteArea reset-drawer-body" style="">
                <i-form class="form-reset" ref="orgForm" :rules="orgValidate" :model="org">
                    <form-item label="单位名称" prop='organName'>
                        <i-input v-model="org.organName" placeholder="" name="organName"></i-input>
                    </form-item>
                    <form-item label="单位代码" prop='organCode'>
                        <i-input v-model="org.organCode" placeholder="" name="organCode"></i-input>
                    </form-item>
                    <form-item label="电子邮件" prop="email">
                        <i-input v-model="org.email" name="email"></i-input>
                    </form-item>
                    <form-item label="联系方式" prop="tel">
                        <i-input v-model="org.tel" name="tel"></i-input>
                    </form-item>
                    <form-item label="所属区域" prop="areaName">
                        <i-input v-model="org.areaName" disabled><span slot="append" @click="area"
                                                                       name="areaName">选择</span></i-input>
                    </form-item>
                    <form-item label="排序字段（大于1的整数）" prop="sort">
                        <i-input v-model="org.sort" name="sort"></i-input>
                    </form-item>
                    <form-item label="父节点" prop="parent">
                        <i-input v-model="currentParentOrg.title" disabled></i-input>
                    </form-item>
                    <form-item label="单位类型" prop="otype">
                        <i-input v-model="org.otype" name="otype"></i-input>
                    </form-item>
                    <form-item label="经度" prop="lon">
                        <i-input v-model="org.lon" name="lon" placeholder="例如:156.666666"></i-input>
                    </form-item>
                    <form-item label="纬度" prop="lat" style="margin-bottom: 20px">
                        <i-input v-model="org.lat" name="lat" placeholder="例如:46.666666"></i-input>
                    </form-item>
                    <form-item label="启用时间" prop="startTime">
                        <date-picker type="date" placeholder="请选择时间" v-model="org.startTime"
                                     name="startTime"></date-picker>
                    </form-item>
                    <form-item label="停用时间" prop="endTime">
                        <date-picker type="date" placeholder="请选择时间" v-model="org.endTime"
                                     name="endTime"></date-picker>
                    </form-item>
                    <form-item label="地址" prop="address">
                        <i-input v-model="org.address" name="address"></i-input>
                    </form-item>
                    <form-item label="邮编" prop="postcode">
                        <i-input v-model="org.postcode" name="postcode"></i-input>
                    </form-item>
                    <form-item label="传真" prop="fax">
                        <i-input v-model="org.fax" name="fax"></i-input>
                    </form-item>
                    <form-item label="单位简介" prop="briefOrg">
                        <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="org.briefOrg"
                                 name="briefOrg"></i-input>
                    </form-item>
                    <form-item label="职责" prop="dutyOrg">
                        <i-input v-model="org.dutyOrg" name="dutyOrg"></i-input>
                    </form-item>
                    <form-item label="人员职责" prop="briefUser">
                        <i-input v-model="org.briefUser" name="briefUser"></i-input>
                    </form-item>
                    <form-item label="备注" prop="remark">
                        <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="org.remark"
                                 name="remark"></i-input>
                    </form-item>
                </i-form>
            </div>
            <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
                <i-button style="margin-right: 8px" @click="orgDrawer = false">取消</i-button>
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
        <Modal v-model="areaChoose" title="选择区域">
            <Tree :data="are_tree" @on-select-change="treeAreSelectChange" class="tree-menu"></Tree>
            <div slot="footer">
                <i-button @click="canNoAretree" size="large">取消</i-button>
                <i-button @click="canYesAretree" size="large">确定</i-button>
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

        func.organCodeDump = function (rule, organCode, callback) {
            var url = '<@c.rootPath/>' + api.sysOrg_organCodeDump;
            var formdata = new FormData();
            formdata.append("organCode", organCode);
            formdata.append("organId", appData.org.id);
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
            orgDrawer: false,//抽屉指针
            deleteConfirm: false,//删除确认modal
            areaChoose: false,//选择区域
            pageTotal: pageSizes,
            org_tree: [],
            are_tree: [],//区域
            currentParentOrg: {},
            currentParentArea: {},//区域
            pageSize: pageSizes,
            org: {//当前对象
                id: "",
                organCode: "",
                organName: "",
                areaName: "",
                sort: "",
                parentId: ""
            },
            searchParam: {//查询参数
                orgCode: "",
                organName: "",
                areaName: "",
                parentId: "1",//parent id
                curPage: 1,
                pageSize: pageSizes,
                containParent: true
            },
            orgColumn: [//list列信息
                {
                    type: 'index',
                    width: 60,
                    align: 'center',
                    indexMethod: function (row) {
                        return (appData.searchParam.curPage - 1) * 10 + row._index + 1;
                    }
                },
                {
                    title: '单位名',
                    align: 'center',
                    tooltip: true,
                    key: 'organName'
                },
                {
                    title: '单位代码',
                    align: 'center',
                    tooltip: true,
                    key: 'organCode'
                },
                {
                    title: '所属区域',
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
            orgList: [],//list 数据
            orgValidate: {
                organName: [
                    {required: true, message: '输入单位名不能为空', trigger: 'blur', validator: _u.validate_notNull},
                    {type: 'string', min: 2, message: '必须是2个以上的字符组成', trigger: 'blur'},
                    {type: 'string', max: 20, message: '长度不超过20个字', trigger: 'blur'}
                ],
                organCode: [
                    {required: true, message: '输入单位代码不能为空', trigger: 'blur', validator: _u.validate_notNull},
                    {type: 'string', min: 2, message: '必须是2个以上的字符组成', trigger: 'blur'},
                    {type: 'string', max: 10, message: '长度不能超过10个字符', trigger: 'blur'},
                    {validator: func.organCodeDump, trigger: 'blur', message: '单位代码不能其他单位重复'}
                ],
                sort: [
                    {validator: _u.validate_int, trigger: 'blur', message: '需填入正整数'}
                ],
                email: [
                    {required: true, message: '邮箱不能为空', trigger: 'blur'},
                    {type: 'string', min: 5, message: '邮箱必须是5位以上的数字或字母组成', trigger: 'blur'},
                    {validator: _u.validate_email, trigger: 'blur', message: '请填写邮箱 须是字母数字开头且包含@与.例xxxxx.@qq.com'}
                ],
                tel: [
                    {required: true, message: '联系方式不能为空', trigger: 'blur'},
                    {validator: _u.validate_telphone, trigger: 'blur', message: '请填写正确格式的联系方式'}
                ],
                postcode: [
                    {validator: _u.validate_postcode, trigger: 'blur', message: '请填写正确格式的邮政编码'}
                ],
                lon: [
                    {validator: _u.validate_lon, trigger: 'blur', message: '请输入正确格式的经度'}
                ],
                lat: [
                    {validator: _u.validate_lat, trigger: 'blur', message: '请输入正确格式的纬度'}
                ],
                fax: [
                    {validator: _u.validate_fax, trigger: 'blur', message: '请输入正确格式的传真'}
                ]
            }
        };

        /*页面交互*/
        func.add = function () {
            appData.drawerTitle = "单位新增";
            appData.operateType = "add";
            for (var key in appData.org) {
                appData.org[key] = ""
            }
            appData.org.parentId = appData.currentParentOrg.id;
            appData.orgDrawer = true;
            App.$nextTick(function () {
                App.$refs['orgForm'].validate()
            })
        };
        func.edit = function (org) {
            appData.drawerTitle = "单位信息查看编辑";
            App.$refs['orgForm'].resetFields();
            appData.operateType = "edit";
            appData.org = _$.clone_str(org);
            appData.orgDrawer = true;
        };
        func.delete = function (org) {
            appData.deleteConfirm = true;
            appData.org = _$.clone_str(org);
        };
        func.drawrClose = function () {
            appData.drawerShow = false;
        }

        /*请求*/
        func.delRequest = function () {
            _$.ajax({
                url: '<@c.rootPath/>' + api.sysOrg_del,
                dataType: 'json',
                type: 'POST',
                data: {id: appData.org.id},
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
            var url = '<@c.rootPath/>' + api.sysOrg_query;
            var data = {
                param: JSON.stringify(appData.searchParam),
                curPage: appData.searchParam.curPage,
                pageSize: appData.searchParam.pageSize
            };
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    var res = _r.data;
                    appData.orgList = res.records;
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
            _u.getOrgTree(App, function (data) {
                appData.org_tree = [data];
                if (reset) {
                    appData.currentParentOrg = data;
                }
            })
        };
        func.getAreTree = function (reset) {
            _u.getAreaTree(App, function (data) {
                appData.are_tree = [data];
                if (reset) {
                    appData.currentParentArea = reset.data;
                }
            })
        };
        func.addOrEditRequest = function () {
            var url = "";
            var request = new FormData(document.querySelector('form'));
            request.set('startTime',document.getElementsByName('startTime')[0].value+" 00:00:00");
            request.set('endTime',document.getElementsByName('endTime')[0].value+" 00:00:00");
            if (appData.operateType === 'edit') {
                url = '<@c.rootPath/>' + api.sysOrg_edit;
                request.append('id', appData.org.id);
            } else if (appData.operateType === 'add') {
                url = '<@c.rootPath/>' + api.sysOrg_add;
                request.append('parentId', appData.org.parentId);
            } else {
                _u.error(App, "bug原因：operateType 不在所给范围内");
            }
            App.$refs['orgForm'].validate(function (valid) {
                if (valid) {
                    _$.post(App, request, url, function (_r, _x) {
                        if (_r.success) {
                            _u.success(App, '操作成功，刷新列表');
                            appData.orgDrawer = false;
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
                el: '#appOrg',
                data: appData,
                mounted: function () {
                    this.$nextTick(function () {
                        func.refreshList();
                        func.getTree(true);
                        func.getAreTree(true);
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
                    area: function () {
                        this.areaChoose = true;
                        console.log("chooseOrg")
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
                    canNoAretree: function () {
                        this.areaChoose = false;
                        console.log("canNoAretree")
                    },
                    canYesAretree: function () {
                        this.org.areaName = this.searchParam.areaName;
                        this.areaChoose = false;
                        console.log("canYesAretree")
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
                    },
                    treeAreSelectChange: function (node) {
                        if (node.length > 0) {
                            this.currentParentArea = _$.clone_str(node[0]);
                            this.searchParam.parentId = this.currentParentArea.id;//查询以选中项作为根节点
                            this.searchParam.containParent = true;
                            this.searchParam.areaName = this.currentParentArea.title;//单击赋值
                            func.refreshList();
                        }
                    }
                }
            });
        };
    </script>
</@l.manage>

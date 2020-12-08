<@l.manage tab="sysRole" title="角色管理" import="base,vue,iview,layout" js="" >
<div id="appRole" class="reletiveContainer list-panel-container" v-cloak>
    <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
        <div class="reletiveContainer">
            <div class="absoluteArea func-area">
                <div class="right func-item">
                    <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                </div>
            </div>
            <div class="absoluteArea list-area" >
                <i-table border :columns="roleColumn" :data="roleList"></i-table>
            </div>
            <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                <Page :total="pageTotal" :page-size-opts="[10,20]" :page-size="pageSize" @on-change="pageChange" @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
            </div>
        </div>
    </div>
    <Drawer :title="drawerTitle" width="420" :closable="false" v-model="roleDrawer">
        <div class="absoluteArea reset-drawer-body" style="">
            <i-form class="form-reset" ref="roleForm" :rules="roleValidate" :model="role" >
                <form-item label="角色名称" prop='roleName' >
                    <i-input v-model="role.roleName" placeholder="" name="roleName"></i-input>
                </form-item>
                <form-item label="角色代码" prop='roleCode' >
                    <i-input v-model="role.roleCode" placeholder="" name="roleCode"></i-input>
                </form-item>
                <form-item label="角色类型" prop='roleType' >
                    <i-input v-model="role.roleType" placeholder="" name="roleType"></i-input>
                </form-item>
                <form-item label="备注" prop="remark" >
                    <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="role.remark" name="remark"></i-input>
                </form-item>
            </i-form>
        </div>
        <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
            <i-button style="margin-right: 8px" @click="roleDrawer = false">取消</i-button>
            <i-button type="primary" @click="operate">提交</i-button>
        </div>
    </Drawer>
    <Modal v-model="deleteConfirm"  title="确认删除">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>确认删除</span>
        </p>
        <p>此操作将删除角色及关联信息，是否继续操作？</p>
        <div slot="footer">
            <i-button @click="cancelDel"  size="large" >取消</i-button>
            <i-button @click="confirmDel"  size="large" >确定</i-button>
        </div>
    </Modal>
    <Modal v-model="roleModal" :mask-closable="false" title="关联菜单">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>关联菜单</span>
        </p>
        <Transfer
                :data="roleData"
                :target-keys="roleTarget"
                :render-format="roleRender"
                @on-change="roleChange">
        </Transfer>
        <div slot="footer">
            <i-button @click="confirmRole"  size="large" >确定</i-button>
        </div>
    </Modal>
</div>

<style type="text/css">

</style>

<script type="text/javascript">
    var App = {};
    var func = {};
    var pageSizes=10;
    if(top.document.body.clientWidth>1900){
        pageSizes=20
    }

    func.roleNameDump = function (rule,roleName,callback) {
        var url = '<@c.rootPath/>' + api.sysrole_roleNameDump;
        var formdata = new FormData();
        formdata.append("roleName",roleName);
        formdata.append("roleId",appData.role.id);
        _$.post(App,formdata,url,function(_r,_x){
            if (_r.success) {
                var res = _r.data;
                if (res){
                    return callback(new Error());
                }
                return callback();
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
            return callback();
        })
    };
    func.roleCodeDump = function (rule,roleCode,callback) {
        var url = '<@c.rootPath/>' + api.sysrole_roleCodeDump;
        var formdata = new FormData();
        formdata.append("roleCode",roleCode);
        formdata.append("roleId",appData.role.id);
        _$.post(App,formdata,url,function(_r,_x){
            if (_r.success) {
                var res = _r.data;
                if (res){
                    return callback(new Error());
                }
                return callback();
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
            return callback();
        })
    };
    var appData = {
        drawerTitle:"角色详情及修改",//展开抽屉的标题
        operateType:"add",
        roleDrawer:false,//抽屉指针
        menuChoose:false,//选择菜单
        deleteConfirm:false,
        roleModal:false,
        pageTotal:10,
        menu_tree:[],//菜单
        roleData: [],
        roleTarget:["1","2"],
        pageSize:pageSizes,
        role:{//当前角色
            id:"",
            roleName:"",
            roleCode:"",
            remark:"",
            name:"",
            menu:[]
        },
        searchParam:{//查询参数
            curPage:1,
            pageSize:pageSizes,
            roleName:""
        },
        roleColumn: [//list列信息
            {
                type: 'index',
                width: 60,
                align: 'center',
                indexMethod:function (row) {
                    return (appData.searchParam.curPage - 1)*10 + row._index + 1;
                }
            },
            {
                title: '角色名',
                align: 'center',
                tooltip:true,
                key: 'roleName'
            },
            {
                title: '角色代码',
                align: 'center',
                tooltip:true,
                key: 'roleCode'
            },
            {
                title: '角色类型',
                align: 'center',
                tooltip:true,
                key: 'roleType'
            },
            {
                title: '备注',
                align: 'center',
                tooltip:true,
                key: 'remark'
            },
            {
                title: '操作',
                key: 'action',
                width: 220,
                align: 'center',
                fixed: 'right',
                render:function (h, params) {
                    return h("div",[
                        h('Button',{
                            props: {
                                type: 'primary',
                                size: 'small'
                            },
                            style: {
                                marginRight: '5px'
                            },
                            on: {
                                click: function(){
                                    func.menu(params.row);
                                }
                            }
                        },"菜单"),
                        h('Button',{
                            props: {
                                type: 'primary',
                                size: 'small'
                            },
                            style: {
                                marginRight: '5px'
                            },
                            on: {
                                click: function(){
                                    func.edit(params.row);
                                }
                            }
                        },"修改"),
                        h('Button',{
                            props: {
                                type: 'error',
                                size: 'small'
                            },
                            style: {
                                marginRight: '5px'
                            },
                            on: {
                                click: function(){
                                    func.delete(params.row);
                                }
                            }
                        },"删除")
                    ])
                }
            }
        ],
        roleList: [],//list 数据
        roleValidate:{
            roleName: [
                { required: true, message: '输入角色名不能为空', trigger: 'blur',validator:_u.validate_notNull},
                { type: 'string', min: 4, message: '登录名必须是4个以上的字符串组成', trigger: 'blur' },
                {validator:func.roleNameDump,trigger: 'blur',message: '角色名称不能与现有的角色名称重复'}
            ],
            roleCode:[
                { required: true, message: '输入角色代码不能为空', trigger: 'blur' },
                { type: 'string', min: 4, message: '显示名必须是4个以上的字符串组成', trigger: 'blur' },
                {validator:_u.validate_char_Num,trigger: 'blur',message: '角色代码只允许字符数字下划线'},
                {validator:func.roleCodeDump,trigger: 'blur',message: '角色代码不能与现有的角色代码重复'}
            ]
        }
    };
    func.menu = function(role){
        appData.role = _$.clone_str(role);
        var url = '<@c.rootPath/>' + api.sysmenu_query_roleid;
        var data = {RoleId:appData.role.id};
        _$.post(App,data,url,function(_r,_x){
            if (_r.success) {
                var roleMenu = _r.data;
                var tar = [];
                roleMenu.forEach(function (item) {
                    tar.push(item.menuId);
                });
                appData.roleTarget = tar;
                appData.roleModal = true;
            } else {
                _u.error(App,"菜单数据获取失败"+_r.message);
            }
        },function(_ex){
            _u.error(App,"菜单数据获取失败"+JSON.stringify(_ex));
        });
    };
    /*页面交互*/
    func.add = function(){
        appData.drawerTitle = "角色新增";
        appData.operateType = "add";
        for (var key in appData.role) {
            appData.role[key] = ""
        }
        appData.roleDrawer = true;
        App.$nextTick(function () {
            App.$refs['roleForm'].validate()
        })
    };
    func.edit = function(role){
        appData.drawerTitle = "角色查看修改";
        App.$refs['roleForm'].resetFields();
        appData.operateType = "edit";
        appData.role = _$.clone_str(role);
        appData.roleDrawer = true;
        App.$nextTick(function () {
            /*App.$refs['roleForm'].validate();*/
        })
    };
    func.delete = function(role){
        appData.deleteConfirm = true;
        appData.role = _$.clone_str(role);
    };

    /*请求*/
    func.delRequest = function(){
        _$.ajax({
            url: '<@c.rootPath/>' + api.sysrole_del,
            dataType: 'json',
            type: 'POST',
            data: {roleId:appData.role.id},
            success: function (_r, _s) {
                if (_r.success) {
                    _u.success(App,'该角色与关联信息删除成功，刷新列表');
                    func.refreshList();
                } else {
                    _u.error(App,_r.message);
                }
            },
            fail: function (_ex) {
                _u.error(App,JSON.stringify(_ex));
            }
        });
    };
    func.refreshList = function() {
        var url='<@c.rootPath/>' + api.sysrole_query;
        var data = {
            param: JSON.stringify(appData.searchParam),
            curPage: appData.searchParam.curPage,
            pageSize: appData.searchParam.pageSize
        };
        _$.post(App, data, url, function (_r, _x) {
            if (_r.success) {
                var res = _r.data;
                appData.roleList = res.records;
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
    func.addOrEditRequest = function () {
        var url = "";
        var request = new FormData(document.querySelector('form'));
        if (appData.operateType === 'edit'){
            url = '<@c.rootPath/>' + api.sysrole_edit;
            request.append('id',appData.role.id);
        } else if (appData.operateType === 'add') {
            url = '<@c.rootPath/>' + api.sysrole_add
        }else {
            _u.error(App,"bug原因：operateType 不在所给范围内");
        }
        var role = _$.clone_str(appData.role);
        App.$refs['roleForm'].validate(function (valid) {
            if (valid) {
                _$.post(App, request, url, function (_r, _x) {
                    if (_r.success) {
                        _u.success(App, '操作成功，刷新列表');
                        appData.roleDrawer=false;
                        func.refreshList();
                        func.getTree(false);
                    } else {
                        _u.error(App, _r.message);
                    }
                }, function (_ex) {
                    _u.error(App, JSON.stringify(_ex));
                });
            } else {
                _u.error(App,"表单校验失败，请按提示修改");
            }
        });
    };
    func.confirmRole = function(){
        appData.roleModal = false;
        var url = '<@c.rootPath/>' + api.sysrole_menu_update;
        var data = {
            rodeId:appData.role.id,
            menuIds:appData.roleTarget
        };
        _$.post(App,data,url,function(_r,_x){
            if (_r.success) {
                _u.success(App,'操作成功');
            } else {
                _u.error(App,_r.message);
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
        });
    };
    func.init = function(){
        _u.getMenus(App,function (data) {
            var roles = [];
            data.forEach(function (role) {
                var item = {
                    key:role.id,
                    label:role.menuName,
                    disabled:false
                };
                roles.push(item);
            });
            appData.roleData = roles;
        })
    };
    window.onload = function () {
        App = new Vue({
            el: '#appRole',
            data: appData,
            mounted: function () {
                this.$nextTick(function () {
                    func.refreshList();
                    func.init();
                })
            },
            methods: {
                add: function () {
                    func.add();
                },
                cancelDel:function () {
                    this.deleteConfirm = false;
                    console.log("cancelDel")
                },
                confirmDel:function () {
                    func.delRequest();
                    this.deleteConfirm = false;
                    console.log("confirmDel")
                },
                operate:function () {
                    func.addOrEditRequest();
                },
                pageChange:function (page) {
                    this.searchParam.curPage = page;
                    func.refreshList();
                },
                pageSizeChange:function (size) {
                    this.searchParam.pageSize = size;
                    func.refreshList();
                },
                roleRender:function (item) {
                    return item.label;
                },
                roleChange:function (newTargetKeys, direction, moveKeys) {
                    this.roleTarget = newTargetKeys;
                },
                confirmRole:function () {
                    func.confirmRole();
                }
            }
        });
    };
</script>
</@l.manage>

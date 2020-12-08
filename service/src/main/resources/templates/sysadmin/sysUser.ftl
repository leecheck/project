<@l.manage tab="sysUser" title="用户管理" import="base,vue,iview,layout" js="" >
<div id="appUser" class="reletiveContainer list-panel-container" v-cloak>
    <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
        <div class="reletiveContainer">
            <div class="absoluteArea func-area">
                <div class="right func-item">
                    <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                </div>
                <div class="right func-item" style="padding-top: 9px">
                    <i-input placeholder="点击按钮选择所属单位" :label-in-value="true" disabled v-model="searchParam.organName"><span slot="append" @click="orgSelect">单位</span></i-input>
                </div>
                <div class="right func-item" style="padding-top: 9px">
                    <i-input placeholder="输入昵称或账号" v-model="searchParam.username"><span style="cursor: pointer;" slot="append" @click="search">搜索</span></i-input>
                </div>
            </div>
            <div class="absoluteArea list-area" >
                <i-table border :columns="userColumn" :data="userList"></i-table>
            </div>
            <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                <Page :total="pageTotal" :page-size-opts="[10,20]" :page-size="pageSize" @on-change="pageChange" @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
            </div>
        </div>
    </div>
    <Drawer :title="drawerTitle" width="420" :closable="false" v-model="userDrawer">
        <div class="absoluteArea reset-drawer-body" style="">
            <i-form class="form-reset" ref="userForm" :rules="userValidate" :model="user" >
                <form-item label="账号" prop='username'>
                    <i-input v-model="user.username" :disabled="usernameDisabled" placeholder="" name="username"></i-input>
                </form-item>
                <form-item label="昵称" prop='displayName' >
                    <i-input v-model="user.displayName" :disabled="userFormDisabled" placeholder="" name="displayName"></i-input>
                </form-item>
                <form-item v-if="operateType == 'add'" label="用户密码" prop="password" >
                    <i-input v-model="user.password" :disabled="userFormDisabled" type="password" name="password"></i-input>
                </form-item>
                <form-item label="所属单位" prop="organName" >
                    <i-input v-model="user.organName" name="organName" disabled><span slot="append" @click="orgAdd" :disabled="userFormDisabled">选择</span></i-input>
                </form-item>
                <form-item label="用户邮箱" prop="email" >
                    <i-input v-model="user.email" :disabled="userFormDisabled" placeholder="" name="email"></i-input>
                </form-item>
                <form-item label="手机号" prop="phone" >
                    <i-input v-model="user.phone" :disabled="userFormDisabled" placeholder="" name="phone"></i-input>
                </form-item>
                <form-item label="性别" prop="sex" >
                    <i-input v-model="user.sex" placeholder="" name="sex" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="职务" prop="position" >
                    <i-input v-model="user.position" placeholder="" name="position" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="身份证" prop="idcard" >
                    <i-input v-model="user.idcard" :disabled="userFormDisabled" placeholder="" name="idcard" ></i-input>
                </form-item>
                <form-item label="学历" prop="education" >
                    <i-input v-model="user.education" placeholder="" name="education" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="专业" prop="major" >
                    <i-input v-model="user.major" placeholder="" name="major" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="毕业院校" prop="school" >
                    <i-input v-model="user.school" placeholder="" name="school" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="政治面貌" prop="politicalStatus" >
                    <i-input v-model="user.politicalStatus" placeholder="" name="politicalStatus" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="出生地" prop="birthplace" >
                    <i-input v-model="user.birthplace" placeholder="" name="birthplace" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="证书编号" prop="certifacate" >
                    <i-input v-model="user.certifacate" placeholder="" name="certifacate" :disabled="userFormDisabled"></i-input>
                </form-item>
                <form-item label="任职时间" prop="assumeTime">
                    <date-picker type="date" placeholder="请选择时间" v-model="user.assumeTime" name="assumeTime" :disabled="userFormDisabled"></date-picker>
                </form-item>
                <form-item label="出生日期" prop="birth">
                    <date-picker type="date" placeholder="请选择时间" v-model="user.birth" name="birth" :disabled="userFormDisabled"></date-picker>
                </form-item>
                <form-item label="工作时间" prop="workTime">
                    <date-picker type="date" placeholder="请选择时间" v-model="user.workTime" name="workTime" :disabled="userFormDisabled"></date-picker>
                </form-item>
                <form-item label="到站时间" prop="intime">
                    <date-picker type="date" placeholder="请选择时间" v-model="user.intime" name="intime" :disabled="userFormDisabled"></date-picker>
                </form-item>
                <form-item label="离站时间" prop="outtime">
                    <date-picker type="date" placeholder="请选择时间" v-model="user.outtime" name="outtime" :disabled="userFormDisabled"></date-picker>
                </form-item>
                <form-item label="备注" prop="remark" >
                    <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="user.remark" :disabled="userFormDisabled" name="remark"></i-input>
                </form-item>
                <form-item label="单位类型" prop="isEnabled">
                    <i-switch :true-value="Number(1)" :false-value="Number(0)" v-model="user.isEnabled" size="large" :disabled="userFormDisabled">
                        <span slot="open">激活</span>
                        <span slot="close">锁定</span>
                    </i-switch>
                </form-item>
            </i-form>
        </div>
        <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
            <i-button style="margin-right: 8px" @click="userDrawer = false">取消</i-button>
            <i-button type="primary" @click="operate">提交</i-button>
        </div>
        <div v-if="operateType == 'view'" class="custom-drawer-footer">
            <i-button style="margin-right: 8px" @click="userDrawer = false">确定</i-button>
        </div>
    </Drawer>
    <Modal v-model="editPassModal" :mask-closable="false" title="重置密码">
        <p slot="header" style="color:#354457;">
            <Icon type="ios-information-circle"></Icon>
            <span>重置该用户密码</span>
        </p>
        <i-form ref="passForm" :rules="passValidate" :model="passForm" class="form-reset">
            <form-item label="用户密码" prop="pass1">
                <i-input v-model="passForm.pass1" type="password"></i-input>
            </form-item>
            <form-item label="确认密码" prop="pass2">
                <i-input v-model="passForm.pass2" type="password"></i-input>
            </form-item>
        </i-form>
        <div slot="footer">
            <i-button @click="confirmPass" size="large" >确定</i-button>
        </div>
    </Modal>
    <Modal v-model="deleteConfirm" :mask-closable="false" title="确认删除">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>确认删除</span>
        </p>
        <p>此操作将删除用户及关联信息，是否继续操作？</p>
        <div slot="footer">
            <i-button @click="cancelDel"  size="large" >取消</i-button>
            <i-button @click="confirmDel"  size="large" >确定</i-button>
        </div>
    </Modal>
    <Modal v-model="orgSelectModal" :mask-closable="false" title="选择进行查询">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>选择进行查询</span>
        </p>
        <Tree :data="org_tree" @on-select-change="orgSelectChange" ></Tree>
        <div slot="footer">
            <i-button @click="search"  size="large" >确定</i-button>
        </div>
    </Modal>
    <Modal v-model="orgAddModal" :mask-closable="false" title="选择所属单位">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>选择所属单位</span>
        </p>
        <Tree :data="org_tree" @on-select-change="orgAddChange" ></Tree>
        <div slot="footer">
            <i-button @click="confirmOrg"  size="large" >确定</i-button>
        </div>
    </Modal>
    <Modal v-model="roleModal" :mask-closable="false" title="关联角色">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>关联角色</span>
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

    func.usernameDump = function (rule,userName,callback) {
        var url = '<@c.rootPath/>' + api.sysuser_usernameDump;
        var data = {
            username:userName,
            userId:appData.user.id
        };
        _$.post(App,data,url,function(_r,_x){
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
        });
    };
    var appData = {
        operateType:"add",//展开抽屉的类型
        drawerTitle:"新增用户",//展开抽屉的标题
        userDrawer:false,//抽屉指针
        usernameDisabled:true,//登录名禁用指针
        userFormDisabled:true,//用户表单禁用指针
        editPassModal:false,//修改密码modal
        deleteConfirm:false,//删除确认modal
        orgSelectModal:false,
        orgAddModal:false,
        roleModal:false,
        pageTotal:20,
        pageSize:pageSizes,
        org_tree:[],
        org_wait:{//选择单位是待确定变量
            name:"",
            id:""
        },
        roleData: [],
        roleTarget:[],
        passForm:{//密码确认变量
            pass1:"",
            pass2:""
        },
        user:{//当前用户
            id:"",
            password:"",
            username:"",
            displayName:"",
            organName:"",
            organId:"",
            isEnabled:"",
            isEnableType:"",
            email:"",
            phone:"",
            sex:'',
            position:'',
            idcard:'',
            education:'',
            major:'',
            school:"",
            politicalStatus:'',
            birthplace:'',
            certifacate:'',
            assumeTime:'',
            birth:'',
            workTime:'',
            intime:'',
            outtime:'',
            remark:''
        },
        searchParam:{//查询参数
            organId:"",
            organName:"",
            username:"",
            curPage:1,
            pageSize:pageSizes
        },
        userColumn: [//list列信息
            {
                type: 'index',
                width: 60,
                align: 'center',
                indexMethod:function (row) {
                    return (appData.searchParam.curPage - 1)*10 + row._index + 1;
                }
            },
            {
                title: '昵称',
                align: 'center',
                tooltip:true,
                key: 'displayName'
            },
            {
                title: '账号',
                align: 'center',
                tooltip:true,
                key: 'username'
            },
            {
                title: '所属单位',
                align: 'center',
                tooltip:true,
                key: 'organName'
            },
            {
                title: '备注',
                align: 'center',
                tooltip:true,
                key: 'remark'
            },
            {
                title: '邮箱',
                align: 'center',
                tooltip:true,
                key: 'email'
            },
            {
                title: '手机号',
                align: 'center',
                tooltip:true,
                key: 'phone'
            },
            {
                title: '用户状态',
                align: 'center',
                tooltip:true,
                key: 'isEnabled',
                render:function (h,params) {
                    return h("div",func.userEnableAssert(this.row));
                }
            },
            {
                title: '操作',
                key: 'action',
                width: 280,
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
                                    func.role(params.row);
                                }
                            }
                        },"角色"),
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
                                    func.view(params.row);
                                }
                            }
                        },"查看"),
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
                                type: 'warning',
                                size: 'small'
                            },
                            style: {
                                marginRight: '5px'
                            },
                            on: {
                                click: function(){
                                    func.editPass(params.row);
                                }
                            }
                        },"密码"),
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
        userList: [],//list 数据
        passValidate:{//密码确认表单验证
            pass1: [
                { required: true, message: '输入密码不能为空', trigger: 'blur' }
            ],
            pass2: [
                { required: true, message: '重复密码不能为空', trigger: 'blur' }
            ]
        },
        userValidate:{
            username: [
                { required: true, message: '输入账号不能为空', trigger: 'blur' },
                { type: 'string', min: 4, message: '账号必须是4个以上的字符组成', trigger: 'blur' },
                {validator:_u.validate_char,trigger: 'blur',message: '账号取值由字母组成'},
                {validator:func.usernameDump,trigger: 'blur',message: '账号已重复'}
            ],
            displayName:[
                { required: true, message: '输入昵称不能为空', trigger: 'blur' },
                { type: 'string', min: 2, message: '昵称必须是2个以上的字符组成', trigger: 'blur'},
                {validator:_u.validate_notNull,trigger: 'blur',message: '输入昵称不能为空格'}
            ],
            password:[
                { required: true, message: '密码不能为空', trigger: 'blur' },
                { type: 'string', min: 4, message: '密码必须是4个以上的字符串组成', trigger: 'blur' },
                {validator:_u.validate_Pwd,trigger: 'blur',message: '密码必须是字母数字组合且长度超过6位数'}
            ],
            email:[
                { required: true, message: '邮箱不能为空', trigger: 'blur' },
                { type: 'string', min: 5, message: '邮箱必须是5位以上的数字或字母组成', trigger: 'blur' },
                {validator:_u.validate_email,trigger: 'blur',message: '请填写邮箱 须是字母数字开头且包含@与.例xxxxx.@qq.com'}
            ],
            phone:[
                { required: true, message: '手机号不能为空', trigger: 'blur' },
                {validator:_u.validate_phone,trigger: 'blur',message: '请填写手机号 须是11位数字'}
            ],
            idcard:[
                { required: true, message: '身份证号不能为空', trigger: 'blur'},
                {validator:_u.validate_idcard,trigger: 'blur',message: '请填写正确的身份证号'}
            ]
        }
    };

    func.userEnableAssert = function(user){
        user.isEnableType = "锁定";
        if (user.isEnabled>0) {
            user.isEnableType = "激活";
            return "激活";
        }
        return "锁定";
    };

    /*页面交互*/
    func.add = function(){
        appData.drawerTitle = "用户新增";
        appData.operateType = "add";
        appData.userFormDisabled = false;
        appData.usernameDisabled = false;
        for (var key in appData.user) {
            appData.user[key] = ""
        }
        appData.user.isEnabled = 1;
        appData.userDrawer = true;
        App.$nextTick(function () {
            App.$refs['userForm'].validate()
        })
    };
    func.view = function(user){
        appData.drawerTitle = "用户信息查看";
        appData.operateType = "view";
        appData.usernameDisabled = true;
        appData.userFormDisabled = true;
        appData.user = _$.clone_str(user);
        func.userEnableAssert(appData.user);
        appData.userDrawer = true;
        App.$nextTick(function () {
            App.$refs['userForm'].validate()
        })
    };
    func.edit = function(user){
        appData.drawerTitle = "用户信息编辑";
        App.$refs['userForm'].resetFields();
        appData.operateType = "edit";
        appData.userFormDisabled = false;
        appData.usernameDisabled = true;
        appData.user = _$.clone_str(user);
        func.userEnableAssert(appData.user);
        appData.userDrawer = true;
        App.$nextTick(function () {
            /*App.$refs['userForm'].validate()*/
        })
    };
    func.delete = function(user){
        appData.deleteConfirm = true;
        appData.user = _$.clone_str(user);
    };
    func.editPass = function(user){
        appData.passForm.pass1 = "";
        appData.passForm.pass2 = "";
        appData.user = _$.clone_str(user);
        appData.editPassModal = true;
    };
    func.role = function(user){
        appData.user = _$.clone_str(user);
        var url = '<@c.rootPath/>' + api.sysrole_query_uid;
        var data = {userId:appData.user.id};
        _$.post(App,data,url,function(_r,_x){
            if (_r.success) {
                var userRole = _r.data;
                var tar = [];
                userRole.forEach(function (item) {
                    tar.push(item.roleId);
                });
                appData.roleTarget = tar;
                appData.roleModal = true;
            } else {
                _u.error(App,"角色数据获取失败"+_r.message);
            }
        },function(_ex){
            _u.error(App,"角色数据获取失败"+JSON.stringify(_ex));
        });
    };

    /*请求*/
    func.delRequest = function(){
        _$.ajax({
            url: '<@c.rootPath/>' + api.sysuser_del,
            dataType: 'json',
            type: 'POST',
            data: {userId:appData.user.id},
            success: function (_r, _s) {
                if (_r.success) {
                    _u.success(App,'用户删除成功，刷新列表');
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
    func.confirmPass = function(){
        App.$refs['passForm'].validate(function (valid) {
            if (valid){
                if (appData.passForm.pass1 !== appData.passForm.pass2){
                    _u.error(App,"两次输入的密码不同，请检查");
                    return false;
                }
                var fo = new FormData();
                fo.append("userId",App.user.id);
                fo.append("password",appData.passForm.pass1);
                var url = '<@c.rootPath/>' + api.sysuser_edit_pass;
                _$.ajax({
                    url: url,
                    dataType: 'json',
                    type: 'POST',
                    data: fo,
                    success: function (_r, _s) {
                        if (_r.success) {
                            appData.editPassModal = false;
                            _u.success(App,'操作成功，刷新列表');
                            func.refreshList();
                        } else {
                            _u.error(App,_r.message);
                        }
                    },
                    fail: function (_ex) {
                        _u.error(App,JSON.stringify(_ex));
                    }
                });
            } else {
                _u.error(App,"表单校验失败，请按提示修改");
            }
        });
    };
    func.refreshList = function(){
        var url='<@c.rootPath/>' + api.sysuser_query;
        var data = {
            param: JSON.stringify(appData.searchParam),
            curPage: appData.searchParam.curPage,
            pageSize: appData.searchParam.pageSize
        };
        _$.post(App, data, url, function (_r, _x) {
            if (_r.success) {
                var res = _r.data;
                appData.userList = res.records;
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
        request.append('isEnabled',appData.user.isEnabled);
        request.append('organId',appData.user.organId);
        request.append('organName',appData.user.organName);
        if (appData.operateType === 'edit'){
            url = '<@c.rootPath/>' + api.sysuser_edit;
            request.append('id',appData.user.id);
        } else if (appData.operateType === 'add') {
            url = '<@c.rootPath/>' + api.sysuser_add;
        }else {
            _u.error(App,"bug原因：operateType 不在所给范围内");
        }
        var user = _$.clone_str(appData.user);
        App.$refs['userForm'].validate(function (valid) {
            if (valid) {
                _$.post(App, request, url, function (_r, _x) {
                    if (_r.success) {
                        _u.success(App, '操作成功，刷新列表');
                        appData.userDrawer=false;
                        func.refreshList();
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
        var url = '<@c.rootPath/>' + api.sysUser_role_update;
        var data = {
            userId:appData.user.id,
            roleIds:appData.roleTarget
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
        _u.getOrgTree(App,function (data) {
            appData.org_tree = [data];
        });
        _u.getRoles(App,function (data) {
            var roles = [];
            data.forEach(function (role) {
                var item = {
                    key:role.id,
                    label:role.roleName,
                    code:role.roleCode,
                    disabled:false
                };
                roles.push(item);
            });
            appData.roleData = roles;
        })
    };

    window.onload = function () {
        App = new Vue({
            el: '#appUser',
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
                search:function () {
                    func.refreshList();
                    this.orgSelectModal = false;
                    console.log("search")
                },
                orgAdd:function () {
                    this.org_wait.name = this.user.organName;
                    this.org_wait.id = this.user.organId;
                    this.orgAddModal = true;
                },
                orgSelect:function(){
                    this.orgSelectModal = true;
                },
                orgSelectChange:function (data) {
                    if (data.length > 0){
                        var node = data[0];
                        this.searchParam.organName = node.title;
                        this.searchParam.organId = node.id;
                    } else {
                        this.searchParam.organName = "";
                        this.searchParam.organId = "";
                    }
                    console.log("orgChange")
                },
                orgAddChange:function (data) {
                    var node = data[0];
                    this.org_wait.name = node.title;
                    this.org_wait.id = node.id;
                },
                confirmOrg:function(){
                    this.user.organName = this.org_wait.name;
                    this.user.organId = this.org_wait.id;
                    this.orgAddModal = false;
                },
                confirmPass:function () {
                    func.confirmPass();
                    console.log("confirmPass")
                },
                cancelDel:function () {
                    this.deleteConfirm = false;
                    console.log("cancelDel");
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

<@l.manage tab="sysStoreBackup" title="数据库备份" import="base,vue,iview,layout" js="">
<div id="appSysLog" class="reletiveContainer list-panel-container" v-cloak>
    <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
        <div class="reletiveContainer">
            <div class="absoluteArea func-area">
                <div class="right func-item">
                    <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                </div>
            </div>
            <div class="absoluteArea list-area" >
                <i-table border :columns="storeColumn" :data="storeList"></i-table>
            </div>
            <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange" :page-size="pageSize" @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
            </div>
        </div>
    </div>
    <Drawer :title="drawerTitle" width="420" :closable="false" v-model="storeDrawer">
        <div class="absoluteArea reset-drawer-body" style="">
            <i-form class="form-reset" ref="storeForm" :rules="storeValidate" :model="store" >
                <form-item label="ip" prop='ip' >
                    <i-input v-model="store.ip"  placeholder="" name="ip"></i-input>
                </form-item>
                <form-item label="port" prop='port' >
                    <i-input v-model="store.port"  placeholder="" name="port"></i-input>
                </form-item>
                <form-item label="数据库名称" prop='databaseName' >
                    <i-input v-model="store.databaseName" placeholder="" name="databaseName"></i-input>
                </form-item>
                <form-item label="数据库用户" prop="instanceUser" >
                    <i-input v-model="store.instanceUser" placeholder="" name="instanceUser"></i-input>
                </form-item>
                <form-item label="数据库密码(提交以后加密保存请确保填写正确)" prop="instancePass" >
                    <i-input v-model="store.instancePass" placeholder="" name="instancePass"></i-input>
                </form-item>
                <form-item label="数据库类型" >
                    <i-select v-model="store.instanceType" placeholder="请选择类型">
                        <i-option value="mysql" key="mysql">mysql</i-option>
                        <i-option value="oracle" key="oracle">oracle</i-option>
                    </i-select>
                </form-item>
            </i-form></div>
        <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
            <i-button style="margin-right: 8px" @click="storeDrawer = false">取消</i-button>
            <i-button type="primary" @click="operate">提交</i-button>
        </div>
    </Drawer>
    <Modal v-model="deleteConfirm" :mask-closable="false" title="确认删除">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>确认删除</span>
        </p>
        <p>确定要删除吗？</p>
        <div slot="footer">
            <i-button @click="deleteConfirm = false"  size="large" >取消</i-button>
            <i-button @click="confirmDel"  size="large" >确定</i-button>
        </div>
    </Modal>
    <Modal v-model="timeConfirm" :mask-closable="false" title="开启定时备份">
        <p slot="header" style="color:#f60;">
            <Icon type="ios-information-circle"></Icon>
            <span>开启定时备份</span>
        </p>
        <Checkbox :true-value="1" :false-value="0" v-model="store.activeTime">开启定时数据库备份</Checkbox>
        <div slot="footer">
            <i-button @click="timeConfirm = false"  size="large" >取消</i-button>
            <i-button @click="confirmTime"  size="large" >确定</i-button>
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

    var appData = {
        drawerTitle:"",
        operateType:"",
        storeDrawer:false,
        deleteConfirm:false,//删除确认modal
        timeConfirm:false,//确认开启定时
        pageTotal:pageSizes,
        pageSize:pageSizes,
        store:{
            id:"",
            ip:"",
            port:"",
            databaseName:"",
            instancePass:"",
            instanceUser:"",
            corn:"",
            instanceType:"mysql",
            remark:""
        },
        storeColumn: [//list列信息
            {
                type: 'index',
                width: 60,
                align: 'center',
                indexMethod:function (row) {
                    return (appData.searchParam.curPage - 1)*10 + row._index + 1;
                }
            },
            {
                title: 'ip',
                align: 'center',
                tooltip:true,
                key: 'ip'
            },
            {
                title: 'port',
                align: 'center',
                tooltip:true,
                key: 'port'
            },
            {
                title: '数据库名称',
                align: 'center',
                tooltip:true,
                key: 'databaseName'
            },{
                title: '数据库用户',
                align: 'center',
                tooltip:true,
                key: 'instanceUser'
            },
            {
                title: '数据库密码',
                align: 'center',
                tooltip:true,
                key: 'instancePass'
            },{
                title: '数据库类型',
                align: 'center',
                tooltip:true,
                key: 'instanceType'
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
                        },"删除"),
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
                                    func.connect(params.row);
                                }
                            }
                        },"测试"),
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
                                    func.backup(params.row);
                                }
                            }
                        },"备份"),
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
                                    func.setTime(params.row);
                                }
                            }
                        },"定时")
                    ])
                }
            }
        ],
        storeList:[],
        searchParam:{//查询参数
            curPage:1,
            pageSize:pageSizes
        },
        storeValidate:{
            ip: [
                { required: true, message: '不能为空', trigger: 'blur',validator:_u.validate_notNull }
            ],
            port: [
                { required: true, message: '不能为空', trigger: 'blur',validator:_u.validate_notNull }
            ],
            databaseName: [
                { required: true, message: '不能为空', trigger: 'blur',validator:_u.validate_notNull }
            ],
            instancePass: [
                { required: true, message: '不能为空', trigger: 'blur',validator:_u.validate_notNull }
            ],
            instanceUser: [
                { required: true, message: '不能为空', trigger: 'blur',validator:_u.validate_notNull }
            ]
        }
    };
    func.setTime = function(store){
        appData.store = _$.clone_str(store);
        appData.timeConfirm = true;
    };
    func.connect = function(store){
        appData.store = _$.clone_str(store);
        var url = '<@c.rootPath/>' + api.sysStore_connect;
        var fd = new FormData();
        fd.append("id",appData.store.id);
        _$.post(App,fd,url,function(_r,_x){
            if (_r.success) {
                var res = _r.data;
                if (res){
                    _u.notice(App,"连接成功");
                }
            }else {
                _u.error(App,_r.message);
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
        });
    };
    func.backup = function(store){
        appData.store = _$.clone_str(store);
        var url = '<@c.rootPath/>' + api.sysStore_backup;
        var fd = new FormData();
        fd.append("id",appData.store.id);
        _$.post(App,fd,url,function(_r,_x){
            if (_r.success) {
                _u.notice(App,_r.data);
            }else {
                _u.error(App,_r.message);
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
        });
    };
    func.add = function(){
        appData.store = {
            id:"",
            ip:"",
            port:"",
            databaseName:"",
            instancePass:"",
            instanceUser:"",
            instanceType:"mysql",
            remark:""
        };
        appData.operateType = "add";
        appData.drawerTitle = "新增数据库连接";
        appData.storeDrawer = true;
        App.$nextTick(function () {
            App.$refs['storeForm'].resetFields()
        })
    };
    func.delete = function(store){
        appData.deleteConfirm = true;
        appData.store = _$.clone_str(store);
    };
    /*请求*/
    func.delRequest = function(){
        var url = '<@c.rootPath/>' + api.sysStore_del;
        var fd = new FormData();
        fd.append("id",appData.store.id);
        _$.post(App,fd,url,function(_r,_x){
            if (_r.success) {
                _u.success(App,'信息删除成功，刷新列表');
                func.refreshList();
            }else {
                _u.error(App,_r.message);
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
        });
    };
    func.refreshList = function(){
        var data = {
            curPage: appData.searchParam.curPage,
            pageSize: appData.searchParam.pageSize
        };
        var url = '<@c.rootPath/>' + api.sysStore_query;
        _$.post(App,data,url,function(_r,_x){
            if (_r.success) {
                var res = _r.data;
                if (res){
                    appData.storeList = res.records;
                    appData.searchParam.pageSize = res.size;
                    appData.searchParam.curPage = res.current;
                    appData.pageTotal = res.total;
                }
            }else {
                _u.error(App,_r.message);
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
        });
    };
    func.addOrEditRequest = function () {
        var url = "";
        var request = new FormData(document.querySelector('form'));
        request.append('parentId',appData.store.instanceType);
        if (appData.operateType === 'add') {
            url = '<@c.rootPath/>' + api.sysStore_add
        }else {
            _u.error(App,"bug原因：operateType 不在所给范围内");
        }
        App.$refs['storeForm'].validate(function (valid) {
            if (valid) {
                _$.post(App,request,url,function(_r,_x){
                    if (_r.success) {
                        _u.success(App,'操作成功，刷新列表');
                        appData.storeDrawer=false;
                        func.refreshList();
                    } else {
                        _u.error(App,_r.message);
                    }
                },function(_ex){
                    _u.error(App,JSON.stringify(_ex));
                });
            } else {
                _u.error(App,"表单校验失败，请按提示修改");
            }
        });
    };
    window.onload = function () {
        App= new Vue({
            el: '#appSysLog',
            data: appData,
            mounted:function () {
                this.$nextTick(function () {
                    func.refreshList();
                })
            },
            methods: {
                add:function(){
                    func.add();
                },
                confirmDel:function () {
                    func.delRequest();
                    this.deleteConfirm = false;
                    console.log("confirmDel")
                },
                confirmTime:function(){
                    var url = '<@c.rootPath/>' + api.sysStore_edit;
                    var fd = new FormData();
                    var store = {
                        id:appData.store.id,
                        activeTime:appData.store.activeTime
                    };
                    fd.append("store",JSON.stringify(store));
                    _$.post(App,fd,url,function(_r,_x){
                        if (_r.success) {
                            _u.notice(App,"操作成功，但需重启服务才能生效");
                        }else {
                            _u.error(App,_r.message);
                        }
                    },function(_ex){
                        _u.error(App,JSON.stringify(_ex));
                    });
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
                }
            }
        });
    };
</script>

</@l.manage>

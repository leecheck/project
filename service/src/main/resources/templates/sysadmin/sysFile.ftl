<@l.manage tab="sysFile" title="文件管理" import="base,vue,iview,layout" js="">
<div id="App" class="reletiveContainer list-panel-container" v-cloak>
    <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
        <div class="reletiveContainer">
          <#--  <div class="absoluteArea func-area">
                <div class="right func-item">
                    <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                </div>
            </div>-->
            <div class="absoluteArea list-area" >
                <i-table border :columns="fileColumn" :data="fileList"></i-table>
            </div>
            <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange" :page-size="pageSize" @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
            </div>
        </div>
    </div>
    <Drawer :title="drawerTitle" width="420" :closable="false" v-model="fileDrawer">
        <div class="absoluteArea reset-drawer-body" style="">
            <i-form class="form-reset" ref="fileForm" :rules="fileValidate" :model="file" >
                <form-item label="fileName" prop='fileName' >
                    <i-input v-model="file.fileName"  placeholder=""></i-input>
                </form-item>
                <form-item label="port" prop='port' >
                    <i-input v-model="file.port"  placeholder=""></i-input>
                </form-item>
                <form-item label="数据库名称" prop='databaseName' >
                    <i-input v-model="file.databaseName" placeholder=""></i-input>
                </form-item>
                <form-item label="数据库用户" prop="instanceUser" >
                    <i-input v-model="file.instanceUser" placeholder=""></i-input>
                </form-item>
                <form-item label="数据库密码(提交以后加密保存请确保填写正确)" prop="instancePass" >
                    <i-input v-model="file.instancePass" placeholder=""></i-input>
                </form-item>
                <form-item label="数据库类型" >
                    <i-select v-model="file.instanceType" placeholder="请选择类型">
                        <i-option value="mysql" key="mysql">mysql</i-option>
                        <i-option value="oracle" key="oracle">oracle</i-option>
                    </i-select>
                </form-item>
            </i-form></div>
        <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
            <i-button style="margin-right: 8px" @click="fileDrawer = false">取消</i-button>
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
            <i-button @click="cancelDel"  size="large" >取消</i-button>
            <i-button @click="confirmDel"  size="large" >确定</i-button>
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
        fileDrawer:false,
        deleteConfirm:false,//删除确认modal
        pageTotal:pageSizes,
        pageSize:pageSizes,
        file:{
            id:"",
            fileName:"",
            fileType:"",
            filePath:"",
            createAt:"",
            remark:""
        },
        fileColumn: [//list列信息
            {
                type: 'index',
                width: 60,
                align: 'center',
                indexMethod:function (row) {
                    return (appData.searchParam.curPage - 1)*10 + row._index + 1;
                }
            },
            {
                title: '文件名称',
                align: 'center',
                tooltip:true,
                key: 'name'
            },
            {
                title: '文件类型',
                align: 'center',
                tooltip:true,
                key: 'type'
            },
            {
                title: '保存时间',
                align: 'center',
                tooltip:true,
                key: 'createAt'
            },{
                title: '备注',
                align: 'center',
                tooltip:true,
                key: 'remarks'
            },
            {
                title: '操作',
                key: 'action',
                width: 150,
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
                                    func.download(params.row);
                                }
                            }
                        },"下载")
                    ])
                }
            }
        ],
        fileList:[],
        searchParam:{//查询参数
            curPage:1,
            pageSize:pageSizes,
            fileType:""
        },
        fileValidate:{
            fileName: [
                { required: true, message: '不能为空', trigger: 'blur',validator:_u.validate_notNull}
            ]
        }
    };
    func.download = function(file){
        appData.file = _$.clone_str(file);
        var url = '<@c.rootPath/>' + api.sysfile_download;
        window.open('<@c.rootPath/>' + api.sysfile_download + "?id=" + appData.file.id);
    };
    func.add = function(){
        appData.file = {
            id:"",
            fileName:"",
            fileType:"",
            filePath:"",
            createAt:"",
            remark:""
        };
        appData.operateType = "add";
        appData.drawerTitle = "上传文件";
        appData.fileDrawer = true;
        App.$nextTick(function () {
            App.$refs['fileForm'].resetFields()
        })
    };
    func.delete = function(file){
        appData.deleteConfirm = true;
        appData.file = _$.clone_str(file);
    };
    /*请求*/
    func.delRequest = function(){
        var url = '<@c.rootPath/>' + api.sysfile_del;
        var fd = new FormData();
        fd.append("id",appData.file.id);
        _$.post(App,fd,url,function(_r,_x){
            if (_r.success) {
                _u.success(App,'删除成功，刷新列表');
                func.refreshList();
            }else {
                _u.error(App,_r.message);
            }
        },function(_ex){
            _u.error(App,JSON.stringify(_ex));
        });
    };
    func.refreshList = function(){
        var url = '<@c.rootPath/>' + api.sysfile_query;
        var data = {
            param: JSON.stringify(appData.searchParam),
            curPage: appData.searchParam.curPage,
            pageSize: appData.searchParam.pageSize
        };
        _$.post(App, data, url, function (_r, _x) {
            if (_r.success) {
                var res = _r.data;
                appData.fileList = res.records;
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
        if (appData.operateType === 'add') {
            url = '<@c.rootPath/>' + api.sysfile_add
        }else {
            _u.error(App,"bug原因：operateType 不在所给范围内");
        }
        App.$refs['fileForm'].validate(function (valid) {
            if (valid) {
                var request = new FormData();
                request.append("file", appData.file);
                _$.post(App, request, url, function (_r, _x) {
                    if (_r.success) {
                        _u.success(App, '操作成功，刷新列表');
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
    window.onload = function () {
        App= new Vue({
            el: '#App',
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
                }
            }
        });
    };
</script>

</@l.manage>

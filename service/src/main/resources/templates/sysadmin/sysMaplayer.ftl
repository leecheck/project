<@l.manage tab="sysMaplayer" title="图层管理" import="base,vue,iview,layout" js="" >
<div id="layerApp" class="reletiveContainer list-panel-container" v-cloak>
    <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
        <div class="reletiveContainer">
            <div class="absoluteArea func-area">
                <div class="right func-item">
                    <i-button icon="ios-add-circle-outline" @click="add">新增</i-button>
                </div>
                <div class="right func-item" style="">
                    <i-select v-model="searchParam.layerGroup" style="width:200px" @on-change="groupChange">
                        <i-option v-for="item in syncLayerGroup" :value="item.value" :key="item.value">{{ item.label }}</i-option>
                    </i-select>
                </div>
            </div>
            <div class="absoluteArea list-area" >
                <i-table border :columns="layerColumn" :data="layerList"></i-table>
            </div>
            <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange" :page-size="pageSize" @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
            </div>
        </div>
    </div>
    <Drawer :title="drawerTitle" width="420" :closable="false" v-model="layerDrawer">
        <div class="absoluteArea reset-drawer-body" style="">
            <i-form class="form-reset" ref="layerForm" :rules="layerValidate" :model="layer" >
                <form-item label="图层名称" prop='layerName' >
                    <i-input v-model="layer.layerName" placeholder="" name="layerName"></i-input>
                </form-item>
                <form-item label="图层id(输入唯一的图层id进行区分)" prop='layerId' >
                    <i-input v-model="layer.layerId" placeholder="" name="layerId"></i-input>
                </form-item>
                <form-item label="地图服务url(http://)" prop='mapUrl' >
                    <i-input v-model="layer.mapUrl" placeholder="" name="mapUrl"></i-input>
                </form-item>
                <form-item label="所属图层组" prop='layerGroup'>
                    <i-select v-model="layer.layerGroup" style="">
                        <i-option v-for="item in syncLayerGroup" :value="item.value" :key="item.value">{{ item.label }}</i-option>
                    </i-select>
                </form-item>
                <form-item label="图层类型" prop='layerType' >
                    <i-select v-model="layer.layerType" style="">
                        <i-option v-for="item in layerType" :value="item.value" :key="item.value">{{ item.label }}</i-option>
                    </i-select>
                </form-item>
                <form-item label="要素服务url（按需填写，查询图层必填 http://）" prop='layerUrl' >
                    <i-input v-model="layer.layerUrl" placeholder="" name="layerUrl"></i-input>
                </form-item>
                <form-item label="要素主键字段" prop='objectKey' >
                    <i-input v-model="layer.objectKey" placeholder="" name="objectKey"></i-input>
                </form-item>
                <form-item label="标题字段" prop='titleField' >
                    <i-input v-model="layer.titleField" placeholder="" name="titleField"></i-input>
                </form-item>
                <form-item label='精简展示字段组：[{"key":"","name":""}],默认填[]' prop='infoFields' >
                    <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="layer.infoFields" placeholder="" name="infoFields"></i-input>
                </form-item>
                <form-item label='展示字段组：[{"key":"","name":""}],默认填[]' prop='showFields' >
                    <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="layer.showFields" placeholder="" name="showFields"></i-input>
                </form-item>
                <form-item label='查询字段组：[{"key":"","name":"",value:""}],默认填[]' prop='queryFields' >
                    <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="layer.queryFields" placeholder="" name="queryFields"></i-input>
                </form-item>
                <form-item label="数据源表名" prop='featureLayer' >
                    <i-input v-model="layer.featureLayer" placeholder="" name="featureLayer"></i-input>
                </form-item>
                <form-item label="是否加载" prop="isLoad">
                    <i-switch :true-value="Number(1)" :false-value="Number(0)" v-model="layer.isLoad" size="large">
                        <span slot="open">加载</span>
                        <span slot="close">不加载</span>
                    </i-switch>
                </form-item>

                <form-item label="备注" prop="description" >
                    <i-input type="textarea" :autosize="{minRows: 2,maxRows: 5}" v-model="layer.description" name="description"></i-input>
                </form-item>
            </i-form>
        </div>
        <div v-if="operateType == 'add'||operateType == 'edit'" class="custom-drawer-footer">
            <i-button style="margin-right: 8px" @click="layerDrawer = false">取消</i-button>
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
</div>

<style type="text/css">

</style>

<script type="text/javascript">
    var layerApp = {};
    var func = {};
    var pageSizes=10;
    if(top.document.body.clientWidth>1900){
        pageSizes=20
    }
    var appData = {
        drawerTitle:"图层详情及修改",//展开抽屉的标题
        operateType:"add",
        layerDrawer:false,//抽屉指针
        deleteConfirm:false,
        pageTotal:pageSizes,
        pageSize:pageSizes,
        layer:{//当前图层
            id:"",
            layerName:"",
            layerId:"",
            mapUrl:"",
            layerUrl:"",
            objectKey:"",
            titleField:"",
            showFields:"",
            infoFields:"",
            featureLayer:"",
            isLoad:"",
            layerGroup:"baseLayer",
            layerGroupName:"baseLayer",
            queryFields:"",
            activeIdenty:"",
            layerType:"",
            description:"",
            menu:[]
        },
        syncLayerGroup:[],
        layerType:_u.layerType,
        searchParam:{//查询参数
            layerName:"",
            layerGroup:"topicLayer",
            curPage:1,
            pageSize:pageSizes
        },
        layerColumn: [//list列信息
            {
                type: 'index',
                width: 60,
                align: 'center',
                indexMethod:function (row) {
                    return (appData.searchParam.curPage - 1)*10 + row._index + 1;
                }
            },
            {
                title: '图层名称',
                align: 'center',
                tooltip:true,
                key: 'layerName'
            },
            {
                title: '图层id',
                align: 'center',
                tooltip:true,
                key: 'layerId'
            },
            {
                title: '图层组',
                align: 'center',
                tooltip:true,
                key: 'layerGroup'
            },
            {
                title: '图层类型',
                align: 'center',
                tooltip:true,
                key: 'layerType'
            },
            {
                title: '标题字段',
                align: 'center',
                tooltip:true,
                key: 'titleField'
            },
            {
                title: '地图服务url',
                align: 'center',
                tooltip:true,
                key: 'mapUrl'
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
        layerList: [],//list 数据
        layerValidate:{
            layerName: [
                { required: true, message: '输入图层名不能为空', trigger: 'blur',validator:_u.validate_notNull },
                { type: 'string', min: 2, message: '图层名必须是4个以上的字符串组成', trigger: 'blur' }
            ],
            layerId:[
                { required: true, message: '输入图层id不能为空', trigger: 'blur',validator:_u.validate_notNull },
                { type: 'string', min: 2, message: '图层id必须是2个以上的字符串组成', trigger: 'blur' }
            ],
            mapUrl:[
                { required: true, message: '地图服务url不能为空', trigger: 'blur',validator:_u.validate_notNull },
                { type: 'string', min: 10, message: '必须是10个以上的字符串组成', trigger: 'blur' }
            ],
            infoFields:[
                {validator:_u.validate_isJSON,trigger: 'blur',message: 'JSON字符串格式：[{"key":"","name":""}],默认填[]'}
            ],
            showFields:[
                {validator:_u.validate_isJSON,trigger: 'blur',message: 'JSON字符串格式：[{"key":"","name":""}],默认填[]'}
            ],
            queryFields:[
                {validator:_u.validate_isJSON,trigger: 'blur',message: 'JSON字符串格式：[{"key":"","name":"",value:""}],默认填[]'}
            ],

        }
    };

    /*页面交互*/
    func.add = function(){
        appData.drawerTitle = "地图图层新增";
        appData.operateType = "add";
        for (var key in appData.layer) {
            appData.layer[key] = ""
        }
        appData.layer.infoFields = "[]";
        appData.layer.showFields = "[]";
        appData.layer.queryFields = "[]";
        appData.layer.isLoad = 1;
        appData.layer.layerType = appData.layerType[0].value;
        appData.layer.layerGroup = appData.syncLayerGroup[0].value;
        appData.layerDrawer = true;
        layerApp.$nextTick(function () {
            layerApp.$refs['layerForm'].validate()
        })
    };
    func.edit = function(layer){
        appData.drawerTitle = "角色查看修改";
        appData.operateType = "edit";
        appData.layer = _$.clone_str(layer);
        appData.layerDrawer = true;
        layerApp.$nextTick(function () {
            layerApp.$refs['layerForm'].validate()
        })
    };
    func.delete = function(layer){
        appData.deleteConfirm = true;
        appData.layer = _$.clone_str(layer);
    };

    /*请求*/
    func.delRequest = function(){
        var url = '<@c.rootPath/>' + api.sysMaplayer_del;
        var data = {id:appData.layer.id};
        _$.post(layerApp,data,url,function(_r,_x){
            if (_r.success) {
                _u.success(layerApp,'图层删除成功，刷新列表');
                appData.deleteConfirm = false;
                func.refreshList();
            } else {
                _u.error(layerApp,_r.message);
            }
        },function(_ex){
            _u.error(layerApp,JSON.stringify(_ex));
        });
    };
    func.refreshList = function(){
        var url = '<@c.rootPath/>' + api.sysMaplayer_query;
        var data = {param:JSON.stringify(appData.searchParam)};
        _$.post(layerApp,data,url,function(_r,_x){
            if (_r.success) {
                var res = _r.data;
                appData.layerList = res.records;
                appData.searchParam.pageSize = res.size;
                appData.searchParam.curPage = res.current;
                appData.pageTotal = res.total;
            } else {
                _u.error(layerApp,_r.message);
            }
        },function(_ex){
            _u.error(layerApp,JSON.stringify(_ex));
        });
    };
    func.addOrEditRequest = function () {
        var url = "";
        var request = new FormData(document.querySelector('form'));
        request.append('layerGroup',appData.layer.layerGroup);
        request.append('layerType',appData.layer.layerType);
        request.append('isLoad',appData.layer.isLoad);
        if (appData.operateType === 'edit'){
            url = '<@c.rootPath/>' + api.sysMaplayer_edit;
            request.append('id',appData.layer.id);
        } else if (appData.operateType === 'add') {
            url = '<@c.rootPath/>' + api.sysMaplayer_add;
        }else {
            _u.error(layerApp,"bug原因：operateType 不在所给范围内");
        }
        layerApp.$refs['layerForm'].validate(function (valid) {
            if (valid) {
                _$.post(layerApp,request,url,function(_r,_x){
                    if (_r.success) {
                        _u.success(layerApp, '操作成功，刷新列表');
                        appData.layerDrawer=false;
                        func.refreshList();
                        func.getTree(false);
                    } else {
                        _u.error(layerApp,_r.message);
                    }
                },function(_ex){
                    _u.error(layerApp,JSON.stringify(_ex));
                });
            } else {
                _u.error(layerApp,"表单校验失败，请按提示修改");
            }
        });
    };
    func.init = function(){
        _u.getLayerGroup(layerApp,function (groups) {
            var options = [];
            groups.forEach(function (group) {
                var option = {
                    value:group.dictValue,
                    label:group.dictName
                };
                options.push(option)
            });
            appData.syncLayerGroup = options;
            if (appData.syncLayerGroup.length !== 0){
                appData.searchParam.layerGroup = appData.syncLayerGroup[0].value;
            }
        })
    };

    window.onload = function () {
        layerApp = new Vue({
            el: '#layerApp',
            data: appData,
            mounted: function () {
                this.$nextTick(function () {
                    func.init();
                    func.refreshList();
                })
            },
            methods: {
                add: function () {
                    func.add();
                },
                groupChange:function(value){
                    appData.searchParam.layerGroup = value;
                    func.refreshList();
                },
                cancelDel:function () {
                    this.deleteConfirm = false;
                },
                confirmDel:function () {
                    func.delRequest();
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

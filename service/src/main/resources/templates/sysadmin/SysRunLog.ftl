<@l.manage tab="sysRunLog" title="运行日志" import="base,vue,iview,layout" js="">
<div id="appSysLog" class="reletiveContainer list-panel-container" v-cloak>
    <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
        <div class="reletiveContainer">
            <div class="absoluteArea func-area">
                <div class="right func-item" style="padding-top: 2px">
                    <i-button slot="append" @click="search">搜索</i-button>
                </div>
                <div class="right func-item" style="padding-top: 2px">
                    <Date-picker
                            @on-change="overChange"
                            type="datetime"
                            format="yyyy-MM-dd HH:mm:ss"
                            placeholder="请选择结束时间"
                            style="width: 180px"
                            placement="bottom-end">
                    </Date-picker>
                </div>
                <div class="right func-item" style="padding-top: 2px">
                    <Date-picker
                            @on-change="handleChange"
                            type="datetime"
                            format="yyyy-MM-dd HH:mm:ss"
                            placeholder="请选择开始时间"
                            style="width: 180px"
                            placement="bottom-end">
                    </Date-picker>
                </div>
                <div class="right func-item" style="padding-top: 2px">
                    <i-input placeholder="输入用户名称" v-model="searchParam.userName"></i-input>
                </div>
            </div>
            <div class="absoluteArea list-area" >
                <i-table border :columns="userColumn" :data="runLogList"></i-table>
            </div>
            <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange" :page-size="pageSize" @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
            </div>
        </div>
    </div>
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
    var stationApp = {};
    var func = {};
    var pageSizes=10;
    if(top.document.body.clientWidth>1900){
        pageSizes=20
    }

    var appData = {
        deleteConfirm:false,//删除确认modal
        orgSelectModal:false,
        pageTotal:pageSizes,
        pageSize:pageSizes,
        user:{//当前用户
            id:"",
            userName:"",
        },
        searchParam:{//查询参数
            userName:"",
            actionStart:"",
            actionEnd:"",
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
                title: '操作人',
                align: 'center',
                tooltip:true,
                key: 'userName'
            },
            {
                title: '操作IP地址',
                align: 'center',
                tooltip:true,
                key: 'actionIp'
            },
            {
                title: '操作类',
                align: 'center',
                tooltip:true,
                key: 'actionClass'
            },
            {
                title: '操作方法',
                align: 'center',
                tooltip:true,
                key: 'actionMethod'
            },
            {
                title: '操作时间',
                align: 'center',
                tooltip:true,
                key: 'actionStart'
            },
            {
                title: '结束时间',
                align: 'center',
                tooltip:true,
                key: 'actionEnd'
            },
            {
                title: '描述',
                align: 'center',
                tooltip:true,
                key: 'actionDesc'
            },
            {
                title: '操作',
                key: 'action',
                width: 100,
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
                        },"删除")
                    ])
                }
            }
        ],
        runLogList: [],//list 数据
    };

    func.delete = function(user){
        appData.deleteConfirm = true;
        appData.user = _$.clone_str(user);
    };
    /*请求*/
    func.delRequest = function(){
        _$.ajax({
            url: '<@c.rootPath/>' + api.sysrunlog_del,
            dataType: 'json',
            type: 'POST',
            data: {id:appData.user.id},
            success: function (_r, _s) {
                if (_r.success) {
                    _u.success(stationApp,'信息删除成功，刷新列表');
                    func.refreshList();
                } else {
                    _u.error(stationApp,_r.message);
                }
            },
            fail: function (_ex) {
                _u.error(stationApp,JSON.stringify(_ex));
            }
        });
    };
    func.refreshList = function(){
        _$.ajax({
            url: '<@c.rootPath/>' + api.sysrunlog_query,
            dataType: 'json',
            type: 'POST',
            data: {param:JSON.stringify(appData.searchParam)},
            success: function (_r, _s) {
                if (_r.success) {
                    var res = _r.data;
                    appData.runLogList = res.records;
                    appData.searchParam.pageSize = res.size;
                    appData.searchParam.curPage = res.current;
                    appData.pageTotal = res.total;
                } else {
                    _u.error(stationApp,_r.message);
                }
            },
            fail: function (_ex) {
                _u.error(stationApp,JSON.stringify(_ex));
            }
        });
    };

    window.onload = function () {
        stationApp= new Vue({
            el: '#appSysLog',
            data: appData,
            mounted:function () {
                this.$nextTick(function () {
                    func.refreshList();
                })
            },
            methods: {
                search:function () {
                    func.refreshList();
                    this.orgSelectModal = false;
                    console.log("search")
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
                handleChange:function(time){
                    this.searchParam.actionStart=time;
                },
                overChange:function(time){
                    this.searchParam.actionEnd=time;
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

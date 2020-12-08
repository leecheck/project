<@l.manage tab="sysLog" title="操作日志" import="base,vue,iview,layout" js="">
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
                        <template>
                            <i-select v-model="searchParam.logType" style="width:100px" placeholder="请选择类型">
                                <i-option value="">请选择</i-option>
                                <i-option v-for="item in syncLogType" :value="item.value" :key="item.value">{{
                                    item.label }}
                                </i-option>
                            </i-select>
                        </template>
                    </div>
                    <div class="right func-item" style="padding-top: 2px ">
                        <template>
                            <i-select v-model="searchParam.userId" style="width:120px" placeholder="请选择操作人">
                                <i-option value="">请选择</i-option>
                                <i-option v-for="item in userList" :value="item.id">{{item.displayName}}</i-option>
                            </i-select>
                        </template>
                    </div>
                </div>
                <div class="absoluteArea list-area">
                    <i-table border :columns="userColumn" :data="logList"></i-table>
                </div>
                <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                    <Page :total="pageTotal" :page-size-opts="[10,20]" :page-size="pageSize" @on-change="pageChange"
                          @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
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
                <i-button @click="cancelDel" size="large">取消</i-button>
                <i-button @click="confirmDel" size="large">确定</i-button>
            </div>
        </Modal>

        <Modal v-model="logDetailModal" :mask-closable="false" title="日志详情">
            <p slot="header" style="color:#f60;">
                <Icon type="ios-information-circle"></Icon>
                <span>日志详情</span>
            </p>
            <div class="modal-detail">
                {{ logDetail }}
            </div>
            <div slot="footer">
                <i-button @click="logDetailModal = false" size="large">确定</i-button>
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
        var appData = {
            logDetail: "",
            logDetailModal: false,
            deleteConfirm: false,//删除确认modal
            orgSelectModal: false,
            syncLogType: [],
            pageTotal: pageSizes,
            pageSize: pageSizes,
            user: {//当前用户
                userId: "",
                logType: "",
            },
            searchParam: {//查询参数
                logType: "",//操作类型
                createTime: "",
                overTime: "",
                userId: "",//用户id
                curPage: 1,
                pageSize: pageSizes
            },
            userColumn: [//list列信息
                {
                    type: 'index',
                    width: 60,
                    align: 'center',
                    indexMethod: function (row) {
                        return (appData.searchParam.curPage - 1) * 10 + row._index + 1;
                    }
                },
                {
                    title: '操作人',
                    align: 'center',
                    tooltip: true,
                    key: 'display_name'
                },
                {
                    title: '操作时间',
                    align: 'center',
                    tooltip: true,
                    key: 'create_time'
                },
                {
                    title: '内容',
                    align: 'center',
                    tooltip: true,
                    key: 'event'
                },
                {
                    title: '操作',
                    key: 'action',
                    width: 150,
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
                                        func.showDetail(params.row);
                                    }
                                }
                            }, "详情"),
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
            userList: [],//list 数据
            logList: [],
            logTypeDict: {}
        };
        func.showDetail = function (log) {
            appData.logDetailModal = true;
            appData.logDetail = log.detail;
        };
        func.syncLogType = function () {
            _u.getLogType(App, function (group) {
                var options = [];
                group.forEach(function (group) {
                    var option = {
                        value: group.dictValue,
                        label: group.dictName
                    };
                    options.push(option)
                });
                appData.syncLogType = options;
            })
        };
        func.delete = function (user) {
            appData.deleteConfirm = true;
            appData.user = _$.clone_str(user);
        };
        /*请求*/
        func.delRequest = function () {
            _$.ajax({
                url: '<@c.rootPath/>' + api.syslog_del,
                dataType: 'json',
                type: 'POST',
                data: {id: appData.user.id},
                success: function (_r, _s) {
                    if (_r.success) {
                        _u.success(App, '信息删除成功，刷新列表');
                        func.refreshList();
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
            var url = '<@c.rootPath/>' + api.syslog_query;
            var data = {
                param: JSON.stringify(appData.searchParam),
                curPage: appData.searchParam.curPage,
                pageSize: appData.searchParam.pageSize
            };
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    var res = _r.data;
                    appData.logList = res.records;
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
        func.nameList = function () {
            _u.getUsers(App, function (res) {
                appData.userList = res.records;
            })
        };
        window.onload = function () {
            App = new Vue({
                el: '#appSysLog',
                data: appData,
                mounted: function () {
                    this.$nextTick(function () {
                        func.syncLogType();
                        func.refreshList();
                        func.nameList();
                    })
                },
                methods: {
                    search: function () {
                        func.refreshList();
                        this.orgSelectModal = false;
                        console.log("search")
                    },
                    cancelDel: function () {
                        this.deleteConfirm = false;
                        console.log("cancelDel");
                    },
                    confirmDel: function () {
                        func.delRequest();
                        this.deleteConfirm = false;
                        console.log("confirmDel")
                    },
                    handleChange: function (time) {
                        this.searchParam.createTime = time;
                    },
                    overChange: function (time) {
                        this.searchParam.overTime = time;
                    },
                    pageChange: function (page) {
                        this.searchParam.curPage = page;
                        func.refreshList();
                    },
                    pageSizeChange: function (size) {
                        this.searchParam.pageSize = size;
                        func.refreshList();
                    }
                }
            });
        };
    </script>

</@l.manage>

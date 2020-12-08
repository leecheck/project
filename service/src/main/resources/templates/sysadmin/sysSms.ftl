<@l.manage tab="sysSms" title="短信管理" import="base,vue,iview,layout" js="">
    <div id="AppSms" class="reletiveContainer list-panel-container" v-cloak>
        <div class="absoluteArea panel-body" style="top: 0px;bottom: 0;width: 100%">
            <div class="reletiveContainer">
                <div class="absoluteArea func-area">
                    <div class="right func-item">
                        <i-button icon="ios-add-circle-outline" @click="addOpt">新增</i-button>
                    </div>
                    <#--<div class="right func-item" style="padding-top: 2px">
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
                    </div>-->
                </div>
                <div class="absoluteArea list-area">
                    <i-table border :columns="column" :data="list"></i-table>
                </div>
                <div class="absoluteArea page-area" style="bottom: 0px;height: 40px;width: 100%">
                    <Page :total="pageTotal" :page-size-opts="[10,20]" @on-change="pageChange" :page-size="pageSize"
                          @on-page-size-change="pageSizeChange" show-total show-elevator show-sizer/>
                </div>
            </div>
        </div>
        <Modal v-model="deleteModal.show" :mask-closable="false" title="确认删除">
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
        <Modal v-model="bindModal.show" :mask-closable="false" title="关联用户">
            <p slot="header" style="color:#f60;">
                <Icon type="ios-information-circle"></Icon>
                <span>关联用户</span>
            </p>
            <Transfer
                    :data="bindModal.userData"
                    :target-keys="bindModal.Target"
                    :render-format="bindModal.Render"
                    @on-change="bindModal.Change">
            </Transfer>
            <div slot="footer">
                <i-button @click="bindConfirm" size="large">确定</i-button>
            </div>
        </Modal>
        <Drawer title="新增短信模板" width="420" :closable="false" v-model="addDrawer.show">
            <div class="absoluteArea reset-drawer-body" style="">
                <i-form class="form-reset" ref="menuForm" :rules="Validate" :model="addDrawer.sms">
                    <form-item label="短信模板名称" prop='smsName'>
                        <i-input v-model="addDrawer.sms.smsName" placeholder="" name="smsName"></i-input>
                    </form-item>
                    <form-item label="模板内容" prop="smsTemplate">
                        <i-input v-model="addDrawer.sms.smsTemplate" name="smsTemplate"></i-input>
                    </form-item>
                    <form-item label="模板代码" prop="smsCode">
                        <i-input v-model="addDrawer.sms.smsCode" name="smsCode"></i-input>
                    </form-item>
                    <form-item label="模板说明" prop='smsNote'>
                        <i-input v-model="addDrawer.sms.smsNote" placeholder="" name="smsNote"></i-input>
                    </form-item>
                    <form-item label="签名" prop="smsSingname">
                        <i-input v-model="addDrawer.sms.smsSingname" placeholder="" name="smsSingname"></i-input>
                    </form-item>
                </i-form>
            </div>
            <div class="custom-drawer-footer">
                <i-button style="margin-right: 8px" @click="addDrawer.show = false">取消</i-button>
                <i-button type="primary" @click="addSms">提交</i-button>
            </div>
        </Drawer>
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
            deleteModal: {
                show: false,
                id: "",
            },//删除确认modal
            addDrawer: {
                show: false,
                sms: {},
            },
            bindModal: {
                show: false,
                id: "",
                userData: [],
                Target: [],
                Render: function (item) {
                    return item.label;
                },
                Change: function (newTargetKeys, direction, moveKeys) {
                    appData.bindModal.Target = newTargetKeys;
                }
            },
            pageTotal: pageSizes,
            pageSize: pageSizes,
            searchParam: {//查询参数
                userName: "",
                actionStart: "",
                actionEnd: "",
                curPage: 1,
                pageSize: pageSizes
            },
            column: [//list列信息
                {
                    type: 'index',
                    width: 60,
                    align: 'center',
                    indexMethod: function (row) {
                        return (appData.searchParam.curPage - 1) * 10 + row._index + 1;
                    }
                },
                {
                    title: '短信模板名称',
                    align: 'center',
                    tooltip: true,
                    key: 'smsName'
                },
                {
                    title: '模板内容',
                    align: 'center',
                    tooltip: true,
                    key: 'smsTemplate'
                },
                {
                    title: '模板说明',
                    align: 'center',
                    tooltip: true,
                    key: 'smsNote'
                },
                {
                    title: '签名',
                    align: 'center',
                    tooltip: true,
                    key: 'smsSingname'
                },
                {
                    title: '模板代码',
                    align: 'center',
                    tooltip: true,
                    key: 'smsCode'
                },
                {
                    title: '操作',
                    key: 'action',
                    width: 200,
                    align: 'center',
                    fixed: 'right',
                    render: function (h, params) {
                        return h("div", [
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
                            }, "删除"),
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
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click: function () {
                                        func.bindUser(params.row);
                                    }
                                }
                            }, "绑定用户")
                        ])
                    }
                }
            ],
            list: [],//list 数据
            Validate: {
                smsName: [
                    {required: true, message: '不能为空', trigger: 'blur', validator: _u.validate_notNull},
                ],
                smsTemplate: [
                    {required: true, message: '不能为空', trigger: 'blur', validator: _u.validate_notNull},
                ],
                smsCode: [
                    {required: true, message: '不能为空', trigger: 'blur', validator: _u.validate_notNull},
                ]
            }
        };
        func.initUsers = function () {
            _u.getUsers(App, function (users) {
                var us = [];
                users.records.forEach(function (user) {
                    var item = {
                        key: user.id + "",
                        label: user.displayName,
                        disabled: false
                    };
                    us.push(item);
                });
                appData.bindModal.userData = us;
            });
        };
        func.edit = function (sms) {
            appData.addDrawer.show = true;
            appData.addDrawer.sms = sms;
        };
        func.delete = function (sms) {
            appData.deleteModal.show = true;
            appData.deleteModal.id = sms.id;
        };
        /*请求*/
        func.delRequest = function () {
            _$.ajax({
                url: '<@c.rootPath/>' + api.sysSms_del,
                dataType: 'json',
                type: 'POST',
                data: {id: appData.deleteModal.id},
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
        func.bindUser = function (sms) {
            appData.bindModal.id = sms.id;
            var users = typeof (sms.smsUser) !== "undefined" ? sms.smsUser : "";
            var tar = [];
            var tarOri = users.split(',');
            tarOri.forEach(function (item) {
                if (item.replace(/\s+/g, "") !== "") {
                    tar.push(item);
                }
            });
            appData.bindModal.Target = tar;
            appData.bindModal.show = true;
        };
        func.refreshList = function () {
            var url = '<@c.rootPath/>' + api.sysSms_query;
            var data = {
                param: JSON.stringify(appData.searchParam),
                curPage: appData.searchParam.curPage,
                pageSize: appData.searchParam.pageSize
            };
            _$.post(App, data, url, function (_r, _x) {
                if (_r.success) {
                    var res = _r.data;
                    appData.list = res.records;
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
        window.onload = function () {
            App = new Vue({
                el: '#AppSms',
                data: appData,
                mounted: function () {
                    this.$nextTick(function () {
                        func.refreshList();
                        func.initUsers();
                    })
                },
                methods: {
                    cancelDel: function () {
                        this.deleteModal.show = false;
                        console.log("cancelDel");
                    },
                    confirmDel: function () {
                        func.delRequest();
                        this.deleteModal.show = false;
                        console.log("confirmDel")
                    },
                    pageChange: function (page) {
                        this.searchParam.curPage = page;
                        func.refreshList();
                    },
                    pageSizeChange: function (size) {
                        this.searchParam.pageSize = size;
                        func.refreshList();
                    },
                    bindConfirm: function () {
                        var url = '<@c.rootPath/>' + api.sysSms_bind;
                        var formdata = new FormData();
                        formdata.append("id", appData.bindModal.id);
                        var ids = "";
                        appData.bindModal.Target.forEach(function (value) {
                            ids += value;
                            ids += ",";
                        });
                        formdata.append("userIds", ids);
                        _$.post(App, formdata, url, function (_r, _x) {
                            if (_r.success) {
                                _u.success(App, "绑定成功");
                                func.refreshList();
                                appData.bindModal.show = false;
                            } else {
                                _u.error(App, _r.reason);
                            }
                        }, function (_ex) {
                            _u.error(App, JSON.stringify(_ex));
                        });
                    },
                    addSms: function () {
                        var url = '<@c.rootPath/>' + api.sysSms_add;
                        var request = new FormData(document.querySelector('form'));
                        debugger
                        if (appData.addDrawer.sms.id) {
                            request.append('id', appData.addDrawer.sms.id);
                        }
                        _$.post(App, request, url, function (_r, _x) {
                            if (_r.success) {
                                _u.success(App, '操作成功，刷新列表');
                                appData.addDrawer.show = false;
                                func.refreshList();
                                func.getTree(false);
                            } else {
                                _u.error(App, _r.reason);
                            }
                        }, function (_ex) {
                            _u.error(App, JSON.stringify(_ex));
                        });
                    },
                    addOpt: function () {
                        appData.addDrawer.show = true;
                        appData.addDrawer.sms = {
                            smsName: "",
                            smsTemplate: "",
                            smsNote: "",
                            smsSingname: "",
                            smsCode: ""
                        }
                    }
                }
            });
        };
    </script>

</@l.manage>

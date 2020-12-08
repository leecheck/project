<@l.manage tab="sysUser" title="用户管理" import="vue,iview,layout" >
    <@c.script url="js/lib.js"></@c.script>
<div id="app" v-cloak>
    <h1>Hello boy, ${test!}</h1><br>
    <h1><a href="<@c.rootPath/>/logout">注销</a></h1><br>
    <h1><i-button @click="addDrawer">新增用户</i-button></h1><br>
    <ul>
        <li v-for="user in userList">{{ user.username }}
            <Tag color="#FFA2D3" @click.native="edit(user)">修改</Tag>
            <Tag color="#FFA2D3">删除</Tag>
            <Tag color="lime">角色</Tag>
        </li>
    </ul>
    <i-button @click="showModal">showModal</i-button>
    <i-button @click="showDrawer">showDrawer</i-button>
    <Tree :data="org_tree"></Tree>
    <Tree :data="menu_tree"></Tree>
    <Tree :data="dict_tree"></Tree>
    <Modal v-model="visible" draggable title="Welcome">
        <p slot="header" style="color:#f60;text-align:center">
            <Icon type="ios-information-circle"></Icon>
            <span>Delete confirmation</span>
        </p>
        <div style="text-align:center">
            <p>After this task is deleted, the downstream 10 tasks will not be implemented.</p>
            <p>Will you delete it?</p>
        </div>
        <div slot="footer">
            <i-button type="error" size="large" >Delete</i-button>
        </div>
    </Modal>
    <Drawer title="Basic Drawer" width="620" :closable="false" v-model="drawerShow">
        <i-form  :model="user" >
            <form-item label="用户名称" prop='username' >
                <i-input  v-model="user.username" placeholder="请输入用户名"></i-input>
            </form-item>
            <form-item label="用户密码" prop="password">
                <i-input v-model="user.password" type="password"></i-input>
            </form-item>
        </i-form>
        <div class="demo-drawer-footer">
            <i-button style="margin-right: 8px" @click="drawerShow = false">取消</i-button>
            <i-button type="primary" @click="operate()">提交</i-button>
        </div>
    </Drawer>
</div>

<style type="text/css">
    [v-cloak] {
        display: none;
    }

</style>

<script type="text/javascript">

    var appData = {
        user:{
            username:"",
            password:""
        },
        org_tree: [
            {
                title: '青岛无线电研究所',
                expand: true,
                children: [
                    {
                        title: '李沧区无线电中心',
                        expand: true,
                        children: [
                            {
                                title: '王村镇中心'
                            },
                            {
                                title: '李村镇中心'
                            }
                        ]
                    },
                    {
                        title: '黄岛区中心',
                        expand: true,
                        children: [
                            {
                                title: '红石崖中心'
                            },
                            {
                                title: '唐岛湾中心'
                            }
                        ]
                    }
                ]
            }
        ],
        menu_tree: [
            {
                title: '系统权限根节点',
                expand: true,
                children: [
                    {
                        title: '主菜单',
                        expand: true,
                        children: [
                            {
                                title: '首页'
                            },
                            {
                                title: '任务管理'
                            },
                            {
                                title: '系统管理'
                            }
                        ]
                    },
                    {
                        title: '任务子菜单',
                        expand: true,
                        children: [
                            {
                                title: '频谱查看'
                            },
                            {
                                title: 'tdoa定位'
                            }
                        ]
                    }
                ]
            }
        ],
        dict_tree: [
            {
                title: '系统字典根节点',
                expand: true,
                children: [
                    {
                        title: '图层组',
                        expand: true,
                        children: [
                            {
                                title: '底图'
                            },
                            {
                                title: '专题图'
                            }
                        ]
                    }
                ]
            }
        ],
        manageType:"add",
        userList:[],
        visible: false,
        drawerShow:false,
        pStyle: {
            fontSize: '16px',
            color: 'rgba(0,0,0,0.85)',
            lineHeight: '24px',
            display: 'block',
            marginBottom: '16px'
        },
        styles: {
            height: 'calc(100% - 55px)',
            overflow: 'auto',
            paddingBottom: '53px',
            position: 'static'
        },
    };
    var refreshList = function(){
        _$.ajax({
            url: '<@c.rootPath/>/admin/sysUser/query',
            dataType: 'json',
            type: 'POST',
            data: {},
            success: function (_r, _s) {
                if (_r.success) {
                    appData.userList = _r.data;
                } else {

                }
            },
            fail: function (_ex) {
                var a = "1";
            }
        });
    };
    window.onload = function () {
        var app= new Vue({
            el: '#app',
            data: appData,
            mounted:function () {
                refreshList();
            },
            methods: {
                showModal: function () {
                    this.visible = true;
                },
                showDrawer:function () {
                    this.drawerShow = true;
                },
                editDrawer:function (user) {
                    this.drawerShow = true;
                },
                addDrawer:function () {
                    this.manageType = "add";
                    this.drawerShow = true;
                },
                operate:function () {
                    if (this.manageType == "add"){
                        userOperate.add(this.user)
                    }
                }
            }
        });
    };
    userOperate = {};
    userOperate.add = function (user) {
        _$.ajax({
            url: '<@c.rootPath/>/admin/sysUser/add',
            dataType: 'json',
            type: 'POST',
            data: {
                username:user.username,
                password:user.password
            },
            success: function (_r, _s) {
                if (_r.success) {
                    refreshList();
                } else {

                }
            },
            fail: function (_ex) {
                var a = "1";
            }
        });
    }
</script>
</@l.manage>

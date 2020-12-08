<@c.html title="系统登录" import="base,vue,iview,layout" >
    <div id="App" class="login-bg">
        <div class="login-wapper">
            <i-form class="login-form" ref="loginForm" :rules="infoValidate" :model="user" action="<@c.rootPath/>/login/do" method="post" onsubmit="return verify()">
                <form-item prop='userName' >
                    <i-input v-model="user.userName" name="username" placeholder="">
                        <i class="ivu-icon ivu-icon-ios-person-outline" slot="prepend"></i>
                    </i-input>
                </form-item>
                <form-item prop='passWord' >
                    <i-input type="password" name="password" v-model="user.passWord" placeholder="">
                        <i class="ivu-icon ivu-icon-ios-lock-outline" slot="prepend"></i>
                    </i-input>
                </form-item>
                <form-item >
                    <Checkbox @on-change="rememberChange"  v-model="rememberMe">记住我</Checkbox>
                </form-item>
                <i-button  class="login-btn" html-type="submit" >登录</i-button>
            </i-form>
        </div>
    </div>
    <style type="text/css">
        .login-btn{
            position: absolute;
            bottom: 20px;
            color: #fff;
            background-color: #266c8b;
            border-color: #266c8b;
            width: 260px;
        }
        .login-form{
            width: 100%;
            height: 100%;
        }
        .login-bg{
            position: relative;
            width: 100%;
            height: 100%;
            background-size: cover;
            background-image: url(images/bg.png);
        }
        .login-wapper{
            position: absolute;
            left: 50%;
            top: 50%;
            width: 300px;
            height: 215px;
            margin-top: -100px;
            margin-left: -150px;
            border-radius: 2px;
            background-color: #1e3046;
            padding: 20px;
        }
    </style>
    <script type="text/javascript">
        var username = '${username!}';
        var msg = '${msg!}';
        var appData = {
            user:{
                userName:"",
                passWord:""
            },
            rememberMe:"",
            infoValidate:{
                userName:[{ required: true, message: '用户名不能为空', trigger: 'blur' }],
                passWord:[{ required: true, message: '密码不能为空', trigger: 'blur' }]
            }
        };
        if (store.get('rememberMe') === true){
            appData.user =  store.get('user');
        }
        appData.rememberMe = store.get('rememberMe');
        var verify = function () {
			var isValid = true;
            App.$refs['loginForm'].validate(function (valid) {
                if (valid) {
                    if (appData.rememberMe){
                    	store.set('rememberMe',true);
                        store.set('user',appData.user);
                    }else{
                    	store.set('rememberMe',false);
                        store.remove('user');
                    }
                }
				isValid = valid;
            })
			return isValid;
        };
        var App= new Vue({
            el: '#App',
            data: appData,
            mounted:function () {
                this.$nextTick(function () {
                    if (msg!==''){
                        _u.holdMsg(App,msg);
                    }
                })
            },
            methods: {
                rememberChange:function () {
                    if (appData.rememberMe){
                        store.set('rememberMe',true);
                        store.set('user',appData.user);
                    }else {
                        store.set('rememberMe',false);
                        store.remove('user');
                    }
                }
            }
        });
        /*SYSTEMLOGINPAGEMARKER*/
    </script>
</@c.html>
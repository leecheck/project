<@c.html title="未知页面" import="base,vue,iview,layout" >
    <div id="App" class="login-bg">
        <div class="login-wapper">
            ${msg!}
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
            border-radius: 15px;
            background-color: white;
            padding: 20px;
        }
    </style>
    <script type="text/javascript">
        var appData = {
        };
        var App= new Vue({
            el: '#App',
            data: appData,
            mounted:function () {
                this.$nextTick(function () {

                })
            },
            methods: {
            }
        });
    </script>
</@c.html>
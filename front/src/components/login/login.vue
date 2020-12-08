<template>
    <div class="loginBg">
        <div class="absolute login-title">
            <flareTitle></flareTitle>
        </div>
        <!-- <p>版权所有©天人环境</p> -->
        <div class="center">
            <div class="left">
                <img src="../../../static/img/login/left.png" style="width:100%;height: 100%;">
            </div>
            <div class="form border-box">
                <p>用户登录</p>
                <div class="input">
                    <i class="iconfont iconuser"></i>
                    <input v-model="username" placeholder="请输入用户名" @keyup.enter="dologin" />
                </div>
                <div class="input" style="margin-bottom: 20%;">
                    <i class="iconfont iconmima1"></i>
                    <input type="password" v-model="password" placeholder="请输入密码" @keyup.enter="dologin" />
                </div>
                <Button type="primary" @click="dologin">登录</Button>
            </div>
        </div>
    </div>
</template>

<script>
    import {
        notify
    } from '@/util/util'

    import flareTitle from "@/baseUI/flareTitle"
    import { user } from "@/config/config"
    import storage from '@/store/storage'

    export default {
        name: "container",
        components: {
            flareTitle
        },
        data() {
            return {
                inputText: 'text',
                username: '',
                password: '',
                savePsw: ''
            }
        },
        methods: {
            changeInputValue() {
                if (this.password) {
                    this.inputText = 'password';
                } else {
                    this.inputText = 'text';
                }
            },
            dologin() {
                if (this.username == user.username && this.password == user.password) {
                    storage.setUser({
                        name: "管理员"
                    });
                    storage.setToken("token");
                    this.jump();
                } else {
                    notify.error("认证信息错误")
                }
            },
            jump() {
                let path = '';
                let query = {};
                path = '/data/dataKanban';
                this.$router.push({
                    path: path,
                    query: query
                });
            }
        },
        mounted() {
            var that = this;
            //var loginInfo = this.$storage.getLoginInfo();
        }
    }
</script>

<style lang="scss" scoped>
    .login-title {
        width: 950px;
        left: 50%;
        top: 120px;
        transform: translateX(-50%);
    }

    .login-title /deep/ .shiny {
        font-size: 3.8vw;
    }

    .savePsw {
        float: left;
        margin-bottom: 10%;
        margin-left: 10px;
        color: #fff;
        font-family: "PingFang SC";
    }

    .loginBg {
        width: 100%;
        height: 100%;
        position: relative;
        min-width: 1200px;
        overflow: hidden;
        background: url("/static/img/login/loginBg.jpg") center no-repeat;
        background-size: 100%;
    }

    .loginBg>p {
        position: absolute;
        width: 100%;
        left: 0;
        bottom: 4.97%;
        font-size: 14px;
        color: #fff;
        line-height: 14px;
        font-family: "PingFang SC";
    }

    .center {
        width: 790px;
        height: 390px;
        -webkit-border-radius: 10px;
        -moz-border-radius: 10px;
        border-radius: 10px;
        background: rgba(0, 27, 63, 0.7);
        left: calc(50% - 395px);
        top: calc(50% - 140px);
        position: absolute;
    }

    .form {
        width: 50%;
        float: right;
        height: 100%;
        padding: 0 7.34%;
    }

    .form>p {
        font-size: 24px;
        color: #2bffff;
        line-height: 24px;
        letter-spacing: 10px;
        margin: 12.54% 0 20% 0;
    }

    .input,
    .check {
        width: 100%;
        overflow: hidden;
        height: 10.25%;
        position: relative;
        margin-bottom: 14%;
    }

    .input>input,
    .check>input {
        width: 100%;
        background: #f2f7ff;
        border: none;
        -webkit-border-radius: 20px;
        -moz-border-radius: 20px;
        border-radius: 20px;
        height: 100%;
        line-height: 100%;
        text-indent: 40px;
        outline: none;
        font-size: 16px;
    }

    .input>i {
        font-size: 18px;
        line-height: 18px;
        color: #adb6c5;
        position: absolute;
        margin-top: 3.94%;
        top: 0;
        left: 11px;
    }

    .check {
        margin-bottom: 14.3%;
    }

    .check>input {
        width: 64.5%;
        float: left;
        text-indent: 20px;
    }

    .form>button {
        width: 100%;
        height: 10.76%;
        border: none;
        background-color: #00a4ff;
        /* 浏览器不支持时显示 */
        background-image: linear-gradient(to bottom, #00dbd5, #0099d9);
        color: #fff;
        line-height: 100%;
        font-size: 18px;
    }

    .left {
        float: left;
        width: 50%;
        height: 100%;
    }

    .input>input::-webkit-input-placeholder {
        color: #bbb;
    }

    /* firefox火狐浏览器*/
    .input>input:-moz-placeholder {
        color: #bbb;
    }


    .input>input::-moz-placeholder {
        color: #bbb;
    }

    /*  IE浏览器*/
    .input>input:-ms-input-placeholder {
        color: #bbb;
    }
</style>
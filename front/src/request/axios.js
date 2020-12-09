import axios from 'axios'; // 使用axios库
import config from './config';
import Cookies from "js-cookie";
import router from '../router'
import NProgress from 'nprogress';

let $axios = {};
/**
 * 基础方法
 * responseType `responseType` 表示服务器响应的数据类型，可以是 'arraybuffer', 'blob', 'document', 'json', 'text', 'stream'
 */
$axios.base = (req, opt) => {
    NProgress.start();
    let { url, data, method, param, responseType = "json" } = req;
    let { timeout = config.timeout, headers = config.headers, Auth = true, tokenKey = global.tokenKey || "token" } = opt;
    return new Promise((resolve, reject) => {
        const instance = axios.create({
            headers: headers,
            timeout: timeout,
            withCredentials: false
        });
        // request 请求拦截器
        instance.interceptors.request.use(
            config => {
                if (Auth) {
                    let token = Cookies.get('token');
                    // 发送请求时携带token
                    if (token) {
                        config.headers[tokenKey] = token
                    } else {
                        // 重定向到登录页面
                        router.push("/Login")
                    }
                }
                return config
            },
            error => {
                // 判断请求超时
                if (error.code === 'ECONNABORTED' && error.message.indexOf('timeout') !== -1) {
                    console.log('timeout请求超时')
                }
                const errorInfo = error.response;
                return Promise.reject(errorInfo) // 在调用的那边可以拿到(catch)你想返回的错误信息
            }
        );

        // response 响应拦截器
        instance.interceptors.response.use(
            response => {
                let res = response.data;
                if (res.code == 0) {
                    return res.data
                }
                return res
            },
            err => {
                if (err && err.response) {
                    switch (err.response.code) {
                        case 400:
                            err.message = '请求错误';
                            break;
                        default:
                    }
                }
                return Promise.reject(err) // 返回接口返回的错误信息
            }
        );
        // 请求处理
        instance(req).then(res => {
            NProgress.done();
            resolve(res);
            return false
        }).catch(error => {
            NProgress.done();
            reject(error)
        })
    })
}

/**
 * 不带token的请求
 */
$axios.postFree = ({ url, data }) => {
    return $axios.base({ url, data, method: 'post' }, { withCredentials: false })
}

$axios.post = ({ url, data }) => {
    return $axios.base({ url, data, method: 'post' }, {})
}

$axios.get = ({ url, param }) => {
    return $axios.base({ url, param, method: 'get' }, {})
}

$axios.newFD = (data) => {
    const formData = new FormData();
    Object.keys(data).map(key => {
        formData.append(key, data[key]);
    });
    return formData;
}

export default $axios

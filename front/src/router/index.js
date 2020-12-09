import Vue from 'vue'
import Router from 'vue-router'
import storage from '@/store/storage'
import NProgress from 'nprogress';

Vue.use(Router);

const globalRoutes = [{
        path: '/login',
        name: 'login',
        component: (resolve) => require(['@/components/login/login'], resolve),
        meta: {}
    },
    {
        path: '/container',
        name: 'container',
        component: resolve => require(['@/containers/container'], resolve),
        meta: {},
        children: [
            {
                path: 'oneMap',
                name: 'oneMap',
                component: resolve => {
                    require(['@/components/map/oneMap'], resolve);
                },
                meta: {},
            },
        ]
    },
    {
        path: "*",
        redirect: "/container/oneMap"
    }
];


const defaultPath = '/container/home';

const router = new Router({
    //routes: globalRoutes.concat(mainRoutes),
    //menuAdd: false, //是否已经添加动态(菜单)路由
    routes: globalRoutes,
    menuAdd: true, //是否已经添加动态(菜单)路由
});

router.beforeEach((to, from, next) => {
    NProgress.start();
    var token = storage.getToken();
    if (token) { //登录未过期
        if (router.options.menuAdd || isGlobalRoutes(to)) {
            if (to.name == 'login') {
                next({
                    path: defaultPath
                });
            } else {
                next();
            }
        } else {
            addDynamicMenu()
            next({ ...to, replace: true })
        }
    } else { //登录已过期
        if (to.name == 'login') {
            next();
        } else {
            next({
                path: '/login'
            });
        }
    }
});

const addDynamicMenu = () => {
    let menus = storage.getMenus();
    menus.forEach(menu => {
        mainRoutes.children.push(backendMenusToRouters(menu))
    })
    router.addRoutes([ //vue-routers2.2版本以上才支持。
        mainRoutes
    ])
    router.options.menuAdd = true
}
const backendMenusToRouters = (menu) => {
    if (!menu.compUrl) { //未配置组件url 则默认组件url=powerUrl
        menu.compUrl = menu.powerUrl
    }
    let route = {
        path: menu.powerUrl,
        name: menu.powerCode,
        component: resolve => {
            require(['@/views' + menu.compUrl], resolve)
        },
        meta: {
            breadcrumb: menu.powerName
        },
        children: []
    }
    if (menu.children && menu.children.length !== 0 && menu.name != "") {
        menu.children.forEach(sub => {
            route.children.push(backendMenusToRouters(sub));
        })
    }
    return route
}

//判断当前是否全局路由
function isGlobalRoutes(to) {
    for (let i in globalRoutes) {
        if (globalRoutes[i].path == to.path) {
            return true;
        }
    }
    return false;
}

router.afterEach(route => {
    NProgress.done();
    window.scrollTo(0, 0);
});

export default router

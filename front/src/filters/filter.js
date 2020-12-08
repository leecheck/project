import Vue from 'vue'
/**
 * 货币格式化
 * currencyType 货币符号
 */

Vue.filter('formatPrice', function(value = '0', currencyType = '') {
    let res;
    if (value.toString().indexOf('.') === -1) {
        res = (value || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + '.00'
    } else {
        let prev = value.toString().split('.')[0]
        let next = value.toString().split('.')[1] < 10 ? value.toString().split('.')[1] + '0' : value.toString().split('.')[1]
        res = (prev || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + '.' + next
    }
    return currencyType + res
})

Vue.filter('curStr', function(str = '', len = 10, ellipse = "..") {
    if (str == "") {
        return str;
    }
    let temp, icount = 0,
        patrn = /[^\x00-\xff]/,
        strre = "";
    for (let i = 0; i < str.length; i++) {
        if (icount < len) {
            temp = str.substr(i, 1);
            if (patrn.exec(temp) == null) {
                icount = icount + 1
            } else {
                icount = icount + 2
            }
            strre += temp
        } else {
            if (typeof(ellipse) === "string") {
                strre = strre + ellipse;
            }
            break;
        }
    }
    return strre;
})

/**
 * format null
 */
Vue.filter('fn', function(str = '', de = "-") {
    if (!str || str == "" || str == "null" || str == "NULL") {
        return de;
    }
    return str
})
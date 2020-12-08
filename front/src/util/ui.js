
//工具类方法的配置
import {
  Notification
} from 'element-ui'


//日期格式化
let timeUtil = {
  formatYMD: function (date) {
    return date.Format('yyyy-MM-dd');
  },
  formatYM: function (date) {
    return date.Format('yyyy-MM');
  },
  formatY: function (date) {
    return date.Format('yyyy');
  },
  formatHMS: function (date) {
    return date.Format('hh:mm:ss');
  },
  format: function (date) {
    return date.Format('yyyy-MM-dd hh:mm:ss');
  },
  ios: function (datestr) {
    return datestr.replace(/-/g, '/');
  }
}

//提示信息封装
let notify = {
  success: function (message,duration) {
    Notification.success({
      title: "成功",
      message: message,
      duration: duration||3000
    });
  },
  warning: function (message,duration) {
    Notification.warning({
      title: "提醒",
      message: message,
      duration: duration||3000
    });
  },
  error: function (message,duration) {
    Notification.error({
      title: "错误",
      message: message,
      duration: duration||3000
    });
  },
  holdMsg: function (message) {
    Notification.info({
      title: "提示",
      message: message,
      duration: 0
    });
  },
};

export {
  notify
}

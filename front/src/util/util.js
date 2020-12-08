
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

//提交请求的统一处理
let _formPost=function(isValid,that,_ref,param,url,callback){
  if(isValid){//是否校验表单
    that.$refs[_ref].validate(valid => {
      if (valid) {
        that.$req.post(
          param,
          url,
          function(_r, _x) {
            if (_r.success) {
              callback(_r);
            } else {
              that.$util.notify.success(_r.message);
            }
          },
          function error(e) {
            that.$util.notify.error(JSON.stringify(e.message));
          }
        );
      } else {
        that.$util.notify.warning(that, "表单校验失败，请按提示修改");
      }
    });
  }else{
    that.$req.post(
      param,
      url,
      function(_r, _x) {
        if (_r.success) {
          callback(_r);
        } else {
          that.$util.notify.success(_r.message);
        }
      },
      function error(e) {
        that.$util.notify.error(JSON.stringify(e.message));
      }
    );
  }
}

//表单校验
let _validForm={
  telPhone:/^((0\d{2,3}-\d{7,8})|(1[3567894]\d{9}))$/,//手机号/电话号校验
  idCard:/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,//身份证校验
  serverAddress:/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/,//服务器地址
  
}

export {
  timeUtil,
  notify,
  _formPost,
  _validForm,
}

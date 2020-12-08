var pre = {};
pre.drag = function(obj)
{
    pre.addEvent(obj,'mousedown',function(ev){
        var oEvent=ev||event;
        pre.addEvent(document,'mousemove',move);
        pre.addEvent(document,'mouseup',up);
        oEvent.preventDefault();//阻止默认事件，取消文字选中
        function move(ev)
        {
            var oEvent=ev||event;
            obj.setCapture&&obj.setCapture();//低版本IE阻止默认事件，取消文字选中
        }
        function up()
        {
            pre.removeEvent(document,'mousemove',move);
            pre.removeEvent(document,'mouseup',up);
            obj.releaseCapture&&obj.releaseCapture();//低版本IE取消阻止默认事件
        }
    })
}
//添加事件绑定
pre.addEvent = function(obj,sEv,fn)
{
    if(obj.addEventListener)
    {
        obj.addEventListener(sEv,fn,false);
    }else{
        obj.attachEvent('on'+sEv,fn);
    }
}

//删除事件绑定
pre.removeEvent = function(obj,sEv,fnName)
{
    if(obj.removeEventListener)
    {
        obj.removeEventListener(sEv,fnName,false);
    }else{
        obj.detachEvent('on'+sEv,fnName);
    }
}
//代码加载完执行js
pre.ready = function(fn)
{
    if(document.addEventListener)
    {
        document.addEventListener('DOMContentLoaded',fn,false)
    }else{
        document.attachEvent('onreadystatechange',function(){
            if(document.readyState=='complete')
            {fn();}
        })
    }
}

pre.ready(function(){
    var modals = document.querySelectorAll('.ivu-modal-content-drag');
    if (modals instanceof Array){
        modals.forEach(function (modal) {
            pre.drag(modal);
        })
    }
});

pre.set = function(){
    var modals = document.querySelectorAll('.ivu-modal-content-drag .ivu-modal-header');
    if (modals instanceof NodeList){
        modals.forEach(function (modal) {
            pre.drag(modal);
        })
    }
};

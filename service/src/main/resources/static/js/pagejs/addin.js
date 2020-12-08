var addin = {};
addin.setListSize = function () {
    if (addin.getClientHeight()>800){
        appData.pageSizeOpt = [15,30];
        appData.searchParam.pageSize = 15;
    }
};

addin.getClientHeight = function(){
    var clientHeight=0;
    if(document.body.clientHeight&&document.documentElement.clientHeight){
        clientHeight = (document.body.clientHeight<document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
    }
    else{
        clientHeight = (document.body.clientHeight>document.documentElement.clientHeight)?document.body.clientHeight:document.documentElement.clientHeight;
    }
    return clientHeight;
};
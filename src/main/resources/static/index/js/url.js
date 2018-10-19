var curWwwPath=window.document.location.href;
//获取主机地址之后的目录，如：
//          curWwwPath:http://localhost/Interface/xxxxxx.html

var pathName=window.document.location.pathname;
///         pathName:/Interface/

var pos=curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8083
//          pos:16

var localhostPath=curWwwPath.substring(0,pos);
//获取带"/"的项目名，如：
//          localhostPath:http://localhost

var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//          projectName:/Interface

var realPath=localhostPath+projectName;

var path = realPath+'/';

$.ajaxSetup({
    complete:function(xhr,status){
        //若HEADER中含有REDIRECT说明后端想重定向
        if("REDIRECT" == xhr.getResponseHeader("REDIRECT")){
            //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
            window.location.href = xhr.getResponseHeader("CONTENTPATH");
        }
    }});
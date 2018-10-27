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

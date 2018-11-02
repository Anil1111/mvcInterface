function UploadFile(id, allowExt, onUploadFinish,fileBlob,rateCallBack) {
    var flag = Object.prototype.toString.call(fileBlob) === "[object String]"?1:0;
    var fileObj = fileBlob || document.getElementById(id).files[0];
    //上传格式限制
    //var allowExt = '.jpg,.png,.jpeg,.pdf,.doc,.docx,.txt';
    if(!fileBlob){
        var
            fileExtArr = fileObj.name.split("."),
            fileExt = fileExtArr[fileExtArr.length - 1];
        fileExt = fileExt.toLowerCase();
        if (allowExt != 0 && allowExt.indexOf(fileExt) == -1)//judge file format
        {
            //layer.alert('该文件类型不支持上传！' + fileExt, {icon: 3});
            onUploadFinish(false, '该文件类型不支持上传！', null);
            return;
        }
    }
    $('#coverlayer').css('display', '');
    $('#' + id).attr('disabled', true);
    var FileController = "/upload";
    var form = new FormData();
    form.append("author", "hooyes");
    form.append("file", fileObj);
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    if(flag){
        xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded")
    }
    xhr.onload = function () {
        $('#' + id).attr('disabled', false);
        if (xhr.readyState == 4) {
            $('#coverlayer').css('display', 'none');
            //判断对象状态是否交互成功,如果成功则为200
            if (xhr.status == 200) {
                //接收数据,得到服务器输出的纯文本数据
                //var response = eval(xhr.responseText);
                var response = eval('(' + xhr.responseText + ')');
                //var response = jQuery.parseJSON(xhr.responseText);
                code = response['code'];
                if (code == 100) {
                    var _uri = response['msg'][1];
                    var _uuid = response['msg'][0];
                    var url = '//sxsimg.xiaoyuanzhao.com/' + _uri;
                    onUploadFinish(true, _uuid, url);
                } else {
                    onUploadFinish(false, response['msg'], null);
                }
            } else {
                onUploadFinish(false, null, null);
            }
        }
    };
    //侦查当前附件上传情况
    xhr.upload.onprogress = function (evt) {
        if(!rateCallBack){return;}
        var loaded = evt.loaded,
        tot = evt.total,
        per = Math.floor(100 * loaded / tot); //已经上传的百分比
        rateCallBack(per);

    };
    //xhr.upload.addEventListener("progress",progressFunction,false);
    xhr.send(flag?fileBlob:form)
}
//
//function progressFunction(evt) {
//            var progressBar = document.getElementById("progressBar");
//            var percentageDiv = document.getElementById("percentage");
//            if (evt.lengthComputable) {
//                progressBar.max = evt.total;
//                progressBar.value = evt.loaded;
//                percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
//            }
//        }
function UploadPPTFile(id, allowExt, onUploadFinish,user_uuid,stream_id) {
    var fileObj = document.getElementById(id).files[0];
    //上传格式限制
    //var allowExt = '.jpg,.png,.jpeg,.pdf,.doc,.docx,.txt';
    if(1){
        var
            fileExtArr = fileObj.name.split("."),
            fileExt = fileExtArr[fileExtArr.length - 1];
        fileExt = fileExt.toLowerCase();
        if (allowExt != 0 && allowExt.indexOf(fileExt) == -1)//judge file format
        {
            //layer.alert('该文件类型不支持上传！' + fileExt, {icon: 3});
            onUploadFinish(false, '该文件类型不支持上传！', null);
            return;
        }
    }
    $('#coverlayer').css('display', '');
    $('#' + id).attr('disabled', true);
    var FileController = "/xuanjiang/upload";
    var form = new FormData();
    form.append("author", "hooyes");
    form.append("user_uuid", user_uuid);
    form.append("stream_id", stream_id);
    form.append("file", fileObj);
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function () {
        $('#' + id).attr('disabled', false);
        if (xhr.readyState == 4) {
            $('#coverlayer').css('display', 'none');
            //判断对象状态是否交互成功,如果成功则为200
            if (xhr.status == 200) {
                //接收数据,得到服务器输出的纯文本数据
                //var response = eval(xhr.responseText);
                var response = eval('(' + xhr.responseText + ')');
                //var response = jQuery.parseJSON(xhr.responseText);
                var code = response['code'];
                if (code == 100) {
                    onUploadFinish(true);
                } else {
                    onUploadFinish(false);
                }
            } else {
                onUploadFinish(false);
            }
        }
    };
    //侦查当前附件上传情况
    //xhr.upload.onprogress = function (evt) {
    //    if(!rateCallBack){return;}
    //    var loaded = evt.loaded,
    //    tot = evt.total,
    //    per = Math.floor(100 * loaded / tot); //已经上传的百分比
    //    rateCallBack(per);
    //
    //};
    //xhr.upload.addEventListener("progress",progressFunction,false);
    xhr.send(form)
}
//
//function progressFunction(evt) {
//            var progressBar = document.getElementById("progressBar");
//            var percentageDiv = document.getElementById("percentage");
//            if (evt.lengthComputable) {
//                progressBar.max = evt.total;
//                progressBar.value = evt.loaded;
//                percentageDiv.innerHTML = Math.round(evt.loaded / evt.total * 100) + "%";
//            }
//        }
$("input#on").change(function () { //切换禁用启用
    var obj = {
        "disableFlag": 1
    };
    $.ajax({
        url: path + 'users/user/' + $(this).attr('name'),
        type: "PUT",
        // dataType : "json",
        data: obj,//必要
        success: function () {
           return;
        },
        error: function (result) {
            alert(result);
        }
    });
});
$("input#off").change(function () { //切换禁用启用
    var obj = {
        "disableFlag": 0
    };
    $.ajax({
        url: path + 'users/user/' + $(this).attr('name'),
        type: "PUT",
        // dataType : "json",
        data: obj,//必要
        success: function () {
            return;
        },
        error: function (result) {
            alert(result);
        }
    });
});



function showInsertLayer(){
    $('div#insert').modal('show');
};


function showDeleteLayer(id){
    $("button#deleteOK").attr("name",id);
    $('div#delete').modal('show');
};

function showUpdateLayer(id){
    $("button#updateOK").attr("name",id);
    $('div#update').modal('show');
    $.ajax({
        url: path + 'users/user/' + id,
        type: "GET",
        data: {},
        success:function (result) {
            $('#updateId').val(result.data.id);
            $('#updateNickName').val(result.data.nickName);
            $('#updateUsername').val(result.data.username);
            $('#updatePassword').val(result.data.password);
            $('#updateDescription').val(result.data.description);
        },
        error: function (result) {
            alert(result)
        }
    })

};



$("button#insertOK").click(function (){
    var obj = {
        nickName: $('#insertNickName').val(),
        username:  $('#insertUsername').val(),
        password: $('#insertPassword').val(),
        description: $('#insertDescription').val()
    };
    $.ajax({
        url: path + 'users/user',
        type: "POST",
        // dataType : "json",
        data: obj,//必要
        success: function () {
            return;
        },
        error: function (result) {
            alert(result);
        }
    });
});



$("button#deleteOK").click(function (){
    $.ajax({
        url: path + 'users/user/' + $(this).attr('name'),
        type: "DELETE",
        // dataType : "json",
        data: {},
        success: function () {
            return;

        },
        error: function (result) {
            alert(result);
        }
    });
});

$("button#updateOK").click(function (){
    var obj = {
        id: $('#updateId').val(),
        nickName: $('#updateNickName').val(),
        username:  $('#updateUsername').val(),
        password: $('#updatePassword').val(),
        description: $('#updateDescription').val()
    };
    $.ajax({
        url: path + 'users/user/' + $('#updateId').val(),
        type: "PUT",
        // dataType : "json",
        data: obj,//必要
        success: function () {
            return;
        },
        error: function (result) {
            alert(result);
        }
    });
});



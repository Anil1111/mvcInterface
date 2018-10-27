$("input#on").change(function () {
    console.log("ok");
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
$("input#off").change(function () {
    console.log("ok");
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
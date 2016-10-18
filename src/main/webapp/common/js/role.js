/*编辑角色 开始*/
var editRoleId = 0;
var editCheckbox = 0;
$("button[name='editRole']").each(function () {
    $(this).live("click", function () {
        $("#myModal").hide();
        editRoleId = $(this).attr("id");
        //初始化数据
        $("input[name='editRoleName']").val($(this).attr("roleName"));
        $("#statusDiv").html("<input type=\"checkbox\" name=\"editCheckbox\" id=\"editCheckbox\" checked title=\"状态\">");
        editCheckbox = parseInt($(this).attr("status")) == 1 ? 1 : 0;
        $("[name='editCheckbox']").bootstrapSwitch({
            state: parseInt($(this).attr("status")) == 1,
            handleWidth: "50",
            onColor: "primary",
            offColor: "warning",
            size: "small",
            onText: '启用',
            offText: '禁用',
            onSwitchChange: function (event, state) {
                if (state == true) {
                    editCheckbox = 1;
                } else {
                    editCheckbox = 0;
                }
            }
        });
        $("input[name='editRoleDescn']").val($(this).attr("descn"));

        $.get("../../role/getEditRoleMenu/" + $(this).attr("id"), function (response) {
            var treeDataSource = new DataSourceTree({data: response});
            $("#setPermissions").html("<div id=\"tree2\" class=\"tree tree-selectable2\"></div>");
            $('#tree2').ace_tree({
                dataSource: treeDataSource,
                multiSelect: true,
                loadingHTML: '<div class="tree-loading"><i class="icon-refresh icon-spin blue"></i></div>',
                'open-icon': 'icon-minus',
                'close-icon': 'icon-plus',
                'selectable': true,
                'selected-icon': 'icon-ok',
                'unselected-icon': 'icon-remove'
            });
        });
        var mt = $('#editRoleModal');
        mt.modal({backdrop: 'static', keyboard: false, handle: "#editRoleModal"});
        mt.draggable();//为模态对话框添加拖拽
    });
});


var editRoleName = $("input[name='editRoleName']");
var editRoleDescn = $("input[name='editRoleDescn']");
editRoleDescn.bind('input propertychange', function () {
    roleDescnCheck(editRoleDescn);
});

$(".edit-role-from").live("click", function () {
    if (!("" != editRoleDescn.val())) {
        editRoleDescn.parent().parent().parent().addClass("error");
        editRoleDescn.next("span").html("输入有误");
        return;
    }

    //editRoleName//角色
    //editCheckbox//状态1 启用，0 禁用
    //editRoleDescn//角色说明
    var tree = [];//定义数组
    $(".tree-selected").each(function () {
        tree.push($(this).find("div").attr("id"));
        tree.push($(this).parent().parent().find("div").find("div").attr("id"));
    });

    var editDataAry = {};
    editDataAry.rolename = editRoleName.val();
    editDataAry.checkbox = editCheckbox;
    editDataAry.roledescn = editRoleDescn.val();
    editDataAry.tree = tree;

    /*设置ajax请求头*/
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader($("#_csrf_header").attr("content"), $("#_csrf").attr("content"));
    });

    $.ajax({
        type: "PUT",
        url: "../../role/role/" + editRoleId,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(editDataAry),
        success: function (data, textStatus) {
            if (data.status) {
                alert("角色修改成功!");
                window.location.href = window.location.href;

                //$.get("../../role/page", function (result) {
                //    $("#dashboard").html(result);
                //});
                //$(".modal-backdrop,.fade,.in").remove();
            } else {
                alert(data.message);
            }
        },
        error: function (XHR, textStatus, errorThrown) {
            alert("XHR=" + XHR + "\ntextStatus=" + textStatus + "\nerrorThrown=" + errorThrown);
        }
    });


});
/*编辑角色 结束*/


/*删除角色 开始*/
var roleId = 0;
$("button[name='deleteRole']").each(function () {
    $(this).live("click", function () {
        $("#myModal").hide();
        roleId = $(this).attr("id");
        $("#deleteRoleBody").html("确定要删除【 " + $(this).attr("text") + " 】这个角色吗？");
        $('#deleteRoleModal').modal('show');
    });
});
$("#deleteRoleButton").live("click", function () {
    /*设置ajax请求头*/
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader($("#_csrf_header").attr("content"), $("#_csrf").attr("content"));
    });

    $.ajax({
        type: "DELETE",
        url: "../../role/role/" + roleId,
        success: function (data, textStatus) {
            if (data.status) {
                alert("角色删除成功!");
                window.location.href = window.location.href;
            } else {
                alert(data.message);
            }
        },
        error: function (XHR, textStatus, errorThrown) {
            alert("XHR=" + XHR + "\ntextStatus=" + textStatus + "\nerrorThrown=" + errorThrown);
        }
    });
});
/*删除角色 结束*/


/*查看角色 开始*/
$("button[name='checkRole']").each(function () {
    $(this).live("click", function () {
        viewRoleTree($(this).attr("id"));
        $("#myModal").hide();
        $('#viewRole').modal('show');
    });
});
/*查看角色 结束*/

/*添加角色 开始*/
$("#sample_editable_1_new").live("click", function () {
    var mt = $('#myModal');
    mt.modal({backdrop: 'static', keyboard: false, handle: "#myModal"});
    mt.draggable();//为模态对话框添加拖拽
});

/*状态开关*/
$("[name='my-checkbox']").bootstrapSwitch({
    handleWidth: "50",
    onColor: "primary",
    offColor: "warning",
    size: "small",
    onText: '启用',
    offText: '禁用'
});


var rolename = $("input[name='rolename']");
var roledescn = $("input[name='roledescn']");
var parent = /^[A-Za-z]+$/;
rolename.bind('input propertychange', function () {
    if ("" != rolename.val()) {
        rolename.next("span").html("");
        if (parent.test(rolename.val())) {
            rolename.parent().parent().parent().removeClass("error");
            rolename.next("span").html("");
        } else {
            rolename.parent().parent().parent().addClass("error");
            rolename.next("span").html("必须为英文");
        }
    } else {
        rolename.parent().parent().parent().addClass("error");
        rolename.next("span").html("不能为空");
    }
});

roledescn.bind('input propertychange', function () {
    roleDescnCheck(roledescn);
});


$(".save-role-from").click(function () {
    if (!("" != rolename.val() || parent.test(rolename.val()))) {
        rolename.parent().parent().parent().addClass("error");
        rolename.next("span").html("输入有误");
        return;
    }
    if (!("" != roledescn.val())) {
        roledescn.parent().parent().parent().addClass("error");
        roledescn.next("span").html("输入有误");
        return;
    }
    var checkbox = document.getElementById("my-checkbox").checked;//true 启用，false 禁用

    var tree = [];//定义数组
    $(".tree-selected").each(function () {
        tree.push($(this).find("div").attr("id"));
        tree.push($(this).parent().parent().find("div").find("div").attr("id"));
    });

    var saveDataAry = {};
    saveDataAry.rolename = rolename.val();
    saveDataAry.checkbox = checkbox;
    saveDataAry.roledescn = roledescn.val();
    saveDataAry.tree = tree;

    /*设置ajax请求头*/
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader($("#_csrf_header").attr("content"), $("#_csrf").attr("content"));
    });

    $.ajax({
        type: "POST",
        url: "../../role/role",
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(saveDataAry),
        success: function (data, textStatus) {
            if (data.status) {
                alert("角色创建成功!");
                window.location.href = window.location.href;

                /*会重复加载JS不知道怎么处理，先刷新当前页*/
//                    $.get("../../role/page", function (result) {
//                        $("#dashboard").html(result);
//                    });
//
//                    $(".modal-backdrop,.fade,.in").remove();
            } else {
                alert(data.message);
            }
        },
        error: function (XHR, textStatus, errorThrown) {
            alert("XHR=" + XHR + "\ntextStatus=" + textStatus + "\nerrorThrown=" + errorThrown);
        }
    });
});
/*添加角色 结束*/


function roleDescnCheck(roleDescnCheck) {
    if ("" != roleDescnCheck.val()) {
        roleDescnCheck.parent().parent().parent().removeClass("error");
        roleDescnCheck.next("span").html("");
    } else {
        roleDescnCheck.parent().parent().parent().addClass("error");
        roleDescnCheck.next("span").html("不能为空");
    }
}



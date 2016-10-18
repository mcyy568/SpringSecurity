var Index = function () {

    return {
        //启动模块的主要功能
        init: function () {
            App.addResponsiveHandler(function () {
                Index.initCalendar();
                jQuery('.vmaps').each(function () {
                    var map = jQuery(this);
                    map.width(map.parent().width());
                });
            });
        },

        /*更改样式*/
        initCalendar: function () {
            if (!jQuery().fullCalendar) {
                return;
            }

            var h = {};
            if ($('#calendar').width() <= 400) {
                $('#calendar').addClass("mobile");
                h = {
                    left: 'title, prev, next',
                    center: '',
                    right: 'today,month,agendaWeek,agendaDay'
                };
            } else {
                $('#calendar').removeClass("mobile");
                if (App.isRTL()) {
                    h = {
                        right: 'title',
                        center: '',
                        left: 'prev,next,today,month,agendaWeek,agendaDay'
                    };
                } else {
                    h = {
                        left: 'title',
                        center: '',
                        right: 'prev,next,today,month,agendaWeek,agendaDay'
                    };
                }
            }
        }
    };

}();

//处理菜单选中点击子菜单
$(".sub-menu li").click(function () {
    $("*").removeClass("active");
    $(this).addClass("active");
    $(this).parent().parent().addClass("active");

    var item = $("#item-Only-Url");
    var url = item.val() + $(this).attr("text");
    window.history.pushState({}, 0, 'http://' + window.location.host + item.val() + "/home" + $(this).attr("text"));
    $.get(url, function (result) {
        $("#dashboard").html(result);
        //window.location.href = window.location.href;
    });

});

/*单击父菜单*/
$(".no-Child").click(function () {
    $("*").removeClass("active");
    $(this).addClass("active");

    var item = $("#item-Only-Url");
    var url = item.val() + $(this).attr("text");
    window.history.pushState({}, 0, 'http://' + window.location.host + item.val() + "/home" + $(this).attr("text"));
    $.get(url, function (result) {
        $("#dashboard").html(result);
        //window.location.href = window.location.href;
    });
});

var Login = function () {
    return {
        init: function () {
            $('.login-form').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    username: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    captcha: {
                        required: true
                    },
                    remember: {
                        required: false
                    }
                },

                messages: {
                    username: {
                        required: "账户名不能为空."
                    },
                    password: {
                        required: "密码不能为空."
                    },
                    captcha: {
                        required: "验证码不能为空."
                    }
                },
                invalidHandler: function (event, validator) { //显示表单提交的错误警报
                    $('.alert-error', $('.login-form')).show();
                },

                highlight: function (element) { // hightlight error inputs
                    $(element).closest('.control-group').addClass('error'); // set error class to the control group
                },

                success: function (label) {//登录from获取失去焦点调用
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },

                errorPlacement: function (error, element) {//当文本框输入值失去焦点调用
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },

                submitHandler: function (form) {
                    form.submit()
                }
            });

            $('.login-form input').keypress(function (e) {
                if (e.which == 13) {
                    if ($('.login-form').validate().form()) {
                        $('.login-form').submit();
                    }
                    return false;
                }
            });

            $('.forget-form').validate({
                errorElement: 'label', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    email: {
                        required: true,
                        email: true
                    }
                },

                messages: {
                    email: {
                        required: "Email is required."
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit

                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.control-group').addClass('error'); // set error class to the control group
                },

                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                },

                submitHandler: function (form) {
                    window.location.href = "index.html";
                }
            });

            $('.forget-form input').keypress(function (e) {
                if (e.which == 13) {
                    if ($('.forget-form').validate().form()) {
                        window.location.href = "index.html";
                    }
                    return false;
                }
            });

            jQuery('#forget-password').click(function () {
                jQuery('.login-form').hide();
                jQuery('.forget-form').show();
            });

            jQuery('#back-btn').click(function () {
                jQuery('.login-form').show();
                jQuery('.forget-form').hide();
            });

            /*注册from*/
            $('.register-form').validate({
                errorElement: 'label', //默认输入错误消息容器
                errorClass: 'help-inline', // 默认输入错误消息类
                focusInvalid: false, // 不要集中最后一个无效输入
                ignore: "",
                rules: {
                    username: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    rpassword: {
                        equalTo: "#register_password"
                    },
                    useremail: {
                        required: true,
                        email: true
                    },
                    captcha: {
                        required: true
                    },
                    tnc: {
                        required: true
                    }
                },

                messages: { // 对于单选按钮和复选框的自定义消息
                    username: {
                        required: "账户名不能为空"
                    },
                    password: {
                        required: "密码不能为空"
                    },
                    rpassword: {
                        equalTo: "两次密码必须输入一致"
                    },
                    useremail: {
                        required: "邮箱不能为空",
                        email: "邮箱格式不正确"
                    },
                    captcha: {
                        required: "请输入验证码"
                    },
                    tnc: {
                        required: "您还未接受用户协议"
                    }
                },
                invalidHandler: function (event, validator) { //显示表单提交的错误警报
                },

                highlight: function (element) { // 标出错误输入
                    $(element).closest('.control-group').addClass('error'); // 将错误类设置为对照组
                },

                success: function (label) {
                    label.closest('.control-group').removeClass('error');
                    label.remove();
                },

                errorPlacement: function (error, element) {
                    if (element.attr("name") == "tnc") { // 插入复选框错误后的容器
                        error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
                    } else {
                        error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
                    }
                },

                submitHandler: function (form) {
                    var record = {};
                    var da = $("#registerFrom").serializeArray();
                    for (var i = 0; i < da.length; i++) {
                        record[da[i].name] = da[i].value;
                    }

                    $.ajax({
                        type:"POST",
                        url:"register",
                        dataType:"json",
                        contentType:"application/json",
                        data:JSON.stringify(record),
                        success:function(data){
                            if (data.status) {
                                alert("注册成功！请登录");
                                window.location.href = window.location.href;
                            } else {
                                var f = $("#register-form-Prompt");
                                f.find("span").html(data.message);
                                f.show();
                                var img = $('#registerFromImg');
                                img.attr("src", img.attr("src") + "?" + Math.random());
                            }
                        },
                        error: function(e) {
                            alert("系统错误！");
                        }
                    });
                }
            });

            jQuery('#register-btn').click(function () {
                jQuery('.login-form').hide();
                jQuery('.register-form').show();
            });

            jQuery('#register-back-btn').click(function () {
                jQuery('.login-form').show();
                jQuery('.register-form').hide();
            });


            jQuery('.captcha-img').click(function () {
                this.src = this.src + "?" + Math.random();
            });
        }
    };

}();
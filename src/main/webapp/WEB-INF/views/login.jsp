<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>登录系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <link href="${pageContext.request.contextPath}/common/template/css/bootstrap/bootstrap.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/common/template/css/bootstrap/bootstrap-responsive.min.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/common/template/css/font-awesome.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/common/template/css/style-metro.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/common/template/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/common/template/css/style-responsive.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/common/template/css/themes/default.css" rel="stylesheet"
          type="text/css" id="style_color"/>
    <link href="${pageContext.request.contextPath}/common/template/css/uniform.default.css" rel="stylesheet"
          type="text/css"/>

    <link href="${pageContext.request.contextPath}/common/template/css/login.css" rel="stylesheet" type="text/css"/>

    <link rel="shortcut icon" href="${pageContext.request.contextPath}/common/template/image/favicon.ico"/>
</head>
<body class="login">
<!-- BEGIN LOGO -->
<div class="logo">
    <img src="${pageContext.request.contextPath}/common/template/image/logo-big.png" alt=""/>
</div>
<!-- END LOGO -->
<!-- BEGIN LOGIN -->
<div class="content">
    <!-- 登录 FORM -->
    <form class="form-vertical login-form" name="loginForm" action="<c:url value='checklogin'/>"
          method='POST'>
        <h3 class="form-title">登录系统</h3>
        <div class="alert alert-error hide">
            <button class="close" data-dismiss="alert"></button>
            <span>输入有误请检查后重新输入.</span>
        </div>
        <c:if test="${not empty error}">
            <div class="alert alert-error">
                <button class="close" data-dismiss="alert"></button>
                <span>${error}.</span>
            </div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="alert alert-success">
                <button class="close" data-dismiss="alert"></button>
                <span>${msg}.</span>
            </div>
        </c:if>

        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-user"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="账户" name="username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="password"/>
                </div>
            </div>
        </div>
        <c:if test="${SPRING_SESSION_FAIL_TIMES > 3}">
            <div class="control-group">
                <div class="controls">
                    <div class="input-icon left">
                        <i class="icon-youtube"></i>
                        <input class="m-wrap placeholder-no-fix" type="text" placeholder="验证码" name="captcha" style="width:160px;"/>
                        <img title="点击更换" alt="验证图片" src="captcha" class="pull-right captcha-img">
                    </div>
                </div>
            </div>
        </c:if>
        <div class="form-actions">
            <label class="checkbox">
                <input type="checkbox" name="remember" value="1"/> 记住密码
            </label>
            <button type="submit" class="btn green pull-right">
                登录 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
        <div class="forget-password">
            <h4>忘记密码 ?</h4>
            <p>
                <a href="javascript:;" class="" id="forget-password">找回密码</a>
            </p>
        </div>
        <div class="create-account">
            <p>
                没有账户 ?&nbsp;
                <a href="javascript:;" id="register-btn" class="">创建账户</a>
            </p>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <!-- END 登录 FROM -->

    <!-- 忘记密码 FORM -->
    <form class="form-vertical forget-form" action="index.html">
        <h3 class="">忘记密码 ?</h3>
        <p>通过e-mail找回密码</p>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-envelope"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <button type="button" id="back-btn" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" class="btn green pull-right">
                提交 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END 忘记密码 -->

    <!-- 注册 FORM -->
    <form class="form-vertical register-form" id="registerFrom">
        <h3 class="">注册</h3>
        <div class="alert alert-error hide" id="register-form-Prompt">
            <button class="close" data-dismiss="alert"></button>
            <span></span>
        </div>
        <p>创建用户:</p>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-user"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="账户" name="username"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-lock"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" id="register_password"
                           placeholder="密码" name="password"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-ok"></i>
                    <input class="m-wrap placeholder-no-fix" type="password" placeholder="再次输入密码" name="rpassword"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-envelope"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="useremail"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="input-icon left">
                    <i class="icon-youtube"></i>
                    <input class="m-wrap placeholder-no-fix" type="text" placeholder="验证码" name="captcha" style="width:160px;"/>
                    <img title="点击更换" alt="验证图片" src="captcha" class="pull-right captcha-img" id="registerFromImg">
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <label class="checkbox">
                    <input type="checkbox" name="tnc"/> 我同意 <a href="#">服务</a> 和 <a href="#">隐私政策</a>
                </label>
                <div id="register_tnc_error"></div>
            </div>
        </div>
        <div class="form-actions">
            <button id="register-back-btn" type="button" class="btn">
                <i class="m-icon-swapleft"></i> 返回
            </button>
            <button type="submit" id="register-submit-btn" class="btn green pull-right">
                注册 <i class="m-icon-swapright m-icon-white"></i>
            </button>
        </div>
    </form>
    <!-- END 注册 FORM -->

</div>
<!-- END LOGIN -->
<!-- BEGIN COPYRIGHT -->
<div class="copyright">
    2013 &copy; Metronic. Admin Dashboard 模板.
</div>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery-1.10.1.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery-migrate-1.2.1.min.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery-ui-1.10.1.custom.min.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/template/js/bootstrap/bootstrap.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.blockui.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.cookie.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.uniform.min.js"
        type="text/javascript"></script>
<%--jquery.validate.js表单验证--%>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.validate.min.js"
        type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/template/js/login.js" type="text/javascript"></script>

<script>
    jQuery(document).ready(function () {
        Login.init();
    });
</script>
</body>

</html>
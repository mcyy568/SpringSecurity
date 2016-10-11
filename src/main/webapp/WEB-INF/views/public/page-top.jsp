<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header navbar navbar-inverse navbar-fixed-top">
    <!-- 开始顶部导航栏 -->
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="HomePage">
                <img src="${pageContext.request.contextPath}/common/template/image/logo.png" alt="logo"/>
            </a>
            <!-- 开始菜单显示开关响应 -->
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="${pageContext.request.contextPath}/common/template/image/menu-toggler.png" alt=""/>
            </a>
            <!-- 开始顶部导航菜单 -->
            <ul class="nav pull-right">
                <!-- 从用户登录下拉-->
                <li class="dropdown user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img alt="" src="${pageContext.request.contextPath}/common/template/image/avatar1_small.jpg"/>
                        <span class="username">杨辽</span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="${pageContext.request.contextPath}/logout"><i class="icon-key"></i> 注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>

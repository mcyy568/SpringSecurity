<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8"/>
    <title>Metronic | Admin Dashboard Template</title>
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
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/common/template/image/favicon.ico"/>
</head>
<body class="page-header-fixed">
<!-- BEGIN HEADER -->
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


<!-- 开始的容器 -->
<div class="page-container">
    <!-- 从侧边栏 开始 -->
    <div class="page-sidebar nav-collapse collapse">
        <!-- 开始的侧边栏菜单-->
        <ul class="page-sidebar-menu">
            <li>
                <div class="sidebar-toggler hidden-phone"></div>
            </li>

            <c:forEach items="${appMenus}" var="menus" varStatus="status">
                <c:if test="${menus.parentid == 0}">
                    <li class="<c:if test="${status.count%2==1}">start active</c:if>">
                        <a href="${pageContext.request.contextPath}${menus.menuurl}">
                            <i class="${menus.menucode}"></i>
                            <span class="title">${menus.menuname}</span>
                            <c:if test="${empty menus.appMenus}">
                                <span class="selected"></span>
                            </c:if>
                            <c:if test="${not empty menus.appMenus}">
                                <span class="arrow"></span>
                            </c:if>
                        </a>
                        <c:if test="${not empty menus.appMenus}">
                            <ul class="sub-menu">
                                <c:forEach items="${menus.appMenus}" var="menus2">
                                    <li>
                                        <a href="${pageContext.request.contextPath}${menus2.menuurl}">
                                            <i class="${menus2.menucode}"></i>
                                                ${menus2.menuname}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:if>
            </c:forEach>
        </ul>
    </div>
    <!-- 从侧边栏 结束 -->

    <!-- 开始页 -->
    <div class="page-content">
        <!--开始页面容器-->
        <div class="container-fluid">
            <!-- 开始页面标题-->
            <div class="row-fluid">
                <div class="span12">
                    <!-- 开始的风格定制 -->
                    <div class="color-panel hidden-phone">
                        <div class="color-mode-icons icon-color"></div>
                        <div class="color-mode-icons icon-color-close"></div>
                        <div class="color-mode">
                            <p>主题颜色</p>
                            <ul class="inline">
                                <li class="color-black current color-default" data-style="default"></li>
                                <li class="color-blue" data-style="blue"></li>
                                <li class="color-brown" data-style="brown"></li>
                                <li class="color-purple" data-style="purple"></li>
                                <li class="color-grey" data-style="grey"></li>
                                <li class="color-white color-light" data-style="light"></li>
                            </ul>
                            <label>
                                <span>布局</span>
                                <select class="layout-option m-wrap small">
                                    <option value="fluid" selected>Fluid</option>
                                    <option value="boxed">Boxed</option>
                                </select>
                            </label>
                            <label>
                                <span>页头</span>
                                <select class="header-option m-wrap small">
                                    <option value="fixed" selected>Fixed</option>
                                    <option value="default">Default</option>
                                </select>
                            </label>
                            <label>
                                <span>侧边栏</span>
                                <select class="sidebar-option m-wrap small">
                                    <option value="fixed">Fixed</option>
                                    <option value="default" selected>Default</option>
                                </select>
                            </label>
                            <label>
                                <span>页脚</span>
                                <select class="footer-option m-wrap small">
                                    <option value="fixed">Fixed</option>
                                    <option value="default" selected>Default</option>
                                </select>
                            </label>
                        </div>
                    </div>

                    <!-- 开始页面的标题和面包屑-->
                    <h3 class="page-title">
                        仪表板
                        <small>统计和比较</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="index.html">首页</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">仪表板</a></li>
                    </ul>
                </div>
            </div>

            <div id="dashboard">
                <!--仪表数据的开始-->
                <div class="row-fluid">
                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                        <div class="dashboard-stat blue">
                            <div class="visual">
                                <i class="icon-comments"></i>
                            </div>
                            <div class="details">
                                <div class="number">1349</div>
                                <div class="desc">新的反馈</div>
                            </div>
                            <a class="more" href="#">
                                查看更多 <i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>

                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                        <div class="dashboard-stat green">
                            <div class="visual">
                                <i class="icon-shopping-cart"></i>
                            </div>
                            <div class="details">
                                <div class="number">549</div>
                                <div class="desc">新订单</div>
                            </div>
                            <a class="more" href="#">
                                查看更多<i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>

                    <div class="span3 responsive" data-tablet="span6  fix-offset" data-desktop="span3">
                        <div class="dashboard-stat purple">
                            <div class="visual">
                                <i class="icon-globe"></i>
                            </div>
                            <div class="details">
                                <div class="number">+89%</div>
                                <div class="desc">品牌知名度</div>
                            </div>
                            <a class="more" href="#">
                                查看更多<i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>

                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                        <div class="dashboard-stat yellow">
                            <div class="visual">
                                <i class="icon-bar-chart"></i>
                            </div>
                            <div class="details">
                                <div class="number">12,5M$</div>
                                <div class="desc">利润总额</div>
                            </div>
                            <a class="more" href="#">
                                查看更多<i class="m-icon-swapright m-icon-white"></i>
                            </a>
                        </div>
                    </div>
                </div>

                <!--正文-->
                <h1>qqq</h1>
                <!--换行div-->
                <div class="clearfix"></div>
                <h1>456</h1>


            </div>
        </div>
    </div>
</div>

<!-- 页脚-->
<div class="footer">
    <div class="footer-inner">2013 &copy; Metronic by keenthemes.</div>
    <div class="footer-tools">
        <span class="go-top">
        <i class="icon-angle-up"></i>
        </span>
    </div>
</div>

<!-- 开始Javascript（底部，加载javascript这将减少页面加载时间） -->
<!-- 核心插件 -->
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery-1.10.1.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery-migrate-1.2.1.min.js"
        type="text/javascript"></script>

<!-- 重要！前负荷jquery-ui-1.10.1.custom.min.js bootstrap.min.js修复引导提示冲突与jQuery UI工具提示 -->
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery-ui-1.10.1.custom.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/bootstrap/bootstrap.min.js"
        type="text/javascript"></script>

<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/common/template/js/excanvas.min.js"></script>
<script src="${pageContext.request.contextPath}/common/template/js/respond.min.js"></script>
<![endif]-->

<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.slimscroll.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.blockui.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.cookie.min.js"
        type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.uniform.min.js"
        type="text/javascript"></script>

<!-- 开始页面级插件 -->
<script src="${pageContext.request.contextPath}/common/template/js/jquery/jquery.pulsate.min.js"
        type="text/javascript"></script>

<!-- 开始页面级脚本 -->
<script src="${pageContext.request.contextPath}/common/template/js/app.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/index.js" type="text/javascript"></script>

<script>
    jQuery(document).ready(function () {
        App.init(); // initlayout and 核心插件
        Index.init();
        Index.initCalendar(); // 更改样式
    });
</script>

</body>
</html>


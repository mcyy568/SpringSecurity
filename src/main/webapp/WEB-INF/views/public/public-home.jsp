<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8"/>
    <title>首页</title>
    <c:import url="page-css.jsp"/>
</head>
<body class="page-header-fixed">

<c:import url="page-top.jsp"/>

<!-- 开始的容器 -->
<div class="page-container">
    <c:import url="page-left.jsp"/>

    <!-- 开始页 -->
    <div class="page-content">
        <!--开始页面容器-->
        <div class="container-fluid">
            <!-- 开始页面标题-->
            <div class="row-fluid">
                <div class="span12">
                    <%--页面风格--%>
                    <c:import url="page-style.jsp"/>

                    <h3 class="page-title">首页</h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="#">首页</a>
                        </li>
                    </ul>
                </div>
            </div>

            <%--正文内容--%>
            <div id="dashboard">
                <c:import url="${url}"/>
            </div>
        </div>
    </div>
</div>

<c:import url="page-js.jsp"/>

</body>
</html>


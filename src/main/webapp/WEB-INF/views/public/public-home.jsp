<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
    <meta charset="utf-8"/>
    <title>${title}</title>
    <c:import url="page-css.jsp"/>
</head>
<body class="page-header-fixed">

<c:import url="page-top.jsp"/>

<!-- 开始的容器 -->
<div class="page-container">
    <c:import url="page-left.jsp"/>
    <c:import url="page-js.jsp"/>
    <!-- 开始页 -->
    <div class="page-content">
        <!--开始页面容器-->
        <div class="container-fluid">
            <%--正文内容--%>
            <div id="dashboard">
                <input type="hidden" id="item_url" value="${pageContext.request.contextPath}${url}">
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

<script>
    $(function(){
        $.get($("#item_url").val(), function (result) {
            $("#dashboard").html(result);
        });
    });
    jQuery(document).ready(function () {
        App.init(); // initlayout and 核心插件
        Index.init();
        Index.initCalendar(); // 更改样式
    });
</script>

</body>
</html>


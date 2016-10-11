<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

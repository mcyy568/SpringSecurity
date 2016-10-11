<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="page-sidebar nav-collapse collapse">
    <!-- 开始的侧边栏菜单-->
    <ul class="page-sidebar-menu">
        <li>
            <div class="sidebar-toggler hidden-phone"></div>
        </li>

        <%--项目名--%>
        <input type="hidden" value="${pageContext.request.contextPath}" id="item-Only-Url">

        <c:forEach items="${appMenus}" var="menus" varStatus="status">
            <c:if test="${menus.parentid == 0}">
                <li class="start
                        <c:if test="${empty menus.appMenus}"> no-Child </c:if><%--没有子菜单添加样式点击用--%>
                        <c:if test="${menus.menuurl == url}"> active </c:if>
                        <c:forEach items="${menus.appMenus}" var="menus2">
                            <c:if test="${menus2.menuurl == url}"> active </c:if>
                        </c:forEach>"<%--默认选中--%>
                        <c:if test="${empty menus.appMenus}">text="${menus.menuurl}"</c:if>>
                    <a>
                        <i class="${menus.menucode}"></i>
                        <span class="title">${menus.menuname}</span>
                        <c:if test="${empty menus.appMenus}">
                            <span class="selected"></span>
                        </c:if>
                        <c:if test="${not empty menus.appMenus}">
                            <span class="arrow"></span>
                        </c:if>
                    </a>
                    <c:if test="${not empty menus.appMenus}"><%--menus.appMenus不等于null表示有子菜单--%>
                        <ul class="sub-menu"
                            <c:forEach items="${menus.appMenus}" var="menus2">
                                <c:if test="${menus2.menuurl == url}">style="display: block;"</c:if>
                            </c:forEach>>
                            <c:forEach items="${menus.appMenus}" var="menus2">
                                <li text="${menus2.menuurl}" class="<c:if test="${menus2.menuurl == url}">active</c:if>">
                                    <a>
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
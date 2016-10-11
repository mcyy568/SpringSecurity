<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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

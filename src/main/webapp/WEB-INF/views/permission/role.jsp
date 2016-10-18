<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%--d3插件展示角色权限的关系报表覆盖的样式--%>
<style>
    .node circle{fill:#fff;stroke:#4682b4;stroke-width:1.5px}
    .node{font:12px sans-serif}
    .link{fill:none;stroke:#ccc;stroke-width:1.5px}
</style>

<div class="row-fluid">
    <div class="span12">
        <c:import url="../public/page-style.jsp"/>
        <h3 class="page-title">角色管理</h3>
        <ul class="breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a href="#">系统管理</a>
                <i class="icon-angle-right"></i>
            </li>
            <li>
                <a href="#">角色管理</a>
            </li>
        </ul>
    </div>
</div>

<div class="row-fluid">
    <div class="span12">
        <div class="portlet box blue">
            <div class="portlet-title">
                <div class="caption"><i class="icon-edit"></i>角色列表</div>
                <div class="tools">
                    <%--ajax刷新列表--%>
                    <%--<a href="javascript:;" class="reload"></a>--%>
                </div>
            </div>
            <div class="portlet-body">
                <div class="clearfix">
                    <div class="btn-group">
                        <button id="sample_editable_1_new" class="btn green">
                            <i class="icon-plus"></i>&nbsp;
                            添加角色
                        </button>
                    </div>
                    <div class="btn-group pull-right">
                        <button class="btn dropdown-toggle" data-toggle="dropdown">工具 <i class="icon-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu pull-right">
                            <li><a href="#">打印</a></li>
                            <li><a href="#">导出PDF</a></li>
                            <li><a href="#">导出Excel</a></li>
                        </ul>
                    </div>
                </div>
                <table class="table table-striped table-hover table-bordered" id="sample_editable_1">
                    <thead>
                    <tr>
                        <th>角色名称</th>
                        <th>末次修改时间</th>
                        <th>是否启用</th>
                        <th>角色说明</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${appRoles}" var="roles">
                        <tr class="">
                            <td>${roles.name}</td>
                            <td><fmt:formatDate value="${roles.lastupdate}" pattern="yyyy年MM月dd日HH点mm分ss秒"/></td>
                            <td>
                                <c:if test="${roles.status == 1}">启用</c:if>
                                <c:if test="${roles.status == 0}">禁用</c:if>
                            </td>
                            <td class="center">${roles.descn}</td>
                            <td>
                                <button class="btn blue" type="button" name="checkRole" id="${roles.id}">
                                    <i class="icon-search"></i>
                                    查看
                                </button>
                                <button class="btn purple" type="button" name="editRole" id="${roles.id}"
                                        roleName="${roles.name}" status="${roles.status}" descn="${roles.descn}">
                                    <i class="icon-pencil"></i>
                                    编辑
                                </button>
                                <button class="btn red delete" type="button" name="deleteRole" id="${roles.id}" text="${roles.descn}">
                                    <i class="icon-trash icon-white"></i>
                                    删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<!-- 编辑角色 -->
<div class="modal fade" id="editRoleModal" tabindex="-1" role="dialog" aria-labelledby="editRoleModal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">添编辑角色</h4>
            </div>
            <div class="modal-body">
                <form action="#" class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label">角色</label>
                        <div class="controls">
                            <div class="input-group">
                                <input type="text" class="form-control" name="editRoleName" disabled title="">
                                <span class="help-inline"></span>
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">状态</label>
                        <div class="controls">
                            <div class="switch" id="statusDiv">
                                <%--<input type="checkbox" name="editCheckbox" checked title="状态">--%>
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">角色说明</label>
                        <div class="controls">
                            <div class="input-group">
                                <input type="text" placeholder="角色说明" class="form-control" name="editRoleDescn"/>
                                <span class="help-inline"></span>
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">设置权限</label>
                        <div class="controls">
                            <div class="widget-main padding-8" id="setPermissions">
                                <div id="tree2" class="tree tree-selectable2"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn blue edit-role-from" type="button">
                    <i class="icon-ok"></i>&nbsp;提交
                </button>
            </div>
        </div>
    </div>
</div>

<%--删除角色警告框--%>
<div class="modal fade bs-example-modal-sm" id="deleteRoleModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">警告</h4>
            </div>
            <div class="modal-body" id="deleteRoleBody"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button class="btn red delete" type="button" id="deleteRoleButton">
                    <i class="icon-trash icon-white"></i>&nbsp;确定
                </button>
            </div>
        </div>
    </div>
</div>

<%--查看角色详细信息--%>
<div class="modal fade" id="viewRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title">查看角色</h4>
            </div>
            <div class="modal-body" id="viewRoleTreeDiv"></div>
        </div>
    </div>
</div>


<!-- 添加角色 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"></button>
                <h4 class="modal-title" id="myModalLabel">添加角色</h4>
            </div>
            <div class="modal-body">
                <form action="#" class="form-horizontal">
                    <div class="control-group">
                        <label class="control-label">角色</label>
                        <div class="controls">
                            <div class="input-group">
                                <span class="help-inline">ROLE_</span>
                                <input type="text" class="form-control" name="rolename" placeholder="角色唯一标识"
                                       style="width: 158px;">
                                <span class="help-inline"></span>
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">状态</label>
                        <div class="controls">
                            <div class="switch">
                                <input type="checkbox" name="my-checkbox" checked title="状态" id="my-checkbox">
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">角色说明</label>
                        <div class="controls">
                            <div class="input-group">
                                <input type="text" placeholder="角色说明" class="form-control" name="roledescn"/>
                                <span class="help-inline"></span>
                            </div>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">设置权限</label>
                        <div class="controls">
                            <div class="widget-main padding-8">
                                <div id="tree1" class="tree tree-selectable"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button class="btn blue save-role-from" type="button">
                    <i class="icon-ok"></i>&nbsp;添加
                </button>
            </div>
        </div>
    </div>
</div>

<%--设置POST请求头防止POST过滤--%>
<input type="hidden" id="_csrf" content="${_csrf.token}"/>
<input type="hidden" id="_csrf_header" content="${_csrf.headerName}"/>

<%--开关插件--%>
<link href="${pageContext.request.contextPath}/common/template/css/bootstrap/bootstrap-switch.min.css" rel="stylesheet"
      id="bootstrapSwitch" type="text/css"/>
<script src="${pageContext.request.contextPath}/common/template/js/bootstrap/bootstrap-switch.min.js"
      id="bootstrapSwitchMin" type="text/javascript"></script>

<%--权限树插件--%>
<link href="${pageContext.request.contextPath}/common/template/css/ace/ace.min.css" rel="stylesheet"
        type="text/css"/>
<script src="${pageContext.request.contextPath}/common/template/js/fuelux.tree/fuelux.tree.min.js"
        id="fueluxTreeMin" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/ace/ace-elements.min.js"
        id="aceElementsMin" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/ace/ace.min.js"
        id="aceMin" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/common/template/js/fuelux.tree/fuelux.tree-sampledata.js"
        id="fueluxTreeSampledata" type="text/javascript"></script><%--加载数据--%>

<%--d3插件展示角色权限的关系报表--%>
<script src="${pageContext.request.contextPath}/common/template/js/d3.v3/d3.v3.min.js"
        id="d3V3Min" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/common/template/js/d3.v3/roleReport.js"
        id="roleReport" type="text/javascript"></script>

<%--页面js--%>
<script src="${pageContext.request.contextPath}/common/js/role.js" type="text/javascript"></script>

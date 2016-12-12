<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <spring:message code="message.users" var="users"/>
    <spring:message code="message.toggle.blocked" var="blocked"/>
    <spring:message code="message.toggle.unblocked" var="unblocked"/>
    <spring:message code="message.view" var="view"/>
    <spring:message code="message.title.search" var="search"/>
    <spring:message code="message.title.admin" var="admin"/>

    <title>${admin}</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/bootstrap-toggle.min.css" var="bootstrapToggleCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jquery"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/bootstrap-toggle.min.js" var="bootstrapToggleJs"/>
    <spring:url value="/static/core/js/user.view.js" var="userViewJs"/>
    <spring:url value="/static/core/js/user.ajax.js" var="userAjaxJs"/>

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${bootstrapToggleCss}" rel="stylesheet"/>
    <link href="${customPanelCss}" rel="stylesheet"/>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="${request.contextPath}/userHeader"></jsp:include>
<div class="container" style="margin-top: 80px;">
    <div class="panel panel-primary">
        <div class="col-md-12 panel-heading">
            <div class="col-md-4" style="margin-top: 10px;">
                <h3 class="panel-title">${users}</h3>
            </div>
            <div class="col-md-4" style="text-align: center">
                <div class="form-inline">
                    <a href="#" id="refresh-button" class="btn btn-link"><span class="glyphicon glyphicon-refresh" aria-hidden="true"></span></a>
                    <div class="form-group">
                        <input type="text" class="username-field form-control" name="username" placeholder="${search}">
                    </div>
                    <a href="#" id="search-by-username" type="submit" class="btn btn-warning btn-search">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tbody id="user-container">
                    <c:forEach items="${userList}" var="user">
                        <c:if test="${user.username ne pageContext.request.userPrincipal.name}">
                            <tr>
                                <td>
                                    <img src="${user.profile.imageUrl}" class="img-thumbnail" width="90" height="100"/>
                                </td>
                                <td>
                                    <h4>${user.username}</h4>
                                    <h4>${user.profile.email}</h4>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${user.enabled}">
                                            <input type="checkbox" checked data-username="${user.username}" data-on="${unblocked}" data-off="${blocked}" data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="checkbox" data-username="${user.username}" data-on="${unblocked}" data-off="${blocked}" data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <form action="/user" class="form-inline">
                                        <input type="hidden" name="username" value="${user.username}"/>
                                        <button type="submit" class="btn btn-warning">
                                            ${view} <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="panel-footer">
            <div id="paging-user-container" class="panel-paging" data-paging="2">
                <a class="more-paging paging-user" href="#">
                    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                </a>
                <a class="turn-paging paging-user" href="#">
                    <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
                </a>
            </div>
        </div>
    </div>
</div>
<script src="${jquery}"></script>
<script src="${bootstrapJs}"></script>
<script src="${bootstrapToggleJs}"></script>
<script src="${userAjaxJs}"></script>
<script src="${userViewJs}"></script>
</body>
</html>
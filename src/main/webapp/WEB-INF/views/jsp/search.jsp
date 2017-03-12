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
    <spring:message code="message.search.empty" var="emptySearch"/>

    <title>${search}</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/bootstrap-toggle.min.css" var="bootstrapToggleCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jquery"/>
    <spring:url value="/static/core/js/material.min.js" var="materialJs"/>
    <spring:url value="/static/core/js/ripples.min.js" var="ripplesJs"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/bootstrap-toggle.min.js" var="bootstrapToggleJs"/>
    <spring:url value="/static/core/js/user.view.js" var="userViewJs"/>
    <spring:url value="/static/core/js/user.ajax.js" var="userAjaxJs"/>
    <spring:url value="/static/core/js/search.validation.js" var="searchValidationJs"/>
    <spring:url value="/static/core/pictures/favicon.ico" var="favicon"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${bootstrapToggleCss}" rel="stylesheet"/>
    <%--<link href="${customPanelCss}" rel="stylesheet"/>--%>
    <link rel="shortcut icon" href="${favicon}" type="image/x-icon">
    <link rel="icon" href="${favicon}" type="image/x-icon">
    <!-- Material Design fonts -->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/icon?family=Material+Icons">

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">

    <!-- Bootstrap Material Design -->
    <link rel="stylesheet" type="text/css" href="/static/core/css/bootstrap-material-design.css">
    <link rel="stylesheet" type="text/css" href="/static/core/css/ripples.min.css">

</head>
<body>
<jsp:include page="user_header.jsp"/>
<div class="container" style="margin-top: 80px;">
    <div class="panel panel-info">
        <div class="col-md-12 panel-heading" style="padding: 0px;">
            <div class="col-md-4" style="margin-top: 17px;">
                <h3 class="panel-title" style="font-weight: bold;">${users}</h3>
            </div>
            <div class="col-md-6" style="text-align: center">
                <div class="form-inline">
                    <a href="#" style="margin: 0;" id="refresh-button" class="btn btn-primary"><span class="glyphicon glyphicon-refresh"
                                                                               aria-hidden="true"></span></a>
                    <div class="form-group" style="position: relative; margin: 0;">
                        <input type="text" class="username-field form-control" name="username" placeholder="${search}">
                        <div id="error-div" style="display: none; z-index: 100;">
                            <div class="alert alert-danger">
                                ${emptySearch}
                            </div>
                        </div>
                    </div>
                    <a href="#" style="margin: 0px;" id="search-by-username" type="submit" class="btn btn-primary btn-search">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <table class="table table-hover" style="margin-top: 51px;">
                <tbody id="user-container">
                <jsp:include page="userList.jsp"/>
                </tbody>
            </table>
        </div>
        <div class="panel-footer">
            <div id="paging-user-container" style="text-align: center;" class="panel-paging" data-paging="2">
                <a class="more-paging paging-user" style="margin-right: 15px;" href="#">
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
<script src="${ripplesJs}"></script>
<script src="${materialJs}"></script>
<script src="${bootstrapToggleJs}"></script>
<script src="${userAjaxJs}"></script>
<script src="${userViewJs}"></script>
<script src="${searchValidationJs}"></script>
<script>
    $.material.init();
</script>
</body>
</html>
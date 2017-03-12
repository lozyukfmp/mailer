<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="message.title.login"/></title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jquery"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/material.min.js" var="materialJs"/>
    <spring:url value="/static/core/js/ripples.min.js" var="ripplesJs"/>
    <spring:url value="/static/core/pictures/favicon.ico" var="favicon"/>

    <spring:message code="message.login.username" var="username"/>
    <spring:message code="message.login.password" var="password"/>
    <spring:message code="message.login" var="login"/>
    <spring:message code="message.modal_login" var="modalLogin"/>
    <spring:message code="message.register" var="register"/>
    <spring:message code="message.language" var="language"/>
    <spring:message code="message.russian" var="russian"/>
    <spring:message code="message.english" var="english"/>
    <spring:message code="message.go_to_welcome" var="goToWelcome"/>
    <spring:message code="message.successRegistration" var="successMessage"/>
    <spring:message code="message.error" var="errorMessage"/>
    <spring:message code="message.logout" var="logoutMessage"/>
    <spring:message code="message.blocked" var="blockedMessage"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
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
<div class="container" style="margin-top: 25px;">
    <div class="col-md-4 col-md-offset-4">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title" style="font-weight: bold;">${modalLogin}</h3>
            </div>
            <div class="panel-body">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${errorMessage}</strong>
                    </div>
                </c:if>
                <c:if test="${not empty logout}">
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${logoutMessage}</strong>
                    </div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${successMessage}</strong>
                    </div>
                </c:if>
                <c:if test="${not empty blocked}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${blockedMessage}</strong>
                    </div>
                </c:if>
                <c:url value="/login" var="loginUrl"/>
                <form:form action="${loginUrl}" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="${username}" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="${password}" required/>
                    </div>
                    <div class="form-group">
                        <button type="submit" name="login" class="btn btn-raised btn-block btn-info">
                            <h3 class="panel-title" style="font-weight: bolder;">${login}</h3>
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="panel-footer">
                    <a class="btn btn-info btn-group btn-group-justified" href="${pageContext.request.contextPath}/welcome">
                            ${goToWelcome}
                    </a>
                    <a class="btn btn-info btn-group btn-group-justified" href="${pageContext.request.contextPath}/registration-page">
                            ${register}
                    </a>
                    <div class="btn-group btn-group-justified">
                        <a href="#" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            ${language}
                            <span class="caret"></span>
                            <div class="ripple-container"></div>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}?lang=ru_RU">${russian}</a></li>
                            <li><a href="${pageContext.request.contextPath}?lang=en">${english}</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${jquery}"></script>
<script src="${bootstrapJs}"></script>
<script src="${ripplesJs}"></script>
<script src="${materialJs}"></script>
<script>
    $.material.init();
</script>
</body>
</html>
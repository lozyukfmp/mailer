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
    <title><spring:message code="message.title.welcome"/></title>

    <!-- .css and .js -->
    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/material.min.js" var="materialJs"/>
    <spring:url value="/static/core/js/ripples.min.js" var="ripplesJs"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/pictures/favicon.ico" var="favicon"/>
    <spring:url value="/static/core/css/bootstrap-material-design.css" var="materialCss"/>
    <spring:url value="/static/core/css/ripples.min.css" var="ripplesCss"/>

    <!-- localization -->
    <spring:message code="message.welcome" var="welcome"/>
    <spring:message code="message.login.username" var="username"/>
    <spring:message code="message.login.password" var="password"/>
    <spring:message code="message.login" var="login"/>
    <spring:message code="message.modal_login" var="modalLogin"/>
    <spring:message code="message.register" var="register"/>
    <spring:message code="message.language" var="language"/>
    <spring:message code="message.russian" var="russian"/>
    <spring:message code="message.english" var="english"/>
    <spring:message code="message.welcome.prefix" var="prefix"/>
    <spring:message code="message.welcome.postfix" var="postfix"/>

    <link rel="shortcut icon" href="${favicon}" type="image/x-icon">
    <link rel="icon" href="${favicon}" type="image/x-icon">
    <!-- Material Design fonts -->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/icon?family=Material+Icons">

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">
    <link href="${customPanelCss}" rel="stylesheet"/>

    <!-- Bootstrap Material Design -->
    <link rel="stylesheet" type="text/css" href="${materialCss}">
    <link rel="stylesheet" type="text/css" href="${ripplesCss}">

</head>
<body>
<div class="container" style="margin-top: 25px;">
    <div class="jumbotron">
        <div class="container">
            <h1 style="color: #2780e3;font-size: 100px;font-weight: bolder;">${welcome}</h1>
            <p>${prefix}<br><strong>${welcome}</strong> ${postfix}</p>
            <div class="btn-group">
                <a href="#" class="btn btn-primary btn-raised">${language}<div class="ripple-container"></div></a>
                <a href="#" class="btn btn-primary btn-raised dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="caret"></span><div class="ripple-container"></div></a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}?lang=ru_RU">${russian}</a></li>
                    <li><a href="${pageContext.request.contextPath}?lang=en">${english}</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/login-page" class="btn btn-raised btn-info">
                    ${login}
                </a>
                <a href="${pageContext.request.contextPath}/registration-page" class="btn btn-raised btn-warning">
                    ${register}
                </a>
            </div>
        </div>
    </div>
</div>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${ripplesJs}"></script>
<script src="${materialJs}"></script>
<script>
    $.material.init();
</script>
</body>
</html>
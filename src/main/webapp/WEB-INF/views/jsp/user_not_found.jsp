<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <spring:message code="message.title.userNotFound" var="userNotFoundTitle"/>
    <spring:message code="message.userNotFound" var="userNotFoundMessage"/>
    <spring:message code="message.title.user" var="userPage"/>

    <title>${userPage}</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/search.validation.js" var="searchValidationJs"/>
    <spring:url value="/static/core/pictures/favicon.ico" var="favicon"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${customPanelCss}" rel="stylesheet"/>
    <link rel="shortcut icon" href="${favicon}" type="image/x-icon">
    <link rel="icon" href="${favicon}" type="image/x-icon">
</head>
<body>
<security:authorize access="hasRole('ROLE_USER')">
    <jsp:include page="user_header.jsp"/>
</security:authorize>
<div class="container" style="margin-top: 85px;">
    <div class="jumbotron">
        <h1>${userNotFoundTitle}</h1>
        <p>${userNotFoundMessage} : ${username}</p>
    </div>
</div>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${searchValidationJs}"></script>
</body>
</html>

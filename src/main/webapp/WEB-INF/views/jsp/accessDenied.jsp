<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:message code="message.title.user" var="userPage"/>

    <title>${userPage}</title>

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet"/>

</head>
<body>
<h2>Access denied page</h2>
<c:url value="/admin" var="adminPage"/>
<c:url value="/user" var="userPage"/>
<security:authorize access="hasRole('ROLE_ADMIN')">
    <a href="${adminPage}">Back</a>
</security:authorize>
<security:authorize access="hasRole('ROLE_USER')">
    <a href="${userPage}">Back</a>
</security:authorize>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>

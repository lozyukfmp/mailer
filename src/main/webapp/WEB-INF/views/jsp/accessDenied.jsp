<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User page</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet"/>


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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

Failed URL: ${url}
Exception: ${exception.message}
<c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
</c:forEach>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</body>
</html>

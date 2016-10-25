<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration page</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/css/login.css" var="loginCss" />
    <spring:url value="/static/core/css/container.css" var="customContainerCss" />
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs" />

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${loginCss}" rel="stylesheet" />
    <link href="${customContainerCss}" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="col-md-4 col-md-offset-4">
        <div class="loginmodal-container">
            <h1>Create new account</h1><br>
            <c:if test="${not empty usernameExist}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${usernameExist}</strong>
                </div>
            </c:if>
            <c:url value="/registration" var="registrationUrl" />
            <form:form action="${registration}" modelAttribute="user" method="POST" enctype="utf8">
                    <form:input path="username" value="" />
                    <form:errors path="username" element="div"/>
                    <form:input path="password" value="" />
                    <form:errors path="password" element="div" />
                    <input type="submit">Register</input>
            </form:form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>

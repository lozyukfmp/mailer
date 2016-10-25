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
    <title>Login page</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/css/login.css" var="loginCss" />
<<<<<<< HEAD
=======
    <spring:url value="/static/core/css/container.css" var="customContainerCss" />
>>>>>>> 55a369f74a4353a5bfd59fbe03abdd95434eec67
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs" />

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${loginCss}" rel="stylesheet" />
<<<<<<< HEAD
=======
    <link href="${customContainerCss}" rel="stylesheet" />
>>>>>>> 55a369f74a4353a5bfd59fbe03abdd95434eec67

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container" style="margin-top: 100px;">
    <div class="col-md-4 col-md-offset-4">
        <div class="loginmodal-container">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${error}</strong>
                </div>
            </c:if>
            <c:if test="${not empty logout}">
                <div class="alert alert-info">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${logout}</strong>
                </div>
            </c:if>
            <h1>Login to Your Account</h1><br>
            <c:url value="/login" var="loginUrl" />
            <form:form action="${loginUrl}" method="post">
                <input type="text" name="username" placeholder="Username">
                <input type="password" name="password" placeholder="Password">
                <input type="submit" name="login" class="login loginmodal-submit" value="Login">
                <a href="/welcome">Go to welcome page</a>
            </form:form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Admin page</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs" />

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet" />


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Mailer</a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <c:url value="/j_spring_security_logout" var="logoutUrl" />
            <form action="${logoutUrl}" method="post">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                    Logout
                </button>
            </form>
        </ul>
    </div>
</nav>
<h2>Admin page</h2>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>Welcome : ${pageContext.request.userPrincipal.name}</h2>
</c:if>

<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</body>
</html>
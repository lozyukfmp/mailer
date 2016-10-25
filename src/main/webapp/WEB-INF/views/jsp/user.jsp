<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User page</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/css/container.css" var="customContainerStyle" />
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs" />

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${customContainerStyle}" rel="stylesheet" />


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <!-- Static navbar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        Welcome : ${pageContext.request.userPrincipal.name}
                    </c:if>
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <c:url value="/logout" var="logoutUrl" />
                        <a href="${logoutUrl}">
                            <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            Logout
                        </a>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
    <h2>User page</h2>
</div>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</body>
</html>
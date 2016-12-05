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
    <title><spring:message code="message.title.welcome"/></title>

    <!-- .css and .js -->
    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/css/custom.container.css" var="customContainerCss" />
    <spring:url value="/static/core/css/welcome.css" var="welcomeCss" />
    <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />

    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs" />

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

    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${loginCss}" rel="stylesheet" />
    <link href="${customContainerCss}" rel="stylesheet" />
    <link href="${languageDropdown}" rel="stylesheet" />
    <link href="${welcomeCss}" rel="stylesheet" />

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <div class="container">
            <div class="welcome-dropdown dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">
                    ${language}
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="?lang=ru_RU">${russian}</a></li>
                    <li><a href="?lang=en">${english}</a></li>
                </ul>
            </div>
            <h1>${welcome}</h1>
            <div class="btn-group">
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#login-modal">
                    ${login}
                </button>
                <a href="${pageContext.request.contextPath}/registration-page" class="btn btn-primary btn-lg">
                    ${register}
                </a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width: 400px;">
        <div class="custom-container login-container">
            <h1>${modalLogin}</h1><br>
            <c:url value="/login" var="loginUrl" />
            <form:form action="${loginUrl}" method="post">
                <input type="text" name="username" placeholder="${username}" required>
                <input type="password" name="password" placeholder="${password}" required>
                <input type="submit" name="login" class="login-submit" value="${login}">
                <a href="${pageContext.request.contextPath}/registration-page">${register}</a>
            </form:form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>
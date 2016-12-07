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
    <%--<spring:url value="/static/core/css/custom.container.css" var="customContainerCss" />
    <spring:url value="/static/core/css/welcome.css" var="welcomeCss" />
    <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />--%>

    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>

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

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${loginCss}" rel="stylesheet"/>
    <link href="${customContainerCss}" rel="stylesheet"/>
    <link href="${languageDropdown}" rel="stylesheet"/>
    <link href="${welcomeCss}" rel="stylesheet"/>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container" style="margin-top: 100px;">
    <div class="jumbotron">
        <div class="container">
            <h1>${welcome}</h1>
            <div class="btn-group">
                <a href="#" class="btn btn-primary">${language}</a>
                <a href="#" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"><span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}?lang=ru_RU">${russian}</a></li>
                    <li><a href="${pageContext.request.contextPath}?lang=en">${english}</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/login-page" class="btn btn-info">
                    ${login}
                </a>
                <a href="${pageContext.request.contextPath}/registration-page" class="btn btn-primary">
                    ${register}
                </a>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>
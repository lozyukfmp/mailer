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
    <title><spring:message code="message.title.login"/></title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <%--<spring:url value="/static/core/css/custom.container.css" var="customContainerCss" />
    <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />--%>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>

    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>

    <!-- localization -->
    <spring:message code="message.login.username" var="username"/>
    <spring:message code="message.login.password" var="password"/>
    <spring:message code="message.login" var="login"/>
    <spring:message code="message.modal_login" var="modalLogin"/>
    <spring:message code="message.register" var="register"/>
    <spring:message code="message.language" var="language"/>
    <spring:message code="message.russian" var="russian"/>
    <spring:message code="message.english" var="english"/>
    <spring:message code="message.go_to_welcome" var="goToWelcome"/>
    <spring:message code="message.successRegistration" var="successMessage"/>
    <spring:message code="message.error" var="errorMessage"/>
    <spring:message code="message.logout" var="logoutMessage"/>

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${customPanelCss}" rel="stylesheet"/>
    <%-- <link href="${customContainerCss}" rel="stylesheet" />
     <link href="${languageDropdown}" rel="stylesheet" />--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container" style="margin-top: 80px;">
    <div class="col-md-4 col-md-offset-4">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">${modalLogin}</h3>
            </div>
            <div class="panel-body">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${errorMessage}</strong>
                    </div>
                </c:if>
                <c:if test="${not empty logout}">
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${logoutMessage}</strong>
                    </div>
                </c:if>
                <c:if test="${not empty success}">
                    <div class="alert alert-success">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${successMessage}</strong>
                    </div>
                </c:if>
                <c:url value="/login" var="loginUrl"/>
                <form:form action="${loginUrl}" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="${username}" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="${password}" required/>
                    </div>
                    <div class="form-group">
                        <button type="submit" name="login" class="btn btn-block btn-primary">
                            <h3 class="panel-title" style="font-weight: bolder;">${login}</h3>
                        </button>
                    </div>
                </form:form>
            </div>
            <div class="panel-footer">
                <div class="btn-group btn-group-justified">
                    <div class="col-sm-4">
                        <a href="${pageContext.request.contextPath}/welcome">
                            ${goToWelcome}
                        </a>
                    </div>
                    <div class="col-sm-4">
                        <a href="${pageContext.request.contextPath}/registration-page">
                            ${register}
                        </a>
                    </div>
                    <div class="col-sm-4">
                        <div class="btn-group">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                ${language}
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}?lang=ru_RU">${russian}</a></li>
                                <li><a href="${pageContext.request.contextPath}?lang=en">${english}</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/css/container.css" var="customContainerStyle" />
    <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs" />

    <!-- localization -->
    <spring:message code="message.language" var="language"/>
    <spring:message code="message.russian" var="russian"/>
    <spring:message code="message.english" var="english"/>
    <spring:message code="message.user.logout" var="logout"/>
    <spring:message code="message.user.welcome" var="welcome"/>
    <spring:message code="message.profile" var="profile"/>
    <spring:message code="message.go_to_main" var="home"/>
</head>
<body>
<div class="container">
    <!-- Static navbar -->
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">
                    <c:if test="${pageContext.request.userPrincipal.name != null}">
                        ${welcome} : ${pageContext.request.userPrincipal.name}
                    </c:if>
                </a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/user/profile">${profile}</a></li>
                    <li><a href="/user">${home}</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="form-dropdown dropdown" style="border-width: 0px;padding: 15px;">
                        <a class="btn btn-link dropdown-toggle" type="button" data-toggle="dropdown">
                            ${language}
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="?lang=ru_RU">${russian}</a></li>
                            <li><a href="?lang=en">${english}</a></li>
                        </ul>
                    </li>
                    <li>
                        <c:url value="/logout" var="logoutUrl" />
                        <a href="${logoutUrl}">
                            <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            ${logout}
                        </a>
                    </li>
                </ul>
            </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
    </nav>
</div>
</body>
</html>

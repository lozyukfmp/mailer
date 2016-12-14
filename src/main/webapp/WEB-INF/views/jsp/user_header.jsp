<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:message code="message.language" var="language"/>
<spring:message code="message.russian" var="russian"/>
<spring:message code="message.english" var="english"/>
<spring:message code="message.user.logout" var="logout"/>
<spring:message code="message.user.welcome" var="welcome"/>
<spring:message code="message.profile" var="profile"/>
<spring:message code="message.go_to_main" var="home"/>
<spring:message code="message.title.search" var="search"/>
<spring:message code="message.search.empty" var="emptySearch"/>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    ${pageContext.request.userPrincipal.name}
                </c:if>
            </a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <security:authorize access="hasRole('ROLE_USER')">
                    <li><a href="${pageContext.request.contextPath}/profile">${profile}</a></li>
                    <li><a href="${pageContext.request.contextPath}/user">${home}</a></li>
                </security:authorize>
                <security:authorize access="hasRole('ROLE_ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin">${home}</a></li>
                </security:authorize>
            </ul>
            <security:authorize access="hasRole('ROLE_USER')">
                <form action="user" style="position: relative;" class="navbar-form navbar-left">
                    <div class="form-group">
                        <input id="username-field" type="text" class="form-control" name="username" placeholder="${search}">
                    </div>
                    <button type="submit" class="btn btn-warning btn-search">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    </button>
                    <div id="error-div" style="display: none;">
                        <div class="alert alert-danger">
                                ${emptySearch}
                        </div>
                    </div>
                </form>
            </security:authorize>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" type="button" data-toggle="dropdown">
                        ${language}
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="?lang=ru_RU">${russian}</a></li>
                        <li><a href="?lang=en">${english}</a></li>
                    </ul>
                </li>
                <li>
                    <c:url value="/logout" var="logoutUrl"/>
                    <a href="${logoutUrl}">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                        ${logout}
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>

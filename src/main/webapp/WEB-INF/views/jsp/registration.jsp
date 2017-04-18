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
    <title><spring:message code="message.title.registration"/></title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/js/material.min.js" var="materialJs"/>
    <spring:url value="/static/core/js/ripples.min.js" var="ripplesJs"/>
    <spring:url value="/static/core/pictures/favicon.ico" var="favicon"/>
    <spring:url value="/static/core/css/bootstrap-material-design.css" var="materialCss"/>
    <spring:url value="/static/core/css/ripples.min.css" var="ripplesCss"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link rel="shortcut icon" href="${favicon}" type="image/x-icon">
    <link rel="icon" href="${favicon}" type="image/x-icon">
    <!-- Material Design fonts -->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/icon?family=Material+Icons">

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">

    <!-- Bootstrap Material Design -->
    <link rel="stylesheet" type="text/css" href="${materialCss}">
    <link rel="stylesheet" type="text/css" href="${ripplesCss}">

    <spring:message code="message.login.username" var="username"/>
    <spring:message code="message.login.password" var="password"/>
    <spring:message code="message.registration.confirm" var="confirm"/>
    <spring:message code="message.registration.email" var="email"/>
    <spring:message code="message.registration.firstname" var="firstname"/>
    <spring:message code="message.registration.secondname" var="secondname"/>
    <spring:message code="message.registration.thirdname" var="thirdname"/>
    <spring:message code="message.login" var="login"/>
    <spring:message code="message.modal_login" var="modalLogin"/>
    <spring:message code="message.register" var="register"/>
    <spring:message code="message.language" var="language"/>
    <spring:message code="message.russian" var="russian"/>
    <spring:message code="message.english" var="english"/>
    <spring:message code="message.go_to_welcome" var="goToWelcome"/>
    <spring:message code="message.user_exist" var="userExistMessage"/>
    <spring:message code="message.registration" var="registration"/>
</head>
<body>
<div class="container" style="margin-top: 25px;">
    <div class="col-sm-6 col-sm-offset-3">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title" style="font-weight: bold;">${registration}</h3>
            </div>
            <div class="panel-body">
                <form action="register" method="POST" enctype="utf-8">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="col-sm-6">
                        <c:if test="${not empty usernameExist}">
                            <div class="alert alert-danger">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>${userExistMessage}</strong>
                            </div>
                        </c:if>
                        <spring:bind path="user.username">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <c:forEach items="${status.errorMessages}" var="error">
                                        <p class="text-danger"><c:out value="${error}"/></p>
                                    </c:forEach>
                                    <div class="form-group has-error">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${username}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${username}">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </spring:bind>
                        <spring:bind path="user">
                            <c:if test="${status.error}">
                                <c:forEach items="${status.errorMessages}" var="error">
                                    <p class="text-danger"><c:out value="${error}"/></p>
                                </c:forEach>
                            </c:if>
                        </spring:bind>
                        <spring:bind path="user.password">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <c:forEach items="${status.errorMessages}" var="error">
                                        <p class="text-danger"><c:out value="${error}"/></p>
                                    </c:forEach>
                                    <div class="form-group has-error">
                                        <input type="password"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${password}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input type="password"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${password}">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </spring:bind>
                        <spring:bind path="user.confirmPassword">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <c:forEach items="${status.errorMessages}" var="error">
                                        <p class="text-danger"><c:out value="${error}"/></p>
                                    </c:forEach>
                                    <div class="form-group has-error">
                                        <input type="password"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${confirm}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input type="password"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${confirm}">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </spring:bind>
                    </div>
                    <div class="col-sm-6">
                        <spring:bind path="userProfile.email">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <c:forEach items="${status.errorMessages}" var="error">
                                        <p class="text-danger"><c:out value="${error}"/></p>
                                    </c:forEach>
                                    <div class="form-group has-error">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${email}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${email}">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </spring:bind>
                        <spring:bind path="userProfile.firstName">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <c:forEach items="${status.errorMessages}" var="error">
                                        <p class="text-danger"><c:out value="${error}"/></p>
                                    </c:forEach>
                                    <div class="form-group has-error">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${firstname}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${firstname}">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </spring:bind>
                        <spring:bind path="userProfile.secondName">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <c:forEach items="${status.errorMessages}" var="error">
                                        <p class="text-danger"><c:out value="${error}"/></p>
                                    </c:forEach>
                                    <div class="form-group has-error">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${secondname}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${secondname}">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </spring:bind>
                        <spring:bind path="userProfile.thirdName">
                            <c:choose>
                                <c:when test="${status.error}">
                                    <c:forEach items="${status.errorMessages}" var="error">
                                        <p class="text-danger"><c:out value="${error}"/></p>
                                    </c:forEach>
                                    <div class="form-group has-error">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${thirdname}">
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <input type="text"
                                               class="form-control"
                                               value="<c:out value="${status.value}"/>"
                                               name="<c:out value="${status.expression}"/>"
                                               placeholder="${thirdname}">
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </spring:bind>
                    </div>
                    <div class="col-sm-6 col-sm-offset-6">
                        <button type="submit" class="btn btn-block btn-info btn-raised" style="padding: 8px 0px;">
                            <h3 class="panel-title" style="font-weight: bolder;">${register}</h3>
                        </button>
                    </div>
                </form>
            </div>
            <div class="panel-footer">
                <a class="btn btn-info btn-group btn-group-justified" href="${pageContext.request.contextPath}/welcome">
                    ${goToWelcome}
                </a>
                <a class="btn btn-info btn-group btn-group-justified" href="${pageContext.request.contextPath}/login-page">
                    ${login}
                </a>
                <div class="btn-group btn-group-justified">
                    <a href="#" class="btn btn-info dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                        ${language}
                        <span class="caret"></span>
                        <div class="ripple-container"></div>
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
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${ripplesJs}"></script>
<script src="${materialJs}"></script>
<script>
    $.material.init();
</script>
</body>
</html>

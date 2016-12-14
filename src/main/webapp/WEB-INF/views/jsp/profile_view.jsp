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
    <title><spring:message code="message.profile"/></title>

    <!--Styles-->
    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/fileinput.min.css" var="fileInputCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>

    <!--Javascript-->
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/fileinput.min.js" var="fileInputJs"/>
    <spring:url value="/static/core/js/fileinput.custom.js" var="fileInputCustomJs"/>
    <spring:url value="/static/core/js/userProfile.ajax.js" var="userProfileAjaxJs"/>
    <spring:url value="/static/core/js/userProfile.view.js" var="userProfileViewJs"/>
    <spring:url value="/static/core/js/search.validation.js" var="searchValidationJs"/>
    <spring:url value="/static/core/pictures/favicon.ico" var="favicon"/>

    <!-- Styles -->
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${fileInputCss}" rel="stylesheet"/>
    <link href="${customPanelCss}" rel="stylesheet"/>
    <link rel="shortcut icon" href="${favicon}" type="image/x-icon">
    <link rel="icon" href="${favicon}" type="image/x-icon">

    <!-- localization -->
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
    <spring:message code="message.profile" var="profile"/>
    <spring:message code="message.go_to_main" var="main"/>
    <spring:message code="message.user.welcome" var="welcome"/>
    <spring:message code="message.user.logout" var="logout"/>
    <spring:message code="message.user.welcome" var="welcome"/>
    <spring:message code="message.profile" var="profile"/>
    <spring:message code="message.go_to_main" var="home"/>
    <spring:message code="message.change" var="change"/>
    <spring:message code="message.successProfileChange" var="profileChange"/>
    <spring:message code="message.title.photo" var="photo"/>
</head>
<body>
<jsp:include page="user_header.jsp"></jsp:include>
<div class="row" style="margin: 80px 10px;">
    <div class="col-md-4">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">${photo}</h3>
            </div>
            <div class="panel-body">
                <form enctype="multipart/form-data">
                    <input id="user-image" name="userImage" type="file" class="file-loading">
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">${profile}</h3>
            </div>
            <div class="panel-body">
                <c:if test="${not empty successProfileChange}">
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>${profileChange}</strong>
                    </div>
                </c:if>
                <form:form action="${profileUrl}" modelAttribute="userProfile" method="POST" enctype="utf8">

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
                    <div class="form-group">
                        <button type="submit" class="btn btn-block btn-primary">
                            <h3 class="panel-title" style="font-weight: bolder;">${change}</h3>
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${fileInputJs}"></script>
<script src="${fileInputCustomJs}"></script>
<script src="${userProfileAjaxJs}"></script>
<script src="${userProfileViewJs}"></script>
<script src="${searchValidationJs}"></script>
</body>
</html>

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
    <title><spring:message code="message.profile"/></title>

    <!--Styles-->
    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/css/fileinput.min.css" var="fileInputCss" />
    <spring:url value="/static/core/css/custom.container.css" var="customContainerCss" />
    <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />

    <!--Javascript-->
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/static/core/js/fileinput.min.js" var="fileInputJs" />
    <spring:url value="/static/core/js/fileinput.custom.js" var="fileInputCustomJs" />
    <spring:url value="/static/core/js/userProfile.ajax.js" var="userProfileAjaxJs" />
    <spring:url value="/static/core/js/userProfile.view.js" var="userProfileViewJs" />

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${fileInputCss}" rel="stylesheet" />
    <link href="${customContainerCss}" rel="stylesheet" />
    <link href="${languageDropdown}" rel="stylesheet" />

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

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="${request.contextPath}/userHeader"></jsp:include>
<div class="container">
    <div class="col-md-4">
        <div class="custom-container">
            <h1>${photo}</h1><br>
            <form enctype="multipart/form-data">
                <input id="user-image" name="userImage" type="file" class="file-loading">
            </form>
        </div>
    </div>
    <div class="col-md-4">
        <div class="custom-container">
            <h1>${profile}</h1><br>
            <c:if test="${not empty successProfileChange}">
                <div class="alert alert-info">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>${profileChange}</strong>
                </div>
            </c:if>
            <form:form action="${profileUrl}" modelAttribute="userProfile" method="POST" enctype="utf8">
                <form:errors path="email" element="div" cssClass="error-div"/>
                <form:input type="text" path="email" placeholder="${email}"/>
                <form:errors path="firstName" element="div" cssClass="error-div"/>
                <form:input type="text" path="firstName" placeholder="${firstname}"/>
                <form:errors path="secondName" element="div" cssClass="error-div"/>
                <form:input type="text" path="secondName" placeholder="${secondname}"/>
                <form:errors path="thirdName" element="div" cssClass="error-div"/>
                <form:input type="text" path="thirdName" placeholder="${thirdname}"/>
                <input type="submit" class="login-submit" value="${change}"/>
            </form:form>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="${bootstrapJs}"></script>
<script src="${fileInputJs}"></script>
<script src="${fileInputCustomJs}"></script>
<script src="${userProfileAjaxJs}"></script>
<script src="${userProfileViewJs}"></script>
</body>
</html>

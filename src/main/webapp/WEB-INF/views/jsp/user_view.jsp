<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <spring:message code="message.title.comments" var="comments"/>
    <spring:message code="message.title.posts" var="posts"/>
    <spring:message code="message.title.photo" var="photo"/>
    <spring:message code="message.title.personal" var="personal"/>
    <spring:message code="message.title.control" var="control"/>
    <spring:message code="message.title.message" var="messageTitle"/>
    <spring:message code="message.title.comment" var="comment"/>
    <spring:message code="message.title.search" var="search"/>
    <spring:message code="message.title.user" var="userPage"/>
    <spring:message code="message.registration.email" var="email"/>
    <spring:message code="message.registration.firstname" var="firstname"/>
    <spring:message code="message.registration.secondname" var="secondname"/>
    <spring:message code="message.registration.thirdname" var="thirdname"/>
    <spring:message code="message.post.send" var="send"/>

    <title>${userPage}</title>

    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/fileinput.min.css" var="fileInputCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>

    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/js/material.min.js" var="materialJs"/>
    <spring:url value="/static/core/js/ripples.min.js" var="ripplesJs"/>
    <spring:url value="/static/core/js/fileinput.min.js" var="fileInputJs"/>
    <spring:url value="/static/core/js/fileinput.custom.js" var="fileInputCustomJs"/>
    <spring:url value="/static/core/js/message.view.js" var="messageViewJs"/>
    <spring:url value="/static/core/js/message.ajax.js" var="messageAjaxJs"/>
    <spring:url value="/static/core/js/comment.ajax.js" var="commentAjaxJs"/>
    <spring:url value="/static/core/js/comment.view.js" var="commentViewJs"/>
    <spring:url value="/static/core/js/search.validation.js" var="searchValidationJs"/>
    <spring:url value="/static/core/pictures/favicon.ico" var="favicon"/>

    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${fileInputCss}" rel="stylesheet"/>
    <link rel="shortcut icon" href="${favicon}" type="image/x-icon">
    <link rel="icon" href="${favicon}" type="image/x-icon">
    <!-- Material Design fonts -->
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Roboto:300,400,500,700">
    <link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/icon?family=Material+Icons">

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="${bootstrapCss}">

    <!-- Bootstrap Material Design -->
    <link rel="stylesheet" type="text/css" href="/static/core/css/bootstrap-material-design.css">
    <link rel="stylesheet" type="text/css" href="/static/core/css/ripples.min.css">
</head>
<body>
<jsp:include page="user_header.jsp"/>
<div class="row" style="margin: 80px 10px;">
    <div class="col-md-3">
        <div class="panel panel-info panel-image">
            <div class="panel-heading">
                <h3 class="panel-title" style="font-weight: bold;">${profile.username}</h3>
            </div>
            <div class="panel-body user-image" data-username="${profile.username}">
                <img src="${profile.imageUrl}" class="img-thumbnail" width="100%" height="300"/>
            </div>
        </div>
        <div class="panel-group" id="accordion">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h4 class="panel-title" style="font-weight: bold;">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                            ${personal}</a>
                    </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse in">
                    <div class="panel-body" style="border-top: none;">
                        <table class="table table-striped table-hover ">
                            <tbody>
                            <tr>
                                <td>${firstname}:</td>
                                <td>${profile.firstName}</td>
                            </tr>
                            <tr>
                                <td>${secondname}:</td>
                                <td>${profile.secondName}</td>
                            </tr>
                            <tr>
                                <td>${thirdname}:</td>
                                <td>${profile.thirdName}</td>
                            </tr>
                            <tr>
                                <td>${email}:</td>
                                <td>${profile.email}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${profile.username == pageContext.request.userPrincipal.name}">
            <button id="view-create-message-modal-button"
                    class="btn btn-block btn-info btn-raised">
                    ${send} <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
            </button>
        </c:if>
    </div>
    <div class="col-md-4">
        <div class="panel panel-info">
            <div class="panel-heading">
                <h3 class="panel-title" style="font-weight: bold;">${posts}</h3>
            </div>
            <div id="message-container" class="panel-body">
                <jsp:include page="messageList.jsp"/>
            </div>
            <div class="panel-footer" style="padding: 10px 15px;">
                <div id="paging-message-container" style="text-align: center;" class="panel-paging" data-paging="2">
                    <a class="more-paging paging-message" style="margin-right: 15px;" href="#">
                        <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                    </a>
                    <a class="turn-paging paging-message" href="#">
                        <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="create-message-modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-body">
                <form enctype="multipart/form-data" name="message-form">
                    <div class="form-group">
                        <div id="image-error"></div>
                        <label>${photo}:</label>
                        <input id="post-image" name="postImage" type="file" class="file-loading">
                    </div>
                    <div class="form-group">
                        <div id="post-error"></div>
                        <label for="message-text">${messageTitle}:</label>
                        <textarea id="message-text" class="form-control" rows="5"></textarea>
                    </div>
                    <div class="form-group">
                        <a href="#" id="create-message-button" class="btn btn-block btn-info btn-raised">
                            <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="view-message-modal" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width: 400px;">
        <div class="modal-content">
            <div class="modal-body">
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="create-comment-modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="margin-top: 150px;width: 300px;">
        <div class="modal-content">
            <div class="modal-body">
                <div id="comment-error"></div>
                <label for="create-comment-text">${comment}:</label>
                <textarea id="create-comment-text" class="form-control" rows="5"></textarea>
                <br>
                <button id="create-comment-button" class="btn btn-block btn-info btn-raised">
                    <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
                </button>
            </div>
        </div>
    </div>
</div>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${ripplesJs}"></script>
<script src="${materialJs}"></script>
<script src="${fileInputJs}"></script>
<script src="${fileInputCustomJs}"></script>
<script src="${messageAjaxJs}"></script>
<script src="${commentAjaxJs}"></script>
<script src="${messageViewJs}"></script>
<script src="${commentViewJs}"></script>
<script src="${searchValidationJs}"></script>
<script>
    $.material.init();
</script>
</body>
</html>
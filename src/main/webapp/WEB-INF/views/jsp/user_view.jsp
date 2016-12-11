<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

    <title>${userPage}</title>

    <!-- Styles -->
    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <spring:url value="/static/core/css/fileinput.min.css" var="fileInputCss"/>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss"/>

    <!-- Javascript -->
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/js/fileinput.min.js" var="fileInputJs"/>
    <spring:url value="/static/core/js/fileinput.custom.js" var="fileInputCustomJs"/>
    <spring:url value="/static/core/js/message.view.js" var="messageViewJs"/>
    <spring:url value="/static/core/js/message.ajax.js" var="messageAjaxJs"/>
    <spring:url value="/static/core/js/comment.ajax.js" var="commentAjaxJs"/>
    <spring:url value="/static/core/js/comment.view.js" var="commentViewJs"/>

    <!-- Styles -->
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${fileInputCss}" rel="stylesheet"/>
    <link href="${customPanelCss}" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<jsp:include page="${request.contextPath}/userHeader"></jsp:include>
<div class="row" style="margin: 80px 10px;">
    <div class="col-md-3">
        <div class="panel panel-primary panel-image">
            <div class="panel-heading">
                <h3 class="panel-title">${photo}</h3>
            </div>
            <div class="panel-body user-image" data-username="${profile.username}">
                <img src="${profile.imageUrl}" class="img-thumbnail" width="100%" height="300"/>
                <c:if test="${profile.username == pageContext.request.userPrincipal.name}">
                    <div class="tool-panel">
                        <a href="#">
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                        </a>
                        <a href="${pageContext.request.contextPath}/user/profile">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="panel-group" id="accordion">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                            ${personal}</a>
                    </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse in">
                    <div class="panel-body">
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
            <button style="margin-top: 10px; font-weight: bolder;" id="show-message-modal-button" class="btn btn-block btn-info">
                    ${send} <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
            </button>
        </c:if>
    </div>
    <div class="col-md-9">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">${posts}</h3>
            </div>
            <div id="message-container" class="panel-body">
                <c:forEach items="${messageList}" var="message">
                    <div class="panel panel-default panel-message">
                        <div class="panel-heading">
                            <strong>${message.username} | </strong>
                            <strong>${message.date}</strong>
                        </div>
                        <c:choose>
                            <c:when test="${message.imageUrl ne null}">
                                <div class="panel-body message-image">
                                    <img src="${message.imageUrl}" width='100%' height='300' class='img-thumbnail'/>
                                    <div class="tool-panel"
                                            <c:if test="${message.username ne pageContext.request.userPrincipal.name}">
                                                style="width: 48px;"
                                            </c:if>>
                                        <a class="comment-message-button" href='#' data-id="${message.id}">
                                            <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
                                        </a>
                                        <c:if test="${message.username == pageContext.request.userPrincipal.name}">
                                            <a class="edit-message-button" href='#' data-id="${message.id}">
                                                <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                                            </a>
                                            <a class="remove-message-button" href="#" data-id="${message.id}">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="panel-footer">${message.text}</div>
                            </c:when>
                            <c:otherwise>
                                <div class="panel-body message-image">
                                    <p>${message.text}</p>
                                    <div class="tool-panel">
                                        <a class="comment-message-button" href='#' data-id="${message.id}">
                                            <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
                                        </a>
                                        <c:if test="${message.username == pageContext.request.userPrincipal.name}">
                                            <a class="edit-message-button" href='#' data-id="${message.id}">
                                                <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                                            </a>
                                            <a class="remove-message-button" href="#" data-id="${message.id}">
                                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                            </a>
                                        </c:if>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:forEach>
            </div>
            <div class="panel-footer">
                <div id="paging-message-container" class="panel-paging" data-paging="2">
                    <a class="more-paging paging-message" href="#">
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
                        <label for="post">${messageTitle}:</label>
                        <textarea id="message-text" class="form-control" rows="5" id="post"></textarea>
                    </div>
                    <div class="form-group">
                        <a href="#" id="create-message-button" class="btn btn-block btn-info">
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
    <div class="modal-dialog">
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
                <label>${comment}:</label>
                <textarea id="create-comment-text" class="form-control" rows="5"></textarea>
                <br>
                <button id="create-comment-button" class="btn btn-block btn-info">
                    <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
                </button>
            </div>
        </div>
    </div>
</div>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${fileInputJs}"></script>
<script src="${fileInputCustomJs}"></script>
<script src="${messageViewJs}"></script>
<script src="${messageAjaxJs}"></script>
<script src="${commentViewJs}"></script>
<script src="${commentAjaxJs}"></script>
<script>
</script>
</body>
</html>
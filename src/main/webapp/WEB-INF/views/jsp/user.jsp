<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>User page</title>

    <!-- Styles -->
    <spring:url value="/static/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/static/core/css/fileinput.min.css" var="fileInputCss" />
    <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />
    <spring:url value="/static/core/css/custom.container.css" var="customContainer" />
    <spring:url value="/static/core/css/message.container.css" var="messageContainerCss" />

    <!-- Javascript -->
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/js/fileinput.min.js" var="fileInputJs"/>
    <spring:url value="/static/core/js/fileinput.custom.js" var="fileInputCustomJs"/>
    <spring:url value="/static/core/js/user.view.js" var="userViewJs"/>
    <spring:url value="/static/core/js/user.ajax.js" var="userAjaxJs"/>
    <spring:url value="/static/core/js/message.view.js" var="messageViewJs"/>
    <spring:url value="/static/core/js/message.ajax.js" var="messageAjaxJs"/>
    <spring:url value="/static/core/js/comment.ajax.js" var="commentAjaxJs"/>
    <spring:url value="/static/core/js/comment.view.js" var="commentViewJs"/>

    <!-- Styles -->
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${fileInputCss}" rel="stylesheet" />
    <link href="${languageDropdown}" rel="stylesheet" />
    <link href="${customContainer}" rel="stylesheet" />
    <link href="${messageContainerCss}" rel="stylesheet" />

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
            <%--<form enctype="multipart/form-data">
                <input id="user-image" name="user-image" type="file" class="file-loading">
            </form>--%>
            <div class='user-image'>
            </div>
        </div>
    </div>
    <div class="col-md-5">
        <div class="custom-container">
            <div id="message-container">
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="custom-container">
            <button id="view-create-message-modal-button" class="btn btn-lg btn-info">
                <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
            </button>
        </div>
    </div>
</div>
<div class="modal fade" id="create-message-modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width: 400px;">
        <div class="custom-container">
            <form enctype="multipart/form-data" name="message-form">
                <div class="form-group">
                    <label>Image:</label>
                    <input id="post-image" name="postImage" type="file" class="file-loading">
                </div>
                <div class="form-group">
                    <label for="post">Message:</label>
                    <textarea id="message-text" class="form-control" rows="5" id="post"></textarea>
                </div>
                <div class="form-group">
                    <a href="#" id="create-message-button" class="btn btn-lg btn-info send">
                        <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="modal fade" id="view-message-modal" tabindex="-1"
     role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width: 800px;">
        <div class="custom-container" style="height: 600px;">
        </div>
    </div>
</div>
<div class="modal fade" id="create-comment-modal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="margin-top: 150px;width: 300px;">
        <div class="custom-container" style="height: 240px;">
            <label>Comment:</label>
            <textarea id="create-comment-text" class="form-control" rows="5"></textarea>
            <br>
            <button id="create-comment-button" class="btn btn-lg btn-info">
                <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
            </button>
        </div>
    </div>
</div>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${fileInputJs}"></script>
<script src="${fileInputCustomJs}"></script>
<script src="${userAjaxJs}"></script>
<script src="${userViewJs}"></script>
<script src="${messageViewJs}"></script>
<script src="${messageAjaxJs}"></script>
<script src="${commentViewJs}"></script>
<script src="${commentAjaxJs}"></script>
</body>
</html>
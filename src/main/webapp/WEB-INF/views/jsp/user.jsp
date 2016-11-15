<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <spring:url value="/static/core/css/container.css" var="customContainerStyle" />
    <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />
    <spring:url value="/static/core/css/custom.container.css" var="customContainer" />
    <spring:url value="/static/core/css/fileinput.min.css" var="fileInputCss" />
    <spring:url value="/static/core/css/message.container.css" var="messageContainerCss" />

    <!-- Javascript -->
    <spring:url value="/static/core/js/bootstrap.min.js" var="bootstrapJs"/>
    <spring:url value="/static/core/js/jquery-3.1.1.min.js" var="jqueryJs"/>
    <spring:url value="/static/core/js/fileinput.min.js" var="fileInputJs"/>
    <spring:url value="/static/core/js/fileinput.custom.js" var="fileInputCustomJs"/>
    <spring:url value="/static/core/js/message.view.js" var="messageViewJs"/>
    <spring:url value="/static/core/js/message.ajax.js" var="messageAjaxJs"/>

    <!-- Bootstrap -->
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${customContainerStyle}" rel="stylesheet" />
    <link href="${languageDropdown}" rel="stylesheet" />
    <link href="${customContainer}" rel="stylesheet" />
    <link href="${fileInputCss}" rel="stylesheet" />
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
            <form enctype="multipart/form-data">
                <input id="user-image" name="user-image" type="file" class="file-loading">
            </form>
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
            <button class="btn btn-lg btn-info" data-toggle="modal" data-target="#send-message-modal">
                <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
            </button>
            <button class="btn btn-lg btn-primary">Second button</button>
        </div>
    </div>
</div>
<div class="modal fade" id="send-message-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    <div class="modal-dialog" style="width: 400px;">
        <div class="custom-container">
            <form enctype="multipart/form-data" name="message-form">
                <div class="form-group">
                    <label>Image:</label>
                    <input id="post-image" name="postImage" type="file" class="file-loading">
                </div>
                <div class="form-group">
                    <label for="comment">Message:</label>
                    <textarea id="message-text" class="form-control" rows="5" id="comment"></textarea>
                </div>
                <div class="form-group">
                    <a href="#" id="send-message-button" class="btn btn-lg btn-info">
                        <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${fileInputJs}"></script>
<script src="${fileInputCustomJs}"></script>
<script src="${messageViewJs}"></script>
<script src="${messageAjaxJs}"></script>
</body>
</html>
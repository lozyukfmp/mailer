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
   <%-- <spring:url value="/static/core/css/language_dropdown.css" var="languageDropdown" />
    <spring:url value="/static/core/css/custom.container.css" var="customContainer" />
    <spring:url value="/static/core/css/message.container.css" var="messageContainerCss" />
    <spring:url value="/static/core/css/paging.css" var="paging" />--%>
    <spring:url value="/static/core/css/panel.custom.css" var="customPanelCss" />

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
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${fileInputCss}" rel="stylesheet" />
    <%--<link href="${languageDropdown}" rel="stylesheet" />
    <link href="${customContainer}" rel="stylesheet" />
    <link href="${messageContainerCss}" rel="stylesheet" />
    <link href="${paging}" rel="stylesheet" />--%>
    <link href="${customPanelCss}" rel="stylesheet" />

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
    <div class="col-md-4">
        <div class="panel panel-primary panel-image">
            <div class="panel-heading">
                <h3 class="panel-title">Photo</h3>
            </div>
            <div class="panel-body user-image" data-username="${profile.username}">
                <img src="${profile.imageUrl}" class="img-thumbnail" width="100%" height="300"/>
                <div class="tool-panel">
                    <a href="#">
                        <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </a>
                    <a href="#">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
        <div class="panel-group" id="accordion">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">
                            Personal information</a>
                    </h4>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                    <div class="panel-body">
                        <table class="table table-striped table-hover ">
                            <tbody>
                            <tr>
                                <td>Firstname: </td>
                                <td>Artem</td>
                            </tr>
                            <tr>
                                <td>Secondname: </td>
                                <td>Lozyuk</td>
                            </tr>
                            <tr>
                                <td>Thirdname: </td>
                                <td>Nicolaevich</td>
                            </tr>
                            <tr>
                                <td>Email: </td>
                                <td>lozyuk-artem@mail.ru</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">
                            Control panel</a>
                    </h4>
                </div>
                <div id="collapse2" class="panel-collapse collapse">
                    <div class="panel-body" style="padding-top: 5px;">
                        <button id="view-create-message-modal-button" class="btn btn-block btn-info">
                            <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
                        </button>
                        <button class="btn btn-block btn-warning">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        </button>
                        <button class="btn btn-block btn-success">
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                        </button>
                        <button class="btn btn-block btn-primary">
                            <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-8">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">Posts</h3>
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
                <div id="paging-message-container" class= "panel-paging" data-paging="2">
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
                        <label>Image:</label>
                        <input id="post-image" name="postImage" type="file" class="file-loading">
                    </div>
                    <div class="form-group">
                        <label for="post">Message:</label>
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
                <label>Comment:</label>
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
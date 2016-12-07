<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="message.title.comment" var="comment"/>
<spring:message code="message.title.comments" var="comments"/>
<div class="row message-view">
    <div class="panel panel-default panel-message" data-id="${message.id}">
        <div class="panel-heading">
            <strong>${message.username} | </strong>
            <strong>${message.date}</strong>
        </div>
        <c:if test="${message.imageUrl ne null}">
            <div class="panel-body message-image">
                <img src="${message.imageUrl}" width='100%' height='300' class='img-thumbnail'/>
            </div>
        </c:if>
        <div class="panel-footer">${message.text}</div>
    </div>
    <button id="view-create-comment-modal-button" class="btn btn-block btn-info" href='#' style="margin-bottom: 15px;">
        ${comment}     <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
    </button>
    <div class="panel panel-default">
        <div class="panel-heading">
            ${comments}:
        </div>
        <div class="panel-body">
            <div class="comment-container">
                <c:forEach items="${message.comments}" var="comment">
                    <div class="panel panel-default panel-comment">
                        <div class="panel-heading">
                            <strong>${comment.username} | </strong>
                            <strong>${comment.date}</strong>
                        </div>
                        <div class="panel-body">
                                ${comment.text}
                            <c:if test="${comment.username == pageContext.request.userPrincipal.name}">
                                <div class="tool-panel">
                                    <a class="edit-comment-button" href='#' data-id="${comment.id}">
                                        <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                                    </a>
                                    <a class="remove-comment-button" href="#" data-id="${comment.id}">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </a>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="panel-footer">
            <div id="paging-comment-container" class= "panel-paging" data-paging="2">
                <a class="more-paging paging-comment" href="#">
                    <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span>
                </a>
                <a class="turn-paging paging-comment" href="#">
                    <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
                </a>
            </div>
        </div>
    </div>
</div>

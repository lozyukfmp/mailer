<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
        ${comment} <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
    </button>
    <div class="panel panel-default">
        <div class="panel-heading">
            ${comments}:
        </div>
        <div class="panel-body">
            <div class="comment-container">
                <jsp:include page="commentList.jsp"/>
            </div>
        </div>
        <div class="panel-footer">
            <div id="paging-comment-container" class="panel-paging" data-paging="2">
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

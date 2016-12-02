<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${messageList}" var="message">
    <div class='well well-message'>
        <span class= "message-header">
            <strong>${message.username} | </strong>
            <strong>${message.date}</strong>
        </span>
        <c:if test="${message.imageUrl ne null}">
            <div class='message-image'>
                <img src="${message.imageUrl}" width='100%' height='300' class='img-thumbnail'/>
            </div>
        </c:if>
        <div class="message-text">
            <p>${message.text}</p>
        </div>
        <div class="message-link-group">
            <a class="btn btn-lg btn-link comment-message-button" href='#' data-id="${message.id}">
                <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
            </a>
            <c:if test="${message.username == pageContext.request.userPrincipal.name}">
                <a class="btn btn-lg btn-link edit-message-button" href='#' data-id="${message.id}">
                    <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                </a>
                <a class="btn btn-lg btn-link remove-message-button" href="#" data-id="${message.id}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a>
            </c:if>
        </div>
    </div>
</c:forEach>

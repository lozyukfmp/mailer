<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

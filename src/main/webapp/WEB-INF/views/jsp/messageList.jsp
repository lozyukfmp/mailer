<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:forEach items="${messageList}" var="message">
    <div class="panel panel-default panel-message">
        <div class="panel-heading">
            <strong>${message.username} | </strong>
            <strong>${message.date}</strong>
        </div>
        <div class="panel-body message-image">
            <img src="${message.imageUrl}" width='100%' height='300' class='img-thumbnail'/>
            <div class="btn-group btn-group-justified">
                <a class="btn btn-info comment-message-button" href='#' data-id="${message.id}">
                    <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
                </a>
                <c:choose>
                    <c:when test="${message.username == pageContext.request.userPrincipal.name}">
                        <a class="btn btn-info edit-message-button" href='#' data-id="${message.id}">
                            <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                        </a>
                        <a class="btn btn-info remove-message-button" href="#" data-id="${message.id}">
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <a class="btn btn-info remove-message-button" href="#" data-id="${message.id}">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </security:authorize>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <div class="panel-footer">${message.text}</div>
    </div>
</c:forEach>

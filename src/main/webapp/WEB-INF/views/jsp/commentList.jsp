<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${commentList}" var="comment">
    <div class='well well-comment'>
        <span class= "comment-header">
            <strong>${comment.username} | </strong>
            <strong>${comment.date}</strong>
        </span>
        <div class="comment-text">
            <p>${comment.text}</p>
        </div>
        <c:if test="${comment.username == pageContext.request.userPrincipal.name}">
            <div class="comment-link-group">
                <a class="btn btn-lg btn-link edit-comment-button" href="#" data-id="${comment.id}">
                    <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                </a>
                <a class="btn btn-lg btn-link remove-comment-button" href="#" data-id="${comment.id}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                </a>
            </div>
        </c:if>
    </div>
</c:forEach>

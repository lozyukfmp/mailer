<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${commentList}" var="comment">
    <div class='well well-message'>
        <span class= "message-header">
            By <strong>${comment.username}</strong>
            on <strong>${comment.date}</strong>
        </span>
        <div class="message-text">
                ${comment.text}
        </div>
        <div class="message-link-group">
            <a class="btn btn-lg btn-link edit-message-button" href='#' data-id="${comment.id}">
                <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
            </a>
            <a class="btn btn-lg btn-link remove-message-button" href="#" data-id="${comment.id}">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </a>
        </div>
    </div>
</c:forEach>

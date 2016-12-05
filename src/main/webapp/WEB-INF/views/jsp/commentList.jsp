<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach items="${commentList}" var="comment">
    <div class="panel panel-default panel-comment">
        <div class="panel-heading">
            <strong>${comment.username} | </strong>
            <strong>${comment.date}</strong>
        </div>
        <div class="panel-body">
                ${comment.text}
            <div class="tool-panel">
                <c:if test="${comment.username == pageContext.request.userPrincipal.name}">
                    <a class="edit-comment-button" href='#' data-id="${comment.id}">
                        <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                    </a>
                    <a class="remove-comment-button" href="#" data-id="${comment.id}">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</c:forEach>

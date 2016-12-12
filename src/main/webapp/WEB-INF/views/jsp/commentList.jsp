<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:forEach items="${commentList}" var="comment">
    <div class="panel panel-default panel-comment">
        <div class="panel-heading">
            <strong>${comment.username} | </strong>
            <strong>${comment.date}</strong>
        </div>
        <div class="panel-body">
                ${comment.text}
                <c:choose>
                    <c:when test="${comment.username == pageContext.request.userPrincipal.name}">
                        <div class="tool-panel">
                            <a class="edit-comment-button" href='#' data-id="${comment.id}">
                                <span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>
                            </a>
                            <a class="remove-comment-button" href="#" data-id="${comment.id}">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <security:authorize access="hasRole('ROLE_ADMIN')">
                            <div class="tool-panel">
                                <a class="remove-comment-button" href="#" data-id="${comment.id}">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </a>
                            </div>
                        </security:authorize>
                    </c:otherwise>
                </c:choose>
        </div>
    </div>
</c:forEach>

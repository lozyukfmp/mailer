<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="message.toggle.blocked" var="blocked"/>
<spring:message code="message.toggle.unblocked" var="unblocked"/>
<spring:message code="message.view" var="view"/>
<c:forEach items="${userList}" var="user">
    <c:if test="${user.username ne pageContext.request.userPrincipal.name}">
        <tr>
            <td>
                <img src="${user.profile.imageUrl}" class="img-thumbnail" width="90" height="100"/>
            </td>
            <td>
                <h4>${user.username}</h4>
                <h4>${user.profile.email}</h4>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.enabled}">
                        <input type="checkbox" checked data-username="${user.username}" data-on="${unblocked}" data-off="${blocked}" data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" data-username="${user.username}" data-on="${unblocked}" data-off="${blocked}" data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <form action="/user" class="form-inline">
                    <input type="hidden" name="username" value="${user.username}"/>
                    <button type="submit" class="btn btn-warning">
                        ${view} <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </button>
                </form>
            </td>
        </tr>
    </c:if>
</c:forEach>



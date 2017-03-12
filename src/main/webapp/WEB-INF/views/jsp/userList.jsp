<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<spring:message code="message.toggle.blocked" var="blocked"/>
<spring:message code="message.toggle.unblocked" var="unblocked"/>
<spring:message code="message.view" var="view"/>
<spring:message code="message.isAdmin" var="isAdmin"/>
<spring:message code="message.isUser" var="isUser"/>
<c:forEach items="${userList}" var="user">
    <c:if test="${user.username ne pageContext.request.userPrincipal.name}">
        <tr>
            <td style="border: none;">
                <img src="${user.profile.imageUrl}" class="img-thumbnail" width="90" height="100"/>
            </td>
            <td style="border: none;">
                <h4>${user.username}</h4>
                <h4>${user.profile.email}</h4>
            </td>
            <security:authorize access="hasRole('ROLE_ADMIN')">
                <td style="border: none;">
                    <c:choose>
                        <c:when test="${user.enabled}">
                            <input class="enb" type="checkbox" checked data-username="${user.username}" data-on="${unblocked}"
                                   data-off="${blocked}" data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                        </c:when>
                        <c:otherwise>
                            <input class="enb" type="checkbox" data-username="${user.username}" data-on="${unblocked}" data-off="${blocked}"
                                   data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="border: none;">
                    <c:choose>
                        <c:when test="${user.admin}">
                            <input class="adm" type="checkbox" checked data-username="${user.username}" data-on="${isAdmin}"
                                   data-off="${isUser}" data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                        </c:when>
                        <c:otherwise>
                            <input class="adm" type="checkbox" data-username="${user.username}" data-on="${isAdmin}" data-off="${isUser}"
                                   data-toggle="toggle" data-onstyle="success" data-offstyle="danger">
                        </c:otherwise>
                    </c:choose>
                </td>
            </security:authorize>
            <td style="border: none;">
                <form action="user" class="form-inline">
                    <input type="hidden" name="username" value="${user.username}"/>
                    <button type="submit" class="btn btn-warning">
                            ${view} <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                    </button>
                </form>
            </td>
        </tr>
    </c:if>
</c:forEach>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:message code="message.view" var="view"/>
<c:forEach items="${friendsList}" var="friend" varStatus="loop">
    <c:if test="${friend.username ne pageContext.request.userPrincipal.name}">
        <div class="thumbnail" style="width: 100px; margin-left: 10px; display: inline-block;">
            <img src="${friend.profile.imageUrl}" class="img-thumbnail" width="90" height="100"/>
            <div class="caption" style="font-size: 13px;">
                ${friend.username}
            </div>
        </div>
    </c:if>
</c:forEach>
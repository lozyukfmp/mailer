<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
    <div class="col-md-6">
        <div class="message" data-id="${message.id}">
            <div class='message-image'>
                <c:if test="${message.imageUrl ne null}">
                    <img src="${message.imageUrl}" width='100%' height='300' class='img-thumbnail'/>
                </c:if>
            </div>
            <div class="message-text">
                <p>${message.text}</p>
                <button id="view-create-comment-modal-button" class="btn btn-lg btn-info" href='#'>
                    Comment <span class='glyphicon glyphicon-comment' aria-hidden='true'></span>
                </button>
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <label>Comments:</label>
        <div class="comment-container">
            <c:forEach items="${message.comments}" var="comment">
                <div class="well comment">
                    <p class="comment-header">
                        <strong>${comment.username} | </strong>
                        <strong>${comment.date}</strong>
                    </p>
                    <div class="comment-text">
                        ${comment.text}
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
        </div>
        <div id="paging-comment-container" class= "paging-container" data-paging="2">
            <a class="more-paging paging-comment" href="#">
                <span class="glyphicon glyphicon-triangle-bottom" aria-hidden="true"></span> More
            </a>
            <a class="turn-paging paging-comment" href="#">
                <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span> Turn
            </a>
        </div>
    </div>
</div>

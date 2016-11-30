;(function () {

    var viewMessageModal = $("#view-message-modal"),
        createCommentButton = $("#create-comment-button"),
        createCommentModal = $("#create-comment-modal");

    function getCommentData(comment) {
        comment = comment || {};
        
        comment.postId = viewMessageModal.find(".message").attr('data-id');
        comment.text = $("#create-comment-text").val();

        return comment;
    }

    function showCommentList(commentCount) {
        commentAjax.getCommentListByPostId(
            viewMessageModal.find(".message").attr('data-id'), commentCount,
            function (commentList) {
                $(".comment-container").html(commentList);
                $("#paging-comment-container").attr("data-paging", commentCount);
        });
    }

    function initCreateCommentModal(comment, isEdit) {
        comment = comment || {};
        isEdit = isEdit || false;

        $("#create-comment-text").val(comment.text);

        createCommentButton.off();

        if(isEdit) {
            createCommentButton.on('click', function () {
                commentAjax.updateComment(getCommentData(comment), function () {
                    showCommentList($("#paging-comment-container").attr("data-paging"));
                });

                createCommentModal.modal("hide");
            });
        } else {
            createCommentButton.on('click', function () {
                commentAjax.createComment(getCommentData(), function () {
                    showCommentList($("#paging-comment-container").attr("data-paging"));
                });

                createCommentModal.modal("hide");
            });
        }
    }

    viewMessageModal.on('click', "#view-create-comment-modal-button", function () {
        initCreateCommentModal();
        createCommentModal.modal('show');
    });

    viewMessageModal.on('click', '.remove-comment-button', function () {
        commentAjax.deleteComment($(this).attr('data-id'), function () {
            showCommentList($("#paging-comment-container").attr("data-paging"));
        });
    });

    viewMessageModal.on('click', '.edit-comment-button', function () {
        commentAjax.getComment($(this).attr('data-id'), function (comment) {
            initCreateCommentModal(comment, true);
            createCommentModal.modal('show');
        });
    });

    viewMessageModal.on('click', '.more-paging.paging-comment', function () {
        var index = $("#paging-comment-container").attr("data-paging");
        showCommentList( + index + 2);
        
    });

    viewMessageModal.on('click', '.turn-paging.paging-comment', function () {
        showCommentList(2);
    });
    
})();

var commentView = (function () {

    var viewMessageModal = $("#view-message-modal"),
        createCommentButton = $("#create-comment-button"),
        createCommentModal = $("#create-comment-modal");

    function getCommentData(comment) {
        comment = comment || {};
        
        comment.postId = viewMessageModal.find(".message").attr('data-id');
        comment.text = $("#create-comment-text").val();

        return comment;
    }

    function showCommentList() {
        commentAjax.getCommentListByPostId(viewMessageModal.find(".message").attr('data-id'),
            function (commentList) {
                $(".comment-container").html(commentList);
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
                    showCommentList();
                });

                createCommentModal.modal("hide");
            });
        } else {
            createCommentButton.on('click', function () {
                commentAjax.createComment(getCommentData(), function () {
                    showCommentList();
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
            showCommentList();
        });
    });

    viewMessageModal.on('click', '.edit-comment-button', function () {
        commentAjax.getComment($(this).attr('data-id'), function (comment) {
            initCreateCommentModal(comment, true);
            createCommentModal.modal('show');
        });
    });
    
})();

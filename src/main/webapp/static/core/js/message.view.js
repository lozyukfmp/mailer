;(function () {

    var messageContainer = $("#message-container");
    var messagePagingContainer = $("#paging-message-container");

    var createMessageModal = $("#create-message-modal");
    var viewMessageModal = $("#view-message-modal");

    var viewCreateMessageModalButton = $("#view-create-message-modal-button");
    var createMessageButton = $("#create-message-button");

    var selfUsername = $(".user-image").attr("data-username");

    function getMessageData(message) {
        message = message || {};

        var formData = new FormData();
        var image = $("#post-image")[0].files[0];
        message.imageUrl = createMessageModal.find("img").attr('src');
        message.text = $("#message-text").val();

        formData.append("postImage", image);
        formData.append("postMessage", JSON.stringify(message));

        return formData;
    }

    function showMessageList(messageCount, username) {
        messageAjax.getMessageList(function (messageList) {
            messageContainer.html(messageList);
            $("#paging-message-container").attr("data-paging", messageCount);
            $(".message-image").off();
            $(".message-image").hover(function () {
                $(this).find(".tool-panel").fadeIn(150);
            }, function () {
                $(this).find(".tool-panel").fadeOut(100);
            });
        }, messageCount, username);
    }

    function initCreateMessageModal(message, isEdit) {
        message = message || {};
        isEdit = isEdit || false;

        $("#post-image").fileinput('clear');

        message.imageUrl && $("#post-image").fileinput('refresh', {
            initialPreviewAsData: true,
            initialPreview: [
                message.imageUrl
            ],
        });

        $("#message-text").val(message.text);

        createMessageButton.off();

        if (isEdit) {
            createMessageButton.on('click', function () {
                messageAjax.updateMessage(getMessageData(message), function () {
                    showMessageList($("#paging-message-container").attr("data-paging"), selfUsername);
                });

                createMessageModal.modal("hide");
            });
        } else {
            createMessageButton.on('click', function () {
                messageAjax.createMessage(getMessageData(), function () {
                    showMessageList($("#paging-message-container").attr("data-paging"), selfUsername);
                });

                createMessageModal.modal("hide");
            });
        }
    }

    viewCreateMessageModalButton.click(function () {
        initCreateMessageModal();
        createMessageModal.modal('show');
    });

    messageContainer.on('click', '.remove-message-button', function () {
        messageAjax.deleteMessage($(this).attr('data-id'), function () {
            showMessageList($("#paging-message-container").attr("data-paging"), selfUsername);
        });
    });

    messageContainer.on('click', '.edit-message-button', function () {
        messageAjax.getMessage($(this).attr('data-id'), function (message) {
            initCreateMessageModal(message, true);
            createMessageModal.modal('show');
        });
    });

    messageContainer.on('click', '.comment-message-button', function () {
        messageAjax.getMessageView($(this).attr('data-id'), function (messageView) {
            viewMessageModal.find(".modal-body").html(messageView);
            $(".panel-comment > .panel-body").hover(function () {
                $(this).find(".tool-panel").fadeIn(150);
            }, function () {
                $(this).find(".tool-panel").fadeOut(100);
            });
            viewMessageModal.modal('show');
        });
    });

    $(".message-image").hover(function () {
        $(this).find(".tool-panel").fadeIn(150);
    }, function () {
        $(this).find(".tool-panel").fadeOut(100);
    });

    $(".panel-image").hover(function () {
        $(this).find(".tool-panel").fadeIn(150);
    }, function () {
        $(this).find(".tool-panel").fadeOut(100);
    });

    messagePagingContainer.on('click', '.more-paging.paging-message', function () {
        var index = $("#paging-message-container").attr("data-paging");
        showMessageList(+index + 2, selfUsername);
    });

    messagePagingContainer.on('click', '.turn-paging.paging-message', function () {
        showMessageList(2, selfUsername);
    });

    return {
        showMessageList: function (index, username) {
            showMessageList(index, username);
        }
    };
})();

$(document).ready(function () {

    var messageContainer = $("#message-container");
    var messagePagingContainer = $("#paging-message-container");

    var createMessageModal = $("#create-message-modal");
    var viewMessageModal = $("#view-message-modal");

    var viewCreateMessageModalButton = $("#view-create-message-modal-button");
    var createMessageButton = $("#create-message-button");

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

    function showMessageList(messageCount) {
        messageAjax.getMessageList(messageCount, function (messageList) {
            messageContainer.html(messageList);
            $("#paging-message-container").attr("data-paging", messageCount);
        });
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

        if(isEdit) {
            createMessageButton.on('click', function () {
                messageAjax.updateMessage(getMessageData(message), function () {
                    showMessageList($("#paging-message-container").attr("data-paging"));
                });

                createMessageModal.modal("hide");
            });
        } else {
            createMessageButton.on('click', function () {
                messageAjax.createMessage(getMessageData(), function () {
                    showMessageList($("#paging-message-container").attr("data-paging"));
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
            showMessageList($("#paging-message-container").attr("data-paging"));
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
            viewMessageModal.find(".custom-container").html(messageView);
            viewMessageModal.modal('show');
        });
    });

    messagePagingContainer.on('click', '.more-paging.paging-message', function () {
        var index = $("#paging-message-container").attr("data-paging");
        showMessageList(+ index + 2);
    });

    messagePagingContainer.on('click', '.turn-paging.paging-message', function () {
        showMessageList(2);
    });
    
    showMessageList(2);
    
});

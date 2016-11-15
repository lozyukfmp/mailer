$(document).ready(function () {

    var messageContainer = $("#message-container");

    var messageModal = $("#send-message-modal");
    var commentModal = $("#write-comment-modal");

    var sendMessageButton = $("#send-message-button");
    var showMessageModalButton = $("#show-message-modal");

    function getMessageData(message) {
        message = message || {};

        var formData = new FormData();
        var image = $("#post-image")[0].files[0];
        message.text = $("#message-text").val();
        
        formData.append("postImage", image);
        formData.append("postMessage", JSON.stringify(message));
        
        return formData;
    }

    function showMessageList() {
        messageAjax.getMessageList(function (messageList) {
            messageContainer.html(messageList);
        });
    }

    function initMessageModal(message, isEdit) {
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

        sendMessageButton.off();

        if(isEdit) {
            sendMessageButton.on('click', function () {
                messageAjax.updateMessage(getMessageData(message), function () {
                    showMessageList();
                });

                messageModal.modal("hide");
            });
        } else {
            sendMessageButton.on('click', function () {
                messageAjax.createMessage(getMessageData(), function () {
                    showMessageList();
                });

                messageModal.modal("hide");
            });
        }
    }

    function initCommentModal(message) {
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

        sendMessageButton.off();

        if(isEdit) {
            sendMessageButton.on('click', function () {
                messageAjax.updateMessage(getMessageData(message), function () {
                    showMessageList();
                });

                messageModal.modal("hide");
            });
        } else {
            sendMessageButton.on('click', function () {
                messageAjax.createMessage(getMessageData(), function () {
                    showMessageList();
                });

                messageModal.modal("hide");
            });
        }
    }

    showMessageModalButton.click(function () {
        initMessageModal();
        messageModal.modal('show');
    });

    messageContainer.on('click', '.remove-message-button', function () {
        messageAjax.deleteMessage($(this).attr('data-id'), function () {
            showMessageList();
        });
    });

    messageContainer.on('click', '.edit-message-button', function () {
        messageAjax.getMessage($(this).attr('data-id'), function (message) {
            initMessageModal(message, true);
            messageModal.modal('show');
        });
    });

    messageContainer.on('click', '.comment-message-button', function () {
        messageAjax.getMessage($(this).attr('data-id'), function (message) {
            initCommentModal(message);
            commentModal.modal('show');
        });
    });
    
    showMessageList();
    
});

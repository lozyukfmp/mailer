$(document).ready(function () {

    var messageContainer = $("#message-container");
    var sendMessageModal = $("#send-message-modal");

    var sendMessageButton = $("#send-message-button");

    function getMessageData() {
        
        var formData = new FormData();
        var image = $("#post-image")[0].files[0] || null;
        
        formData.append("postImage", image);
        formData.append("postMessage", JSON.stringify({
            username: undefined,
            text: $("#message-text").val(),
            date: undefined,
            imageUrl: undefined
        }));
        
        return formData;
    }

    function showMessageList() {
        messageAjax.getMessageList(function (messageList) {
            messageContainer.html(messageList);
        });
    }

    messageContainer.on('click', '.remove-message-button', function () {
        messageAjax.deleteMessage($(this).attr('data-id'), function () {
            showMessageList();
        });
    });

    sendMessageButton.click(function () {
        messageAjax.createMessage(getMessageData(), function () {
            showMessageList();
        });

        sendMessageModal.modal("hide");
    });

});

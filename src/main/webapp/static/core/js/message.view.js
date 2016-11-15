$(document).ready(function () {

    var sendMessageButton = $("#send-message-button");
    var sendMessageModal = $("#send-message-modal");
    var messageContainer = $("#message-container");

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

    

    sendMessageButton.click(function () {
        messageAjax.createMessage(getMessageData(), function () {
            messageAjax.getMessageList(function (messageList) {
                messageContainer.html(messageList);
            })
        });

        sendMessageModal.modal("hide");
    });

});

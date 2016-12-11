var messageAjax = (function () {

    return {
        createMessage: function (messageForm, successCallback, errorCallback) {
            $.ajax({
                type: "POST",
                url: "post/create",
                dataType: 'text',
                processData: false,
                contentType: false,
                data: messageForm,
                success: successCallback,
                error: errorCallback
            });
        },
        updateMessage: function (messageForm, successCallback, errorCallback) {
            $.ajax({
                type: "POST",
                url: "post/update",
                dataType: 'text',
                processData: false,
                contentType: false,
                data: messageForm,
                success: successCallback,
                error: errorCallback
            });
        },
        deleteMessage: function (id, callback) {
            $.ajax({
                type: "POST",
                url: "post/delete/" + id,
                success: callback
            });
        },
        getMessage: function (id, callback) {
            $.ajax({
                type: "GET",
                url: "post/" + id,
                success: callback
            });
        },
        getMessageView: function (id, callback) {
            $.ajax({
                type: "GET",
                url: "post/view/" + id,
                success: callback
            });
        },
        getMessageList: function (callback, messageCount, username) {
            $.ajax({
                type: "GET",
                url: "post/all/" + username + "/" + messageCount,
                success: function (response) {
                    callback(response);
                }
            });
        }
    }
})();
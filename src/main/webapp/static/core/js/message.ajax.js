var messageAjax = (function () {

	return {
		createMessage: function (messageForm, callback) {
			$.ajax({
				type: "POST",
				url: "post/create",
				dataType: 'text',
    			processData: false,
    			contentType: false,
				data: messageForm,
				success: callback
			});
		},
		updateMessage: function (messageForm, callback) {
			$.ajax({
				type: "POST",
				url: "post/update",
				dataType: 'text',
    			processData: false,
    			contentType: false,
				data: messageForm,
				success: callback
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
		getMessageView: function(id, callback) {
			$.ajax({
				type: "GET",
				url: "post/view/" + id,
				success: callback
			});
		},
		getMessageList: function (messageCount, callback) {
			$.ajax({
				type: "GET",
				url: "post/all/" + messageCount,
				success: function(response) {
					callback(response);
				}
			});
		}
	}
})();
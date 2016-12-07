var commentAjax = (function () {
    return {
        createComment: function (comment, callback) {
            $.ajax({
                type: "POST",
                url: "comment/create",
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                data: JSON.stringify(comment),
                success: callback
            });
        },
        updateComment: function (comment, callback) {
            $.ajax({
                type: "POST",
                url: "comment/update",
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                data: JSON.stringify(comment),
                success: callback
            });
        },
        deleteComment: function (id, callback) {
            $.ajax({
                type: "POST",
                url: "comment/delete/" + id,
                success: callback
            });
        },
        getComment: function (id, callback) {
            $.ajax({
                type: "GET",
                url: "comment/" + id,
                success: callback
            });
        },
        getCommentListByPostId: function (postId, lastCommentIndex, callback) {
            $.ajax({
                type: "GET",
                url: "comment/all/" + postId + "/" + lastCommentIndex,
                success: function (response) {
                    callback(response);
                }
            });
        }
    }
})();

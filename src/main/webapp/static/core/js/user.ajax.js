var userAjax = (function ($) {
    return {
        setUserEnabled: function (username, value, callback) {
            $.ajax({
                type: "POST",
                url: "enable/" + username + "/" + value,
                success: callback
            });
        },
        setAdmin: function (username, value, callback) {
            $.ajax({
                type: "POST",
                url: "admin-role/" + username + "/" + value,
                success: callback
            });
        },
        getUser: function (callback, username) {
            $.ajax({
                type: "GET",
                url: "user/" + username,
                success: function (response) {
                    callback(response);
                }
            });
        },
        getUserList: function (callback, userCount) {
            $.ajax({
                type: "GET",
                url: "user/all/" + userCount,
                success: function (response) {
                    callback(response);
                }
            });
        }
    };
})(jQuery);

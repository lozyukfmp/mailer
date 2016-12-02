var userAjax = (function () {
    return {
        getProfile: function(callback, username) {
            $.ajax({
                type: "GET",
                url: "user/profile/info/" + username,
                success: function(profile) {
                    callback(profile);
                }
            });
        }
    };
})();
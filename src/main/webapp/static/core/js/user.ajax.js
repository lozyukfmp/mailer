var userAjax = (function () {
    return {
        getProfile: function(callback) {
            $.ajax({
                type: "GET",
                url: "user/profile/info",
                success: function(profile) {
                    callback(profile);
                }
            });
        }
    };
})();
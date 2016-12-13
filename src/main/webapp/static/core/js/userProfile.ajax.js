var userProfileAjax = (function ($) {
    return {
        updateProfile: function (profile, callback) {
            $.ajax({
                type: "POST",
                url: "profile/update",
                dataType: 'json',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                },
                data: JSON.stringify(profile),
                success: callback
            });
        },
        getProfile: function (callback) {
            $.ajax({
                type: "GET",
                url: "profile/info",
                success: function (profile) {
                    callback(profile);
                }
            });
        },
        deleteProfilePhoto: function () {
            $.ajax({
                type: "POST",
                url: "profile/photo/delete",
            });
        }
    };
})(jQuery);

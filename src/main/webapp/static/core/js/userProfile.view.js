;(function () {
    function initProfilePhoto() {
        userProfileAjax.getProfile(function (profile) {
            $("#user-image").fileinput('clear');

            $("#user-image").fileinput('refresh', {
                initialPreviewAsData: true,
                initialPreview: [
                    profile.imageUrl
                ],
            });

            $(".file-caption-main").on('click', '.fileinput-remove-button', function () {
                userProfileAjax.deleteProfilePhoto();
            });

            $(".file-preview").on('click', '.fileinput-remove', function () {

                userProfileAjax.deleteProfilePhoto();
            });
        });
    }

    initProfilePhoto();
    ;
})();
;(function () {
    userAjax.getProfile(function (profile) {
        $(".user-image").append("<img src='" + profile.imageUrl + "'" +
        "width='100%' height='300' class='img-thumbnail'/>");
    });
})();

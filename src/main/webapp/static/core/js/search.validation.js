(function($) {
    $(".navbar-form.navbar-left").submit(function () {
        if($("#username-field").val().trim() == "") {
            $("#error-div").fadeIn();
            return false;
        } else {
            $("#error-div").fadeOut();
        }
    });
})(jQuery);

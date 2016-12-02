var userView = (function () {
    
    var searchButton = $("#search-by-username"),
        searchInput = $(".username-field");

   /* searchButton.click(function () {
        userAjax.getUserInfoByUsername(searchInput.val(), function (profile) {
            console.log("BINGO");
        });
    });*/
    
    return {
        showProfile: function(username) {
            userAjax.getProfile(function (profile) {
                $(".user-image").append("<img src='" + profile.imageUrl + "'" +
                    "width='100%' height='300' class='img-thumbnail'/>");
            }, username);
        }
    }
})();

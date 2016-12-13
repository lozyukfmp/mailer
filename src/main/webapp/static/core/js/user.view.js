;(function ($) {

    var userContainer = $("#user-container");
    var userPagingContainer = $("#paging-user-container");
    var refreshButton = $("#refresh-button");
    var usernameField = $(".username-field");
    var searchButton = $("#search-by-username");
    
    function showUserList(userCount) {
        userAjax.getUserList(function (userList) {
            userContainer.html(userList);
            $("#paging-user-container").attr("data-paging", userCount);
            if($("input[type=checkbox]").length) {
                $("input[type=checkbox]").off();
                $("input[type=checkbox]").bootstrapToggle();
                $("input[type=checkbox]").change(function() {
                    userAjax.setUserEnabled($(this).attr('data-username'), $(this).prop('checked'), function () {
                        console.log("SUCCESS");
                    });
                });
            }
        }, userCount);
    }
    
    $("td > input").change(function() {
        userAjax.setUserEnabled($(this).attr('data-username'), $(this).prop('checked'), function () {
            console.log("SUCCESS");
        });
    });
    
    userPagingContainer.on('click', '.more-paging.paging-user', function () {
        var index = $("#paging-user-container").attr("data-paging");
        showUserList(+index + 2);
    });

    userPagingContainer.on('click', '.turn-paging.paging-user', function () {
        showUserList(2);
    });

    refreshButton.on('click', function () {
        var index = $("#paging-user-container").attr("data-paging");
        showUserList(+index);
        $("#error-div").fadeOut();
    });

    searchButton.on('click', function () {
        if($(".username-field").val().trim() == "") {
            $("#error-div").fadeIn();
        } else {
            $("#error-div").fadeOut();
            userAjax.getUser(function(response) {
                userContainer.html(response);
                if($("input[type=checkbox]").length) {
                    $("input[type=checkbox]").off();
                    $("input[type=checkbox]").bootstrapToggle();
                    $("input[type=checkbox]").change(function() {
                        userAjax.setUserEnabled($(this).attr('data-username'), $(this).prop('checked'), function () {
                            console.log("SUCCESS");
                        });
                    });
                }
            }, usernameField.val());
        }
    });
    
})(jQuery);

$(document).ready(function() {
        $("#user-image").fileinput({
            uploadUrl: "/user/profile/photo",
            showCaption: false,
            showClose: false,
            browseClass: "btn btn-success",
            browseLabel: "",
            browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
            removeClass: "btn btn-danger",
            removeLabel: "",
            removeIcon: "<i class=\"glyphicon glyphicon-trash\"></i> ",
            uploadClass: "btn btn-info",
            uploadLabel: "",
            uploadIcon: "<i class=\"glyphicon glyphicon-upload\"></i> ",
            autoReplace: true,
            maxFileCount: 1,
            previewTemplates: {
                image: '<div class="file-preview-frame" id="{previewId}" data-fileindex="{fileindex}" data-template="{template}" style="margin-left: 2%;">\n' +
                        '   <div class="kv-file-content">' +
                '       <img src="{data}" class="kv-preview-data file-preview-image" title="{caption}" alt="{caption}" style="height: 220px;">\n' +
                '   </div>\n' +
                '</div>\n'
            },
            layoutTemplates: {
                footer: "",
                main2: "{preview}\n" +
                "<div class=\'input-group {class}\'>\n" +
                "   <div class=\'input-group-btn btn-group btn-group-justified\'>\n" +
                "       {browse}\n" +
                "       {upload}\n" +
                "       {remove}\n" +
                "   </div>\n" +
                "</div>",
                btnDefault: '<div type="{type}" tabindex="500" title="{title}" style="padding: 10px 0px;" class="{css}"{status}>{icon}{label}</div>',
                btnLink: '<div type="{type}" tabindex="500" title="{title}" style="padding: 10px 0px;" class="{css}"{status}>{icon}{label}</div>',
                btnBrowse: '<div type="{type}" tabindex="500" title="{title}" style="padding: 10px 0px;" class="{css}"{status}>{icon}{label}</div>'
            },
            allowedFileExtensions: ["jpg", "png", "gif"]
        });

        $("#post-image").fileinput({
            uploadUrl: " ",
            showCaption: false,
            showClose: false,
            browseClass: "btn btn-success",
            browseLabel: "",
            browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
            removeClass: "btn btn-danger",
            removeLabel: "",
            removeIcon: "<i class=\"glyphicon glyphicon-trash\"></i> ",
            maxFileCount: 1,
            previewTemplates: {
                image: '<div class="file-preview-frame" id="{previewId}" data-fileindex="{fileindex}" data-template="{template}" style="margin-left: 2%;">\n' +
                '   <div class="kv-file-content">' +
                '       <img src="{data}" class="kv-preview-data file-preview-image" title="{caption}" alt="{caption}" style="height: 220px;">\n' +
                '   </div>\n' +
                '</div>\n'
            },
            layoutTemplates: {
                footer: "",
                main2: "{preview}\n" +
                "<div class=\'input-group {class}\'>\n" +
                "   <div class=\'input-group-btn btn-group\'>\n" +
                "       {browse}\n" +
                "       {remove}\n" +
                "   </div>\n" +
                "</div>",
                btnDefault: '<div type="{type}" tabindex="500" title="{title}" style="padding: 10px 20px;" class="{css}"{status}>{icon}{label}</div>',
                btnBrowse: '<div type="{type}" tabindex="500" title="{title}" style="padding: 10px 20px;" class="{css}"{status}>{icon}{label}</div>'
            },
            allowedFileExtensions: ["jpg", "png", "gif"]
        });

    /*$("#send-message-button").click(function () {
        var formData = new FormData();
        formData.append("postImage",
            $("#post-image")[0].files[0]);

        formData.append("post", JSON.stringify({
            username: "Artem",
            text: $("#message-text").val(),
        }));

        console.log(formData);
        $.ajax({
            type: "POST",
            url: "post/create",
            dataType: 'text',
            processData: false,
            contentType: false,
            data: formData,
            complete: function () {
                $("#post-modal").modal("hide");
            }
        });
    });*/

        /*$('#post-image').on('filepreupload', function(event, data, previewId, index) {

            var message = {
                username: "Artem",
                text: "Some message text",
            };

            data.form.append("post", JSON.stringify(message));
            
            console.log(event);
            console.log(data);
            console.log(previewId);
            console.log(index);
        });*/

        $("#image").fileinput({
            showClose: false,
            showBrowse: false,
            previewTemplates: {
                image: '<div class="file-preview-frame" id="{previewId}" data-fileindex="{fileindex}" data-template="{template}" style="margin-left: 2%;">\n' +
                        '   <div class="kv-file-content">' +
                '       <img src="{data}" class="kv-preview-data file-preview-image" title="{caption}" alt="{caption}" style="height: 220px;">\n' +
                '   </div>\n' +
                '</div>\n'
            },
            layoutTemplates: {
                footer: "",
                main2: "{preview}"
            },
            allowedFileExtensions: ["jpg", "png", "gif"]
        });
    });
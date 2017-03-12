$(document).ready(function ($) {
    $("#user-image").fileinput({
        uploadUrl: "profile/photo/upload",
        showCaption: false,
        showClose: true,
        browseClass: "btn btn-success btn-raised",
        browseLabel: "",
        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
        removeClass: "btn btn-danger btn-raised",
        removeLabel: "",
        removeIcon: "<i class=\"glyphicon glyphicon-trash\"></i> ",
        uploadClass: "btn btn-info btn-raised",
        uploadLabel: "",
        uploadIcon: "<i class=\"glyphicon glyphicon-upload\"></i> ",
        autoReplace: true,
        maxFileCount: 1,
        overwriteInitial: true,
        previewTemplates: {
            image: '<div class="file-preview-frame" id="{previewId}" data-fileindex="{fileindex}" data-template="{template}" style="margin-left: 2%;">\n' +
            '   <div class="kv-file-content">' +
            '       <img src="{data}" class="kv-preview-data file-preview-image" title="{caption}" alt="{caption}" style="width: 100%; height: 220px;">\n' +
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
        showClose: true,
        browseClass: "btn btn-success btn-raised",
        browseLabel: "",
        browseIcon: "<i class=\"glyphicon glyphicon-picture\"></i> ",
        removeClass: "btn btn-danger btn-raised",
        removeLabel: "",
        removeIcon: "<i class=\"glyphicon glyphicon-trash\"></i> ",
        maxFileCount: 1,
        previewTemplates: {
            image: '<div class="file-preview-frame" id="{previewId}" data-fileindex="{fileindex}" data-template="{template}" style="margin-left: 2%;">\n' +
            '   <div class="kv-file-content">' +
            '       <img src="{data}" class="kv-preview-data file-preview-image" title="{caption}" alt="{caption}" style="width: 100%; height: 220px;">\n' +
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
});
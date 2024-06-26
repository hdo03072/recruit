const domain = "/admin/notice";

var pageObj = {
    init: function () {
        $editor.init("content");
    },

    save: function () {
        if ($("#content").summernote("isEmpty")) {
            alert("내용을 입력해 주세요.");
        }

        $ajax.postMultiPart({
            data: $form.getData(),
            success: function (id) {
                $view.detail(id)
            }
        })
    },

    update: function () {

    },

    delete: function () {

    }
}

pageObj.pageStart = function () {
    pageObj.init();
}

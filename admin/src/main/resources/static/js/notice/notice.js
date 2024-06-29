const domain = "/admin/notice";

var pageObj = {
    init: function () {
        $editor.init("content");
    },

    save: function () {
        if ($("#content").summernote("isEmpty")) {
            al.open({
                type: "warning",
                message: "내용은 필수값입니다."
            })
        }

        $ajax.postMultiPart({
            data: $form.getData(),
            success: function (id) {
                $view.detail(id);
            }
        })
    },

    update: function () {
        if ($("#content").summernote("isEmpty")) {
            al.open({
                type: "warning",
                message: "내용은 필수값입니다."
            })
        }

        $ajax.putMultiPart({
            data: $form.getData(),
            success: function (id) {
                $view.detail(id);
            }
        })
    },

    delete: function (id) {
        console.log($valid.delete())

        // $ajax.delete({
        //     url: "/admin/notice/delete",
        //     data: id,
        //     success: function () {
        //         $view.main();
        //     }
        // })
    },
    deleteAll: function () {
        let checked = $checkBox.getAllChecked();
        if (checked.length <= 0) {
            al.open({
                type: "warning",
                message: "삭제할 항목을 선택해 주세요."
            })
            return false;
        }
        $ajax.delete({
            url: "/admin/notice/deleteAll",
            data: checked
        })
    }
}

pageObj.pageStart = function () {
    pageObj.init();
}

const domain = "/admin/notice";

var pageObj = {
    init: function () {
        $editor.init("content");
    },

    save: function () {
        if ($("#content").summernote("isEmpty")) {
            al.open({
                type: "success",
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
            alert("내용을 입력해 주세요.");
        }

        $ajax.putMultiPart({
            data: $form.getData(),
            success: function (id) {
                $view.detail(id);
            }
        })
    },

    delete: function (id) {
        $ajax.delete({
            url: "/admin/notice/delete",
            data: id,
            success: function () {
                $view.main();
            }
        })
    },
    deleteAll: function () {
        let checked = $checkBox.getAllChecked();
        if (checked.length <= 0) {
            alert("삭제할 항목을 선택해 주세요.");
            return false;
        }
        $ajax.delete({
            url: "/admin/notice/deleteAll",
            data: checked
        })
    }
}

let al = $alert();
pageObj.pageStart = function () {
    pageObj.init();

    // 알림 모달 예시
    // let al = $alert();
    // al.open({
    //     height: "400px",
    //     width: "400px",
    //     button: true,
    //     timeout: 2000,
    //     autoClose: true,
    //     type: "success",
    //     message: "안녕하세요~~"
    // })
}

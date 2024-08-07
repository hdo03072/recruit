const domain = "/admin/notice";

var pageObj = {
    init: function () {
        $editor.init("content");
    },

    save: function () {
        if ($("#content").summernote("isEmpty")) {
            $swal.etc({icon: "warning", title: "내용을 입력해 주세요."});
        }

        $ajax.postMultiPart({
            data: $form.getData(),
            success: function (id) {
                $swal.save(function () {
                    $view.detail(id)
                })
            }
        })
    },

    update: function () {
        if ($("#content").summernote("isEmpty")) {
            $swal.etc({icon: "warning", title: "내용을 입력해 주세요."});
        }

        $ajax.putMultiPart({
            data: $form.getData(),
            success: function (id) {
                $swal.update(function () {
                    $view.detail(id)
                })
            }
        })
    },

    delete: function (id) {
        $swal.confirm({
            icon: "warning",
            title: "삭제하시겠습니까?",
            confirmButtonText: "예",
            cancelButtonText: "아니오"
        }, function () {
            $ajax.delete({
                url: "/admin/notice/delete",
                data: id,
                success: function () {
                    $view.main();
                }
            })
        })
    },
    deleteAll: function () {
        let checked = $checkBox.getAllChecked();
        if (checked.length <= 0) {
            $swal.etc({
                title: "삭제할 항목을 선택해 주세요.",
                icon: "warning"
            })
            return false;
        }

        $swal.confirm({
            icon: "warning",
            title: "삭제하시겠습니까?",
            confirmButtonText: "예",
            cancelButtonText: "아니오"
        }, function () {
            $ajax.delete({
                url: "/admin/notice/deleteAll",
                data: checked
            })
        })
    }
}

pageObj.pageStart = function () {
    pageObj.init();
}

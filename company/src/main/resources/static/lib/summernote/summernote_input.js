var imgIdList = [];
$(document).ready(function () {
    $("#content").summernote({
        height: 500,
        width: 1400,
        minHeight: null,
        maxHeight: null,
        focus: true,
        toolbar: [
            ['fontname', ['fontname']],
            ['fontsize', ['fontsize']],
            ['style', ['hr', 'bold', 'italic', 'underline', 'strikethrough', 'clear']],
            ['color', ['forecolor', 'color']],
            ['table', ['table']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['height', ['height']],
            ['insert', ['picture', 'video']]
        ],
        fontNames: ['맑은 고딕', '궁서', '굴림체', '굴림', '돋움체', '바탕체', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New'],
        fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
        callbacks: {
            onImageUpload: function (files) {
                for (let i = 0; i < files.length; i++) {
                    if (ACTIONS.SIZE_CHECK(files[i]) === false) {
                        alert("파일 사이즈는 30MB 이내로 등록 가능합니다.");
                        return false;
                    }

                    if (ACTIONS.FILE_VALIDATION(files[i]) === false) {
                        alert("이미지 파일만 업로드할 수 있습니다.");
                        return false;
                    }
                }
                for (let i = 0; i < files.length; i++) {
                    ACTIONS.FILE_CHECK(files[i], this);
                }
            },
            onImageLinkInsert: function (url) {
                let img = $('<img>').attr({src: url});
                $('#content').summernote('insertNode', img[0]);
            },
            onPaste: function (e) {
                let clipboardData = e.originalEvent.clipboardData;
                if (clipboardData && clipboardData.items && clipboardData.items.length) {
                    let item = clipboardData.items[0];
                    if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
                        e.preventDefault();
                    }
                }
            }
        },
    })

    $('#content').summernote('fontName', '맑은 고딕');

    let ACTIONS = {
        FILE_CHECK: function (file, editor) {
            let formData = new FormData();
            formData.append("file", file);

            $.ajax({
                type: "POST",
                enctype: "multipart/form-data",
                url: "/admin/homepage/file/saveFiles",
                data: formData,
                processData: false,
                contentType: false,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader($("meta[name='_csrf_header']").attr("content"), $("meta[name='_csrf']").attr("content"));
                },
                success: function (data) {
                    for (let i = 0; i < data.length; i++) {
                        let fileId = data[i];
                        imgIdList.push(fileId);

                        $(editor).summernote('insertImage', '/admin/homepage/file/preview/' + fileId)

                        $("#hidden_img_box").append(`<input type="hidden" id="img_${fileId}" name="imgIdList[]" value="${fileId}">`);
                    }
                },
                error: function () {
                    alert("서버오류로 지연되고있습니다. 잠시 후 다시 시도해주시기 바랍니다.");
                    return false;
                }
            });
        },
        SIZE_CHECK: function (file) {
            if (file.size) {
                let maxSize = 30 * 1024 * 1024;
                return file.size <= maxSize;
            }
        },
        FILE_VALIDATION: function (file) {
            let fileLength = file.name.length;
            let lastDot = file.name.lastIndexOf('.') + 1;
            let fileExt = file.name.substring(lastDot, fileLength).toLowerCase();

            let result = true;
            if ($.inArray(fileExt, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                result = false;
            }

            return result;
        }
    }
});
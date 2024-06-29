$alert = function () {
    /*
        옵션 예시

        options = {
            height: 400,
            width: 400,
            type: "success",
            button: false,
            timeout: 300,
            autoClose: true,
            message: "데이터 저장이 완료되었습니다."
        };
    */

    // type은 info, success, warning, error 4가지
    var defaultOptions = {
        height: "400px",
        width: "400px",
        button: true,
        timeout: 2000,
        autoClose: true,

        type: "success",
        message: "Successfully!"
    };

    var open = function (options) {
        defaultOptions = $.extend({}, defaultOptions, options);
        let alertCode = this.alertCode(defaultOptions);
        $(".container").append(alertCode);

        if (defaultOptions.button) {
            setButton();
        }

        if (defaultOptions.autoClose) {
            autoClose(defaultOptions.timeout);
        }

        setStyle(defaultOptions);
    }

    var close = function () {
        $("#alert_modal").remove();
    }

    var autoClose = function (timeout) {
        setTimeout(function () {
            $("#alert_modal").remove();
        }, timeout)
    }

    var setButton = function () {
        let btn =
            `
                <div class="alert_btn_div">
                    <button type="button" onclick="$alert().close()">닫기</button>
                </div>
                `;
        $("#alert_modal").append(btn);
    }

    var setStyle = function (options) {
        let height = options.height;
        let width = options.width;
        $("#alert_modal").css({height: height, width: width});
    }

    var alertCode = function (options) {
        let msg = options.message;
        // 이미지 경로 예시
        let img = "/image/alert/success.jpg";
        switch (options.type) {
            case "info":
                img = "/image/alert/info.jpg";
                break;

            case "success":
                img = "/image/alert/success.jpg";
                break;

            case "warning":
                img = "/image/alert/warning.jpg";
                break;

            case "error":
                img = "/image/alert/error.jpg";
                break;
        }

        let main =
            `
                <div id="alert_modal" class="alert_modal">
                    <div class="alert_img_div">
                        <img src="${img}" alt="알림 모달 이미지">
                    </div>
                    <div class="alert_main_div">
                        <span>${msg}</span>
                    </div>
                </div>
            `;

        if (options.button) {
            let btn =
                `
                <div class="alert_btn_div">
                    <button type="button" onclick="al.close()">닫기</button>
                </div>
                `;
            $(main).append(btn);
        }
        return main;
    }

    return {
        "open": open,
        "close": close,
        "autoClose": autoClose,
        "setStyle": setStyle,
        "alertCode": alertCode
    }
}

$confirm = function () {
    var cnf;

    var defaultOptions = {
        height: "400px",
        width: "400px",
        message: "진행하시겠습니까?"
    };

    var open = function (options) {
        defaultOptions = $.extend({}, defaultOptions, options);
        let confirmCode = this.confirmCode(defaultOptions);
        $(".container").append(confirmCode);
        setStyle(defaultOptions);
    }

    var close = function () {
        $("#confirm_modal").remove();
    }

    var setStyle = function (options) {
        let height = options.height;
        let width = options.width;
        $("#confirm_modal").css({height: height, width: width});
    }

    var getData = function () {
        return cnf;
    }

    var setData = function (value) {
        cnf = value;
        this.close();
    }

    var confirmCode = function (options) {
        let msg = options.message;
        return `
                <div id="confirm_modal" class="confirm_modal">
                    <div class="confirm_img_div">
                        <img src="/image/confirm/configm.jpg" alt="대화 모달 이미지">
                    </div>
                    <div class="confirm_main_div">
                        <span>${msg}</span>
                    </div>
                    <div class="confirm_btn_div">
                    <button type="button" onclick="conf.setData(true)">예</button>
                    <button type="button" onclick="conf.setData(false)">아니오</button>
                </div>
                </div>
            `;
    }

    return {
        "open": open,
        "close": close,
        "setStyle": setStyle,
        "getData": getData,
        "setData": setData,
        "confirmCode": confirmCode
    }
}

let al = $alert();
let conf = $confirm();
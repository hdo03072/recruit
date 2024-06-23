package com.toy.recruit.core.parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MessageWithStatus {

    private boolean result;

    private String message;

    private static MessageWithStatus toObj(boolean result, String msg) {
        MessageWithStatus ms = new MessageWithStatus();
        ms.result = result;
        ms.message = msg;

        return ms;
    }
}

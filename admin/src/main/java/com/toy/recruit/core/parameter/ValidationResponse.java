package com.toy.recruit.core.parameter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Getter @Setter
public class ValidationResponse {
    private List<GlobalError> globalErrors;
    private List<FieldError> fieldErrors;

    public ValidationResponse(String code, String message) {
        globalErrors = Arrays.asList(new GlobalError(code, message));
    }

    public ValidationResponse(BindingResult bindingResult, MessageSource messageSource, Locale locale) {
        this.globalErrors = bindingResult.getGlobalErrors().stream().map(error -> new GlobalError(error, messageSource, locale))
                .collect(Collectors.toList());

        this.fieldErrors = bindingResult.getFieldErrors().stream().map(error -> new FieldError(error, messageSource, locale))
                .collect(Collectors.toList());
    }

    @Getter @Setter
    static class GlobalError {
        private String code;
        private String message;

        public GlobalError(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public GlobalError(ObjectError objectError, MessageSource messageSource, Locale locale) {
            this.code = objectError.getCode();
            this.message = messageSource.getMessage(objectError, locale);
        }
    }

    @Getter @Setter
    static class FieldError {
        private String field;

        private String code;

        private Object rejectedValue;

        private String message;

        public FieldError(org.springframework.validation.FieldError fieldError, MessageSource messageSource, Locale locale) {
            this.field = fieldError.getField();
            this.code = fieldError.getCode();
            this.rejectedValue = fieldError.getRejectedValue();
            this.message = messageSource.getMessage(fieldError, locale);
        }
    }
}


package cn.fei.item.assistant.exception;

import lombok.Getter;

@Getter
public class JsonException extends RuntimeException {
    private String messageCode;
    private String message;

    public JsonException(String messageCode, String message) {
        this(message);
        this.messageCode = messageCode;
        this.message = message;
    }

    public JsonException(String message) {
        super(message);
    }
}

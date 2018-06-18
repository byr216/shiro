package cn.fei.item.assistant.enums;

import lombok.Getter;

/**
 * @author liufei
 * @date 2018/6/18
 * 系统消息枚举
 */
@Getter
public enum MessageCodeEnum {
    SUCCESS("0", "success"),
    FAIL("-1", "未知错误"),
    //------------账号异常------------
    PLEASE_LOGIN("1000", "请登录"),
    INCORRECT_CREDENTIALS("1001", "密码错误"),
    UNKNOWN_ACCOUNT("1002", "账号不存在"),
    LOCKED_ACCOUNT("1003", "账号被锁定"),
    AUTHENTICATION("1004", "该账号禁止登陆"),
    ALREADY_LOGGED("1005", "账号已登录"),
    //--------------------
    PERMISSION_RELOAD_FAIL("2001", "权限重载异常!"),
    NO_PERMISSION("2002", "没有访问权限");
    private final String messageCode;
    private final String message;

    private MessageCodeEnum(String messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }
}

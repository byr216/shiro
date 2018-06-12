package cn.fei.item.domain;

import lombok.*;

/**
 * 登录信息
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo extends BaseDomain{

    public static int IS_ADMIN = 1;//超级管理员
    public static int IS_NOT_ADMIN = 0;//不是超级管理员

    public static int STATE_NORMAL=0;//账号正常
    public static int STATE_FREEZE = 1;//账号冻结

    private Long userId;

    private String username;

    private String password;

    private int isAdmin = IS_NOT_ADMIN;

    private int state = STATE_NORMAL;

}
package cn.fei.item.domain.entity;

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



    public static int STATE_NORMAL=0;//账号正常
    public static int STATE_FREEZE = 1;//账号冻结

    private Long userId;

    private String username;

    private String password;



    private int state = STATE_NORMAL;

}
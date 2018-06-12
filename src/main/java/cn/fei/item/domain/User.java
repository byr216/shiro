package cn.fei.item.domain;

import lombok.*;

/**
 * 角色
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseDomain{

    private String username;

    private String password;

    private String realName;

    private String telephone;

}
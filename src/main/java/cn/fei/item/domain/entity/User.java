package cn.fei.item.domain.entity;

import lombok.*;

import java.util.List;

/**
 * 角色
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseDomain {
    public static int IS_ADMIN = 1;//超级管理员
    public static int IS_NOT_ADMIN = 0;//不是超级管理员

    private String username;

    private String password;

    private String realName;

    private String telephone;

    private int isAdmin = IS_NOT_ADMIN;

    //获取用户角色
    private List<Role> roleList;

    //权限集合
    private List<Permission> permissionList;

}
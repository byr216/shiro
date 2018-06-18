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
public class Role extends BaseDomain {

    private String name;
    //获取角色权限
    private List<Permission> permissionList;

}
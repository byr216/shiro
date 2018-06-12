package cn.fei.item.domain;

import lombok.*;

/**
 * 角色-权限关系
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission extends BaseDomain{

    private Long roleId;

    private Long permissionId;

}
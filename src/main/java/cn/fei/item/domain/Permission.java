package cn.fei.item.domain;

import lombok.*;

/**
 * 权限
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Permission extends BaseDomain{

    private String permissionName;

    private String permissionValue;

}
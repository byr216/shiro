package cn.fei.item.domain;

import lombok.*;

/**
 * 用户-角色关系
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BaseDomain{

    private Long userId;

    private Long roleId;

}
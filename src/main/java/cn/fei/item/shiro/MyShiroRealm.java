package cn.fei.item.shiro;

import cn.fei.item.domain.entity.LoginInfo;
import cn.fei.item.domain.entity.Permission;
import cn.fei.item.domain.entity.Role;
import cn.fei.item.domain.entity.User;
import cn.fei.item.service.ILoginInfoService;
import cn.fei.item.utils.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liufei
 * @date 2018/6/6 15:35
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "MyShiroRealm";
    }

    @Autowired
    private ILoginInfoService loginInfoService;

    @Value("${shiro.credentials.salt}")
    private String salt;

    /**
     * 登录验证
     *
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        List<LoginInfo> loginInfos = loginInfoService.getUserByUsername(utoken.getUsername());
        if (loginInfos != null && loginInfos.size() != 0) {
            LoginInfo dbUser = loginInfos.get(0);
            ByteSource salt = ByteSource.Util.bytes(this.salt);
            return new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), salt, getName());
        }
        return null;
    }


    /**
     * 权限验证
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        User user = UserUtils.getUser();
        List<String> roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //判断是否是超级管理员
        if (user.getIsAdmin() == User.IS_ADMIN) {
            //获取系统所有权限
            roles.add("admin");
            permissions.add("*:*");
        } else {
            //获取当前用户角色和权限
            for (Role role : user.getRoleList()) {
                roles.add(role.getName());
                for (Permission permission : role.getPermissionList()) {
                    permissions.add(permission.getPermissionValue());
                }
            }
        }
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
    }

}

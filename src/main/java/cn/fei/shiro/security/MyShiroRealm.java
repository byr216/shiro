package cn.fei.shiro.security;

import cn.fei.shiro.domain.User;
import cn.fei.shiro.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private IUserService userService;

    /**
     * 登录验证
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        List<User> userList = userService.getUserByUsername(utoken.getUsername());
        if(userList!=null&&userList.size()!=0){
            User dbUser = userList.get(0);
            return new SimpleAuthenticationInfo(dbUser,dbUser.getPassword(),getName());
        }
        return null;
    }

    /**
     * 权限验证
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

}

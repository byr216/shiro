package cn.fei.item.shiro;

import cn.fei.item.domain.entity.LoginInfo;
import cn.fei.item.service.ILoginInfoService;
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
    private ILoginInfoService loginInfoService;

    /**
     * 登录验证
     * @param token
     * @return
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken utoken = (UsernamePasswordToken) token;
        List<LoginInfo> loginInfos = loginInfoService.getUserByUsername(utoken.getUsername());
        if(loginInfos!=null&&loginInfos.size()!=0){
            LoginInfo dbUser = loginInfos.get(0);
            //密码加密待完善
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

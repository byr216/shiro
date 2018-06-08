package cn.fei.item.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import java.io.Serializable;

/**
 * @author liufei
 * @date 2018/6/8 15:15
 */
public class MySessionDao extends CachingSessionDAO{
    @Override
    protected void doUpdate(Session session) {
        System.err.println("doUpdate");
    }

    @Override
    protected void doDelete(Session session) {
        System.err.println("doDelete");
    }

    @Override
    protected Serializable doCreate(Session session) {
//        super.doCreate(session);
        System.err.println("doCreate");
        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        System.err.println("doReadSession");
        return null;
    }
}

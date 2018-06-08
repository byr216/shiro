package cn.fei.item.service;

import cn.fei.item.domain.LoginInfo;

import java.util.List;

/**
 * @author liufei
 * @date 2018/6/6 15:59
 */
public interface ILoginInfoService {

    /**
     * 根据用户名获取用户
     */
    List<LoginInfo> getUserByUsername(String username);
}

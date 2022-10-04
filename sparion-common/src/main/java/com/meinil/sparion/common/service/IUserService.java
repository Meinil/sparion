package com.meinil.sparion.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meinil.sparion.common.entity.User;
import com.meinil.sparion.common.model.UserLoginModel;
import com.meinil.sparion.common.vo.UserLoginVO;

/**
 * @author Meinil
 * @date 2022/10/3
 * @description
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录
     * @param userLoginVO 登录所需信息
     * @return 用户的信息
     */
    UserLoginModel login(UserLoginVO userLoginVO);

    /**
     * 登出
     * @return 是否登出成功
     */
    Boolean logout();
}

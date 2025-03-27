package com.meinil.common.cache.constants;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description
 */
public class CacheConstants {

    private CacheConstants() {}

    /**
     * 用户登录信息
     */
    public final static String LOGIN_USER_KEY = "login_users:";

    /**
     * 登录账户密码错误次数
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

    /**
     * 验证码
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 接口权限
     */
    public static final String INTERFACE_PERMISSION = "interfaces_permission";
}

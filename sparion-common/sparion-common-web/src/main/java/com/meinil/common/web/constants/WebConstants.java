package com.meinil.common.web.constants;

/**
 * @author Meinil
 * @date 2025/2/26
 * @description
 */
public class WebConstants {

    private WebConstants() {}

    /**
     * token保存的请求头
     */
    public final static String TOKEN_HEADER = "Authorization";

    /**
     * jwt claims中保存的用户id key
     */
    public final static String JWT_CLAIM_USER_ID = "userId";

    /**
     * jwt claims中保存的过期时间key
     */
    public final static String JWT_CLAIM_EXP = "exp";
}

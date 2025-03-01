package com.meinil.auth.domain.vo;

/**
 * @author Meinil
 * @date 2025/2/28
 * @description
 */
public class CaptchaVO {

    private String uuid;

    /**
     * 验证码图片
     */
    private String img;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

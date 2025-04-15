package com.meinil.ai.domain.bo;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
public class AiChatAddBO {

    /**
     * 会话id
     */
    private Long id;

    /**
     * 本次提问提示词
     */
    @NotBlank(message = "request不能为空")
    private String request;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "request不能为空") String getRequest() {
        return request;
    }

    public void setRequest(@NotBlank(message = "request不能为空") String request) {
        this.request = request;
    }
}

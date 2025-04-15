package com.meinil.ai.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.meinil.common.mybatis.domain.BaseEntity;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description ai消息
 */
@TableName("ai_chat_message")
public class AiChatMessage extends BaseEntity {

    /**
     * 会话id
     */
    private Long chatId;

    /**
     * 会话消息
     */
    private String request;

    /**
     * 会话响应
     */
    private String response;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

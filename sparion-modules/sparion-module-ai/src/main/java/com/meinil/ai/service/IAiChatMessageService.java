package com.meinil.ai.service;

import com.meinil.ai.domain.entity.AiChatMessage;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
public interface IAiChatMessageService {
    /**
     * 根据会话id查询本次会话所有信息
     * @param chatId 会话id
     * @return
     */
    List<AiChatMessage> getMessageListByChatId(Long chatId);

    /**
     * 保存一次会话信息
     * @param chatMessage
     */
    int save(AiChatMessage chatMessage);
}

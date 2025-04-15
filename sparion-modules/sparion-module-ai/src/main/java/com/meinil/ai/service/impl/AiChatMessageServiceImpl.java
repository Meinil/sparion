package com.meinil.ai.service.impl;

import com.meinil.ai.domain.entity.AiChatMessage;
import com.meinil.ai.mapper.AiChatMessageMapper;
import com.meinil.ai.service.IAiChatMessageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
@Service
public class AiChatMessageServiceImpl implements IAiChatMessageService {

    private final AiChatMessageMapper baseMapper;

    public AiChatMessageServiceImpl(AiChatMessageMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    public List<AiChatMessage> getMessageListByChatId(Long chatId) {
        return baseMapper.selectListByChatId(chatId);
    }

    @Override
    public int save(AiChatMessage chatMessage) {
        return baseMapper.insert(chatMessage);
    }
}

package com.meinil.ai.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meinil.ai.domain.entity.AiChatMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
public interface AiChatMessageMapper extends BaseMapper<AiChatMessage> {

    /**
     * 根据会话id查询会话信息
     * @param chatId 会话id
     * @return 消息列表
     */
    List<AiChatMessage> selectListByChatId(@Param("chatId") Long chatId);
}

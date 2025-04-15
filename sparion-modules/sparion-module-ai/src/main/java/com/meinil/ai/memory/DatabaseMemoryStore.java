package com.meinil.ai.memory;

import com.meinil.ai.domain.entity.AiChatMessage;
import com.meinil.ai.mapper.AiChatMapper;
import com.meinil.ai.mapper.AiChatMessageMapper;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author meinil
 * @date 2025/4/14
 * @description 数据库memory实现
 */
@Component
public class DatabaseMemoryStore implements ChatMemoryStore {

    private final static Logger log = LoggerFactory.getLogger(DatabaseMemoryStore.class);

    private final AiChatMapper chatMapper;

    private final AiChatMessageMapper chatMessageMapper;

    public DatabaseMemoryStore(AiChatMapper chatMapper, AiChatMessageMapper chatMessageMapper) {
        this.chatMapper = chatMapper;
        this.chatMessageMapper = chatMessageMapper;
    }

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        if (Objects.isNull(memoryId)) {
            return List.of();
        }
        List<AiChatMessage> messages = chatMessageMapper.selectListByChatId(Long.valueOf(memoryId.toString()));

        return messages.stream()
                .map(message -> List.of(UserMessage.from(message.getRequest()), AiMessage.from(message.getResponse())))
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        log.info("Updating messages from {} to {}", memoryId, messages);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        chatMapper.deleteById(Long.valueOf(memoryId.toString()));
    }
}

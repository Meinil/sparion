package com.meinil.ai.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.meinil.ai.domain.bo.AiChatAddBO;
import com.meinil.ai.domain.entity.AiChat;
import com.meinil.ai.domain.entity.AiChatMessage;
import com.meinil.ai.mapper.AiChatMapper;
import com.meinil.ai.memory.DatabaseMemoryStore;
import com.meinil.ai.service.IAiChatMessageService;
import com.meinil.ai.service.IAiChatService;
import com.meinil.ai.service.IAssistant;
import com.meinil.common.web.exception.SparionException;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
@Service
public class AiChatServiceImpl implements IAiChatService {

    private final static Logger log = LoggerFactory.getLogger(AiChatServiceImpl.class);

    private final StreamingChatLanguageModel chatLanguageModel;

    private final ChatLanguageModel languageModel;

    private final TransactionTemplate transactionTemplate;

    private final IAiChatMessageService chatMessageService;

    private final AiChatMapper baseMapper;

    private final DatabaseMemoryStore databaseMemoryStore;

//    private final IAssistant assistant;

    public AiChatServiceImpl(StreamingChatLanguageModel chatLanguageModel, ChatLanguageModel languageModel, TransactionTemplate transactionTemplate, IAiChatMessageService chatMessageService, AiChatMapper baseMapper, DatabaseMemoryStore databaseMemoryStore) {
        this.chatLanguageModel = chatLanguageModel;
        this.languageModel = languageModel;
        this.transactionTemplate = transactionTemplate;
        this.chatMessageService = chatMessageService;
        this.baseMapper = baseMapper;
        this.databaseMemoryStore = databaseMemoryStore;
//        this.assistant = assistant;
    }

//    @Override
//    public Flux<String> chat(AiChatAddBO aiChatAddBO) {
//        List<ChatMessage> messages = new ArrayList<>(){{
//            add(UserMessage.from(aiChatAddBO.getRequest()));
//        }};
//
//        // 保存本次请求内容
//        AiChatMessage chatMessage = new AiChatMessage();
//        AiChat aiChat;
//
//        // 查询上下文
//        Long id = aiChatAddBO.getId();
//
//        if (Objects.nonNull(id)) {
//            aiChat = null;
//            chatMessage.setChatId(id);
//            List<ChatMessage> list = chatMessageService.getMessageListByChatId(id)
//                    .stream()
//                    .map(item -> List.of(UserMessage.from(item.getRequest()), AiMessage.from(item.getResponse()))).flatMap(List::stream)
//                    .toList();
//            messages.addAll(list);
//        } else {
//            aiChat = new AiChat();
//            aiChat.setId(IdWorker.getId());
//            chatMessage.setChatId(aiChat.getId());
//        }
//
//        return Flux.create(sink -> {
//            chatLanguageModel.chat(messages, new StreamingChatResponseHandler() {
//                final StringBuilder sb = new StringBuilder();
//                @Override
//                public void onPartialResponse(String partialResponse) {
//                    sb.append(partialResponse);
//                    log.info("partial response: {}", partialResponse);
//                    sink.next(partialResponse);
//                }
//
//                @Override
//                public void onCompleteResponse(ChatResponse completeResponse) {
//                    transactionTemplate.execute(status -> {
//                        try {
//                            if (Objects.nonNull(aiChat)) {
//                                baseMapper.insert(aiChat);
//                            }
//                            chatMessage.setRequest(aiChatAddBO.getRequest());
//                            chatMessage.setResponse(sb.toString());
//                            chatMessageService.save(chatMessage);
//                            return "success";
//                        } catch (Exception e) {
//                            status.setRollbackOnly();
//                            throw new SparionException(e);
//                        }
//                    });
//                    sink.complete();
//                }
//
//                @Override
//                public void onError(Throwable error) {
//                    sink.error(error);
//                }
//            });
//        });
//    }

    @Override
    public SseEmitter chat(AiChatAddBO aiChatAddBO) {
        SseEmitter emitter = new SseEmitter();

        // 查询上下文
        ChatMemoryProvider memoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(databaseMemoryStore)
                .build();

        // 创建对话对象
        IAssistant assistant = AiServices.builder(IAssistant.class)
                .streamingChatLanguageModel(chatLanguageModel)
                .chatMemoryProvider(memoryProvider)
                .build();


        TokenStream tokenStream = assistant.chat(aiChatAddBO.getId(), aiChatAddBO.getRequest());

        tokenStream.onPartialResponse(partial -> {
                    log.info("Partial response: {}", partial);
                    try {
                        emitter.send(SseEmitter.event().data(partial));
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                        throw new SparionException(e);
                    }
                })
                .onCompleteResponse(chatResponse -> {
                    log.info("Complete response: {}", chatResponse.aiMessage());
                    emitter.complete();
                })
                .onError(emitter::completeWithError)
                .start();
        return emitter;
    }
}

package com.meinil.ai.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

/**
 * @author meinil
 * @date 2025/4/14
 * @description TODO
 */
//@AiService
public interface IAssistant {

    TokenStream chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}

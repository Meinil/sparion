package com.meinil.ai.service;

import com.meinil.ai.domain.bo.AiChatAddBO;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
public interface IAiChatService {

    SseEmitter chat(AiChatAddBO aiChatAddBO);
}

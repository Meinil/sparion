package com.meinil.ai.controller;

import com.meinil.ai.domain.bo.AiChatAddBO;
import com.meinil.ai.service.IAiChatService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author Meinil
 * @date 2025/3/27
 * @description
 */
@RestController
@RequestMapping("/ai/chat")
public class ChatController {

    private final IAiChatService chatService;

    public ChatController(IAiChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chat(@RequestBody AiChatAddBO aiChatAddBO) {
        return chatService.chat(aiChatAddBO);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getChat(@RequestParam("id") Long id,  @RequestParam("request") String request) {
        AiChatAddBO aiChatAddBO = new AiChatAddBO();
        aiChatAddBO.setId(id);
        aiChatAddBO.setRequest(request);
        return chatService.chat(aiChatAddBO);
    }
}

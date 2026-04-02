package com.ly.ai.robot.spring.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


/**
 * @Author: ly
 * @Date: 2026/04/02 12:25
 * @Version: v1.0.0
 * @Description: DeepSeek 聊天
 **/
@RestController
@RequestMapping("/ai")
public class DeepSeekChatController {

    @Resource
    private DeepSeekChatModel chatModel;

    /**
     * 普通对话
     * @param message
     * @return
     */
    @GetMapping("/generate")
    public String generate(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 一次性返回结果
        return chatModel.call(message);
    }

    /**
     * produces = "text/html;charset=utf-8" :
     * text/html：告知客户端（浏览器/HTTP客户端）返回的是 HTML 格式文本;
     * charset=utf-8：字符集定义, 强制响应使用 UTF-8 编码，不然会出现中文乱码情况；
     * Prompt prompt = new Prompt(new UserMessage(message)): 构建提示词;
     * chatModel.stream() : 使用流式输出：
     * chatResponse.getResult().getOutput().getText() : 获取每个响应片段的文本内容，返回给前端；
     */
    /**
     * 流式对话
     * @param message
     * @return
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词
        Prompt prompt = new Prompt(new UserMessage(message));

        // 流式输出
        return chatModel.stream(prompt)
                .mapNotNull(chatResponse -> chatResponse.getResult().getOutput().getText());
    }

}


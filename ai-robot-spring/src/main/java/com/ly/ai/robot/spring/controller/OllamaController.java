package com.ly.ai.robot.spring.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/*
 * @Description:
 * @return:
 * @Author:  mock-l
 * @date:  2026/4/7 09:14
 * @FileName: OllamaController
 */
@RestController
@RequestMapping("/v3/ai")
public class OllamaController {

    @Resource
    private OllamaChatModel chatModel;

    /**
     * 普通对话
     * @param message
     * @return
     */
    @GetMapping("/generate1")
    public String generate1(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词，调用大模型
        ChatResponse chatResponse = chatModel.call(new Prompt(message));

        // 响应回答内容
        return chatResponse.getResult().getOutput().getText();
    }

    /**
     * 普通对话
     * @param message
     * @return
     */
    @GetMapping("/generate2")
    public String generate2(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词，调用大模型
        ChatResponse chatResponse = chatModel.call(new Prompt(
                message,
                OllamaOptions.builder()
                        .model("qwen3:14b") // 模型名称
                        .temperature(0.4) // 温度值
                        .build()
        ));

        // 响应回答内容
        return chatResponse.getResult().getOutput().getText();
    }

    /**
     * 流式对话
     * @param message
     * @return
     */
    @GetMapping(value = "/generateStream", produces = "text/html;charset=utf-8")
    public Flux<String> generateStream(@RequestParam(value = "message", defaultValue = "你是谁？") String message) {
        // 构建提示词
        Prompt prompt = new Prompt(new UserMessage(message));

        // 流式响应
        return this.chatModel.stream(prompt)
                .mapNotNull(chatResponse -> {
                    // 获取响应内容
                    String text = chatResponse.getResult().getOutput().getText();

                    // 处理换行
                    return StringUtils.isNotBlank(text) ? text.replace("\n", "<br>") : text;
                });
    }

}

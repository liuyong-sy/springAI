package com.ly.ai.robot.spring.config;

import com.ly.ai.robot.spring.advisor.MyLoggerAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Description:
 * @return:
 * @Author:  mock-l
 * @date:  2026/4/2 16:49
 * @FileName: ChatClientConfig
 */
@Configuration
public class ChatClientConfig {

    /**
     * 初始化 ChatClient 客户端
     * @param chatModel
     * @return
     */
    @Bean
    public ChatClient chatClient(DeepSeekChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem("请扮演AI Spring项目的客服人员")
                .defaultAdvisors(new SimpleLoggerAdvisor(), // 添加 Spring AI 内置的日志记录功能
                        new MyLoggerAdvisor()) // 添加自定义的日志打印 Advisor
                .build();
    }
}

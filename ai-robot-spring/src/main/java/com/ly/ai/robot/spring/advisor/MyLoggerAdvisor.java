package com.ly.ai.robot.spring.advisor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;

/*
 * @Description: 自定义日志输出
 * @return:
 * @Author:  mock-l
 * @date:  2026/4/2 17:37
 * @FileName: MyLoggerAdvisor
 */
@Slf4j
public class MyLoggerAdvisor implements CallAdvisor {

    /**
     * 在 Spring AI 中，CallAdvisor 和 StreamAdvisor是两种不同类型的 Advisor，
     * 分别用于处理 同步调用 和 流式调用 场景。它们的设计目的是为了适应不同的 AI 模型交互模式，
     * 提供灵活的请求拦截与响应处理能力。咱们这个日志记录 Advisor 仅支持一下同步调用场景，
     * 故只实现了 CallAdvisor 接口；
     */
    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
        log.info("## 请求入参: {}", chatClientRequest);
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        log.info("## 请求出参: {}", chatClientResponse);
        return chatClientResponse;
    }

    @Override
    public int getOrder() {
        return 1; // order 值越小，越先执行
    }

    @Override
    public String getName() {
        // 获取类名称
        return this.getClass().getSimpleName();
    }
}
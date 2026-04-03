```text
application.yml中部分配置的详细说明：

api-key : 填写刚刚申请的 DeepSeek Api Key；
base-url: 填写 DeepSeek 官方的 API 请求链接，可不填，默认值为 api.deepseek.com；
chat.options.models: 想要使用的模型名称，可访问 DeepSeek 官方文档：https://api-docs.deepseek.com/zh-cn/ ，来查看具体的模型名称：
指定 model='deepseek-chat' 即可调用 DeepSeek-V3 模型；
指定 model='deepseek-reasoner'，即可调用 DeepSeek-R1 模型；
temperature: 在机器学习模型中，temperature 参数用于控制生成文本的随机性和创造性。
值越低（接近 0），则表示输出更确定和保守，模型倾向于选择概率最高的词，适合需要准确性的场景；
值越高（如 0.8 或更高）， 增加随机性，生成内容更多样化、富有创造性；
```

```text
流式对话和非流式对话区别：
特性	流式对话	非流式对话（传统模式）
响应方式	逐词/逐句实时推送	一次性返回完整结果
延迟感知	首响应时间极低（毫秒级）	总生成时间后才响应（秒级）
网络占用	持续小流量传输	单次大流量传输
典型场景	聊天机器人、实时翻译	文本摘要、批量生成
用户体验	类似人类打字的过程	需等待完整结果加载
```

```text
Advisor 是 Spring AI 中的核心组件，用于在 AI 模型交互过程中动态拦截和增强请求与响应流，类似于面向切面编程（AOP）的拦截器。其核心目标是通过模块化设计提升 AI 应用的灵活性、安全性和扩展性。

通过它，可以实现功能如下：

请求与响应拦截：Advisor 能够拦截 AI 模型的输入和输出，允许开发者在请求发送前和响应返回后插入自定义逻辑，例如数据增强、敏感词过滤等；
功能模块化：
重复任务封装：例如日志记录、上下文记忆管理等通用功能可封装为可复用组件。
数据转换：优化发送给模型的数据结构（如添加上下文信息），并标准化响应格式。
业务规则注入：例如限制模型输出范围或根据业务需求引导生成内容。
跨模型兼容性：通过抽象接口设计，Advisor 可适配不同 AI 模型（如 DeepSeek、OpenAI 等），提升代码的可移植性。
```

```text
ChatMemoryConfig类描述：

通过 @Resource 注解注入一个 ChatMemoryRepository 的实例。它用于存储聊天会话，当我们没有自定义外部存储时（如 Cassandra、MySQL），默认情况下，Spring AI 使用的是 InMemoryChatMemoryRepository 实现类，会将消息存储在内存中的 ConcurrentHashMap 中。
MessageWindowChatMemory：它是具体的 ChatMemory 实现类，使用 “滑动窗口” 策略管理聊天记录。
滑动窗口策略：保留最近的 N 条消息（这里是50条，默认值20），超出数量限制时自动移除旧消息。因为在 AI 对话中，限制上下文长度，可以避免超出模型的 Token 限制。
```
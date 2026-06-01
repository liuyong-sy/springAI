<template>
  <div class="h-screen max-w-3xl mx-auto relative">
    <!-- 聊天记录区域 -->
    <div class="overflow-y-auto pb-24 pt-4 px-4">
        <!-- 用户提问消息（靠右） -->
        <div class="flex justify-end mb-4">
          <div class="quesiton-container">
            <p>你是谁</p>
          </div>
        </div>

        <!-- 大模型回复消息（靠左） -->
        <div class="flex mb-4">
          <!-- 头像 -->
          <div class="flex-shrink-0 mr-3">
            <div class="w-8 h-8 rounded-full flex items-center justify-center border border-gray-200">
              <SvgIcon name="deepseek-logo" customCss="w-5 h-5"></SvgIcon>
            </div>
          </div>
          <!-- 回复的内容 -->
          <div class="p-1 max-w-[80%] mb-2 grow">
            <p>我是DeepSeek Chat，由深度求索公司开发的智能AI助手！✨ 我可以帮你解答各种问题，无论是学习、工作，还是日常生活中的小困惑，都可以找我聊聊。有什么我可以帮你的吗？😊</p>
          </div>
        </div>
    </div>
    <!-- 提问输入框 -->
    <div class="absolute bottom-0 left-0 w-full mb-5">
      <div class="bg-gray-100 rounded-3xl px-4 py-3 mx-4 border border-gray-200 flex flex-col">
        <textarea 
          v-model="message" 
          placeholder="给小哈 AI 机器人发送消息"
          class="bg-transparent border-none outline-none w-full text-sm resize-none min-h-[24px]" 
          rows="2">
        </textarea>
        
        <!-- 发送按钮 -->
        <div class="flex justify-end">
          <button class="flex items-center justify-center bg-[#4d6bfe] rounded-full w-8 h-8 border border-[#4d6bfe] hover:bg-[#3b5bef] transition-colors">
            <SvgIcon name="up-arrow" customCss="w-5 h-5 text-white"></SvgIcon>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

import SvgIcon from '@/components/SvgIcon.vue'


// 输入的消息
const message = ref('')
</script>

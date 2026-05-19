<template>
  <div class="h-screen max-w-3xl mx-auto relative">
    <!-- 聊天记录区域 -->
    <div></div>
    <!-- 提问输入框 -->
    <div class="absolute bottom-0 left-0 w-full mb-5">
      <div class="bg-gray-100 rounded-3xl px-4 py-3 mx-4 border border-gray-200">
        <textarea 
          v-model="message" 
          placeholder="给小哈 AI 机器人发送消息"
          class="bg-transparent border-none outline-none w-full text-sm resize-none min-h-[24px]" 
          rows="2">
        </textarea>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

// 输入的消息
const message = ref('')
</script>

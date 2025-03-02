<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center">
    <div class="bg-white p-8 rounded shadow-md">
      <h2 class="text-2xl text-center mb-6">用户登录</h2>
      <form @submit.prevent="handleLogin" class="space-y-4">
        <div>
          <label for="username" class="block text-sm font-medium text-gray-700">用户名</label>
          <input
            type="text"
            id="username"
            v-model="username"
            class="mt-1 p-2 w-full border border-gray-300 rounded"
            required
          />
        </div>
        <div>
          <label for="password" class="block text-sm font-medium text-gray-700">密码</label>
          <input
            type="password"
            id="password"
            v-model="password"
            class="mt-1 p-2 w-full border border-gray-300 rounded"
            required
          />
        </div>
        <div>
          <button
            type="submit"
            :disabled="isLoading"
            class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 focus:outline-none focus:shadow-outline"
          >
            {{ isLoading ? '登录中...' : '登录' }}
          </button>
        </div>
      </form>
      <p class="text-red-500 text-sm mt-2">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { useAuthStore } from '@/store';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const username = ref('');
    const password = ref('');
    const isLoading = ref(false);
    const errorMessage = ref('');
    const router = useRouter();
    const handleLogin = async () => {
      errorMessage.value = ''; // 清空错误提示
      isLoading.value = true;

      try {
        // 使用 Axios 发送登录请求
        const response = await axios.post('http://localhost:8080/admin/login', {
          username: username.value,
          password: password.value,
        });

        console.log('登录成功:', response.data.data);
        const authStore = useAuthStore();
        authStore.setToken(response.data.data);
        console.log(authStore.getToken)
        router.push('/home')
        // 登录成功后的逻辑，例如跳转到首页
      } catch (error) {
        // 处理登录失败
        errorMessage.value = '登录失败，请检查用户名和密码';
        console.error('登录失败:', error);
      } finally {
        isLoading.value = false;
      }
    };

    return {
      username,
      password,
      isLoading,
      errorMessage,
      handleLogin,
    };
  },
};
</script>

<style>
/* 添加一些基础样式，确保组件美观 */
body {
  font-family: 'Arial', sans-serif;
}

input,
button {
  outline: none;
}

button:hover {
  cursor: pointer;
}
</style>
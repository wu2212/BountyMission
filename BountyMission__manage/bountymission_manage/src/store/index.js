// src/stores/authStore.js
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  // 定义状态
  state: () => ({
    token: null, // 初始 token 为 null
  }),

  // 定义获取器（计算属性）
  getters: {
    // 获取 token
    getToken: (state) => state.token,
  },

  // 定义操作方法
  actions: {
    // 设置 token
    setToken(newToken) {
      this.token = newToken;
    },

    // 清除 token
    clearToken() {
      this.token = null;
    },
  },
});
// src/stores/auth.js
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    token: null, // 存储 Token
    refreshToken: null, // 存储刷新 Token
    userInfo: {} // 存储用户信息
  }),
  actions: {
    setToken(token, refreshToken = null) {
      this.token = token;
      this.refreshToken = refreshToken;
    },
    clearToken() {
      this.token = null;
      this.refreshToken = null;
      this.userInfo = {};
    },
    setUserInfo(userInfo) {
      this.userInfo = userInfo;
    }
  },
  persist: {
    enabled: true,
    strategies: [
      {
        key: 'auth',
        storage: localStorage // 使用 localStorage 持久化存储
      }
    ]
  }
});
// src/api/axios.js
import axios from 'axios';
import { useAuthStore } from '@/stores/auth'; // 假设你已经创建了 auth Store

const authStore = useAuthStore();
const service = axios.create({
  baseURL: '/api', // 替换为你的 API 基地址
  timeout: 5000 // 请求超时时间
});

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    // 如果是登录请求，不需要 Token
    if (config.url?.includes('/login')) {
      return config;
    }

    // 从 Store 中获取 Token
    const token = authStore.token;
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
service.interceptors.response.use(
  (response) => {
    // 如果响应中包含新的 Token，更新 Store
    if (response.data.token) {
      authStore.setToken(response.data.token);
    }

    return response;
  },
  async (error) => {
    const { response } = error;

    if (response && response.status === 401) {
      // Token 过期，可以在这里处理（例如跳转到登录页）
      authStore.clearToken(); // 清除 Token
      // 重定向到登录页
      window.location.href = '/login';
    }

    return Promise.reject(error);
  }
);

export default service;
// src/axios.js
import axios from 'axios';
import { useAuthStore } from '@/store/index';

// 创建 Axios 实例
const api = axios.create({
  baseURL: 'http://localhost:8080', // 后端 API 的基础路径
  timeout: 5000, // 请求超时时间
  withCredentials: true
});

// 请求拦截器
api.interceptors.request.use(
  (config) => {
    const authStore = useAuthStore(); // 获取 Pinia store
    const token = authStore.getToken; // 从 Pinia 中获取 token
    console.log('Current Token in store:', token); 
    if (token) {
      config.headers.Authorization = token; // 将 token 添加到请求头
      console.log(config.headers.Authorization)
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
api.interceptors.response.use(
  (response) => {
    const authStore = useAuthStore(); // 获取 Pinia store
    const newToken = response.headers['new-token']; // 假设后端在响应头中返回新的 token

    if (newToken) {
      authStore.setToken(newToken); // 将新的 token 存储到 Pinia 中
    }
    return response;
  },
  (error) => {
    // 处理响应错误（如 401、500 等）
    return Promise.reject(error);
  }
);

export default api;
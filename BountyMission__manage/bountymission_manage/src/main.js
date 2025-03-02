import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { createPinia } from 'pinia';
import 'element-plus/dist/index.css';
// 创建 Vue 应用实例
const app = createApp(App);

// 创建 Pinia 实例并安装到应用中
const pinia = createPinia();
app.use(pinia);

// 安装 Vue Router
app.use(router);

// 挂载应用到 DOM
app.mount('#app');
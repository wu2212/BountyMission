import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { createPinia } from 'pinia';

const pinia = createPinia();
App.use(pinia);
createApp(App).use(router).mount('#app')

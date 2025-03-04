<template>
  <div class="flex items-center justify-center min-h-screen">
    <div class="text-center">
      <h1>首页</h1>
      <el-button type="primary" @click="handleQuery">发送查询请求</el-button>
      <div v-if="queryResult" class="mt-4">
        <h2>查询结果</h2>
        <div v-for="(item, index) in queryResult" :key="index" class="mt-2">
          <p>ID: {{ item.id }}</p>
          <p>姓名: {{ item.name }}</p>
          <p>性别: {{ item.gender }}</p>
          <p>手机: {{ item.phone }}</p>
          <p>所属单位: {{ item.orgName }}</p>
          <p>创建时间: {{ new Date(item.createTime).toLocaleDateString() }}</p>
          <hr />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ElMessage } from 'element-plus';
import{ref} from 'vue'
//import axios from 'axios';
import request from '@/interceptor/index'
const queryResult = ref(null);

const handleQuery = async () => {
  try {
    const response = await request.get('http://localhost:8080/admin/find');

    if (response.status === 200) {
      console.log('查询成功:', response.data);
      queryResult.value = response.data;
      ElMessage.success('查询成功，数据已加载！');
    } else {
      ElMessage.error('查询失败，请检查网络或后端服务！');
    }
  } catch (error) {
    ElMessage.error('请求失败，请检查网络或后端服务！');
    console.error('查询请求失败:', error);
  }
};
</script>

<style scoped>
.el-button {
  margin-top: 20px;
}
</style>
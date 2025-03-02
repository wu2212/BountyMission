const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/admin': {
        target: 'http://localhost:8080', // 后端服务的地址
        changeOrigin: true, // 修改请求的 origin
        pathRewrite: {
          '^/admin': '/admin' // 如果需要重写路径
        }
      }
    }
  }
});
<template>
  <div class="login-container">
    <div class="login-box">
      <el-tabs v-model="activeTab" stretch>
        <!-- 账号密码登录 -->
        <el-tab-pane label="账号登录" name="account">
          <el-form :model="loginForm" :rules="loginRules" ref="loginFormRef">
            <el-form-item prop="username">
              <el-input v-model="loginForm.username" placeholder="请输入账号" />
            </el-form-item>
            <el-form-item prop="password">
              <el-input
                v-model="loginForm.password"
                type="password"
                placeholder="请输入密码"
                show-password
              />
            </el-form-item>
            <el-button type="primary" @click="handleLogin">登录</el-button>
          </el-form>
        </el-tab-pane>

        <!-- 短信验证码登录 -->
        <el-tab-pane label="短信登录" name="sms">
          <el-form :model="smsForm" :rules="smsRules" ref="smsFormRef">
            <el-form-item prop="phone">
              <el-input v-model="smsForm.phone" placeholder="请输入手机号" />
            </el-form-item>
            <el-form-item prop="code">
              <div class="code-input">
                <el-input v-model="smsForm.code" placeholder="请输入验证码" />
                <el-button
                  :disabled="smsCountdown > 0"
                  @click="sendSmsCode"
                >
                  {{ smsCountdown ? `${smsCountdown}秒后重试` : '获取验证码' }}
                </el-button>
              </div>
            </el-form-item>
            <el-button type="primary" @click="handleSmsLogin">登录</el-button>
          </el-form>
        </el-tab-pane>
      </el-tabs>

      <div class="register-link">
        <el-button link @click="showRegisterDialog">立即注册</el-button>
      </div>
    </div>

    <!-- 注册弹窗 -->
    <el-dialog v-model="registerVisible" title="用户注册" width="500px">
      <el-form
        :model="registerForm"
        :rules="registerRules"
        ref="registerFormRef"
        label-width="80px"
      >
        <!-- <el-form-item label="账号" prop="username">
          <el-input v-model="registerForm.username" />
        </el-form-item> -->
        <el-form-item label="手机号" prop="username">
          <el-input v-model="registerForm.username" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="registerForm.password" type="password" show-password />
        </el-form-item>
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="registerForm.nickname" />
        </el-form-item>
        <el-form-item label="学校" prop="school">
          <el-select
            v-model="registerForm.school"
            filterable
            remote
            reserve-keyword
            placeholder="请输入学校名称"
            :remote-method="searchSchool"
            :loading="schoolLoading"
          >
            <el-option
              v-for="item in schoolList"
              :key="item.id"
              :label="item.schoolName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        
        <el-form-item label="验证码" prop="code">
          <div class="code-input">
            <el-input v-model="registerForm.code" />
            <el-button
              :disabled="registerCountdown > 0"
              @click="sendRegisterCode"
            >
              {{ registerCountdown ? `${registerCountdown}秒后重试` : '获取验证码' }}
            </el-button>
          </div>
        </el-form-item>
        <el-button type="primary" @click="handleRegister">立即注册</el-button>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'

import { SchoolList ,SmsCode,register,login,smslogin} from '@/api/user/login'
import { useRouter } from 'vue-router'
const router = useRouter()
// 登录相关
const activeTab = ref('account')
const loginForm = reactive({
  username: '',
  password: ''
})
const loginFormRef = ref()

// 短信登录相关
const smsForm = reactive({
  phone: '',
  code: ''
})
const smsFormRef = ref()
const smsCountdown = ref(0)

// 注册相关
const registerVisible = ref(false)
const registerForm = reactive({
  username: '',
  password: '',
  nickname: '',
  school: '',
  code: ''
})
const initialFormState = {
  username: '',
  password: '',
  nickname: '',
  school: '',
  code: ''
};
const registerFormRef = ref()
const schoolList = ref([])
const schoolLoading = ref(false)
const registerCountdown = ref(0)

// 验证规则
const validatePhone = (rule, value, callback) => {
  if (!/^1[3-9]\d{9}$/.test(value)) {
    callback(new Error('请输入正确的手机号码'))
  } else {
    callback()
  }
}

const loginRules = {
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
}

const smsRules = {
  phone: [{ required: true, validator: validatePhone, trigger: 'blur' }],
  code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
}

const registerRules = {
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
  nickname: [{ required: true, message: '请输入昵称', trigger: 'blur' }],
  school: [{ required: true, message: '请选择学校', trigger: 'change' }],
  phone: [{ required: true, validator: validatePhone, trigger: 'blur' }],
  code: [{ required: true, message: '请输入验证码', trigger: 'blur' }]
}

// 学校搜索
const searchSchool = async (keyword) => {
  if (keyword) {
    schoolLoading.value = true
    try {
      const res = await SchoolList(keyword)
      console.log(res.data.data)
      schoolList.value = res.data.data
    } finally {
      schoolLoading.value = false
    }
  }
}

// 发送验证码
const startCountdown = (type) => {
  let count = 60
  const timer = setInterval(() => {
    count--
    if (type === 'sms') {
      smsCountdown.value = count
    } else {
      registerCountdown.value = count
    }
    if (count <= 0) clearInterval(timer)
  }, 1000)
}

const sendSmsCode = async () => {
  try {
    await SmsCode(smsForm.phone)
    ElMessage.success('验证码已发送')
    startCountdown('sms')
  } catch (error) {
    ElMessage.error('验证码发送失败')
  }
}

const sendRegisterCode = async () => {
  try {
    await SmsCode(registerForm.username )
    ElMessage.success('验证码已发送')
    startCountdown('register')
  } catch (error) {
    ElMessage.error('验证码发送失败')
  }
}

// 注册处理
const showRegisterDialog = () => {
  registerVisible.value = true
}

const handleRegister = async () => {
  try {
    await registerFormRef.value.validate()
    await register(registerForm)
    ElMessage.success('注册成功')
    console.log('正在提交的学校ID:', registerForm.school)
    Object.assign(registerForm, initialFormState)
    registerVisible.value = false
  } catch (error) {
    ElMessage.error('注册失败')
  }
}

// 登录处理
const handleLogin = async () => {
  try {
    await loginFormRef.value.validate()
    await login(loginForm)
    router.push('/home')
    ElMessage.success('登录成功')
  } catch (error) {
    console.log(error
      
    )
    ElMessage.error()
  }
}
//短信验证码登录
const handleSmsLogin = async () => {
  try {
    await smsFormRef.value.validate()
    await smslogin(smsForm)
    ElMessage.success('登录成功')
    router.push('/home')
  } catch (error) {
    ElMessage.error('登录失败')
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f0f2f5;
}

.login-box {
  width: 400px;
  padding: 30px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.code-input {
  display: flex;
  gap: 10px;
}

.register-link {
  margin-top: 20px;
  text-align: center;
}

.el-button {
  width: 100%;
  margin-top: 10px;
}
</style>
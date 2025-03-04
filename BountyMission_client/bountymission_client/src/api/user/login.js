import api from '@/interceptor/index'

//查询学校列表
export const SchoolList = (keyword) => {
  return api.get("/user/school",{ params: { keyword }}  )
}


//发送手机号获取验证码
export const SmsCode = (phone) => {
  return api.post("/user/sms",{ phone })
}
//注册
export const register = (registerForm) => {
  return api.post("/user/register",registerForm)
}
//账号密码登录
export const login = (loginForm) => {
  return api.post("/user/login",loginForm)
}
//短信验证码登录
export const smslogin = (smsForm) => {
  return api.post("/user/smslogin",smsForm)
}
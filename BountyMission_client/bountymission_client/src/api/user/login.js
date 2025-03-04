import api from '@/interceptor/index'

//查询学校列表
export const SchoolList = (keyword) => {
  return api.get("/user/school",{ params: { keyword }}  )
}


//发送手机号获取验证码
export const SmsCode = (phone) => {
  return api.post("/user/sms",{ phone })
}

export const register = (registerForm) => {
  return api.post("/user/register",registerForm)
}
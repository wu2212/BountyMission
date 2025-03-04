package com.project.bountymission.utils;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

@Service
public class SmsService {

    @Autowired
    private CCPRestSmsSDK smsSDK;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String SMS_CODE_KEY = "sms:code:";

    @Value("${sms.template-id}")
    private String templateId;

    public void sendVerificationCode(String phone) {
        //生产环境请求地址：app.cloopen.com
        String serverIp = "app.cloopen.com";
        //请求端口
        String serverPort = "8883";
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = "2c94811c946f6bfb01955b1e43842383";
        String accountToken = "4bf722635d16490284916790c0e1fe77";
        //请使用管理控制台中已创建应用的APPID
        String appId = "2c94811c946f6bfb01955b1e4547238a";
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String to = phone;
        String templateId= "1";
        Long expire = 2l;
        String code = generateRandomCode();
        String[] datas = {code,expire.toString()};
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        if("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
            //存储验证码到redis
            String key = SMS_CODE_KEY + phone;
            redisTemplate.opsForValue().set(
                    key,
                    code,
                    2, TimeUnit.MINUTES // 设置过期时间
            );
            System.out.println("短信验证码已存储，手机号："+phone+"，验证码：" +code);
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }



    private String generateRandomCode() {
        SecureRandom random = new SecureRandom();
        return String.format("%04d", random.nextInt(10000)); // 注意参数改为10000
    }

    //验证码校验方法
    public boolean verifyCode(String phone, String inputCode) {
        String key = SMS_CODE_KEY + phone;
        String storedCode = redisTemplate.opsForValue().get(key);

        if (storedCode == null) {
            System.out.println("验证码已过期或未发送");
            return false;
        }

        if (!storedCode.equals(inputCode)) {
            System.out.println("验证码不匹配");
            return false;
        }

        // 验证成功后删除key（使用del命令保证原子性）
        System.out.println("验证码验证成功");
        redisTemplate.delete(key);
        return true;
    }
}

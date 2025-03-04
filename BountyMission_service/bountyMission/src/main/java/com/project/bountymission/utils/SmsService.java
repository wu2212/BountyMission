package com.project.bountymission.utils;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.random.RandomGenerator;

@Service
public class SmsService {

    @Autowired
    private CCPRestSmsSDK smsSDK;

    // 使用内存临时存储验证码（仅供测试）
    private static final Map<String, String> codeStore = new ConcurrentHashMap<>();

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
        String[] datas = {"5200",expire.toString()};
        //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas);
        if("000000".equals(result.get("statusCode"))){
            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
        }
    }

    // 验证码校验方法（内存版）
    public boolean validateCode(String phone, String code) {
        String storedCode = codeStore.get(phone);
        return code != null && code.equals(storedCode);
    }

    private String generateRandomCode() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
    }
}

package com.project.bountymission.config;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
@Configuration
public class SmsConfig {
    @Value("${sms.account-sid}")
    private String accountSid;

    @Value("${sms.auth-token}")
    private String authToken;

    @Value("${sms.app-id}")
    private String appId;

    @Value("${sms.server-ip}")
    private String serverIp;

    @Value("${sms.server-port}")
    private String serverPort;

    @Bean
    public CCPRestSmsSDK ccpRestSmsSDK() {

        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSid, authToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        return sdk;
    }
}


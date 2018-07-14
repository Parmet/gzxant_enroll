package com.gzxant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatAccountConfig {

    private String mpAppId;
    private String mpAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 回调地址
     */
    private String notifyUrl;

    /**
     * 微信登陆获取openid地址
     */
    private String openIdUrl;

    /**
     * 获取openid跳转地址
     */
    private String openIdReturnUrl;


}

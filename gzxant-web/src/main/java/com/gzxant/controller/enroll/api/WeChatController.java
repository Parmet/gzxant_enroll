package com.gzxant.controller.enroll.api;


import com.gzxant.config.WechatAccountConfig;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
@RequestMapping("/front/wechat")
public class WeChatController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @GetMapping("/authorize")
    public String authorize() {
        String returnUrl = wechatAccountConfig.getOpenIdReturnUrl();
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(wechatAccountConfig.getOpenIdUrl(), WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code,redirectUrl={}", redirectUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userinfo")
    public String userinfo(@RequestParam("code") String code, @RequestParam("state") String state) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页{}】", e);
            throw new SecurityException("登陆失败，请重试");
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info(openId);
        return "redirect:" + state + "?openid=" + openId;
    }
}

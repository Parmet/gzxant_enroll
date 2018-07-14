package com.gzxant.util.message;

import com.gzxant.base.entity.MessageSend;
import com.gzxant.base.service.ISysDictService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;

import java.io.IOException;

public class MchuanMessageUtil {

    private static final String API_ROOT = "http://112.74.139.4:8002/sms3_api/xmlapi/";
    private static final String SEND_API = API_ROOT + "/send.jsp";
    private static final String USER_ID = "";
    private static final String PASSWORD = "";

    @Autowired
    private ISysDictService dictService;

    public static boolean send(MessageSend msg) {
        if (msg == null || msg.getPhone() == null
                || msg.getPhone().isEmpty()
                || StringUtils.isBlank(msg.getTemplateKey())) {
            return false;
        }

        // http 请求
        // 生成请求内容
        // 发送请求
        // 解析返回的请求
        // 保存短信验证码
        // 返回发送结果

        return false;
    }

    private String bulidSendContent(MessageSend msg) {
        if (msg == null || msg.getPhone() == null
                || msg.getPhone().isEmpty()
                || StringUtils.isBlank(msg.getTemplateKey())) {
            return null;
        }

        return "";
    }

}

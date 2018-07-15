package com.gzxant.common.service.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzxant.common.entity.config.SysConfig;
import com.gzxant.common.entity.message.MchuanMessage;
import com.gzxant.common.entity.message.MchuanMessageParams;
import com.gzxant.common.entity.message.MchuanMessageSubmit;
import com.gzxant.common.entity.message.SendMessage;
import com.gzxant.common.service.config.ISysConfigService;
import com.gzxant.util.HttpUtil;
import com.gzxant.util.data.JsonUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MchuanMessageService implements IMessageService {

    private static final String API_ROOT = "http://112.74.139.4:8002/sms3_api/jsonapi";
    private static final String SEND_API = API_ROOT + "/jsonrpc2.jsp";
    private static final String USER_ID = "202778";
    private static final String PASSWORD = "e06b4c3bb97882aa4327b62b76b99999";
    private static final String SYS_CONFIG_TEMPLATE_KEY = "MESSAGE_TEMPLATE";

    @Autowired
    private ISysConfigService configService;

//    @Autowired
//    private GzxantLogDao gzxantLogDao;

    @Override
    public boolean send(SendMessage msg) {
        if (msg == null || msg.getPhone() == null
                || msg.getPhone().isEmpty()
                || StringUtils.isBlank(msg.getTemplateKey())) {
            return false;
        }

        long start = System.currentTimeMillis();
        // 保存短信验证码
        // 生成请求内容
        String content = bulidSendContent(msg);
        if (StringUtils.isBlank(content)) {
            return false;
        }

        System.out.println("================request=======================");
        System.out.println(content);
        System.out.println("================request=======================");

        // 发送请求
        String result = HttpUtil.doPostJson(SEND_API, content);
        System.out.println("================response=======================");
        System.out.println(result);
        System.out.println("================response=======================");
        // 解析返回的请求, 返回发送结果
        if (isSuccess(result)) {
            return true;
        }

        // 日志记录未发送原因
//        GzxantLog log = new GzxantLog();
//        log.setCreateDate(new Date(System.currentTimeMillis()));
//        log.setDelFlag("Y");
//        log.setType(SYS_CONFIG_TEMPLATE_KEY);
//        log.setTag(msg.getTemplateKey());
//        log.setSrc("com.gzxant.common.service.message.MchuanMessageService.send()");
//        log.setMsg(getErrorMsg(result));
//        log.setParams(result);
//        log.setName(msg.getPhone().get(0));
//
//        long end = System.currentTimeMillis();
//        log.setUseTime(end - start);
//
//        gzxantLogDao.insert(log);
        return false;
    }

    private String getErrorMsg(String result) {
        if (StringUtils.isBlank(result)) {
            return "接口无返回数据";
        }

        Map map = JsonUtil.stringToCollect(result);
        if (map.containsKey("error")
                && map.get("error") instanceof JSONObject) {
            JSONObject obj = (JSONObject) map.get("error");
            return obj.get("message").toString();
        }

        return "接口数据返回格式变动或不正确";
    }

    /**
     * 只支持单个校验
     * @param result
     * @return
     */
    public boolean isSuccess(String result) {
        if (StringUtils.isBlank(result)) {
            return false;
        }

        Map map = JsonUtil.stringToCollect(result);
        if (map.containsKey("result")
                && map.get("result") instanceof JSONArray) {
            JSONArray arr = (JSONArray) map.get("result");
            if (!arr.isEmpty()) {
                JSONObject obj = (JSONObject) arr.get(0);
                if (obj.get("return").equals("0")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * {
     *     "id": id ,
     *     "method":"接口类型，发送为send(必填)",
     *     "params":{
     *         "userid":"账号(必填)",
     *         "password":"密码(必填)",
     *         "submit": [{
     *             "content":"短信内容(必选)",
     *             "phone":"电话号码，若多个号码用”,”隔开(必选)"
     *          },{
     *             "content":"短信内容(必选)",
     *             "phone":"电话号码，若多个号码用”,”隔开(必选)"
     *          },
     *          ……
     *          ,{
     *             "content":"短信内容(必选)",
     *             "phone":"电话号码，若多个号码用”,”隔开(必选)"
     *          }]
     *     }
     * }
     *
     * @param msg
     * @return
     */
    private String bulidSendContent(SendMessage msg) {
        if (msg == null || msg.getPhone() == null
                || msg.getPhone().isEmpty()
                || StringUtils.isBlank(msg.getTemplateKey())) {
            return null;
        }

        MchuanMessage content = new MchuanMessage();
        content.setId(String.valueOf(System.currentTimeMillis()));
        content.setMethod("send");

        MchuanMessageParams params = new MchuanMessageParams();
        params.setUserid(USER_ID);
        params.setPassword(PASSWORD);

        List<MchuanMessageSubmit> submits = new ArrayList<>();
        for (String phone : msg.getPhone()) {
            MchuanMessageSubmit submit = new MchuanMessageSubmit();
            submit.setPhone(phone);
            submit.setContent(bulidMsgContent(msg));
            submits.add(submit);
        }

        params.setSubmit(submits);
        content.setParams(params);
        String contentStr = JsonUtil.toJSONString(content);

        return contentStr;
    }

    private String bulidMsgContent (SendMessage msg) {
        if (msg == null || StringUtils.isBlank(msg.getTemplateKey())) {
            return "";
        }

        // 根据 templatekey 获取内容模板
        List<SysConfig> templates = configService.getSub(SYS_CONFIG_TEMPLATE_KEY);
        // 读取params, 使用freemarker填充内容
        String result = "";
        for (SysConfig template : templates) {
            if (template.getJkey().equals(msg.getTemplateKey())) {
                result = parseContent(template.getValue(), msg.getParam());
            }
        }

        return result;
    }

    private String parseContent(String content, Map<String, Object> params) {
        if (StringUtils.isBlank(content)) {
            return "";
        }

        if (params == null || params.isEmpty()) {
            params = new HashMap<>();
        }

        Configuration config = new Configuration();
        StringWriter out = new StringWriter();
        try {
            new Template("template", new StringReader(content), config).process(params, out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return out.toString();
    }

}


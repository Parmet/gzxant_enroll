package com.gzxant.util.message;

import java.util.List;
import java.util.Map;

public class MchuanMessageUtil {

    private static final String API_ROOT = "http://112.74.139.4:8002/sms3_api/xmlapi/";
    private static final String SEND_API = API_ROOT + "/send.jsp";
    private static final String USER_ID = "";
    private static final String PASSWORD = "";

    public static boolean send(List<String> phone, Map<String, Object> param) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        return false;
    }

    private String bulidSendContent(List<String> phone, Map<String, Object> param) {
        if (phone == null || phone.isEmpty()) {
            return null;
        }

        return "";
    }
}

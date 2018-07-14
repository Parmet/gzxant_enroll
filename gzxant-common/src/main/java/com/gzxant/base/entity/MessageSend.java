package com.gzxant.base.entity;

import java.util.List;
import java.util.Map;

public class MessageSend {

    private List<String> Phone;
    private Map<String, Object> param;
    private String templateKey;

    public List<String> getPhone() {
        return Phone;
    }

    public void setPhone(List<String> phone) {
        Phone = phone;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public String getTemplateKey() {
        return templateKey;
    }

    public void setTemplateKey(String templateKey) {
        this.templateKey = templateKey;
    }
}

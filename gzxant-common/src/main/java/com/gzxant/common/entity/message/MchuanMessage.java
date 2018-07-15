package com.gzxant.common.entity.message;

public class MchuanMessage {
    private String id;
    private String method;
    private MchuanMessageParams params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public MchuanMessageParams getParams() {
        return params;
    }

    public void setParams(MchuanMessageParams params) {
        this.params = params;
    }
}

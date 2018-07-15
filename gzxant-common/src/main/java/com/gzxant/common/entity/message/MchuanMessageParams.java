package com.gzxant.common.entity.message;

import java.util.List;

public class MchuanMessageParams {
    private String userid;
    private String password;
    private List<MchuanMessageSubmit> submit;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MchuanMessageSubmit> getSubmit() {
        return submit;
    }

    public void setSubmit(List<MchuanMessageSubmit> submit) {
        this.submit = submit;
    }
}

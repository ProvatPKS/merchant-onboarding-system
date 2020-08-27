package com.mc.program.vo;

import java.util.StringJoiner;

public class Response {

    private String status;
    private String responseCode;
    private String responseDesc;
    private String responseMessage;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Response.class.getSimpleName() + "[", "]")
                .add("status='" + status + "'")
                .add("responseCode='" + responseCode + "'")
                .add("responseDesc='" + responseDesc + "'")
                .add("responseMessage='" + responseMessage + "'")
                .toString();
    }
}

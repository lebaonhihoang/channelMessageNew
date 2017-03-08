package com.example.hoangleb.channelmessaging;

/**
 * Created by nhi on 11/02/2017.
 */

public class connect {

    private String response;
    private int code;
    private String accessToken;

    public connect() {
        //Keep empty.
    }

    public String getResponse() {
        return response;
    }

    public String getAccesstoken() {
        return accessToken;
    }

    public int getCode() {
        return code;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public void setAccesstoken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "Connect{" +
                "response='" + response + '\'' +
                ", code=" + code +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}

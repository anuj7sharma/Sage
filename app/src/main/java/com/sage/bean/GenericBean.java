package com.sage.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Anuj Sharma on 5/9/2017.
 */

public class GenericBean {

    /**
     * message : success
     * code : 1
     */

    @SerializedName("message")
    private String message;
    @SerializedName("code")
    private int code;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

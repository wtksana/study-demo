package com.tt.study.demo.common;

/**
 * 响应码
 * date: 2018/3/15
 * author: wt
 */
public enum RespCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    ;

    private final int code;
    private final String desc;

    RespCode(int code,String desc){
        this.code =code;
        this.desc =desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

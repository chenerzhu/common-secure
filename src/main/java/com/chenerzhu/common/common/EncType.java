package com.chenerzhu.common.common;

/**
 * Created by chenerzhu on 2018/8/2.
 */
public enum EncType {
    HEX("HEX"),
    BASE64("BASE64"),
    DEFAULT("DEFAULT");//系统默认编码

    private String type;
    
    EncType(String type){
        this.type=type;
    }
    
    public String getType(){
        return type;
    }
}

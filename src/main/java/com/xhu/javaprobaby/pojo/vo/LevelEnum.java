package com.xhu.javaprobaby.pojo.vo;

import java.util.ArrayList;
import java.util.List;

public enum LevelEnum {
    OWNER(0,"所有人"),
    ONESLFE(1,"仅自己");

    private Integer key;
    private String value;

    LevelEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    /**
     * 获取value
     * @return
     */
    public static List<String> getLevel(){
        List<String> list = new ArrayList<>();
        for(LevelEnum level:LevelEnum.values()){
            list.add(level.getValue());
        }
        return list;
    }
}

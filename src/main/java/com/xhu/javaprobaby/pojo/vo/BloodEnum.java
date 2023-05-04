package com.xhu.javaprobaby.pojo.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 血型枚举类
 * @since 2023-05-04
 */
public enum BloodEnum {
    TYPE_O(0,"O型"),
    TYPE_A(1,"A型"),
    TYPE_B(2,"B型"),
    TYPE_AB(3,"AB型"),
    TYPE_OTHER(4,"其他");


    private Integer key;
    private String value;

    BloodEnum(Integer key, String value) {
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
    public static List<String> getBlood(){
        List<String> list = new ArrayList<>();
        for(BloodEnum blood:BloodEnum.values()){
            list.add(blood.getValue());
        }
        return list;
    }


}

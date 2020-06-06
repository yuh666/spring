package com.example.spring.po;

import java.util.Date;

public class YuebingLog {

    private String id;
    //cf money
    private Integer money;
    private String unit;
    //cf goods
    private String productName;
    //time
    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "YuebingLog{" +
                "id='" + id + '\'' +
                ", money=" + money +
                ", unit='" + unit + '\'' +
                ", productName='" + productName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}

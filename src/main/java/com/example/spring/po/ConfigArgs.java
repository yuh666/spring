package com.example.spring.po;


import org.springframework.data.mongodb.core.mapping.Field;

public class ConfigArgs {

    @Field(name = "name")
    private String name;
    @Field(name = "value")
    private String value;
    @Field(name = "desc")
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ConfigArgs{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}




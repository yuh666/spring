package com.example.spring.po;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "config")
public class Config {

    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @Field(name = "desc")
    private String desc = "";
    @Field(name = "biz_type")
    private Integer bizType;
    @Field(name = "create_time")
    private Date createTime;
    @Field(name = "update_time")
    private Date updateTime;
    @Field(name = "args")
    private List<ConfigArgs> args;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public List<ConfigArgs> getArgs() {
        return args;
    }

    public void setArgs(List<ConfigArgs> args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Config{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", bizType=" + bizType +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

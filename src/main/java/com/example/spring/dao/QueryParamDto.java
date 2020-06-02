package com.example.spring.dao;

/**
 * 列举job的过滤信息
 *
 * @author yuhao
 * @date 2020/5/27 3:31 下午
 */
public class QueryParamDto {


    private Integer bizType;
    private String id;

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

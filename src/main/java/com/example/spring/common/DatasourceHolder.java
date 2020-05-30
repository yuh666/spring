package com.example.spring.common;

import com.example.spring.enums.DatasourceEnum;

public class DatasourceHolder {

    private static ThreadLocal<DatasourceEnum> holder = new ThreadLocal<>();

    public static void set(DatasourceEnum datasourceEnum){
        holder.set(datasourceEnum);
    }

    public static void clear(){
        holder.remove();
    }

    public static DatasourceEnum get(){
        return holder.get();
    }
}

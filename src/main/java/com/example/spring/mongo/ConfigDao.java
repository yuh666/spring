package com.example.spring.mongo;


import com.example.spring.dao.QueryParamDto;
import com.example.spring.po.Config;
import com.example.spring.po.ConfigArgs;

import java.util.List;

//insert _class问题
public interface ConfigDao {

    List<Config> listByPage(QueryParamDto filter, int pageNo, int pageSize);

    long count(QueryParamDto filter);

    Config get(String id);

    void insert(Config config);

    void update(Config config);

    void delete(String id);

    List<ConfigArgs> getArgs(String id);

    void updateArgs(String id, List<ConfigArgs> list);
}

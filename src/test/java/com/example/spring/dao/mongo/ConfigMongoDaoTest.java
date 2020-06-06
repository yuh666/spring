package com.example.spring.dao.mongo;


import com.example.spring.dao.QueryParamDto;
import com.example.spring.po.Config;
import com.example.spring.po.ConfigArgs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class ConfigMongoDaoTest {


    @Autowired
    private ConfigDao dao;


    @Test
    public void testInsert() {
        for (int i = 0; i < 100; i++) {
            Config config = new Config();
            config.setId(UUID.randomUUID().toString().replace("-", ""));
            config.setBizType(1);
            config.setCreateTime(new Date());
            config.setUpdateTime(new Date());
            config.setName("测试conf-name");
            config.setDesc("测试conf-desc");
            dao.insert(config);
        }
    }

    @Test
    public void testUpdate() {
        Config config = new Config();
        config.setId("2b6720950e1b458e8804c12482ef52e4");
        config.setBizType(2);
        config.setName("测试conf-name abc");
        config.setDesc("测试conf-desc abc");
        dao.update(config);
    }

    @Test
    public void testUpdateArgs() {
        ArrayList<ConfigArgs> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ConfigArgs configArgs1 = new ConfigArgs();
            configArgs1.setName("name" + i);
            configArgs1.setDesc("desc" + i);
            configArgs1.setValue("value" + i);
            list.add(configArgs1);
        }
        dao.updateArgs("c407cb2510604ca8889e4992426f0608", list);
    }

    @Test
    public void testGet() {
        Config config = dao.get("f117327e35744534b1a1587c95710a83");
        System.out.println(config);
    }

    @Test
    public void testDelete() {
        dao.delete("f117327e35744534b1a1587c95710a83");
    }

    @Test
    public void testCount() {
        QueryParamDto queryParamDto = new QueryParamDto();
        queryParamDto.setBizType(1);
        long count = dao.count(queryParamDto);
        System.out.println(count);
    }

    @Test
    public void testListByPage() {
        QueryParamDto queryParamDto = new QueryParamDto();
        queryParamDto.setBizType(1);
        List<Config> list = dao.listByPage(queryParamDto, 1, 3);
        System.out.println(list);
    }


    @Test
    public void testGetArgs() {
        List<ConfigArgs> list = dao.getArgs(
                "c407cb2510604ca8889e4992426f0608");
        System.out.println(list);
    }




}

package com.example.spring.mongo.impl;

import com.example.spring.mongo.ConfigDao;
import com.example.spring.dao.QueryParamDto;
import com.example.spring.po.Config;
import com.example.spring.po.ConfigArgs;
import com.mongodb.BasicDBObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ConfigDaoImpl implements ConfigDao {


    @Autowired
    private MongoTemplate template;

    @Override
    public List<Config> listByPage(QueryParamDto filter, int pageNo, int pageSize) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(filter.getId())) {
            criteria.and("_id").is(filter.getId());
        }
        if (filter.getBizType() != null) {
            criteria.and("biz_type").is(filter.getBizType());
        }
        Query query = new Query(criteria)
                .with(Sort.by("create_time").ascending())
                .skip((pageNo - 1) * pageSize).limit(pageSize);
        return template.find(query, Config.class);
    }

    @Override
    public long count(QueryParamDto filter) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(filter.getId())) {
            criteria.and("_id").is(filter.getId());
        }
        if (filter.getBizType() != null) {
            criteria.and("biz_type").is(filter.getBizType());
        }
        return template.count(new Query(criteria), "config");
    }

    @Override
    public Config get(String id) {
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id);
        return template.findOne(new Query(criteria), Config.class);
    }

    @Override
    public void insert(Config config) {
        template.insert(config);
    }

    @Override
    public void update(Config config) {
        Update update = new Update();
        if (StringUtils.isNotBlank(config.getDesc())) {
            update.set("desc", config.getDesc());
        }
        if (StringUtils.isNotBlank(config.getName())) {
            update.set("name", config.getName());
        }
        if (config.getBizType() != null) {
            update.set("biz_type", config.getBizType());
        }
        Criteria criteria = new Criteria();
        criteria.and("_id").is(config.getId());
        template.updateFirst(new Query(criteria), update, "config");
    }

    @Override
    public void delete(String id) {
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id);
        template.remove(new Query(criteria), "config");
    }

    @Override
    public List<ConfigArgs> getArgs(String id) {
        BasicDBObject dbObject = new BasicDBObject();
        dbObject.put("_id", id);
        //指定返回的字段
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("args", true);
        Query query = new BasicQuery(dbObject.toJson(), fieldsObject.toJson());
        return template.findOne(query,Config.class,"config").getArgs();
    }

    @Override
    public void updateArgs(String id, List<ConfigArgs> list) {
        Criteria criteria = new Criteria();
        criteria.and("_id").is(id);

        Query query = new Query(criteria);
        Update update = new Update();

        update.set("args", list);
        template.updateFirst(query, update, "config");

    }
}

package com.example.spring.config;


import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

import java.net.UnknownHostException;

//@Configuration
public class MongoConfig {

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        String uriStr = "mongodb://106.12.15.56/mongo";
        return new SimpleMongoClientDbFactory(uriStr);
    }

    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

}

package com.example.spring.watcher;


import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RedisWatcher implements InitializingBean, DisposableBean {

    private RedisWatchMaster master;
    private RedisWatchSlave[] slaves = new RedisWatchSlave[10];

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        LinkedBlockingQueue<String>[] queues = (LinkedBlockingQueue<String>[]) new LinkedBlockingQueue[slaves.length];
        master = new RedisWatchMaster(queues, redisTemplate);
        for (int i = 0; i < slaves.length; i++) {
            queues[i] = new LinkedBlockingQueue<>();
            slaves[i] = new RedisWatchSlave(redisTemplate, queues[i]);
            slaves[i].start();
        }
        master.start();
    }

    @Override
    public void destroy() throws Exception {
        master.interrupt();
        for (RedisWatchSlave slave : slaves) {
            slave.interrupt();
        }
    }


}

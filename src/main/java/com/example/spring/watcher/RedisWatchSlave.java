package com.example.spring.watcher;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

public class RedisWatchSlave extends Thread {

    private RedisTemplate<String, String> redisTemplate;
    private LinkedBlockingQueue<String> queue;
    private Set<String> topics = new HashSet<>();

    public RedisWatchSlave(RedisTemplate<String, String> redisTemplate,
            LinkedBlockingQueue<String> queue) {
        this.redisTemplate = redisTemplate;
        this.queue = queue;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            while (queue.peek() != null) {
                topics.add(queue.poll());
            }
            Iterator<String> iterator = topics.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                redisTemplate.execute((RedisCallback<Void>) connection -> {
                    if (!connection.exists(next.getBytes())) {
                        iterator.remove();
                        return null;
                    }
                    DataType type = connection.type(next.getBytes());
                    if (type != DataType.LIST) {
                        iterator.remove();
                        return null;
                    }
                    Long len = connection.lLen(next.getBytes());
                    System.out.println("topic: " + next + ",len: " + len);
                    return null;
                });
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

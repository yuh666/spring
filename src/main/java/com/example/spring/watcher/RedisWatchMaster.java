package com.example.spring.watcher;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class RedisWatchMaster extends Thread {


    private LinkedBlockingQueue<String>[] queues;
    private RedisTemplate<String, String> redisTemplate;


    public RedisWatchMaster(LinkedBlockingQueue<String>[] queues,
            RedisTemplate<String, String> redisTemplate) {
        this.queues = queues;
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void run() {
        while (!Thread.interrupted()) {
            List<String> topics = redisTemplate.execute(
                    (RedisCallback<List<String>>) connection -> {
                        ScanOptions options = ScanOptions.scanOptions().count(1000L).match(
                                "*").build();
                        Cursor<byte[]> cursor = connection.scan(options);
                        ArrayList<String> list = new ArrayList<>();
                        while (cursor.hasNext()) {
                            list.add(new String(cursor.next()));
                        }
                        try {
                            cursor.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return list;
                    });
            if (!topics.isEmpty()) {
                for (String topic : topics) {
                    try {
                        queues[topic.hashCode() % queues.length].put(topic);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

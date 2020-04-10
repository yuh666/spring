package com.example.spring;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class TestPercent {

    @Test
    public void testPercent() throws IOException {
        String input = load("/Users/yss/Desktop/develop/java/learn/spring/src/test/" +
                "java/com/example/spring/data18.txt");
        TreeMap<Integer, Integer> map = new TreeMap<>();
        String[] split = input.split("\n");
        int sum = 0;
        for (String s : split) {
            String[] kv = s.split("\\|");
            map.put(Integer.parseInt(kv[0].trim()), Integer.parseInt(kv[1].trim()));
            sum += Integer.parseInt(kv[1].trim());
        }
        int curr = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            curr += entry.getValue();
            System.out.println(
                    "充值次数 <= " + entry.getKey() + " , 占比: " + (int) ((double) curr / sum * 100) + "%");
        }
    }


    private static String load(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        byte[] buffer = new byte[1024 * 1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = fileInputStream.read(buffer);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(buffer, 0, read);
        }
        return byteArrayOutputStream.toString();
    }

}

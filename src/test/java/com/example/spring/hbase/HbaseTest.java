package com.example.spring.hbase;

import com.example.spring.dao.hbase.YuebingLogDao;
import com.example.spring.po.YuebingLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.stream.ByteRecord;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class HbaseTest {

    @Autowired
    private YuebingLogDao dao;

    @Test
    public void testCreateTable() throws IOException {
        dao.createTable();
    }

    @Test
    public void testExistsTable() throws IOException {
        System.out.println(dao.existsTable());
    }

    @Test
    public void testDeleteTable() throws IOException {
        dao.deleteTable();
    }

    @Test
    public void testDisableTable() throws IOException {
        dao.disableTable();
    }

    @Test
    public void TestInsert() {
        YuebingLog yuebingLog = new YuebingLog();
        Date date = new Date();
        yuebingLog.setId("yss_" + date.getTime());
        yuebingLog.setCreateTime(date.toString());
        yuebingLog.setMoney(1000);
        yuebingLog.setUnit("yuan");
        yuebingLog.setProductName("皇家美素佳儿");
        dao.insert(yuebingLog);
    }

    @Test
    public void testGet() {
        YuebingLog log = dao.get("yss_1591426417916");
        System.out.println(log);
    }

    @Test
    public void testByteBuffer() {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
//        allocate.put((byte) 1);
//        allocate.position(0);
//        ByteBuffer byteBuffer = allocate.asReadOnlyBuffer();
//        System.out.println(byteBuffer.getLong());

        allocate.put("hello".getBytes());
        allocate.flip();
//        while(allocate.hasRemaining()){
//            System.out.println(allocate.getChar());
//        }
        byte[] bytes = new byte[5];
        allocate.get(bytes, 0, allocate.remaining());
        System.out.println(new String(bytes));
    }


    @Test
    public void testScan() {
        Date startTime = new Date(Long.parseLong("1591426417916"));
        Date endTime = new Date(Long.parseLong("159142661916"));
        List<YuebingLog> list = dao.scan("yss", startTime, endTime);
        System.out.println(list);
    }

    @Test
    public void testDeleteById() {
        dao.deleteById("yss_1591426417916");
    }

}

package com.example.spring.hbase;

import com.example.spring.dao.hbase.YuebingLogDao;
import com.example.spring.po.YuebingLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.stream.ByteRecord;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//RowKey 设计原则
//选定一个指定的主键  比如用户id等 用这个id作为分区键计算器 类似于Mysql之中的分表
//选定Region的个数 一般是机器数量的3倍
//选定一个排序条件 用于scan时候使用
//rowKey = 分区键_主键_排序条件

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
    public void TestInsert() throws InterruptedException {


        for (int i = 0; i < 100; i++) {
            YuebingLog yuebingLog = new YuebingLog();
            Date date = new Date();
            yuebingLog.setUserId("yss" + (i % 10));
            yuebingLog.setCreateTime(date.toString());
            yuebingLog.setMoney(1000 + i);
            yuebingLog.setUnit("yuan");
            yuebingLog.setProductName("皇家美素佳儿");
            dao.insert(yuebingLog);

            Thread.sleep(3);
        }


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
    public void testScan() throws ParseException {
        List<YuebingLog> list = dao.scan("yss1", new SimpleDateFormat("yyyyMMddHHmmss").parse("20200606182010"),
                new SimpleDateFormat("yyyyMMddHHmmss").parse("20200606182020"));
        System.out.println(list);
    }

}

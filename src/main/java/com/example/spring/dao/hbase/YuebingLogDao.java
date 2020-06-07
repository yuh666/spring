package com.example.spring.dao.hbase;

import com.example.spring.dao.hbase.HbaseDao;
import com.example.spring.po.YuebingLog;
import com.mongodb.operation.FindAndDeleteOperation;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Repository
public class YuebingLogDao extends HbaseDao {

    private static final String TABLE_NAME = "yuebing_log";

    private static final int PARTITONS = 10;

    private static String[] KEYS = new String[PARTITONS];

    static {
        for (int i = 0; i < PARTITONS; i++) {
            KEYS[i] = "0" + i;
        }
    }

    @Autowired
    public YuebingLogDao(HbaseTemplate template) {
        super(template);
    }


    public void createTable() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("user");
        list.add("money");
        list.add("goods");
        list.add("time");
        super.createTable(TABLE_NAME, list, KEYS);
    }

    public boolean existsTable() throws IOException {
        return super.existsTable(TABLE_NAME);
    }

    public void deleteTable() throws IOException {
        super.deleteTable(TABLE_NAME);
    }

    public void disableTable() throws IOException {
        super.disableTable(TABLE_NAME);
    }

    public void insert(YuebingLog log) {
        String datetime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String partitionKey = KEYS[log.getUserId().hashCode() % PARTITONS];
        String rowKey = partitionKey + "_" + log.getUserId() + "_" + datetime;
        template.execute(TABLE_NAME, (TableCallback<Void>) hTableInterface -> {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes("user"), Bytes.toBytes("userId"), Bytes.toBytes(log.getMoney()));
            put.addColumn(Bytes.toBytes("money"), Bytes.toBytes("money"), Bytes.toBytes(log.getMoney()));
            put.addColumn(Bytes.toBytes("money"), Bytes.toBytes("unit"), Bytes.toBytes(log.getUnit()));
            put.addColumn(Bytes.toBytes("goods"), Bytes.toBytes("productName"), Bytes.toBytes(log.getProductName()));
            put.addColumn(Bytes.toBytes("time"), Bytes.toBytes("createTime"), Bytes.toBytes(log.getCreateTime()));
            hTableInterface.put(put);
            return null;
        });
    }


    public List<YuebingLog> scan(String id, Date startTime, Date endTime) {
        String start = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(startTime);
        String end = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(endTime);
        String partitionKey = KEYS[id.hashCode() % PARTITONS];
        String rowKeyStart = partitionKey + "_" + id + "_" + start;
        String rowKeyEnd = partitionKey + "_" + id + "_" + end;
        Scan scan = new Scan(Bytes.toBytes(rowKeyStart), Bytes.toBytes(rowKeyEnd));
        return template.find(TABLE_NAME, scan, (result, i) -> {
            YuebingLog yuebingLog = new YuebingLog();
            yuebingLog.setUserId(id);
            yuebingLog.setUserId(Bytes.toString(result.getValue(Bytes.toBytes("user"), Bytes.toBytes("userId"))));
            yuebingLog.setMoney(Bytes.toInt(result.getValue(Bytes.toBytes("money"), Bytes.toBytes("money"))));
            yuebingLog.setUnit(Bytes.toString(result.getValue(Bytes.toBytes("money"), Bytes.toBytes("unit"))));
            yuebingLog.setProductName(Bytes.toString(result.getValue(Bytes.toBytes("goods"), Bytes.toBytes("productName"))));
            yuebingLog.setCreateTime(Bytes.toString(result.getValue(Bytes.toBytes("time"), Bytes.toBytes("createTime"))));
            return yuebingLog;
        });
    }

}

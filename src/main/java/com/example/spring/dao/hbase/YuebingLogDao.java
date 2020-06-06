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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class YuebingLogDao extends HbaseDao {

    private static final String TABLE_NAME = "yuebing_log";

    @Autowired
    public YuebingLogDao(HbaseTemplate template) {
        super(template);
    }


    public void createTable() throws IOException {
        ArrayList<String> list = new ArrayList<>();
        list.add("money");
        list.add("goods");
        list.add("time");
        super.createTable(TABLE_NAME, list);
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
        template.execute(TABLE_NAME, (TableCallback<Void>) hTableInterface -> {
            Put put = new Put(Bytes.toBytes(log.getId()));
            put.addColumn(Bytes.toBytes("money"), Bytes.toBytes("money"), Bytes.toBytes(log.getMoney()));
            put.addColumn(Bytes.toBytes("money"), Bytes.toBytes("unit"), Bytes.toBytes(log.getUnit()));
            put.addColumn(Bytes.toBytes("goods"), Bytes.toBytes("productName"), Bytes.toBytes(log.getProductName()));
            put.addColumn(Bytes.toBytes("time"), Bytes.toBytes("createTime"), Bytes.toBytes(log.getCreateTime()));
            hTableInterface.put(put);
            return null;
        });
    }


    public YuebingLog get(String id) {
        return template.get(TABLE_NAME, id, (RowMapper<YuebingLog>) (result, i) -> {
            YuebingLog yuebingLog = new YuebingLog();
            yuebingLog.setId(id);
            yuebingLog.setMoney(result.getValueAsByteBuffer(Bytes.toBytes("money"), Bytes.toBytes("money")).getInt());
            yuebingLog.setUnit(new String(result.getValue(Bytes.toBytes("money"), Bytes.toBytes("unit"))));
            yuebingLog.setProductName(new String(result.getValue(Bytes.toBytes("goods"), Bytes.toBytes("productName"))));
            yuebingLog.setCreateTime(new String(result.getValue(Bytes.toBytes("time"), Bytes.toBytes("createTime"))));
            return yuebingLog;
        });
    }

    public List<YuebingLog> scan(String id, Date startTime, Date endTime) {
        Scan scan = new Scan(Bytes.toBytes(id + "_" + startTime.getTime()), Bytes.toBytes(id + "_" + endTime.getTime()));
        return template.find(TABLE_NAME, scan, (result, i) -> {
            YuebingLog yuebingLog = new YuebingLog();
            yuebingLog.setId(id);
            yuebingLog.setMoney(Bytes.toInt(result.getValue(Bytes.toBytes("money"), Bytes.toBytes("money"))));
            yuebingLog.setUnit(Bytes.toString(result.getValue(Bytes.toBytes("money"), Bytes.toBytes("unit"))));
            yuebingLog.setProductName(Bytes.toString(result.getValue(Bytes.toBytes("goods"), Bytes.toBytes("productName"))));
            yuebingLog.setCreateTime(Bytes.toString(result.getValue(Bytes.toBytes("time"), Bytes.toBytes("createTime"))));
            return yuebingLog;
        });
    }

    public void deleteById(String id){
        template.execute(TABLE_NAME, new TableCallback<Void>() {
            @Override
            public Void doInTable(HTableInterface hTableInterface) throws Throwable {
                Delete delete = new Delete(Bytes.toBytes(id));
                hTableInterface.delete(delete);
                return null;
            }
        });
    }

}

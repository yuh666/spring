package com.example.spring.dao.hbase;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HbaseDao {

    protected HbaseTemplate template;

    public HbaseDao(HbaseTemplate template) {
        this.template = template;
    }

    public void createTable(String tableName, List<String> cfs, String[] partitionKeys) throws IOException {
        Connection connection = ConnectionFactory.createConnection(template.getConfiguration());
        Admin admin = connection.getAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        cfs.forEach(cf -> {
            hTableDescriptor.addFamily(new HColumnDescriptor(Bytes.toBytes(cf)));
        });
        byte[][] keys = Arrays.stream(partitionKeys).map(Bytes::toBytes).collect(Collectors.toList()).toArray(new byte[0][]);
        admin.createTable(hTableDescriptor, keys);
    }

    public boolean existsTable(String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(template.getConfiguration());
        Admin admin = connection.getAdmin();
        return admin.tableExists(TableName.valueOf(tableName));
    }

    public void deleteTable(String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(template.getConfiguration());
        Admin admin = connection.getAdmin();
        admin.deleteTable(TableName.valueOf(tableName));
    }

    public void disableTable(String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(template.getConfiguration());
        Admin admin = connection.getAdmin();
        admin.disableTable(TableName.valueOf(tableName));
    }

    public void enableTable(String tableName) throws IOException {
        Connection connection = ConnectionFactory.createConnection(template.getConfiguration());
        Admin admin = connection.getAdmin();
        admin.enableTable(TableName.valueOf(tableName));
    }
}

package com.example.spring.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.example.spring.dao", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DBConfiguration {

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String userName;

    @Value("${db.password}")
    private String password;

    @Bean(name = "dbds", initMethod = "init", destroyMethod = "close")
    public DruidDataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        // 配置初始化大小、最小、最大
        dataSource.setInitialSize(20);
        dataSource.setMinIdle(20);
        dataSource.setMaxActive(100);

        //配置获取链接等待超时的时间
        dataSource.setMaxWait(60000);
        //配置间隔多久才能进行一次检测，检测需要关闭的控线连接，单位是毫秒
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        // 配置一个连接在池中最小生存的时间，单位是毫秒
        dataSource.setMinEvictableIdleTimeMillis(300000);
        // 探测连接是否有效的sql
        dataSource.setValidationQuery("SELECT 'x'");
        //连接检查 保证安全性
        dataSource.setTestWhileIdle(true);
        //申请连接时检查
        dataSource.setTestOnBorrow(true);
        //还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
        dataSource.setTestOnReturn(false);
        return dataSource;
    }

    @Bean(name = "dbSqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(
            @Qualifier("dbds") DataSource ds) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(ds);
        return bean.getObject();
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager sentinelTransactionManager(
            @Qualifier("dbds") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sentinelSqlSessionTemplate(
            @Qualifier("dbSqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }
}
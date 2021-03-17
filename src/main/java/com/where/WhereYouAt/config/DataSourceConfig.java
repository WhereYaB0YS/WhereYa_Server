package com.where.WhereYouAt.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(){
        String username = "wyaboy";
        String password = "*#wyaboy";
        String url = "wyadb.cplrxb3kh41a.ap-northeast-2.rds.amazonaws.com:3306/wyaDb?characterEncoding=utf8";
        String driverClass = "com.mysql.jdbc.Driver";

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        dataSourceBuilder.url(url);
        dataSourceBuilder.driverClassName(driverClass);
        return dataSourceBuilder.build();
    }
}

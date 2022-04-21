package com.example.buyforall;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.ApplicationContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class DatabaseConfigure {
    
    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() throws Exception {
        return new HikariConfig();
    }
    @Bean
    public DataSource dataSource() throws Exception {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }
    @Bean 
    public SqlSessionFactory sqlSessionFactory(DataSource datasource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(datasource);
        Resource configureLocation = new PathMatchingResourcePatternResolver().getResource("classpath:/mapper/config/mybatis-config.xml");
        sqlSessionFactory.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
        sqlSessionFactory.setConfigLocation(configureLocation);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public PlatformTransactionManager transactionManager() throws Exception{
        DataSourceTransactionManager manager = new DataSourceTransactionManager(dataSource());
        return manager;
    }
    
}


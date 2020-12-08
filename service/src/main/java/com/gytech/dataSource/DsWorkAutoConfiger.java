package com.gytech.dataSource;

import javax.sql.DataSource;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.gytech.Configuration.AutoFillHandler;

/**
 * Created by LQ on 2018/6/25.
 * i.lq.web.dataSource
 */
@Configuration
@MapperScan(basePackages = "com.gytech.mapper.work", sqlSessionTemplateRef  = "dsWorkSqlSessionTemplate")
public class DsWorkAutoConfiger {

    private static final Logger logger = LoggerFactory.getLogger(DsWorkAutoConfiger.class);

    @Autowired
    private MultiDs dss;

    @Autowired
    private AutoFillHandler autoFillHandler;

    @Bean(name = "dsWorkDataSource")
    public DataSource DataSource() {
        DruidDataSourceProperty ds = dss.getDss().get("dbWork");
        DruidDataSource druidDataSource = DsUtil.BuildDataSource(ds);
        logger.info("loadDatasource --->" + "dsWorkDataSource");
        return druidDataSource;
    }

    @Bean(name = "dsWorkSqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dsWorkDataSource") DataSource dataSource, PaginationInterceptor paginationInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/*Mapper.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setCallSettersOnNulls(false);
        configuration.addInterceptor(paginationInterceptor);
        sqlSessionFactory.setConfiguration(configuration);
        /*sqlSessionFactory.setPlugins(new Interceptor[]{ //PerformanceInterceptor(),OptimisticLockerInterceptor()
                paginationInterceptor() //添加分页功能
        });*/
        return sqlSessionFactory.getObject();
    }

    @Bean(name = "dsWorkTransactionManager")
    public DataSourceTransactionManager annotationDrivenTransactionManager(@Qualifier("dsWorkDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dsWorkSqlSessionTemplate")
    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("dsWorkSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "dsWorkJdbcTemplate")
    public JdbcTemplate JdbcTemplate(@Qualifier("dsWorkDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

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
import org.springframework.context.annotation.Primary;
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
@MapperScan(basePackages = "com.gytech.mapper.admin", sqlSessionTemplateRef  = "dsAdminSqlSessionTemplate")
public class DsAdminAutoConfiger {

    private static final Logger logger = LoggerFactory.getLogger(DsAdminAutoConfiger.class);

    @Autowired
    private MultiDs dss;

    @Autowired
    private AutoFillHandler autoFillHandler;

    @Bean(name = "dsAdminDataSource")
    @Primary
    public DataSource DataSource() {
        DruidDataSourceProperty ds = dss.getDss().get("dsadmin");
        DruidDataSource druidDataSource = DsUtil.BuildDataSource(ds);
        logger.info("loadDatasource --->" + "dsAdminDataSource");
        return druidDataSource;
    }

    @Bean(name = "dsAdminSqlSessionFactory")@Primary
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dsAdminDataSource") DataSource dataSource, PaginationInterceptor paginationInterceptor) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/**/*Mapper.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        //configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.addInterceptor(paginationInterceptor);
        sqlSessionFactory.setConfiguration(configuration);
        return sqlSessionFactory.getObject();
    }

    @Bean(name = "dsAdminTransactionManager")
    @Primary
    public DataSourceTransactionManager annotationDrivenTransactionManager(@Qualifier("dsAdminDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "dsAdminSqlSessionTemplate")@Primary
    public SqlSessionTemplate SqlSessionTemplate(@Qualifier("dsAdminSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "dsAdminJdbcTemplate")
    public JdbcTemplate JdbcTemplate(@Qualifier("dsAdminDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

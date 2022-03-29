package com.fystock.bigdata.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.fystock.bigdata.cloud.datasource.MultipleDataSource;
import com.fystock.bigdata.cloud.enums.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

/**
 * 多数据源配置
 */
@Slf4j
@Configuration
@MapperScan({"com.fystock.bigdata.cloud.mapper.msyql", "com.fystock.bigdata.cloud.mapper.sqlserver"})
public class MybatisPlusConfig {
    /**
     * mybatis-plus分页插件<br>
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        return new MybatisPlusInterceptor();
    }

    /**
     * 配置bigdataMarketMysql datasource
     *
     * @return
     */
    @Bean(name = "bigdataMarketMysql")
    @ConfigurationProperties(prefix = "spring.datasource.bigdatamarketmysql")
    public DataSource bigdataMarketMysql() {
        return new DruidDataSource();
    }

    /**
     * 配置 sql server datasource
     *
     * @return
     */
    @Bean(name = "f10SqlServer")
    @ConfigurationProperties(prefix = "spring.datasource.f10sqlserver")
    public DataSource f10SqlServer() {
        return new DruidDataSource();
    }

    /**
     * 配置mysql sunlineF10 DataSource
     *
     * @return
     */
    @Bean(name = "sunlineF10Mysql")
    @ConfigurationProperties(prefix = "spring.datasource.sunlinef10mysql")
    public DataSource sunlineF10Mysql() {
        return new DruidDataSource();
    }

    /**
     * 配置sunlineMktInfoMySql datasource
     *
     * @return
     */
    @Bean(name = "sunlineMktInfoMySql")
    @ConfigurationProperties(prefix = "spring.datasource.sunlinemktinfomysql")
    public DataSource sunlineMktInfoMySql() {
        return new DruidDataSource();
    }

    /**
     * 配置CUBPMySql datasource
     *
     * @return
     */
    @Bean(name = "cubpMySql")
    @ConfigurationProperties(prefix = "spring.datasource.cubpmysql")
    public DataSource cubpMySql() {
        return new DruidDataSource();
    }

    /**
     * 使用枚举类 映射两个datasource
     *
     * @param bigdataMarketMysql
     * @param f10SqlServer
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("bigdataMarketMysql") DataSource bigdataMarketMysql,
                                         @Qualifier("f10SqlServer") DataSource f10SqlServer,
                                         @Qualifier("sunlineF10Mysql") DataSource sunlineF10Mysql,
                                         @Qualifier("sunlineMktInfoMySql") DataSource sunlineMktInfoMySql,
                                         @Qualifier("cubpMySql") DataSource cubpMySql) {
        MultipleDataSource multipleDataSource = new MultipleDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.bigdataMarketMysql.getValue(), bigdataMarketMysql);
        targetDataSources.put(DataSourceEnum.f10SqlServer.getValue(), f10SqlServer);
        targetDataSources.put(DataSourceEnum.sunlineF10Mysql.getValue(), sunlineF10Mysql);
        targetDataSources.put(DataSourceEnum.sunlineMktInfoMySql.getValue(), sunlineMktInfoMySql);
        targetDataSources.put(DataSourceEnum.cubpMysql.getValue(), cubpMySql);
        //添加数据源
        multipleDataSource.setTargetDataSources(targetDataSources);
        //设置默认数据源
        multipleDataSource.setDefaultTargetDataSource(bigdataMarketMysql);
        return multipleDataSource;
    }

    /**
     * 会话工厂
     *
     * @return
     * @throws Exception
     */
    @Bean("sqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(
                multipleDataSource(
                        bigdataMarketMysql(),
                        f10SqlServer(),
                        sunlineF10Mysql(),
                        sunlineMktInfoMySql(),
                        cubpMySql()));
        sqlSessionFactory.setMapperLocations(resolveMapperLocations());
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(paginationInterceptor());
        return sqlSessionFactory;
    }

    /**
     * 读取xml配置
     *
     * @return
     */
    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath*:mapper/mysql/*.xml");
        mapperLocations.add("classpath*:mapper/sqlserver/*.xml");
        List<Resource> resources = new ArrayList<>();
        for (String mapperLocation : mapperLocations) {
            try {
                Resource[] mappers = resourceResolver.getResources(mapperLocation);
                resources.addAll(Arrays.asList(mappers));
            } catch (IOException exception) {
                log.info("***********读取xml配置异常,详细信息：******************", exception);
            }
        }

        return resources.toArray(new Resource[resources.size()]);
    }
}
package com.fystock.bigdata.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * MyBatis ClickHouse Druid数据源配置
 *
 * @author He.Yong
 * @since 2021-06-11 15:24:18
 */
@Configuration
@MapperScan(basePackages = "com.fystock.bigdata.cloud.mapper")
public class MyBatisDruidConfig {
    /**
     * 配置Druid数据源
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.clickhouse")
    public DataSource dataSource() throws Exception {
        return new DruidDataSource();
    }

    /**
     * 在Mybatis的所有操作都是基于一个SqlSession的，而SqlSession是由SqlSessionFactory来产生的
     * SqlSessionFactory又是由SqlSessionFactoryBuilder来生成的。
     *
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource());
        // 设置typeAlias 实体entity包扫描路径, 比如 com.fystock.bigdata.cloud.entity.clickhouse
        // typeAliasesPackage：它一般对应我们的实体类所在的包，这个时候会自动取对应包中不包括包名的简单类名作为包括包名的别名。
        // 多个package之间可以用逗号或者分号等来进行分隔。(value的值一定要是包的全名)
        factory.setTypeAliasesPackage("com.fystock.bigdata.cloud.entity.clickhouse");
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // mapperLocations：它表示我们的Mapper文件存放的位置
        // 当我们的Mapper文件跟对应的Mapper接口处于同一位置的时候可以不用指定该属性的值。
        factory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
        // 开启驼峰命名转换
        Objects.requireNonNull(factory.getObject()).getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory.getObject();
    }
}

package com.onejane.multisource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = {"com.onejane.multisource.mapper.basemapper"}, sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class BaseMybatisConfig {

    @Value("${spring.datasource.base.filters}")
    String filters;
    @Value("${spring.datasource.base.driver-class-name}")
    String driverClassName;
    @Value("${spring.datasource.base.username}")
    String username;
    @Value("${spring.datasource.base.password}")
    String password;
    @Value("${spring.datasource.base.jdbc-url}")
    String url;


    @Bean(name="baseDataSource")
    @Primary//必须加此注解，不然报错，下一个类则不需要添加 spring.datasource
    @ConfigurationProperties(prefix="spring.datasource.base")//prefix值必须是application.properteis中对应属性的前缀
    public DataSource baseDataSource() throws SQLException {


        DruidDataSource druid = new DruidDataSource();
        // 监控统计拦截的filters
        druid.setFilters(filters);
        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);

        return druid;
    }


    @Bean(name="baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory baseSqlSessionFactory(@Qualifier("baseDataSource")DataSource dataSource)throws Exception{
        // 创建Mybatis的连接会话工厂实例
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);//// 设置数据源bean
        //添加XML目录
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        try{
            bean.setMapperLocations(resolver.getResources("classpath:baseMapper/*Mapper.xml"));//// 设置mapper文件路径
            return bean.getObject();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name="baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory)throws Exception{
        SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory);//使用上面配置的Factory
        return  template;
    }

}

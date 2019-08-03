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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.sql.SQLException;

//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;

@Configuration
@MapperScan(basePackages = {"com.onejane.multisource.mapper.dicmapper"}, sqlSessionTemplateRef = "dicSqlSessionTemplate")
public class DicMybatisConfig {
    @Value("${spring.datasource.dic.filters}")
    String filters;
    @Value("${spring.datasource.dic.driver-class-name}")
    String driverClassName;
    @Value("${spring.datasource.dic.username}")
    String username;
    @Value("${spring.datasource.dic.password}")
    String password;
    @Value("${spring.datasource.dic.jdbc-url}")
    String url;


    @Bean(name = "dicDataSource")
    @ConfigurationProperties(prefix="spring.datasource.dic")
    public DataSource dicDataSource() throws SQLException {

        DruidDataSource druid = new DruidDataSource();
        // 监控统计拦截的filters
       // druid.setFilters(filters);
        // 配置基本属性
        druid.setDriverClassName(driverClassName);
        druid.setUsername(username);
        druid.setPassword(password);
        druid.setUrl(url);

       /* //初始化时建立物理连接的个数
        druid.setInitialSize(initialSize);
        //最大连接池数量
        druid.setMaxActive(maxActive);
        //最小连接池数量
        druid.setMinIdle(minIdle);
        //获取连接时最大等待时间，单位毫秒。
        druid.setMaxWait(maxWait);
        //间隔多久进行一次检测，检测需要关闭的空闲连接
        druid.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //一个连接在池中最小生存的时间
        druid.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        //用来检测连接是否有效的sql
        druid.setValidationQuery(validationQuery);
        //建议配置为true，不影响性能，并且保证安全性。
        druid.setTestWhileIdle(testWhileIdle);
        //申请连接时执行validationQuery检测连接是否有效
        druid.setTestOnBorrow(testOnBorrow);
        druid.setTestOnReturn(testOnReturn);
        //是否缓存preparedStatement，也就是PSCache，oracle设为true，mysql设为false。分库分表较多推荐设置为false
        druid.setPoolPreparedStatements(poolPreparedStatements);
        // 打开PSCache时，指定每个连接上PSCache的大小
        druid.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
*/
        return druid;
    }

    @Bean(name = "dicSqlSessionFactory")
    public SqlSessionFactory dicSqlSessionFactory(@Qualifier("dicDataSource")DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //添加XML目录
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        try{
            bean.setMapperLocations(resolver.getResources("classpath:dicMapper/*Mapper.xml"));
            return bean.getObject();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name="dicSqlSessionTemplate")
    public SqlSessionTemplate dicSqlSessionTemplate(@Qualifier("dicSqlSessionFactory") SqlSessionFactory sqlSessionFactory)throws Exception{
        SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory);//使用上面配置的Factory
        return  template;
    }

}

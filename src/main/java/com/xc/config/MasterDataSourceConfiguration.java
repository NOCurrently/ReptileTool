package com.xc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = "com.xc.mapper", sqlSessionTemplateRef  = "masterSqlSessionTemplate")
public class MasterDataSourceConfiguration  {


    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.max-active:50}")
    private String maxActive;

    @Value("${spring.datasource.initialSize:10}")
    private String initialSize;


    @Bean(name = "masterDataSource")
    //下面这个注解控制哪个实例优先被注入，我们放在第一个数据源上面
    @Primary
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClass);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(password);
        druidDataSource.setMaxActive(Integer.valueOf(maxActive));
        druidDataSource.setInitialSize(Integer.valueOf(initialSize));
        try {
            druidDataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  druidDataSource;
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
       /* PageInterceptor pageInterceptor = MybatisUtils.getPageInterceptor();
        CatMybatisPlugin catMybatisPlugin = new CatMybatisPlugin();
        bean.setPlugins(new Interceptor[]{pageInterceptor, catMybatisPlugin});*/
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

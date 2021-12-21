package my.qunz.myDemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.proxy.DruidDriver;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@MapperScan("my.qunz.myDemo.mapper")
@Configuration
@EnableTransactionManagement
public class DynamicDatasourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    /**
     *
     * @return 数据源配置
     * @throws SQLException
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource dataSourceMaster() throws SQLException {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriver(new DruidDriver().createDriver(driverClassName));
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUseGlobalDataSourceStat(true);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setValidationQuery("select 1");
        dataSource.setTimeBetweenEvictionRunsMillis(6000);
        return dataSource;
    }


    /**
     * 将默认数据源添加进动态数据源
     * @param dataSourceMaster
     * @return
     */
    @Bean("dynamicDataSource")
    public DynamicRoutingDataSource dynamicDataSource(DataSource dataSourceMaster) {

        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        dynamicRoutingDataSource.addDataSource("master", dataSourceMaster);
        return dynamicRoutingDataSource;
    }


    @Bean
    @Primary
    @ConfigurationProperties(prefix = "mybatis")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DataSource dataSource, @Qualifier("mybatisPlusInterceptor") MybatisPlusInterceptor mybatisPlusInterceptor) {

        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //分页插件
        MybatisPlusInterceptor[] plugins = new MybatisPlusInterceptor[1];
        plugins[0] = mybatisPlusInterceptor;
        sqlSessionFactoryBean.setPlugins(plugins);
        return sqlSessionFactoryBean;
    }

}

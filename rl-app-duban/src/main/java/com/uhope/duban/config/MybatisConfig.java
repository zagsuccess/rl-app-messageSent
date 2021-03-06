package com.uhope.duban.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.uhope.core.Mapper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Mybatis & Mapper & PageHelper 配置
 *
 * @author ChenBin on 2018/09/04
 */
@Configuration
public class MybatisConfig {

    private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize:5}")
    private int initialSize;

    @Value("${spring.datasource.minIdle:5}")
    private int minIdle;

    @Value("${spring.datasource.maxActive:20}")
    private int maxActive;

    @Value("${spring.datasource.maxWait:60000}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis:60000}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis:300000}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery:SELECT 1 FROM DUAL}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle:true}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow:false}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn:false}")
    private boolean testOnReturn;

    @Value("${spring.datasource.filters:stat,wall,log4j}")
    private String filters;


    @Bean
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource
                .setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setTypeAliasesPackage("com.uhope.**.domain");

        // 配置分页插件，详情请查阅官方文档
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        // 分页尺寸为0时查询所有纪录不再执行分页
        properties.setProperty("pageSizeZero", "true");
        // 页码<=0 查询第一页，页码>=总页数查询最后一页
        properties.setProperty("reasonable", "true");
        // 支持通过 Mapper 接口参数来传递分页参数
        properties.setProperty("supportMethodsArguments", "true");
        pageHelper.setProperties(properties);

        // 添加插件
        factory.setPlugins(new Interceptor[]{pageHelper});

        // 添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factory.setMapperLocations(resolver
                .getResources("classpath*:mybatis/mapper/*.xml"));
        factory.setConfiguration(mybatisConfig());
        return factory.getObject();
    }

    /**
     * 方法前面加上static。让使用configuration的类在没有实例化的时候不会去过早的要求@Autowired和@Value,否则会导致@value注入失败
     *
     * @return
     */
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        mapperScannerConfigurer.setBasePackage("com.uhope.**.mapper");

        // 配置通用Mapper，详情请查阅官方文档
        Properties properties = new Properties();
        properties.setProperty("mappers", Mapper.class.getName());
        // insert、update是否判断字符串类型!='' 即 test="str != null"表达式内是否追加 and str != ''
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "SELECT REPLACE(UUID(),\"-\",\"\")");
        properties.setProperty("ORDER", "BEFORE");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    /**
     * 与mybatis-config.xml对应的 java类，通过java对象({@link org.apache.ibatis.session.Configuration})来设置，删掉xml配置文件
     * typeAliases的配置可以参考{@link  org.apache.ibatis.session.Configuration#getTypeAliasRegistry()} ,一般常用的对象都已经默认配置
     *
     * @return
     */
    private org.apache.ibatis.session.Configuration mybatisConfig() {
        org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
        //默认情况下,Mybatis对Map的解析生成, 如果值(value)为null的话,那么key也不会被加入到map中,因此需要设置callSettersOnNulls为true
        mybatisConfig.setCallSettersOnNulls(true);
        return mybatisConfig;
    }

}
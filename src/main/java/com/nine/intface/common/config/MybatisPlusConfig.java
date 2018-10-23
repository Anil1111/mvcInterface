package com.nine.intface.common.config;


import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author : Rubi
 * @version : 2018-10-09 16:21
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.nine.intface.*.dao")
public class MybatisPlusConfig  implements TransactionManagementConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);
    
    @Autowired
    private DataSource dataSource;

    /**
     * check-config-location: true
     * mapper-locations: classpath*:mybatis/*.mapper
     * type-aliases-package: com.noob.po
     * type-aliases-super-type: java.lang.Object
     * #TypeHandler 通常用于自定义类型转换。
     * #type-handlers-package: com.baomidou.mybatisplus.samples.quickstart.handler
     * #枚举类 扫描路径，，会将路径下的枚举类进行注入，让实体类字段能够简单快捷的使用枚举属性，
     * #type-enums-package: com.baomidou.mybatisplus.samples.quickstart.enums
     * aggressive-lazy-loading: true
     * @return
     * @throws Exception
     */
    public Interceptor getpageHelperPlugin(){
        PageInterceptor pageHelperInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("helperDialect", "mysql");
        pageHelperInterceptor.setProperties(properties);
        return pageHelperInterceptor;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception{




        logger.info("----------------ConfigInit:sqlSessionFactory");

        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource);
        mybatisPlus.setTypeAliasesPackage("com.nine.intface.*.po");

        //分页插件
        GlobalConfig gc = new GlobalConfig();
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        ISqlInjector sqlInjector = new LogicSqlInjector();
        gc.setBanner(false);
        gc.setDbConfig(dbConfig).setSqlInjector(sqlInjector);


        //添加插件
        mybatisPlus.setPlugins(new Interceptor[]{getpageHelperPlugin()});

        //设置
        MybatisConfiguration mc = new MybatisConfiguration();
        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mc.setMapUnderscoreToCamelCase(true); //用驼峰
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        mybatisPlus.setMapperLocations(resolver.getResources("classpath:mapper/*/*.xml"));
//        try {
//            bean.setMapperLocations(resolver.getResources("classpath:mapper/*.mapper"));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
        mybatisPlus.setGlobalConfig(gc);
        mybatisPlus.setConfiguration(mc);
        return mybatisPlus.getObject();
    }

//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        logger.info("----------------ConfigInit:annotationDrivenTransactionManager");
        return new DataSourceTransactionManager(dataSource);
    }

    //    public ConfigurationCustomizer configurationCustomizer(){
//      return  new ConfigurationCustomizer(){
//
//            @Override
//            public void customize(Configuration configuration) {
//                configuration.setMapUnderscoreToCamelCase(true);
//            }
//        };
//    }



}

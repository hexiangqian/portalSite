package zy.news.amain;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {
        MyDbConfSettings.BASE_PACKGE}, sqlSessionFactoryRef = MyDbConfSettings.SQLSESSIONFACTORYREF, sqlSessionTemplateRef = MyDbConfSettings.SQLSESSIONTEMPLATEREF)
public class MyDbConfSettings {
    static final String SQLSESSIONFACTORYREF = "SQLSESSIONFACTORYREF_MYSQL";
    static final String SQLSESSIONTEMPLATEREF = "SQLSESSIONTEMPLATEREF_MYSQL";
    static final String DATASOURCE = "DATASOURCE_MYSQL";

    static final String BASE_PACKGE = "zy.news.web.mapper";
    private static final String MAPPER_LOCATION = "classpath:mysql/mapper/*.xml";
    private static final String TYPEALIASES_PACKAGE = "zy.news.web.bean";

    @Resource(name = MyDbConfSettings.DATASOURCE)
    private DataSource dataSource;


    @Bean(MyDbConfSettings.DATASOURCE)
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dbDataSource() {
        return new DruidDataSource();
    }

    @Bean(SQLSESSIONFACTORYREF)
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(TYPEALIASES_PACKAGE);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));

        // 设置分页插件
        Interceptor interceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        interceptor.setProperties(properties);
        factoryBean.setPlugins(new Interceptor[]{interceptor});

        return factoryBean.getObject();

    }

    @Bean(SQLSESSIONTEMPLATEREF)
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory()); // 使用上面配置的Factory
    }
}
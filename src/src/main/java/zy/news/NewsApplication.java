package zy.news;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import maoko.sdk.SDKCommon;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import zy.news.amain.AfterAppStartUp;

/**
 * 程序启动入口
 *
 * @author fanpei
 */
@EnableAsync
@PropertySource("classpath:application.yml")
@EnableConfigurationProperties
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class,
        JacksonAutoConfiguration.class,
        DataSourceAutoConfiguration.class, PageHelperAutoConfiguration.class})
public class NewsApplication {
    public static void main(String[] args) {
        try {
            SDKCommon.init();
            SpringApplication spApplication = new SpringApplication(NewsApplication.class);
            spApplication.addListeners(new AfterAppStartUp());
            spApplication.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}



package zy.news.amain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 自定义web配置
 *
 * @author maoko
 * @date 2020/2/19 17:47
 */
@Data
@Component
@ConfigurationProperties(prefix = "web")
public class CusWebConfig {
    /**
     * 文件上传位置
     */
    private String uploadPath;
}

package zy.news.web;

import lombok.Data;
import maoko.common.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import zy.news.NewsConfiguration;

import java.io.File;

/**
 * 自定义web配置
 *
 * @author maoko
 * @date 2020/2/19 17:47
 */
@Data
@Component
@ConfigurationProperties(prefix = "web")
public class UploadFilePathConfig {
    /**
     * 文件上传位置
     * windows下路径分割符配置 \\
     * linux下路径配置分割符 /
     */
    private String uploadPath;


    /**
     * 服务端真实保存文件地址
     *
     * @return
     */
    public String getRealSavePath() {
        if (StringUtil.isStringNull(realSavePath)) {
            StringBuilder realSavePathSb = new StringBuilder();
            realSavePathSb.append(uploadPath);
            realSavePathSb.append(NewsConfiguration.STATICFLAG);
            realSavePathSb.append(File.separatorChar);
            realSavePath = realSavePathSb.toString().replace("//", "/");
        }
        return realSavePath;
    }

    private String realSavePath;
}

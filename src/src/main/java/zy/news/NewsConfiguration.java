package zy.news;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import zy.news.web.UploadFilePathConfig;
import zy.news.web.zsys.gson.MyGsonUtil;
import zy.news.web.zsys.interceptor.AuthorityInterceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * web配置
 * 静态资源为static为匹配路径
 *
 * @author fanpei
 */
@Configuration
@EnableWebMvc
public class NewsConfiguration extends WebMvcConfigurerAdapter {
    public static final String STATICFLAG = "static";
    @Autowired
    private UploadFilePathConfig uploadFilePathConfig;

    @Autowired
    private AuthorityInterceptor athinterceptor;

    /*
     * 跨域解决
     *
     * @see
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
     * addCorsMappings(org.springframework.web.servlet.config.annotation.
     * CorsRegistry)
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")// 添加路径映射
                .allowedOrigins("*")// 放行的原始域(url)
                .allowedHeaders("*")// 放行的头部信息
                .allowedMethods("*")// 放行的请求方式
                .allowCredentials(true)// 是否发送Cookie信息
                .maxAge(3600);// 单位：秒，缓存预检测结果时长，避免每次请求进行预检测
    }


    /*
     * 静态资源访问
     *
     * @see
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
     * addResourceHandlers(org.springframework.web.servlet.config.annotation.
     * ResourceHandlerRegistry)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/" + STATICFLAG + "/**")
                .addResourceLocations("file:" + uploadFilePathConfig.getRealSavePath() + "/")
                .addResourceLocations("classpath:/" + STATICFLAG + "/");
        super.addResourceHandlers(registry);
    }

    /*
     * 拦截器
     *
     * @see
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
     * addInterceptors(org.springframework.web.servlet.config.annotation.
     * InterceptorRegistry)
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(athinterceptor);

        // 拦截所有路径
        registration.addPathPatterns("/**");
        // 排除路径
        registration.excludePathPatterns("/error",
                "/back/user/signin",
                "/back/user/logout",
                "/websocket",
                "/static/**");

        super.addInterceptors(registry);
    }

    /*
     * gson替换
     *
     * @see
     * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#
     * configureMessageConverters(java.util.List)
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);
        stringConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_PLAIN));
        converters.add(stringConverter);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());

        GsonHttpMessageConverter msConverter = new GsonHttpMessageConverter();
        Gson gson = MyGsonUtil.getGson();
        msConverter.setGson(gson);
        msConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(msConverter);
        super.configureMessageConverters(converters);
    }

}

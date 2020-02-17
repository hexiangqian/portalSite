package zy.news.web.zsys.interceptor;

/**
 * @author fanpei
 */

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import zy.news.web.zsys.bean.WebMethodLock;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Configuration
public class LockMethodInterceptor {
    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存 1000 个
            .maximumSize(1000)
            // 设置写缓存后 5 秒钟过期
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    @Around("execution(public * *(..)) && @annotation(zy.news.web.zsys.bean.WebMethodLock)")
    public Object interceptor(ProceedingJoinPoint pjp) throws Exception {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        WebMethodLock localLock = method.getAnnotation(WebMethodLock.class);
        String key = "";
        if (-1 != localLock.argIndex()) {
            Object[] args = pjp.getArgs();
            key = pjp.getArgs()[localLock.argIndex()].toString();
        } else
            key = getKey(localLock.key(), pjp.getArgs());
        if (!StringUtils.isEmpty(key)) {
            if (CACHES.getIfPresent(key) != null) {
                throw new RuntimeException("".equals(localLock.tips()) ? "当前操作锁定中" : localLock.tips());
            }
            // 如果是第一次请求 , 就将 key 当前对象压入缓存中
            CACHES.put(key, key);
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        } finally {
            CACHES.invalidate(key);
        }
    }

    /**
     * key 的生成策略 , 如果想灵活可以写成接口与实现类的方式（ TODO 后续讲解）
     *
     * @param keyExpress 表达式
     * @param args       参数
     * @return 生成的 key
     */
    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }
}

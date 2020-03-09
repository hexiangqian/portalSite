package zy.news.web.zsys.interceptor;

import zy.news.web.zsys.utils.ResponseUtil;
import zy.news.web.zsys.bean.StatusCode;
import maoko.common.log.IWriteLog;
import maoko.common.log.Log4j2Writer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Parameter;

/**
 * 异常拦截拦截
 *
 * @author fanpei
 */
@Component
public class MyExceptionResolver implements HandlerExceptionResolver {
    private static final IWriteLog LOG = new Log4j2Writer(MyExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (null != handlerMethod.getBeanType().getAnnotation(ExcuteControllerDsrc.class)) {
            String controller = handlerMethod.getBeanType().getSimpleName();
            String method = handlerMethod.getMethod().getName();
            StatusCode code = StatusCode.执行失败;
            switch (ex.getClass().getSimpleName()) {
                case "PermissonCheckErrorException":
                    code = StatusCode.权限错误;
                    break;
                case "LoginTimeOutException":
                    code = StatusCode.超时重登;
                    break;
                case "WarningException":
                case "TimeoutException":
                case "LoginitException":
                    code = StatusCode.执行失败;
                    LOG.warn("执行[{}]方法名[{}]报出系统提示 提示:{}\n\r", controller, method, ex.getMessage());
                    break;
                default:
                    code = StatusCode.执行失败;
                    LOG.error("执行[{}]方法名[{}]失败\n\r", controller, method, ex);
                    break;
            }
            ResponseUtil.response2Brower(response, code, ex);
        }
        return null;
    }

    @Deprecated
    public static String parametersToString(Parameter[] meters) {
        String value = "no Parameters";
        if (meters != null && meters.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Parameter parameter : meters) {
                sb.append(parameter.toString()).append(" ");
            }
            value = sb.toString();
        }
        return value;
    }

}

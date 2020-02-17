package zy.news.common;

import maoko.common.ExceptionUtil;
import maoko.common.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    private static final Logger log = LogManager.getLogger(ResponseUtil.class);

    /**
     * 向浏览器发送回复
     *
     * @param response
     * @param ex
     * @throws Exception
     */
    public static void response2Brower(HttpServletResponse response, StatusCode code, Throwable ex) {
        response2Brower(response, code, ExceptionUtil.getCauseMessage(ex));
    }

    public static void response2Brower(HttpServletResponse response, StatusCode code, String message) {
        // PrintWriter writer = null;
        ServletOutputStream stream = null;
        try {
            response.reset();
            response.setStatus(code.getCode());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            stream = response.getOutputStream();
            // writer = response.getWriter();
            String errorMsg = message;
            if (StringUtil.isStrNullOrWhiteSpace(errorMsg))
                errorMsg = "发生未知的错误，请联系管理员！";
            stream.write(StringUtil.getUtf8Bytes(errorMsg));
            stream.flush();
        } catch (Exception e) {
            log.warn("向浏览器发送回复数据异常", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}

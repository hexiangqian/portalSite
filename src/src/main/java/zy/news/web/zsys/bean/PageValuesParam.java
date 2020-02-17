package zy.news.web.zsys.bean;

import zy.news.common.exception.WarningException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 分页获取参数
 * 注意：添加参数需要与方法参数顺序一致
 *
 * @author maoko
 * @date 2020/1/13 18:33
 */
public class PageValuesParam<T> {
    private Object[] params;
    private Class[] paramsClazz;
    private Object invoker;
    private String methodNam;


    /**
     * @param invoker   调用者
     * @param methodNam 方法名称
     */
    public PageValuesParam(Object invoker, String methodNam) {
        this.invoker = invoker;
        this.methodNam = methodNam;
    }

    public PageValuesParam(Object invoker, String methodNam, int argCount) {
        this.invoker = invoker;
        this.methodNam = methodNam;
        if (argCount > 0) {
            this.params = new Object[argCount];
            this.paramsClazz = new Class[argCount];
        }
    }

    public void addParam(int index, Object obj) {
        paramsClazz[index] = obj.getClass();
        params[index] = obj;
    }

    private Method getMethod() throws WarningException, NoSuchMethodException {
        Method method = null;
        if (params != null)
            method = invoker.getClass().getMethod(methodNam, paramsClazz);
        else
            method = invoker.getClass().getMethod(methodNam);
        return method;
    }

    public List<T> getRecords() throws WarningException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (null == params)
            return (List<T>) getMethod().invoke(invoker);
        else
            return (List<T>) getMethod().invoke(invoker, params);
    }
}

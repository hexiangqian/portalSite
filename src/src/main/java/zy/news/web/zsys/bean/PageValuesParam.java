package zy.news.web.zsys.bean;

import zy.news.common.exception.WarningException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/**
 * 分页获取参数
 *
 * @author maoko
 * @date 2020/1/13 18:33
 */
public class PageValuesParam<T> {
    private List<Object> params;
    private Object invoker;
    private String methodNam;

    private PageValuesParam() {
    }


    /**
     * @param invoker   调用者
     * @param methodNam 方法名称
     */
    public PageValuesParam(Object invoker, String methodNam) {
        this.invoker=invoker;
        this.methodNam=methodNam;
        this.params = new LinkedList<>();
    }

    /**
     * 添加参数
     * 注意：添加需要与方法参数顺序一致
     *
     * @param param
     */
    public void addParam(Object param) {
        params.add(param);
        //paramClass.add(param.getClass());
    }

    private Method getMethod() throws WarningException {
        Method[] methods = invoker.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodNam)) {
                return method;
            }
        }
        throw new WarningException("未找到此方法" + methodNam);
    }

    public List<T> getRecords() throws WarningException, InvocationTargetException, IllegalAccessException {
        if (params.isEmpty()) {
            return (List<T>) getMethod().invoke(invoker);
        } else {
            return (List<T>) getMethod().invoke(invoker, params.toArray());
        }
    }
}

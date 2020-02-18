package zy.news.common.db.base;


import zy.news.common.db.SearchParam;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Service抽象基类-公共处理函数模块
 *
 * @author fanpei
 */
public abstract class AbstractDbServiceParent<T1, T2> {

    public static final String ARRAY_RANGE_SEPERATOR = "\\|";

    private static Class<?> LISTCLASS = null;

    static {
        try {
            LISTCLASS = Class.forName("java.util.List");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static final ReentrantLock LOCK = new ReentrantLock();
    // private static final String EXAMPLE = "Example";
    // private static final String CRITERIA = ".Criteria";

    protected String entityName;
    protected String exampleName;
    // protected static String criteriaName;

    // protected static Map<String, Method> entityMethods;
    // protected static Map<String, Method> examplMethods;
    // protected static Map<String, Method> criteriaMethods;

    protected boolean hasBlobs = false;// 是否拥有blobs字段

    @PostConstruct
    protected void methodInit() {
        LOCK.lock();
        try {
            {
                if (exampleName == null) {
                    // 获取参数类名称
                    Type clazz1 = ((ParameterizedType) this.getClass().getGenericSuperclass())
                            .getActualTypeArguments()[0];

                    entityName = clazz1.getTypeName();
                    Type clazz2 = ((ParameterizedType) this.getClass().getGenericSuperclass())
                            .getActualTypeArguments()[1];
                    exampleName = clazz2.getTypeName();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     */
    public abstract void init() throws Exception;


    /**
     * 组装T2 example条件
     *
     * @param params
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected T2 getExampleByParams(List<List<SearchParam>> params) throws Exception {
        Class<T2> exampleClass = (Class<T2>) Class.forName(exampleName);
        Class<?> entityClass = Class.forName(entityName);
        return DbExampleUtil.getExample(params, entityClass, exampleClass);
    }

    protected static Object entityGetMethod(Object obj, String methodName, Object value)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return DbExampleUtil.entityGetMethod(obj, methodName, value);
    }

    protected static Object entityGetMethod(Object obj, String methodName, Object value, Class<?> valueType)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return DbExampleUtil.entityGetMethod(obj, methodName, value, valueType);
    }

    protected static Object entityGetMethod(Object criteria1, String methodName)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return DbExampleUtil.entityGetMethod(criteria1, methodName);
    }
}

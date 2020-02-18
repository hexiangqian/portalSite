package zy.news.common.db.base;

import zy.news.common.db.DbMatchRule;
import zy.news.common.db.SearchParam;
import maoko.common.InstanceUitl;
import maoko.common.exception.DataIsNotCorrectFormatException;
import org.apache.commons.lang3.time.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.List;

/**
 * @author fanpei
 */
public class DbExampleUtil {
    private static final String FIRSTFILTER = "and";
    private static final String SETFIRSTFILTER = "set";
    public static final String RANGE_SEPERATOR = "|";
    public static final String ARRAY_RANGE_SEPERATOR = "\\|";

    private static Class<?> LISTCLASS = null;

    static {
        try {
            LISTCLASS = Class.forName("java.util.List");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static <T1, T2> T2 getExample(List<List<SearchParam>> params, Class<T1> entityTarget, Class<T2> exampleTarget) throws Exception {
        T2 t = (T2) InstanceUitl.createObject(exampleTarget);
        Method method = null; // 方法
        if (params != null && params.size() > 0) {
            for (List<SearchParam> kvs : params) {
                Object criteria = creatObject(t);
                for (SearchParam param : kvs) {
                    String methodName = genMethodName(param);
                    Class<?> argType = getFeildType(entityTarget, param);
                    switch (param.getRule()) {
                        case Between:
                            String paramValue = (String) param.getValue();
                            if (!paramValue.contains(RANGE_SEPERATOR)) {
                                throw new DataIsNotCorrectFormatException(
                                        "value not contain seperator,must have two value");
                            }
                            String[] values = paramValue.split(ARRAY_RANGE_SEPERATOR);
                            method = criteria.getClass().getMethod(methodName, argType, argType);
                            method.invoke(criteria, getArgObjValue(argType, values[0]), getArgObjValue(argType, values[1]));
                            break;
                        case In:
                        case NotIn:
                            method = criteria.getClass().getMethod(methodName, LISTCLASS);
                            method.invoke(criteria, param.getValue());
                            break;
                        case GroupbyClause:
                        case OrderByClause:
                            method = t.getClass().getMethod(methodName, String.class);
                            method.invoke(t, (String) param.getValue());
                            break;
                        default:
                            if (argType != null) {
                                Object v = null;
                                method = criteria.getClass().getMethod(methodName, argType);
                                if (DbMatchRule.Like == param.getRule()) {
                                    v = getLikeValue(param.getValue());
                                } else if (argType != String.class) {
                                    v = getArgObjValue(argType, param.getValue());
                                } else {
                                    v = param.getValue();
                                }
                                if (v != null) {
                                    method.invoke(criteria, v);
                                }
                            } else {
                                method = criteria.getClass().getMethod(methodName);
                                method.invoke(criteria);
                            }
                            break;
                    }
                }
            }

        }
        return t;

    }

    private static Object getArgObjValue(Class<?> argType, Object value) throws ParseException {
        Object v = null;
        String objV = String.valueOf(value);
        switch (argType.getSimpleName()) {
            case "Integer":
                v = Integer.valueOf(Integer.parseInt(objV));
                break;
            case "Byte":
                v = Byte.valueOf(Byte.parseByte(objV));
                break;
            case "Long":
                v = Long.valueOf(Long.parseLong(objV));
                break;
            case "Short":
                v = Short.valueOf(Short.parseShort(objV));
                break;
            case "Double":
                v = Double.valueOf(Double.parseDouble(objV));
                break;
            case "Float":
                v = Float.valueOf(Float.parseFloat(objV));
                break;
            case "Date":
                v = DateUtils.parseDate(objV, "yyyy-MM-dd HH:mm:ss");
                break;
            default:
                v = objV;
        }
        return v;
    }

    /**
     * 获取类型
     *
     * @param param
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    private static Class<?> getFeildType(Class<?> target, SearchParam param) throws SecurityException, NoSuchFieldException {
        if (param.getValue() != null && param.getValue() instanceof List) {
            return ((List) param.getValue()).get(0).getClass();
        } else {
            if (param.getValue() != null) {
                return getFeildTypeOld(target, param);
            } else {
                return null;
            }
        }
    }

    private static Class<?> getFeildTypeOld(Class<?> target, SearchParam param)
            throws SecurityException, NoSuchFieldException {
        switch (param.getRule()) {
            case OrderByClause:
            case GroupbyClause:
                return String.class;
            case In:
            case NotIn:
                return LISTCLASS;
            default:
                String lowerKey = param.getKey();
                try {
                    //获取当前类
                    Field f = target.getDeclaredField(lowerKey);
                    f.setAccessible(true);
                    return f.getType();
                } catch (NoSuchFieldException e) {
                    //获取父类
                    Field f = target.getSuperclass().getDeclaredField(lowerKey);
                    f.setAccessible(true);
                    return f.getType();
                }
        }
    }

    /**
     * 获取方法
     *
     * @param obj
     * @param methodName
     * @param value
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public static Object entityGetMethod(Object obj, String methodName, Object value)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return obj.getClass().getMethod(methodName, value.getClass()).invoke(obj, value);
    }

    public static Object entityGetMethod(Object obj, String methodName, Object value, Class<?> valueType)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return obj.getClass().getMethod(methodName, valueType).invoke(obj, value);
    }

    public static Object entityGetMethod(Object criteria1, String methodName)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return criteria1.getClass().getMethod(methodName).invoke(criteria1);
    }

    /**
     * 获取模糊匹配%value%值
     *
     * @param value 待匹配值
     * @return
     */
    public static String getLikeValue(Object value) {
        String likeStr = null;
        if (null == value) {
            return null;
        }
        likeStr = new StringBuilder().append("%").append(value).append("%").toString();
        if ("%%".equals(likeStr)) {
            return null;
        }
        return likeStr;
    }

    private static final String ORCREATECRITERIA = "or";
    private static final String ANDCREATECRITERIA = "createCriteria";

    /**
     * or方法创建对象
     *
     * @param t
     * @return
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    private static Object creatObject(Object t) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Method method = t.getClass().getDeclaredMethod(ORCREATECRITERIA);
        Object obj = method.invoke(t);
        return obj;
    }

    /**
     * 组装方法名
     *
     * @param param
     * @return
     */
    private static String genMethodName(SearchParam param) {
        switch (param.getRule()) {
            case GroupbyClause:
            case OrderByClause:
                return new StringBuilder()//
                        .append(SETFIRSTFILTER)//
                        // .append(upcaseFirst(feildName))//
                        .append(param.getRule()).toString();
            default:
                return new StringBuilder()//
                        .append(FIRSTFILTER)//
                        .append(upcaseFirst(param.getKey()))//
                        .append(param.getRule()).toString();
        }
    }

    /**
     * 大写首字母
     *
     * @param str
     * @return
     */
    private static String upcaseFirst(String str) {
        char[] chars = str.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char) (chars[0] - 32);
        }
        return new String(chars);
    }

}

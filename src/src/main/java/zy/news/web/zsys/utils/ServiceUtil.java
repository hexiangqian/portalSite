package zy.news.web.zsys.utils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import maoko.common.StringUtil;
import maoko.common.log.IWriteLog;
import maoko.common.log.Log4j2Writer;
import zy.news.web.zsys.bean.Page;
import zy.news.web.zsys.bean.ValuesPage;
import zy.news.web.zsys.bean.IKeyFlag;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fanpei
 */
public class ServiceUtil {
    private static final IWriteLog LOG = new Log4j2Writer(ServiceUtil.class);

    /**
     * 分页获取方法
     *
     * @param page            分页
     * @param pageValuesparam 参数
     * @param <T>             返回类型
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <T> ValuesPage getValuePage(Page page, PageValuesParam<T> pageValuesparam) throws Exception {
        List<T> records = null;
        if (page == null) {
            page = new Page();//不分页
        }
        //大于0分页有效
        if (page.getCurrent() > 0) {
            // 配置分页
            PageHelper.startPage(page.getCurrent(), page.getSize());
            records = pageValuesparam.getRecords();
            PageInfo<T> pageInfo = new PageInfo<>(records);
            // 取分页信息
            page.setTotal(pageInfo.getTotal());
            page.setTotalPages(pageInfo.getPages());
        } else {
            records = pageValuesparam.getRecords();
            page.setTotal(records != null ? records.size() : 0);
        }
        return new ValuesPage(records, page);
    }

    /**
     * 获取分页结果
     *
     * @param page
     * @param pageValuesparam
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> PageValuesResult<T> getValuePageResult(Page page, PageValuesParam<T> pageValuesparam) throws Exception {
        List<T> records;
        if (null == page) {
            page = new Page();//不分页
        }
        //大于0分页有效
        if (page.getCurrent() > 0) {
            // 配置分页
            PageHelper.startPage(page.getCurrent(), page.getSize());
            records = pageValuesparam.getRecords();
            PageInfo<T> pageInfo = new PageInfo<>(records);
            // 取分页信息
            page.setTotal(pageInfo.getTotal());
            page.setTotalPages(pageInfo.getPages());
        } else {
            records = pageValuesparam.getRecords();
            page.setTotal(records != null ? records.size() : 0);
        }
        return new PageValuesResult<>(records, page);
    }

    /**
     * 判断返回数据json不为空
     *
     * @param jsonStr
     * @return
     */
    public static boolean jsonNotEmpty(String jsonStr) {
        return !StringUtil.isStrNullOrWhiteSpace(jsonStr)
                & !"[]".equals(jsonStr);
    }

    public static String getKeysFromStr(List<IKeyFlag> values) {
        StringBuilder nodesSb = new StringBuilder();
        if (values != null && !values.isEmpty()) {
            nodesSb = new StringBuilder();
            boolean first = true;
            for (IKeyFlag sni : values) {
                if (first) {
                    first = false;
                } else {
                    nodesSb.append(",");
                }
                nodesSb.append(sni.getKeyFlg());
            }
        }
        return nodesSb.toString();
    }

    /**
     * 获取json key
     *
     * @param jsonStr
     * @return
     */
    public static List<String> jsonKeys(String jsonStr) {
        List<String> clomns = null;
        if (!StringUtil.isStrNullOrWhiteSpace(jsonStr) && !"[]".equals(jsonStr) && !"{}".equals(jsonStr)) {
            String jobjStr = null;
            int index = jsonStr.indexOf("}");
            if (jsonStr.startsWith("[{")) {
                jobjStr = jsonStr.substring(2, index);
            } else {
                jobjStr = jsonStr.substring(1, index);
            }
            String[] jKeyVs = jobjStr.split(",");
            clomns = new ArrayList<>(jKeyVs.length);
            for (String jKvItem : jKeyVs) {
                String[] colKyes = jKvItem.split(":");
                String colKey = colKyes[0].replace("\"", "");
                clomns.add(colKey);
            }
        }
        return clomns;
    }

}

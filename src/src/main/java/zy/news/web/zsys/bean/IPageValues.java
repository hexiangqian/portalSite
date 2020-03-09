package zy.news.web.zsys.bean;

import java.util.List;

/**
 * 用于替代反射 分页获取模式，改用接口
 *
 * @author maoko
 * @date 2020/3/9 13:51
 */
@Deprecated
public interface IPageValues<T> {
    List<T> getRecords();
}

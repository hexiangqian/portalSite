package zy.news.web.service;

import zy.news.web.zsys.bean.Page;
import zy.news.web.zsys.bean.ValuesPage;
import zy.news.common.exception.WarningException;

import java.util.List;

/**
 * cruid基本操作
 *
 * @author maoko
 * @date 2020/1/14 16:20
 */
public interface IBaseCruid<T> {

    int deleteByPrimaryKey(Long id) throws WarningException;

    int insert(T record) throws WarningException;

    T selectByPrimaryKey(Long id);

    List<T> selectAll();

    int updateByPrimaryKey(T record) throws WarningException;

    ValuesPage selectAllPage(Page page) throws Exception;
}

package zy.news.web.service;

import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.exception.WarningException;
import zy.news.web.bean.SysUser;

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

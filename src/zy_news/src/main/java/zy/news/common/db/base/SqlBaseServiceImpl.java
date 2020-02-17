package zy.news.common.db.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.db.SearchParam;
import maoko.common.InstanceUitl;
import maoko.common.exception.DataIsNullException;


import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static zy.news.common.db.base.DbExampleUtil.entityGetMethod;

/**
 * 服务基础类
 *
 * @param <T1>
 * @param <T2> example bean
 * @author fanpei
 */
@SuppressWarnings("unchecked")
public abstract class SqlBaseServiceImpl<T1, T2> extends DbServiceParent<T1, T2> implements ISqlBaseService<T1, T2> {

    @Override
    public void batchAdd(List<T1> records) {
        getMapper().batchInsert(records);
    }

    @Override
    public void add(T1 record) throws Exception {
        getMapper().insert(record);
    }

    @Override
    public boolean containName(String name)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, DataIsNullException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        T2 example = (T2) InstanceUitl.createObject(exampleName);
        Object criteria1 = entityGetMethod(example, "or");
        entityGetMethod(criteria1, "andNameEqualTo", name);
        return getMapper().countByExample(example) > 0;
    }

    @Override
    public boolean containId(Long id)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException,
            InstantiationException, DataIsNullException, NoSuchMethodException {
        T2 example = (T2) InstanceUitl.createObject(exampleName);
        Object criteria1 = entityGetMethod(example, "or");
        entityGetMethod(criteria1, "andIdEqualTo", id);
        return getMapper().countByExample(example) > 0;
    }

    @Override
    public void update(T1 record) {
        getMapper().updateByPrimaryKey(record);
    }

    @Override
    public void delete(List<Long> ids)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException,
            InstantiationException, DataIsNullException, NoSuchMethodException {
        if (ids.size() == 1) {
            getMapper().deleteByPrimaryKey(ids.get(0));
        } else {
            T2 example = (T2) InstanceUitl.createObject(exampleName);
            Object criteria1 = entityGetMethod(example, "or");
            entityGetMethod(criteria1, "andIdIn", ids, List.class);
            getMapper().deleteByExample(example);
        }
    }

    @Override
    public T1 getRecord(long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public ValuesPage getRecords(Page page) throws Exception {
        return getRecords(page, true);
    }

    @Override
    public ValuesPage getRecords(Page page, boolean withBlobs) throws Exception {
        return searchRecordsWithParams(page, null, withBlobs);
    }

    @Override
    public ValuesPage searchRecords(Page page, List<List<SearchParam>> params) throws Exception {
        return searchRecordsWithParams(page, params, true);
    }

    @Override
    public ValuesPage searchRecordsWithParams(Page page, List<List<SearchParam>> params, boolean withBlobs) throws Exception {
        List<T1> records = null;
        if (page == null)
            page = new Page();//不分页
        T2 example = getExampleByParams(params);
        return searchRecordsWithExample(page, example, withBlobs);
    }

    @Override
    public ValuesPage searchRecordsWithExample(Page page, T2 example, boolean withBlobs) throws Exception {
        List<T1> records = null;
        if (page == null)
            page = new Page();//不分页
        //大于0分页有效
        if (page.getCurrent() > 0) {
            // 配置分页
            PageHelper.startPage(page.getCurrent(), page.getSize());
            records = getT1s(withBlobs, example);
            PageInfo<T1> pageInfo = new PageInfo<>(records);
            // 取分页信息
            page.setTotal(pageInfo.getTotal());
            page.setTotalPages(pageInfo.getPages());
        } else {
            records = getT1s(withBlobs, example);
            page.setTotal(records != null ? records.size() : 0);
        }
        ValuesPage vPage = new ValuesPage(records, page);
        return vPage;
    }

    @Override
    public long countRescords() throws Exception {
        return getMapper().countByExample(null);
    }

    @Override
    public long countSearchRecords(List<List<SearchParam>> params) throws Exception {
        T2 example = getExampleByParams(params);
        return getMapper().countByExample(example);
    }


    /**
     * 获取列表
     *
     * @param withBlobs
     * @param example
     * @return
     */
    protected List<T1> getT1s(boolean withBlobs, T2 example) {
        List<T1> records;
        if (hasBlobs && withBlobs)
            records = getMapper().selectByExampleWithBLOBs(example);
        else
            records = getMapper().selectByExample(example);
        return records;
    }

    @Deprecated
    public boolean getHasBlobs() {
        return hasBlobs;
    }

    @Override
    public T2 getExample(List<List<SearchParam>> params) throws Exception {
        return getExampleByParams(params);
    }
}

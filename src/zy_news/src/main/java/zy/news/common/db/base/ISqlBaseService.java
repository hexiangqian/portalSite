package zy.news.common.db.base;


import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.db.SearchParam;

import java.util.List;

/**
 * 基础服务接口
 *
 * @param <T1>
 * @param <T2>
 * @author fanpei
 */
public interface ISqlBaseService<T1, T2> {

    /**
     * 添加多行记录
     *
     * @param records
     */
    void batchAdd(List<T1> records);

    /**
     * 添加一行记录
     *
     * @param record
     */
    void add(T1 record) throws Exception;

    /**
     * 名称是否唯一
     *
     * @param name
     * @return
     * @throws Exception
     */
    boolean containName(String name) throws Exception;

    /**
     * 标识是否唯一
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean containId(Long id) throws Exception;

    /**
     * 更新记录 根据记录id进行更新
     *
     * @param record
     */
    void update(T1 record);

    /**
     * 删除记录-批量删除
     *
     * @param ids 记录id数组
     * @throws Exception
     */
    void delete(List<Long> ids) throws Exception;

    /**
     * 通过id获取记录
     *
     * @param id 表id
     * @return
     */
    T1 getRecord(long id);

    /**
     * 多少个记录
     *
     * @return
     * @throws Exception
     */
    long countRescords() throws Exception;

    /**
     * 获取多行记录列表-带blobs
     *
     * @return
     */
    ValuesPage getRecords(Page page) throws Exception;

    /**
     * 获取多行记录列表
     *
     * @param page
     * @param withBlobs 是否带有blob对象
     * @return
     * @throws Exception
     */
    ValuesPage getRecords(Page page, boolean withBlobs) throws Exception;

    /**
     * 多少个记录
     *
     * @param params
     * @return
     * @throws Exception
     */
    long countSearchRecords(List<List<SearchParam>> params) throws Exception;

    /**
     * 搜索获取记录列表-带blobs
     *
     * @param page
     * @param params 搜索参数 key:数据库字段小写形式
     *               key-value形式，若key对应的值为范围，则以‘|’为分隔符，value的begin参数(起始参数)在左侧，分隔符为右侧为end参数(截止参数)。例：2018-10-11|2019-3-15
     * @return
     * @throws Exception
     */
    ValuesPage searchRecords(Page page, List<List<SearchParam>> params) throws Exception;

    /**
     * 搜索获取记录列表
     *
     * @param page
     * @param params
     * @param withBlobs 是否带有blob对象
     * @return
     * @throws Exception
     */
    ValuesPage searchRecordsWithParams(Page page, List<List<SearchParam>> params, boolean withBlobs) throws Exception;

    /**
     * 通过example搜索记录列表
     *
     * @param page
     * @param example
     * @param withBlobs
     * @return
     * @throws Exception
     */
    ValuesPage searchRecordsWithExample(Page page, T2 example, boolean withBlobs) throws Exception;

    /**
     * 获取mapper数据处理助手
     */
    IMapperBase<T1, T2> getMapper();

    /**
     * 组装example
     *
     * @param params
     * @return
     */
    T2 getExample(List<List<SearchParam>> params) throws Exception;

}

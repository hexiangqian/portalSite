package zy.news.common.db.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mapper基类
 *
 * @author fanpei
 * @param <T1> 数据对象
 * @param <T2> 数据Example对象
 */
public interface IMapperBase<T1, T2> {
    /**
     * 根据example查询条件计算记录总个数
     *
     * @param example
     * @return
     */
    long countByExample(T2 example);

    /**
     * 根据example条件删除记录并返回删除的个数
     *
     * @param example
     * @return
     */
    int deleteByExample(T2 example);

    /**
     * 通过id删除一个记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加一行记录
     *
     * @param record
     * @return
     */
    int insert(T1 record);

    /**
     * 批量添加多行记录
     *
     * @param records
     * @return
     */
    int batchInsert(@Param("records") List<T1> records);

    /**
     * 添加一行记录，只插入字段不为空的字段（选择性插入）
     *
     * @param record
     * @return
     */
    int insertSelective(T1 record);

    /**
     * 通过example查询记录列表【？待确定】
     *
     * @param example
     * @return
     */
    List<T1> selectByExampleWithBLOBs(T2 example);

    /**
     * 通过example查询记录列表
     *
     * @param example
     * @return
     */
    List<T1> selectByExample(T2 example);

    /**
     * 通过id查询记录
     *
     * @param id
     * @return
     */
    T1 selectByPrimaryKey(Long id);

    /**
     * 通过example选择性更新record
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") T1 record, @Param("example") T2 example);

    /**
     * 【？待确定】
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExampleWithBLOBs(@Param("record") T1 record, @Param("example") T2 example);

    /**
     * 通过example更新record
     *
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") T1 record, @Param("example") T2 example);

    int updateByPrimaryKeySelective(T1 record);

    int updateByPrimaryKeyWithBLOBs(T1 record);

    /**
     * 通过主键id更新记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(T1 record);

    void insertList(@Param("records") List<T1> records);
}

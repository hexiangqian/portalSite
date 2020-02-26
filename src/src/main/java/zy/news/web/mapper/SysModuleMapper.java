package zy.news.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.bean.SysModule;

@Repository
public interface SysModuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysModule record);

    SysModule selectByPrimaryKey(Long id);

    List<SysModule> selectAll();

    int updateByPrimaryKey(SysModule record);

    //append

    /**
     * 通过父模块获取可用子模块
     *
     * @param moduleid
     * @return
     */
    List<SysModule> getChildModules(@Param("moduleid") Long moduleid);

    /**
     * 通过指定用户模块id(子id集合)
     *
     * @param userid 用户id
     * @return
     */
    List<Long> getUserEnableModuleIds(@Param("userid") Long userid);

    /**
     * 通过id集合获取父id集合
     *
     * @param ids 子id集合
     * @return
     */
    List<Long> getParentModuleIdsBySonIds(@Param("psIds") List<Long> ids);

    /**
     * 筛选指定模块的子id
     *
     * @param moduleid
     * @param ids
     * @return
     */
    List<Long> getModuleIdsByParent(@Param("moduleid") Long moduleid, @Param("psIds") List<Long> ids);

    /**
     * 获取指定id集合的模块列表
     *
     * @param ids
     * @return
     */
    List<SysModule> getUserEnableModules(@Param("psIds") List<Long> ids);
}
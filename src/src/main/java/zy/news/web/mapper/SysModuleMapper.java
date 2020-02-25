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

    /**
     * 通过父模块获取可用子模块
     *
     * @param mNam 模块名称
     * @return
     */
    List<SysModule> getChildModules(@Param("mNam") String mNam);

    /**
     * 通过指定集合模块id获取一级模块信息
     *
     * @param ids 父集合
     * @return
     */
    List<SysModule> getRoleRootModules(@Param("roleName") String role, @Param("psIds") List<Long> ids);

    /**
     * 通过指定角色获取模块id(子id集合)
     *
     * @param role 角色名称
     * @return
     */
    List<Long> getPsModuleIdsByRole(@Param("roleName") String role);

    /**
     * 通过字父id集合获取父id集合
     *
     * @param ids 字父id集合
     * @return
     */
    List<Long> unionParentAndSon(@Param("psIds") List<Long> ids);

    /**
     * 获取指定角色指定模块指定父亲id集合的子模块列表
     *
     * @param role
     * @param mNam
     * @param ids
     * @return
     */
    List<SysModule> getRoleChildModules(@Param("roleName") String role, @Param("mNam") String mNam, @Param("psIds") List<Long> ids);
}
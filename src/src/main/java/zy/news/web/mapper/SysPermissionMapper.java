package zy.news.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import zy.news.web.bean.SysPermission;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);

    //append
    /**
     * 通过角色获取当前指定模块的权限列表
     *
     * @param roleName
     * @param mNam
     * @return
     */
    List<SysPermission> getPermissonsByRoleModule(@Param("roleName") String roleName, @Param("mNam") String mNam);

    /**
     * 通过角色id获取权限列表
     *
     * @param roleid
     * @return
     */
    List<SysPermission> getHasBindPermsByRoleid(@Param("roleid") Long roleid);

    /**
     * 获取指定角色未绑定的权限列表
     *
     * @param roleid
     * @return
     */
    @Deprecated
    List<SysPermission> getNoBindLists(@Param("roleid") Long roleid);

    /**
     * 模糊匹配获取Url列表
     *
     * @param fastSearch
     * @return
     */
    List<String> getUrlLists(@Param("fastSearch") String fastSearch);
}
package zy.news.web.mapper;

import org.apache.ibatis.annotations.Param;
import zy.news.web.bean.SysUser;

import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);

    /**
     * 绑定角色
     *
     * @param username
     * @param roleid
     */
    void bindUserRole(@Param("username") String username, @Param("roleid") Long roleid);

    /**
     * 更新密码
     *
     * @param passwd
     * @param id
     */
    void updatePasswd(@Param("passwd") String passwd, @Param("id") Long id);

    /**
     * 通过用户名和密码获取用户
     *
     * @param name
     * @param passwd
     * @return
     */
    SysUser selectByNamePasswd(@Param("name") String name, @Param("passwd") String passwd);

    /**
     * 通过名称获取用户
     *
     * @param name
     * @return
     */
    SysUser selectByName(@Param("name") String name);



}
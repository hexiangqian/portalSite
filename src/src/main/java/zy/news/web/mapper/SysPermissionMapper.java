package zy.news.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.bean.SysPermission;

@Repository
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPermission record);

    SysPermission selectByPrimaryKey(Long id);

    List<SysPermission> selectAll();

    int updateByPrimaryKey(SysPermission record);

    //apend

    /**
     * 判断指定用户是否包含url权限
     *
     * @param usrid
     * @param username
     * @param url
     * @return
     */
    int containPermissonByUrl(@Param("userid") Long usrid, @Param("username") String username, @Param("url") String url);

}
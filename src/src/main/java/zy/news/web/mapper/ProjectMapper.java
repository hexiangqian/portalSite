package zy.news.web.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zy.news.web.bean.Project;

public interface ProjectMapper {
    int deleteByPrimaryKey(@Param("contractnum") String contractnum, @Param("projectnum") String projectnum);

    int insert(Project record);

    Project selectByPrimaryKey(@Param("contractnum") String contractnum, @Param("projectnum") String projectnum);

    List<Project> selectAll();

    int updateByPrimaryKey(Project record);
}
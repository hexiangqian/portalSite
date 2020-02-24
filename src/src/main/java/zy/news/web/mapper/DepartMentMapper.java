package zy.news.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.bean.DepartMent;

@Repository
public interface DepartMentMapper {
    int deleteByPrimaryKey(@Param("dptid") Integer dptid, @Param("dptname") String dptname);

    int insert(DepartMent record);

    List<DepartMent> selectAll();
}
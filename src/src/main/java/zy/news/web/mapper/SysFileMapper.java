package zy.news.web.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.SysFile;

@Repository
public interface SysFileMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(SysFile record);

    SysFile selectByPrimaryKey(Long fid);

    List<SysFile> selectAll();

    int updateByPrimaryKey(SysFile record);
}
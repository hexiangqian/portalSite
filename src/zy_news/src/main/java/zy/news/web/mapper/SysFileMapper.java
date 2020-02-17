package zy.news.web.mapper;

import java.util.List;
import zy.news.web.bean.SysFile;

public interface SysFileMapper {
    int deleteByPrimaryKey(Long fid);

    int insert(SysFile record);

    SysFile selectByPrimaryKey(Long fid);

    List<SysFile> selectAll();

    int updateByPrimaryKey(SysFile record);
}
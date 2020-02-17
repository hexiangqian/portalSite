package zy.news.web.mapper;

import java.util.List;
import zy.news.web.bean.Honour;

public interface HonourMapper {
    int deleteByPrimaryKey(Long hid);

    int insert(Honour record);

    Honour selectByPrimaryKey(Long hid);

    List<Honour> selectAll();

    int updateByPrimaryKey(Honour record);
}
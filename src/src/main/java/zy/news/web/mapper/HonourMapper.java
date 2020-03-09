package zy.news.web.mapper;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.Honour;

import java.util.List;

@Repository
public interface HonourMapper {
    int deleteByPrimaryKey(Long hid);

    int insert(Honour record);

    Honour selectByPrimaryKey(Long hid);

    List<Honour> selectAll();

    int updateByPrimaryKey(Honour record);

    //APPEND

    int exist(Honour record);

    /**
     * 浏览量+1
     *
     * @param id
     * @return
     */
    int countViewByPrimaryKey(Long id);
}
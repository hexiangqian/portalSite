package zy.news.web.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.*;

@Repository
public interface OrgTrainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrgTrain record);

    OrgTrain selectByPrimaryKey(Long id);

    List<OrgTrain> selectAll();

    int updateByPrimaryKey(OrgTrain record);

    //APPEND

    int exist(OrgTrain record);
    /**
     * 获取简单的对象不带blob字段
     *
     * @param id
     * @return
     */
    News selectRecordWithOutBlobByPrimaryKey(Long id);

    /**
     * 获取content blob 字段
     *
     * @param id
     * @return
     */
    ContentBase selectContenBlobByPrimaryKey(Long id);

    /**
     * 浏览量+1
     *
     * @param id
     * @return
     */
    int countViewByPrimaryKey(Long id);
}
package zy.news.web.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.KnlgeShare;
import zy.news.web.bean.Notice;
import zy.news.web.bean.OrgTrain;

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
     * 浏览量+1
     *
     * @param id
     * @return
     */
    int countViewByPrimaryKey(Long id);
}
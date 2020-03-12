package zy.news.web.mapper;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.ContentBase;
import zy.news.web.bean.News;
import zy.news.web.bean.Quality;

import java.util.List;

@Repository
public interface QualityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Quality record);

    Quality selectByPrimaryKey(Long id);

    List<Quality> selectAll();

    int updateByPrimaryKey(Quality record);

    //APPEND

    int exist(Quality record);
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
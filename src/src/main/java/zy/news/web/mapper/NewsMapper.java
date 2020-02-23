package zy.news.web.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.News;
import zy.news.web.bean.NewsSimple;

@Repository
public interface NewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(News record);

    News selectByPrimaryKey(Long id);

    List<NewsSimple> selectAll();

    int updateByPrimaryKey(News record);

    //apend

    /**
     * 此新闻是否存在
     *
     * @param news
     * @return
     */
    int exist(News news);
}
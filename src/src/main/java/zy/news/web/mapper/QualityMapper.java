package zy.news.web.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.Quality;

@Repository
public interface QualityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Quality record);

    Quality selectByPrimaryKey(Long id);

    List<Quality> selectAll();

    int updateByPrimaryKey(Quality record);
}
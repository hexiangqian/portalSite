package zy.news.web.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.Quality;
import zy.news.web.bean.Regulations;

@Repository
public interface RegulationsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Regulations record);

    Regulations selectByPrimaryKey(Long id);

    List<Regulations> selectAll();

    int updateByPrimaryKey(Regulations record);

    //APPEND

    int exist(Regulations record);

    /**
     * 浏览量+1
     *
     * @param id
     * @return
     */
    int countViewByPrimaryKey(Long id);
}
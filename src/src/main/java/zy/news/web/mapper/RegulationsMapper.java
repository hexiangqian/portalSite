package zy.news.web.mapper;

import java.util.List;
import zy.news.web.bean.Regulations;

public interface RegulationsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Regulations record);

    Regulations selectByPrimaryKey(Long id);

    List<Regulations> selectAll();

    int updateByPrimaryKey(Regulations record);
}
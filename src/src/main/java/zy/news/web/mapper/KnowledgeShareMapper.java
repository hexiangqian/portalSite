package zy.news.web.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;
import zy.news.web.bean.KnowledgeShare;

@Repository
public interface KnowledgeShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KnowledgeShare record);

    KnowledgeShare selectByPrimaryKey(Long id);

    List<KnowledgeShare> selectAll();

    int updateByPrimaryKey(KnowledgeShare record);
}
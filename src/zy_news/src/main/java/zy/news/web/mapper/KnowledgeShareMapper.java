package zy.news.web.mapper;

import java.util.List;
import zy.news.web.bean.KnowledgeShare;

public interface KnowledgeShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KnowledgeShare record);

    KnowledgeShare selectByPrimaryKey(Long id);

    List<KnowledgeShare> selectAll();

    int updateByPrimaryKey(KnowledgeShare record);
}
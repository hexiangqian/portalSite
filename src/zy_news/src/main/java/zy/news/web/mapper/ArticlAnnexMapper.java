package zy.news.web.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zy.news.web.bean.ArticlAnnex;

public interface ArticlAnnexMapper {
    int deleteByPrimaryKey(@Param("articleid") Long articleid, @Param("fid") Long fid);

    int insert(ArticlAnnex record);

    List<ArticlAnnex> selectAll();
}
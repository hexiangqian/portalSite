package zy.news.web.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import zy.news.web.bean.ArticlAnnex;

public interface ArticlAnnexMapper {
    /**
     * @param articleid
     * @param fid
     * @return
     */
    int deleteByPrimaryKey(@Param("articleid") Long articleid, @Param("fid") Long fid);

    /**
     *
     * @param record
     * @return
     */
    int insert(ArticlAnnex record);

    /**
     *
     * @return
     */
    List<ArticlAnnex> selectAll();
}
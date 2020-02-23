package zy.news.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.bean.ArticlAnnex;

@Repository
public interface ArticlAnnexMapper {
    /**
     * @param articleid
     * @param fid
     * @return
     */
    int deleteByPrimaryKey(@Param("articleid") Long articleid, @Param("fid") Long fid);

    /**
     * @param record
     * @return
     */
    int insert(ArticlAnnex record);

    /**
     * @return
     */
    List<ArticlAnnex> selectAll();

    //append

    /**
     * 批量添加附件
     *
     * @param articleid
     * @param records
     */
    void addAnnexs(@Param("articleid") Long articleid, @Param("records") List<ArticlAnnex> records);

    /**
     * 删除指定文章附件
     *
     * @param articleid
     */
    void deleteByArtAnnexId(Long articleid);

    /**
     * 获取指定文章附件列表
     *
     * @param articleid
     * @return
     */
    List<ArticlAnnex> getAnnexRecords(Long articleid);
}
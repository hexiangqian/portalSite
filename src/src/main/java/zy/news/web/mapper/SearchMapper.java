package zy.news.web.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.ui.result.ArticleCategory;
import zy.news.web.ui.result.ArticleEntry;

import java.util.List;

/**
 * @author maoko
 * @date 2020/3/9 15:55
 */
@Repository
public interface SearchMapper {
    /**
     * 获取文章分类和结果统计
     *
     * @param fastsearch
     * @return
     */
    List<ArticleCategory> selectCategory(@Param("fastsearch") String fastsearch);

    /**
     * 搜索指定类型文章
     *
     * @param articletype
     * @param fastsearch
     * @return
     */
    List<ArticleEntry> selectArticles(@Param("articletype") Byte articletype, @Param("fastsearch") String fastsearch);

    /**
     * 检索所有表
     *
     * @param fastsearch
     * @return
     */
    List<ArticleEntry> selectAllArticles(@Param("fastsearch") String fastsearch);
}

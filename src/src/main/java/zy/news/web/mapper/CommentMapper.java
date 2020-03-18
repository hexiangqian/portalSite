package zy.news.web.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.bean.Comment;
import zy.news.web.bean.CommentSimple;
import zy.news.web.ui.result.ReviewInfo;

import java.util.List;

@Repository
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    Comment selectByPrimaryKey(Long id);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);


    //apend

    /**
     * 删除指定文章所有评论
     *
     * @param id
     */
    void deleteAllArticleComents(Long id);

    List<CommentSimple> selectAllComments(@Param("reviewStatus") Byte reviewStatus);

    List<CommentSimple> selectAllSimple(@Param("articletype") Byte articletype, @Param("articleid") Long articleid, @Param("reviewStatus") Byte reviewStatus);


    /**
     * 更新审核信息
     *
     * @param reviewInfo
     * @return
     */
    int updateReviewInfo(ReviewInfo reviewInfo);

    /**
     * 获取审核详情
     *
     * @param id
     * @return
     */
    ReviewInfo selectReviewInfoByPrimaryKey(Long id);
}
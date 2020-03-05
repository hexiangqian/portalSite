package zy.news.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.bean.KnlgeShare;
import zy.news.web.bean.KnlgeShareSimple;
import zy.news.web.ui.result.ReviewInfo;

@Repository
public interface KnowledgeShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KnlgeShare record);

    KnlgeShare selectByPrimaryKey(Long id);

    /**
     * @param author
     * @param reviewStatus
     * @param isGood       是否更具点赞度排序
     * @return
     */
    List<KnlgeShareSimple> selectAllShareSimple(@Param("author") String author, @Param("reviewStatus") Integer reviewStatus, @Param("isGood") Boolean isGood);

    int updateByPrimaryKey(KnlgeShare record);

    //apend

    /**
     * 此分享是否存在
     *
     * @param knlgeShare
     * @return
     */
    int exist(KnlgeShare knlgeShare);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    KnlgeShare selectDetailByPrimaryKey(Long id);

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

    /**
     * 点赞
     *
     * @param shareid 指定的分享id
     */
    void giveAlike(Long shareid);

    /**
     * 点差 指定的分享id
     *
     * @param shareid
     */
    void giveAbad(Long shareid);
}
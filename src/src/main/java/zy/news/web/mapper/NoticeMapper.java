package zy.news.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import zy.news.web.bean.News;
import zy.news.web.bean.NewsSimple;
import zy.news.web.bean.Notice;
import zy.news.web.bean.NoticeSimple;
import zy.news.web.ui.result.ReviewInfo;

@Repository
public interface NoticeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    Notice selectByPrimaryKey(Long id);

    List<NoticeSimple> selectAllNoticeSimple(@Param("reviewStatus") Integer reviewStatus);

    int updateByPrimaryKey(Notice record);

    //apend

    /**
     * 此通告是否存在
     *
     * @param notice
     * @return
     */
    int exist(Notice notice);

    /**
     * 获取详情
     *
     * @param id
     * @return
     */
    Notice selectDetailByPrimaryKey(Long id);

    /**
     * 浏览量+1
     *
     * @param id
     * @return
     */
    int countViewByPrimaryKey(Long id);

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
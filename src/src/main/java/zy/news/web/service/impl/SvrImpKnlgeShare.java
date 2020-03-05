package zy.news.web.service.impl;

import maoko.common.file.FileIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.news.common.Page;
import zy.news.common.exception.WarningException;
import zy.news.web.bean.*;
import zy.news.web.mapper.KnowledgeShareMapper;
import zy.news.web.service.IAnnex;
import zy.news.web.service.IFiles;
import zy.news.web.service.IKnlgeShare;
import zy.news.web.service.IUserCache;
import zy.news.web.ui.param.Opinion;
import zy.news.web.ui.param.PageIdParam;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.ui.result.ReviewInfo;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.zsys.utils.ServiceBase;
import zy.news.web.zsys.utils.ServiceUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 知识分享助手
 *
 * @author maoko
 * @date 2020/3/4 13:16
 */
@Service
public class SvrImpKnlgeShare extends ServiceBase implements IKnlgeShare {

    private final KnowledgeShareMapper mapper;
    private final IUserCache userCache;

    @Autowired
    public SvrImpKnlgeShare(KnowledgeShareMapper mapper, IUserCache userCache, IAnnex annexService, IFiles filesService) {
        super(annexService, filesService);
        this.mapper = mapper;
        this.userCache = userCache;
    }

    @Override
    public PageValuesResult<KnlgeShareSimple> getKnowledgeShares(HttpSession session, Page page, ReviewStatus reviewStatus, boolean isGood) throws Exception {
        PageValuesParam<KnlgeShareSimple> params = new PageValuesParam<>(mapper, "selectAllShareSimple");
        String author = "";
        if (null != session) {
            SysUser user = userCache.getUserFromSession(session);
            author = user.getUsername();
        }
        params.addParam(author);
        params.addParam(reviewStatus.getValue());
        params.addParam(isGood);
        return ServiceUtil.getValuePageResult(page, params);
    }

    @Override
    public boolean exist(KnlgeShare record) {
        return mapper.exist(record) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(HttpSession session, KnlgeShare record) throws Exception {
        record.validate();

        if (exist(record)) {
            throw new Exception("通告名称已存在，请修改后再试一试！");
        }
        SysUser user = userCache.getUserFromSession(session);
        //赋值
        record.setId(new Long(FileIDUtil.getNextIdLong()));
        record.setAuthor(user.getUsername());//这里与其他新闻/通告不同
        record.setPublishdate(new Date());
        record.convertContent2Blob();
        record.setReviewstatus((byte) 0);
        record.setBadnum(0);
        record.setGoodnum(0);

        //插入news
        mapper.insert(record);

        //插入附件
        addAnnexs(record.getId(), record.getAnnexes());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(HttpSession session, KnlgeShare record) throws Exception {
        //验证
        if (null == record.getId()) {
            throw new Exception("id为空！");
        }
        KnlgeShare tmpKnowledgeShare = mapper.selectByPrimaryKey(record.getId());
        if (null != tmpKnowledgeShare) {
            mapper.deleteByPrimaryKey(record.getId());
            List<ArticlAnnex> annexes = annexService.getAnnexs(record.getId());
            deleteAnnexs(record.getId());
        }
    }

    @Override
    public void update(HttpSession session, KnlgeShare record) throws Exception {
        //验证
        record.validate();
        if (null == record.getId()) {
            throw new Exception("更新操作id不能为空！");
        }
        KnlgeShare tmpRecord = mapper.selectByPrimaryKey(record.getId());
        if (tmpRecord == null) {
            throw new Exception(record.getId() + "已不存在！");
        }
        if (!record.getTitle().equals(tmpRecord.getTitle()) && exist(record)) {
            throw new Exception(record.getTitle() + "名称已存在，请修改后再试一试！");
        }
        SysUser user = userCache.getUserFromSession(session);

        //赋值
        record.setAuthor(user.getUsername());
        record.setPublishdate(new Date());
        record.convertContent2Blob();

        //更新
        mapper.updateByPrimaryKey(record);

        //更新附件
        updateAnnexs(record.getId(), record.getAnnexes());
    }

    @Override
    public KnlgeShare getRecordDetail(Long noticeid) throws Exception {
        KnlgeShare tmpRecord = mapper.selectDetailByPrimaryKey(noticeid);
        if (null == tmpRecord) {
            throw new WarningException(noticeid.toString() + "已不存在!");
        }
        List<ArticlAnnex> annexes = annexService.getAnnexs(noticeid);
        tmpRecord.setAnnexes(annexes);
        return tmpRecord;
    }

    @Override
    public void review(HttpSession session, ReviewInfo info) throws Exception {
        SysUser user = userCache.getUserFromSession(session);
        info.setReviewer(user.getRealname());
        info.setReviewdate(new Date());
        info.validate();
        mapper.updateReviewInfo(info);
    }

    @Override
    public ReviewInfo getReviewComment(Long id) throws Exception {
        ReviewInfo reviewInfo = mapper.selectReviewInfoByPrimaryKey(id);
        if (reviewInfo == null) {
            throw new Exception(id.toString() + "分享已不存在！");
        }
        return reviewInfo;
    }

    @Override
    public void giveOpinion(Opinion opinion) throws Exception {
        //验证
        if (opinion.getTargetid() == null) {
            throw new Exception("taegetid is null");
        }
        if (opinion.getType() == null || (opinion.getType() != 0 && opinion.getType() != 1)) {
            throw new Exception("type is null or value is out of [0,1]");
        }

        if (opinion.getType() == 0) {
            mapper.giveAlike(opinion.getTargetid());
        } else {
            mapper.giveAbad(opinion.getTargetid());
        }
    }

    @Override
    public PageValuesResult<Comment> getArticleComments(PageIdParam pageIdParam) throws Exception {
        PageValuesParam<Comment> params = new PageValuesParam<>(mapper, "selectAllShareSimple");
        params.addParam(pageIdParam.getId());
        return ServiceUtil.getValuePageResult(pageIdParam.getPage(), params);
    }
}

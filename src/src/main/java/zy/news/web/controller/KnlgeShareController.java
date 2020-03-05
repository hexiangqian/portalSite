package zy.news.web.controller;

import org.springframework.web.bind.annotation.*;
import zy.news.common.Page;
import zy.news.web.bean.KnlgeShareSimple;
import zy.news.web.bean.KnlgeShare;
import zy.news.web.service.IKnlgeShare;
import zy.news.web.ui.param.Opinion;
import zy.news.web.ui.param.ReviewStatus;
import zy.news.web.ui.result.ReviewInfo;
import zy.news.web.zsys.bean.*;

import javax.servlet.http.HttpSession;

/**
 * 知识分享
 *
 * @author maoko
 * @date 2020/3/4 13:11
 */
@RestController
@RequestMapping("/knowledgeShare")
@ExcuteControllerDsrc("分享管理")
public class KnlgeShareController {

    private final IKnlgeShare kngeShareService;

    public KnlgeShareController(IKnlgeShare kngeShareService) {
        this.kngeShareService = kngeShareService;
    }

    //region 分享发布-门户浏览端

    @GetMapping("existTitle")
    @ExcuteInterfaceDsrc("标题是否重复")
    @ExcutePermission
    public boolean existTitle(@RequestParam String title) throws Exception {
        KnlgeShare record = new KnlgeShare();
        record.setTitle(title);
        return kngeShareService.exist(record);
    }

    @PostMapping("addKnlgeShare")
    @ExcuteInterfaceDsrc("添加分享")
    @ExcutePermission
    public void addKnlgeShare(HttpSession session, @RequestBody KnlgeShare record) throws Exception {
        kngeShareService.add(session, record);
    }

    @PostMapping("updateKnlgeShare")
    @ExcuteInterfaceDsrc("更新分享")
    @ExcutePermission
    public void updateKnlgeShare(HttpSession session, @RequestBody KnlgeShare record) throws Exception {
        kngeShareService.update(session, record);
    }

    @PostMapping("deleteKnlgeShare")
    @ExcuteInterfaceDsrc("删除分享")
    @ExcutePermission
    public void deleteKnlgeShare(HttpSession session, @RequestBody KnlgeShare record) throws Exception {
        kngeShareService.delete(session, record);
    }

    @PostMapping("getCurrentUserShares")
    @ExcuteInterfaceDsrc("获取当前用户发布分享列表")
    @ExcutePermission
    public PageValuesResult<KnlgeShareSimple> getCurrentUserShares(HttpSession session, @RequestBody Page page) throws Exception {
        return kngeShareService.getKnowledgeShares(session, page, ReviewStatus.所有, false);
    }

    @PostMapping("getKnlgeShares")
    @ExcuteInterfaceDsrc("获取已通过审核分享列表")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public PageValuesResult<KnlgeShareSimple> getKnlgeShares(@RequestBody Page page) throws Exception {
        return kngeShareService.getKnowledgeShares(null, page, ReviewStatus.已审核, false);
    }

    @PostMapping("getGoodKnlgeShares")
    @ExcuteInterfaceDsrc("获取精华帖分享列表")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public PageValuesResult<KnlgeShareSimple> getGoodKnlgeShares(HttpSession session, @RequestBody Page page) throws Exception {
        return kngeShareService.getKnowledgeShares(null, page, ReviewStatus.已审核, true);
    }

    @GetMapping("getKnlgeShareDetail")
    @ExcuteInterfaceDsrc("获取分享详情")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public KnlgeShare getKnlgeShareDetail(@RequestParam Long shareid) throws Exception {
        return kngeShareService.getRecordDetail(shareid);
    }

    @PostMapping("giveOpinion")
    @ExcuteInterfaceDsrc("给分享点赞或者点差")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public void giveOpinion(@RequestBody Opinion opinion) throws Exception {
        kngeShareService.giveOpinion(opinion);
    }

    //endregion

    //region 分享审核-后台审核
    @PostMapping("getPublishShares")
    @ExcuteInterfaceDsrc("获取已发布分享列表")
    @ExcutePermission
    public PageValuesResult<KnlgeShareSimple> getPublishShares(@RequestBody Page page) throws Exception {
        return kngeShareService.getKnowledgeShares(null, page, ReviewStatus.所有, false);
    }

    @GetMapping("getReviewInfo")
    @ExcuteInterfaceDsrc("获取审核详情")
    @ExcutePermission
    public ReviewInfo getReviewInfo(HttpSession session, @RequestParam Long shareid) throws Exception {
        return kngeShareService.getReviewComment(shareid);
    }


    @PostMapping("reviewKnlgeShare")
    @ExcuteInterfaceDsrc("审核指定分享")
    @ExcutePermission
    public void reviewKnlgeShare(HttpSession session, @RequestBody ReviewInfo reviewInfo) throws Exception {
        kngeShareService.review(session, reviewInfo);
    }

    //endregion
}

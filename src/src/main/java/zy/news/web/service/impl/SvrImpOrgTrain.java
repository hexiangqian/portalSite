package zy.news.web.service.impl;

import maoko.common.file.FileIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.news.web.zsys.bean.Page;
import zy.news.common.exception.WarningException;
import zy.news.web.bean.ArticlAnnex;
import zy.news.web.bean.OrgTrain;
import zy.news.web.bean.SysUser;
import zy.news.web.mapper.OrgTrainMapper;
import zy.news.web.service.IAnnex;
import zy.news.web.service.IFiles;
import zy.news.web.service.IOrgTrain;
import zy.news.web.service.IUserCache;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.zsys.utils.ServiceBase;
import zy.news.web.zsys.utils.ServiceUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author maoko
 * @date 2020/3/7 9:52
 */
@Service
public class SvrImpOrgTrain extends ServiceBase implements IOrgTrain {
    private final OrgTrainMapper mapper;
    private final IUserCache userCache;

    @Autowired
    public SvrImpOrgTrain(OrgTrainMapper mapper, IUserCache userCache, IAnnex annexService, IFiles filesService) {
        super(annexService, filesService);
        this.mapper = mapper;
        this.userCache = userCache;
    }

    @Override
    public boolean exist(OrgTrain record) {
        return mapper.exist(record) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(HttpSession session, OrgTrain record) throws Exception {
        record.validate();

        if (exist(record)) {
            throw new Exception("名称已存在，请修改后再试一试！");
        }
        SysUser user = userCache.getUserFromSession(session);
        //赋值
        record.setId(new Long(FileIDUtil.getNextIdLong()));
        record.setAuthor(user.getUsername());
        record.setPublishdate(new Date());
        record.convertContent2Blob();
        record.setPageview(0L);

        //插入news
        mapper.insert(record);

        //插入附件
        addAnnexs(record.getId(), record.getAnnexes());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(HttpSession session, OrgTrain record) throws Exception {
        //验证
        if (null == record.getId()) {
            throw new Exception("id为空！");
        }
        OrgTrain tmpRecord = mapper.selectByPrimaryKey(record.getId());
        if (null != tmpRecord) {
            mapper.deleteByPrimaryKey(record.getId());
            deleteAnnexs(record.getId());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(HttpSession session, OrgTrain record) throws Exception {
        //验证
        record.validate();
        if (null == record.getId()) {
            throw new Exception("更新操作id不能为空！");
        }
        OrgTrain tmpRecord = mapper.selectByPrimaryKey(record.getId());
        if (tmpRecord == null) {
            throw new Exception(record.getId() + "已不存在！");
        }
        if (!record.getTitle().equals(tmpRecord.getTitle()) && exist(record)) {
            throw new Exception(record.getTitle() + "名称已存在，请修改后再试一试！");
        }
        SysUser user = userCache.getUserFromSession(session);

        //赋值
        record.setAuthor(user.getRealname());
        record.setPublishdate(new Date());
        record.convertContent2Blob();
        record.setPageview(0L);

        //更新
        mapper.updateByPrimaryKey(record);

        //更新附件
        updateAnnexs(record.getId(), record.getAnnexes());
    }

    @Override
    public OrgTrain getRecordDetail(Long id) throws Exception {
        OrgTrain train = mapper.selectByPrimaryKey(id);
        if (null == train) {
            throw new WarningException("已不存在!");
        }
        mapper.countViewByPrimaryKey(id);
        List<ArticlAnnex> annexes = annexService.getAnnexs(id);
        train.setAnnexes(annexes);
        return train;
    }

    @Override
    public PageValuesResult<OrgTrain> getOrgTains(Page page) throws Exception {
        PageValuesParam<OrgTrain> params = new PageValuesParam<>(mapper, "selectAll");
        return ServiceUtil.getValuePageResult(page, params);
    }
}

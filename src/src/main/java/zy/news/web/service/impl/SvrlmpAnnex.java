package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.news.web.bean.ArticlAnnex;
import zy.news.web.mapper.ArticlAnnexMapper;
import zy.news.web.service.IAnnex;

import java.util.List;

/**
 * 附件管理
 *
 * @author maoko
 * @date 2020/2/22 22:10
 */
@Service
public class SvrlmpAnnex implements IAnnex {
    private final ArticlAnnexMapper annexMapper;

    @Autowired
    public SvrlmpAnnex(ArticlAnnexMapper annexMapper) {
        this.annexMapper = annexMapper;
    }

    @Override
    public void adds(Long articleid, List<ArticlAnnex> records) throws Exception {
        if (null != records && !records.isEmpty()) {
            annexMapper.addAnnexs(articleid, records);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updates(Long articleid, List<ArticlAnnex> records) throws Exception {
        if (null == records || records.isEmpty()) {
            throw new Exception("附件列表为空，禁止添加！");
        }
        annexMapper.deleteByArtAnnexId(articleid);
        annexMapper.addAnnexs(articleid, records);
    }

    @Override
    public void delete(Long articleid) {
        annexMapper.deleteByArtAnnexId(articleid);
    }

    @Override
    public List<ArticlAnnex> getAnnexs(Long articleid) throws Exception {
        if (articleid <= 0) {
            throw new Exception("文章id为空！");
        }
        return annexMapper.getAnnexRecords(articleid);
    }
}

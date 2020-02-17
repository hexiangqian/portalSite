package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.db.base.DbExampleUtil;
import zy.news.web.bean.SysPermission;
import zy.news.web.mapper.SysPermissionMapper;
import zy.news.web.service.IAuthPermission;
import zy.news.web.zsys.bean.PageValuesParam;

import java.util.List;

@Service
public class SvrImpAuthPermission implements IAuthPermission {
    @Autowired
    private SysPermissionMapper mapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysPermission record) {
        return mapper.insert(record);
    }

    @Override
    public SysPermission selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysPermission> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SysPermission record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public ValuesPage getRecords(Page page) throws Exception {
        PageValuesParam<SysPermission> pVParam = new PageValuesParam<SysPermission>(mapper, "selectAll");
        return ServiceUtil.getValuePage(page, pVParam);
    }

    @Override
    public ValuesPage getUrlLists(Page page, String fastSearch) throws Exception {
        fastSearch = DbExampleUtil.getLikeValue(fastSearch);
        PageValuesParam<String> pVParam = pVParam = new PageValuesParam<String>(mapper, "getUrlLists", 1);
        if (fastSearch != null) {
            pVParam.addParam(0, fastSearch);
        } else {
            pVParam.addParam(0, "");
        }
        return ServiceUtil.getValuePage(page, pVParam);
    }

    @Override
    public ValuesPage selectAllPage(Page page) throws Exception {
        PageValuesParam pVParam = new PageValuesParam(mapper, "selectAll");
        return ServiceUtil.getValuePage(page, pVParam);
    }
}

package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.web.bean.SysPermission;
import zy.news.web.mapper.SysPermissionMapper;
import zy.news.web.service.IAuthPermission;
import zy.news.web.zsys.bean.PageValuesParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        PageValuesParam<SysPermission> pvparam = new PageValuesParam<SysPermission>(mapper, "selectAll");
        return ServiceUtil.getValuePage(page, pvparam);
    }

    @Override
    public ValuesPage getEnablePermsByRoleAndParent(Page page, String roleName, String mNam) throws Exception {
        PageValuesParam<SysPermission> pvparam = new PageValuesParam<SysPermission>(mapper, "getPermissonsByRoleModule");
        pvparam.addParam(roleName);
        pvparam.addParam(mNam);
        return ServiceUtil.getValuePage(page, pvparam);
    }

    @Override
    public ValuesPage getUrlLists(Page page, String fastSearch) throws Exception {
        PageValuesParam<SysPermission> pvparam = new PageValuesParam<SysPermission>(mapper, "getUrlLists");
        pvparam.addParam(fastSearch);
        return ServiceUtil.getValuePage(page, pvparam);
    }

    @Override
    public ValuesPage selectAllPage(Page page) throws Exception {
        PageValuesParam pvparam = new PageValuesParam(mapper, "selectAll");
        return ServiceUtil.getValuePage(page, pvparam);
    }
}

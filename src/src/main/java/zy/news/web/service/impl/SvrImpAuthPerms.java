package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.web.bean.SysModule;
import zy.news.web.bean.SysPermission;
import zy.news.web.mapper.SysPermissionMapper;
import zy.news.web.service.IAuthPermission;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.bean.PageValuesResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SvrImpAuthPerms implements IAuthPermission {
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
    public ValuesPage selectAllPage(Page page) throws Exception {
        PageValuesParam pvparam = new PageValuesParam(mapper, "selectAll");
        return ServiceUtil.getValuePage(page, pvparam);
    }

    @Override
    public PageValuesResult<SysModule> getRootModules(Page page) {
        return null;
    }

    @Override
    public PageValuesResult<SysModule> getChildModulesByParet(Page page, String mNam) {
        return null;
    }

    @Override
    public PageValuesResult<SysModule> getRootModulesByRole(String role) {
        return null;
    }

    @Override
    public PageValuesResult<SysModule> getChildModulesByParetRole(String role, String mNam) {
        return null;
    }
}

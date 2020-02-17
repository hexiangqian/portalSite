package zy.news.web.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.exception.WarningException;
import zy.news.web.bean.SysModule;
import zy.news.web.bean.SysRole;
import zy.news.web.bean.SysUser;
import zy.news.web.mapper.SysRoleMapper;
import zy.news.web.service.IAuthRole;
import zy.news.web.ui.param.RoleModulesBind;
import zy.news.web.ui.param.RolePermsBind;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.cache.UserCache;

import java.util.ArrayList;
import java.util.List;

@Service
public class SvrImpAuthRole implements IAuthRole {



    @Autowired
    private SysRoleMapper mapper;


    @Autowired
    private UserCache userCache;


    @Override
    public int deleteByPrimaryKey(Long id) throws WarningException {
        List<Long> ids = new ArrayList<>(1);
        ids.add(id);
        if (mapper.countByExampleRolePerms(ids) > 0)
            throw new WarningException("角色-权限中仍存在待删除角色信息，请先取消角色关联绑定，再选择此角色进行删除。");
        int count = mapper.deleteByPrimaryKey(id);
        return count;
    }

    @Override
    public int insert(SysRole record) throws WarningException {
        if (SysUser.ADMIN_ROLE.equals(record.getRole()) || SysUser.SYSROLE_DESCR.equals(record.getDescr()))
            throw new WarningException("此用户名或描述已被系统占用，竞争使用，请求改其他名称或描述！");
        long count = mapper.selectRoleByName(record.getRole());
        if (count > 0) {
            throw new WarningException("此角色名称已存在，请修改后再试!");
        }
        return mapper.insert(record);
    }

    @Override
    public SysRole selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysRole> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SysRole record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public ValuesPage specRoleEnableMoudles(String roleName, Page page) {
        List<SysModule> records = null;
        if (page == null)
            page = new Page();//不分页
        //大于0分页有效
        if (page.getCurrent() > 0) {
            // 配置分页
            PageHelper.startPage(page.getCurrent(), page.getSize());
            records = mapper.specRoleEnableMoudles(roleName);
            PageInfo<SysModule> pageInfo = new PageInfo<>(records);
            // 取分页信息
            page.setTotal(pageInfo.getTotal());
            page.setTotalPages(pageInfo.getPages());
        } else {
            records = mapper.specRoleEnableMoudles(roleName);
            page.setTotal(records != null ? records.size() : 0);
        }
        return new ValuesPage(records, page);
    }

    @Override
    public ValuesPage specRoleUnEnableRootMoudles(String roleName, Page page) {
        List<SysModule> records = null;
        if (page == null)
            page = new Page();//不分页
        //大于0分页有效
        if (page.getCurrent() > 0) {
            // 配置分页
            PageHelper.startPage(page.getCurrent(), page.getSize());
            records = mapper.specRoleUnEnableRootMoudles(roleName);
            PageInfo<SysModule> pageInfo = new PageInfo<>(records);
            // 取分页信息
            page.setTotal(pageInfo.getTotal());
            page.setTotalPages(pageInfo.getPages());
        } else {
            records = mapper.specRoleUnEnableRootMoudles(roleName);
            page.setTotal(records != null ? records.size() : 0);
        }
        return new ValuesPage(records, page);
    }

    @Override
    public ValuesPage specRoleUnEnableChildMoudles(String roleName, String mNam, Page page) {
        List<SysModule> records = null;
        if (page == null)
            page = new Page();//不分页
        //大于0分页有效
        if (page.getCurrent() > 0) {
            // 配置分页
            PageHelper.startPage(page.getCurrent(), page.getSize());
            records = mapper.specRoleUnEnableChildMoudles(roleName, mNam);
            PageInfo<SysModule> pageInfo = new PageInfo<>(records);
            // 取分页信息
            page.setTotal(pageInfo.getTotal());
            page.setTotalPages(pageInfo.getPages());
        } else {
            records = mapper.specRoleUnEnableChildMoudles(roleName, mNam);
            page.setTotal(records != null ? records.size() : 0);
        }
        return new ValuesPage(records, page);
    }

    @Override
    @Transactional
    public void bindSpecRoleMoudle(RoleModulesBind modulesBind) throws Exception {
        modulesBind.validate();
        List<Long> moudleids = modulesBind.getMoudleids();
        //mapper.delSpecRoleMoudle(modulesBind.getRoleid());
        for (Long mid : moudleids) {
            mapper.bindSpecRoleMoudle(modulesBind.getRoleid(), mid.longValue());
        }
    }

    @Override
    public void unBindSpecRoleMoudle(RoleModulesBind modulesBind) throws Exception {
        modulesBind.validate();
        mapper.unBindSpecRoleMoudle(modulesBind);
    }

    @Override
    public boolean checkRolePermsExist(RolePermsBind bindShip) {
        return mapper.checkRolePermsExist(bindShip) > 1;
    }

    @Override
    @Deprecated
    public void bindRolePerms(RolePermsBind bindShip) throws Exception {
        bindShip.validate();
        if (checkRolePermsExist(bindShip)) {
            throw new WarningException("部分权限[" + bindShip.getpermisdStr() + "]已存在，请检查！");
        }
        mapper.bindRolePerms(bindShip);
    }

    @Override
    @Deprecated
    public void unBindRolePerms(RolePermsBind bindShip) {
        mapper.unBindRolePerms(bindShip);
    }

    @Override
    public ValuesPage selectAllPage(Page page) throws Exception {
        PageValuesParam pVParam = new PageValuesParam(mapper, "selectAll");
        return ServiceUtil.getValuePage(page,pVParam);
    }
}

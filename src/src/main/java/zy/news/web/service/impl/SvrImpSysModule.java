package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.Page;
import zy.news.web.bean.SysModule;
import zy.news.web.bean.SysUser;
import zy.news.web.mapper.SysModuleMapper;
import zy.news.web.service.IModule;
import zy.news.web.zsys.bean.PageValuesResult;
import zy.news.web.zsys.cache.IUserCache;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 模块管理
 *
 * @author maoko
 * @date 2020/2/26 13:14
 */
@Service
public class SvrImpSysModule implements IModule {
    private final SysModuleMapper mapper;
    private final IUserCache userCache;

    @Autowired
    public SvrImpSysModule(SysModuleMapper mapper, IUserCache userCache) {
        this.mapper = mapper;
        this.userCache = userCache;
    }

    @Override
    public List<SysModule> getRootModules() {
        return getChildModules(null);
    }

    @Override
    public List<SysModule> getChildModules(Long moduleid) {
        return mapper.getChildModules(moduleid);
    }

    @Override
    public List<SysModule> getCurrentUserRootModules(HttpSession session) {
        SysUser user = userCache.getUserFromSession(session);
        List<Long> psIds = null;
        List<SysModule> modules = null;
        if (!SysUser.ADMIN_NAME.equals(user.getUsername())) {
            //获取权限关联的模块id[除数据地图外，全二级以下模块]
            psIds = mapper.getPsModuleIdsByUser(user.getId());
            //获取二级模块的一级模块
            if (!psIds.isEmpty()) {
                psIds = mapper.unionParentAndSon(psIds);
            }
            //获取一级模块列表
            if (!psIds.isEmpty()) {
                modules = mapper.getRoleRootModules(user.getId(), psIds);
            }
        } else {
            modules = getRootModules();
        }

        return modules;
    }

    @Override
    public List<SysModule> getCurrentUserChildModules(HttpSession session, Long moduleid) {
        SysUser user = userCache.getUserFromSession(session);
        List<Long> psIds = null;
        List<SysModule> modules = null;
        if (!SysUser.ADMIN_NAME.equals(user.getUsername())) {
            //获取权限关联的模块id[除数据地图外，全二级以下模块]
            psIds = mapper.getPsModuleIdsByUser(user.getId());
            //获取指定模块的二级模块列表
            if (!psIds.isEmpty()) {
                modules = mapper.getRoleChildModules(user.getId(), moduleid, psIds);
            }
        } else {
            modules = getChildModules(moduleid);
        }
        return modules;
    }
}

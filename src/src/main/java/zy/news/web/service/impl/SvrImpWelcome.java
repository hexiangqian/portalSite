package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.web.bean.SysUser;
import zy.news.web.mapper.SettingsMapper;
import zy.news.web.service.IUserCache;
import zy.news.web.service.IWelcome;
import zy.news.web.ui.param.RoleHas;
import zy.news.web.ui.result.ArticleCategory;
import zy.news.web.ui.result.ToDoItem;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author maoko
 * @date 2020/3/11 14:32
 */
@Service
public class SvrImpWelcome implements IWelcome {
    private final SettingsMapper mapper;
    private final IUserCache userCache;

    @Autowired
    public SvrImpWelcome(SettingsMapper mapper, IUserCache userCache) {
        this.mapper = mapper;
        this.userCache = userCache;
    }

    @Override
    public List<ArticleCategory> getSysOverview() {
        return mapper.getSysOverview();
    }

    @Override
    public List<ToDoItem> getToDolist(HttpSession session) {
        SysUser user = userCache.getUserFromSession(session);
        RoleHas roleHas = mapper.selectUserRoleList(user.getId());
        roleHas.setUsername(user.getUsername());
        return mapper.getToDolist(roleHas);
    }
}

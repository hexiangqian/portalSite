package zy.news.web.service;

import zy.news.web.bean.SysModule;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 模块管理
 *
 * @author maoko
 * @date 2020/2/26 13:13
 */
public interface IModule {
    /**
     * 获取一级模块
     *
     * @return
     */
    List<SysModule> getRootModules();

    /**
     * 通过父模块获取子模块
     *
     * @param moduleid 模块id
     * @return
     */
    List<SysModule> getChildModules(Long moduleid);

    /**
     * 获取当前用户一级模块
     *
     * @param session
     * @return
     */
    List<SysModule> getCurrentUserRootModules(HttpSession session);

    /**
     * 获取当前用户指定父模块获取子模块
     *
     * @param session
     * @param moduleid
     * @return
     */
    List<SysModule> getCurrentUserChildModules(HttpSession session, Long moduleid);
}

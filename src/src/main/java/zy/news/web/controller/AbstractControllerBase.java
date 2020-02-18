package zy.news.web.controller;

import maoko.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.db.SearchParam;
import zy.news.common.db.base.ISqlBaseService;
import zy.news.common.exception.LoginTimeOutException;
import zy.news.common.exception.WarningException;
import zy.news.web.zsys.bean.ExcuteMethodDsrc;
import zy.news.web.zsys.bean.ExcutePermission;
import zy.news.web.zsys.cache.UserCache;
import zy.news.web.zsys.gson.MyGsonUtil;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * controller基类
 *
 * @param <T1>
 * @author fanpei
 */
public abstract class AbstractControllerBase<T1, T2> {
    protected static final String AREADYEXIST_NAME = "this name:{} is aready Exists";
    @Autowired
    protected UserCache userCache;

    public abstract ISqlBaseService<T1, T2> getService();

    /**
     * 添加一个记录
     *
     * @param record record对象json字符串模式
     * @throws Exception
     */
    @PutMapping("add")
    @ExcuteMethodDsrc("添加记录")
    @ExcutePermission
    public abstract void add(HttpSession session, @RequestBody T1 record) throws Exception;

    protected void addRecord(T1 record) throws Exception {
        getService().add(record);
    }

    /**
     * 添加验证
     *
     * @throws Exception
     */
    protected void addCheck(T1 record) throws Exception {
        String name = (String) getReutrnValue(record, "getName");
        if (getService().containName(name)) {
            throw new WarningException(StringUtil.getMsgStr(AREADYEXIST_NAME, name));
        }
    }

    protected void addCheckId(Long id) throws Exception {
        if (null != id) {
            throw new WarningException("添加时，id为系统生成，默认为空！");
        }
    }

    /**
     * 更新
     *
     * @param record 对象的json字符串
     * @throws Exception
     */
    @PostMapping("update")
    @ExcuteMethodDsrc("更新记录")
    @ExcutePermission
    public abstract void update(HttpSession session, @RequestBody T1 record) throws Exception;

    protected void updateRecord(T1 record) throws Exception {
        // updateCheck(record);
        getService().update(record);
    }

    /**
     * 更新验证
     *
     * @throws Exception
     */
    protected void updateCheck(T1 record) throws Exception {

        Long id = (Long) getReutrnValue(record, "getId");
        T1 old = getService().getRecord(id);
        if (old != null) {
            String oldname = (String) getReutrnValue(old, "getName");
            String newname = (String) getReutrnValue(record, "getName");
            if (!oldname.equals(newname)) {
                if (getService().containName(newname)) {
                    throw new WarningException(StringUtil.getMsgStr(AREADYEXIST_NAME, newname));
                }
            }
        } else {
            throw new WarningException("old record is not found");
        }
    }

    /**
     * 删除
     *
     * @param ids 批量删除id,id以逗号分隔,例如1,2,3
     * @throws Exception
     */
    @DeleteMapping("delete")
    @ExcuteMethodDsrc("删除记录/批量")
    @ExcutePermission
    public void delete(@RequestParam("ids") String ids) throws Exception {
        if (!StringUtil.isStrNullOrWhiteSpace(ids)) {
            String[] idsArray = ids.split(",");
            if (idsArray.length > 0) {
                List<Long> idsLong = new ArrayList<>(idsArray.length);
                for (String id : idsArray) {
                    idsLong.add(Long.parseLong(id));
                }
                deletebyLongList(idsLong);
            }
        } else {
            throw new WarningException("need deleted ids is null or uncorect!");
        }
    }

    protected void deletebyLongList(List<Long> idsLong) throws Exception {
        getService().delete(idsLong);
    }

    /**
     * 通过id获取一条记录
     *
     * @param id
     * @return
     */
    @GetMapping("getByid")
    @ExcuteMethodDsrc("通过id获取一条记录")
    @ExcutePermission
    public T1 getRecord(long id) {
        return getService().getRecord(id);
    }

    /**
     * 获取记录列表
     *
     * @param current  当前页 从1开始,小于1时不分页
     * @param pageSize 分页大小
     * @return 返回具有分页信息的列表Json
     * @throws Exception
     */
    @GetMapping("lists")
    @ExcuteMethodDsrc("获取列表")
    @ExcutePermission
    public ValuesPage getRecords(HttpSession session, @RequestParam int current, @RequestParam int pageSize) throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        return getService().getRecords(page);
    }

    /**
     * 高级搜索
     *
     * @param current
     * @param pageSize
     * @param params
     * @return
     * @throws Exception
     */
    @PostMapping(value = "mapSearch")
    @ExcuteMethodDsrc("高级搜索")
    @ExcutePermission
    public ValuesPage searchRecordsMap(HttpSession session, @RequestParam("current") int current, @RequestParam("pageSize") int pageSize,
                                       @RequestBody String params) throws Exception {
        List<List<SearchParam>> paramsObj = MyGsonUtil.genArrayObjects(params, SearchParam.class);
        return searchRecordsbyParam(current, pageSize, paramsObj);
    }

    /**
     * 高级搜索
     *
     * @param current  当前页
     * @param pageSize 分页大小
     * @param params   List<List<MppSearchParam>>参数：外或内且
     *                 {第一层List中的所有所有元素为或的关系，即每一组List'<'MppSearchParam'>'都为
     *                 or关系，第二层List中的每一个SearchParam之间为且的关系，即and关系}
     * @return
     * @throws Exception
     */
    protected ValuesPage searchRecordsbyParam(int current, int pageSize, List<List<SearchParam>> params)
            throws Exception {
        Page page = new Page();
        page.setCurrent(current);
        page.setPageSize(pageSize);
        ValuesPage vPage = getService().searchRecords(page, params);
        return vPage;
    }

    /**
     * 获取对象返回值
     *
     * @param record     对象
     * @param methodName 函数名
     * @return 返回值
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws WarningException
     */
    protected Object getReutrnValue(T1 record, String methodName) throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException, WarningException {
        Method getName = record.getClass().getMethod(methodName);
        Object obj = getName.invoke(record);
        if (obj == null) {
            throw new WarningException(StringUtil.getMsgStr("执行对象方法{}错误，获参数值不能为空。", methodName));
        }
        return obj;
    }

/*    *//**
     * 从会话中获取用户信息
     *
     * @param session
     * @return
     * @throws LoginTimeOutException
     *//*
    public SysUser getUserFromSession(HttpSession session) throws LoginTimeOutException {
        return userCache.loginTimeOutCheck(session);
    }*/

}

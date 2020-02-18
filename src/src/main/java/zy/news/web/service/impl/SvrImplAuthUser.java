package zy.news.web.service.impl;

import maoko.common.agorithm.AesCipher;
import maoko.common.file.FileIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zy.news.common.Page;
import zy.news.common.ValuesPage;
import zy.news.common.exception.LoginitException;
import zy.news.common.exception.WarningException;
import zy.news.web.bean.SysUser;
import zy.news.web.mapper.SysUserMapper;
import zy.news.web.service.IAuthUser;
import zy.news.web.zsys.bean.PageValuesParam;
import zy.news.web.zsys.cache.IUserCache;

import java.util.List;

/**
 * @author fanpei
 */
@Service
public class SvrImplAuthUser implements IAuthUser {

    @Autowired
    private SysUserMapper mapper;

    @Autowired
    private IUserCache userCache;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SysUser record) throws WarningException {
        if (SysUser.ADMIN_ROLE.equals(record.getUsername())) {
            throw new WarningException("禁止操作管理员账户");
        }
        if (mapper.selectByName(record.getUsername()) != null) {
            throw new WarningException("此用户名已存在！");
        }
        return mapper.insert(record);
    }

    @Override
    public SysUser selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SysUser> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SysUser record) throws WarningException {
        if (SysUser.ADMIN_ROLE.equals(record.getUsername())) {
            throw new WarningException("禁止操作管理员账户");
        }
        if (null == record.getId()) {
            throw new WarningException("用户id为空，请重新填写参数！");
        }
        SysUser oldUser = selectByPrimaryKey(record.getId());
        if (oldUser==null) {
            throw new WarningException("用户已不存在！");
        }
        if (!oldUser.getUsername().equals(record.getUsername())) {
            if (mapper.selectByName(record.getUsername()) != null) {
                throw new WarningException("此用户名已存在！");
            }
        }
        return mapper.updateByPrimaryKey(record);
    }


    @Override
    public SysUser login(SysUser usr) throws Exception {
        usr.validate();
        if (null != (usr = selectUserByNamPasswd(usr))) {
        } else {
            throw new LoginitException("用户名或密码错误！");
        }
        usr.setToken(FileIDUtil.getNextId());
        // 加载模块
        userCache.addUser2Cache(usr);
        return usr;
    }

    @Override
    public SysUser selectUserByNamPasswd(SysUser user) {
        return mapper.selectByNamePasswd(user.getUsername(), user.getAesPassWd());
    }

    @Override
    public void updatePasswd(SysUser user, String passwd) throws WarningException {
        if (SysUser.ADMIN_ROLE.equals(user.getUsername())) {
            throw new WarningException("禁止操作管理员账户");
        }
        user.setPasswd(AesCipher.Encrypt(passwd));
        mapper.updatePasswd(user.getPasswd(), user.getId());
    }

    @Override
    public void bindUserRole(String username, Long roleid) throws WarningException {
        if (SysUser.ADMIN_ROLE.equals(username)) {
            throw new WarningException("禁止操作管理员账户");
        }
        mapper.bindUserRole(username, roleid);
    }

    @Override
    public ValuesPage selectAllPage(Page page) throws Exception {
        PageValuesParam pvparam = new PageValuesParam(mapper, "selectAll");
        return ServiceUtil.getValuePage(page, pvparam);
    }
}

package zy.news.web.bean;

import maoko.common.StringUtil;
import maoko.common.agorithm.AesCipher;
import zy.news.common.exception.WarningException;
import zy.news.web.zsys.bean.IValidate;
import zy.news.web.zsys.gson.MyExpose;

public class SysUser implements IValidate {
    public static final String ADMIN_ROLE = "admin";//mapper.xml文件中使用 与数据库统一
    public static final String ADMIN_NAME = "admin";//mapper.xml文件中使用 与数据库统一
    public static final String SYSROLE_DESCR = "超级管理员";

    public static final String SESSION_USER = "LOGINNAME";
    @Deprecated
    public static final String SESSION_USERMODEL = "LOGINUSER";
    public static final String SESSION_TOKEN = "LOGINTOKEN";

    private Long id;//id
    private String username;//登录名
    private String realname;
    @MyExpose(serialize = false)
    private String passwd;
    private Long roleid;//隶属于角色ID
    private String role;//隶属于角色
    private String roleName;//隶属于角色名称
    private String token;//登录验证码

    //region getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static String getSessionUser() {
        return SESSION_USER;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    //endregion

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", realname=").append(realname);
        sb.append(", passwd=").append(passwd);
        sb.append(", roleid=").append(roleid);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(username) || StringUtil.isStrNullOrWhiteSpace(passwd)) {
            throw new WarningException("用户名和密码不能为空！");
        }
    }

    public String getAesPassWd() {
        return AesCipher.Encrypt(passwd);
    }
}
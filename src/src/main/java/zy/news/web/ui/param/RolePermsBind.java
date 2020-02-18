package zy.news.web.ui.param;

import maoko.common.StringUtil;
import zy.news.common.exception.WarningException;
import zy.news.web.zsys.bean.IValidate;

import java.util.List;

/**
 * @author fanpei
 */
@Deprecated
public class RolePermsBind extends SysBodyIds implements IValidate {
    private long roleid; //角色id
    private String permsid;//权限id，逗号分隔
    private List<Long> permsids;

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    public String getPermsid() {
        return permsid;
    }

    public void setPermsid(String permsid) {
        this.permsid = permsid;
    }

    public List<Long> getPermsids() {
        return getIdArray(permsid);
    }

    public String getpermisdStr() {
        StringBuilder permsidsSb = new StringBuilder();
        permsids = getPermsids();
        if (null != permsids && !permsids.isEmpty()) {
            boolean first = true;
            for (Long id : permsids) {
                if (first) {
                    first = false;
                } else {
                    permsidsSb.append(",");
                }
                permsidsSb.append(id);
            }
        }
        return permsidsSb.toString();
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(permsid)
                || roleid <= 0) {
            throw new WarningException("权限和角色必填，不能为空，请检查参数！");
        }
    }
}

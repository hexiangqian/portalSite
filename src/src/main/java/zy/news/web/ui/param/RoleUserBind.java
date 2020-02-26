package zy.news.web.ui.param;

import lombok.Data;
import maoko.common.StringUtil;
import zy.news.common.exception.WarningException;
import zy.news.web.zsys.bean.IValidate;

import java.util.List;

/**
 * @author maoko
 * @date 2020/2/26 19:46
 */
public class RoleUserBind extends SysBodyIds implements IValidate {
    private long userid; //用户id
    private String roleid;//角色id，逗号分隔
    private List<Long> roleids;

    public long getUserid() {
        return userid;
    }


    public List<Long> getRoleids() {
        if (null == roleids) {
            roleids = getIdArray(roleid);
        }
        return roleids;
    }

    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(roleid)
                || userid <= 0) {
            throw new WarningException("用户角色必填，不能为空，请检查参数！");
        }
    }
}

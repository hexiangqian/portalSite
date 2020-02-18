package zy.news.web.ui.param;

import maoko.common.StringUtil;
import zy.news.common.exception.WarningException;
import zy.news.web.zsys.bean.IValidate;

import java.util.List;

/**
 * 权限绑定和解除实体
 *
 * @author fanpei
 */
public class RoleModulesBind extends SysBodyIds implements IValidate {
    private long roleid; //角色id
    private String moudleid;//权限id，逗号分隔
    private List<Long> moudleids;

    public long getRoleid() {
        return roleid;
    }

    public List<Long> getMoudleids() {
        if (null == moudleids) {
            moudleids = getIdArray(moudleid);
        }
        return moudleids;
    }


    @Override
    public void validate() throws Exception {
        if (StringUtil.isStrNullOrWhiteSpace(moudleid)
                || roleid <= 0) {
            throw new WarningException("权限和角色必填，不能为空，请检查参数！");
        }
    }
}

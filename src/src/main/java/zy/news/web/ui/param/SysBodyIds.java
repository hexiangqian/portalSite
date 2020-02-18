package zy.news.web.ui.param;

import maoko.common.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fanpei
 */
public class SysBodyIds {
    public static List<Long> getIdArray(String idsStr) {
        List<Long> idList = null;
        if (!StringUtil.isStrNullOrWhiteSpace(idsStr)) {
            if (!",".contains(idsStr)) {
                idList = Arrays.asList(new Long(Long.parseLong(idsStr)));
            } else {
                String[] ids = idsStr.split(",");
                idList = new ArrayList<>(ids.length);
                for (String id : ids) {
                    idList.add(new Long(Long.parseLong(id)));
                }
            }
        }
        return idList;
    }
}

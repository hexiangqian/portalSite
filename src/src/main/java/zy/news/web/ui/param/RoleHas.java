package zy.news.web.ui.param;

import lombok.Data;

/**
 * @author maoko
 * @date 2020/3/11 15:26
 */
@Data
public class RoleHas {
    private String username;
    private boolean newsRole;
    private boolean noticeRole;
    private boolean shareRole;
    private boolean comentRole;

    public void setNewsRole(long newsRole) {
        this.newsRole = newsRole > 0L;
    }

    public void setNoticeRole(long noticeRole) {
        this.noticeRole = noticeRole > 0L;
    }

    public void setShareRole(long shareRole) {
        this.shareRole = shareRole > 0L;
    }

    public void setComentRole(long comentRole) {
        this.comentRole = comentRole > 0L;
    }
}

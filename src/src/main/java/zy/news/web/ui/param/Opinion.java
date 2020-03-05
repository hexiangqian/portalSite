package zy.news.web.ui.param;

import lombok.Data;

/**
 * 看法
 *
 * @author maoko
 * @date 2020/3/5 10:38
 */
@Data
public class Opinion {
    /**
     * 0：点赞 1：点差
     */
    private Integer type;
    /**
     * 目标id
     */
    private Long targetid;
}

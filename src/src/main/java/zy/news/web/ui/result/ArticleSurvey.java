package zy.news.web.ui.result;

import lombok.Data;

/**
 * 文章普查类
 *
 * @author maoko
 * @date 2020/3/9 15:05
 */
@Data
public class ArticleSurvey {
    private String articleType;
    private String articleTypeStr;
    private String count;
}

package zy.news.web.ui.result;

import lombok.Data;
import zy.news.web.ui.param.ArticleType;
import zy.news.web.ui.param.ReviewStatus;

/**
 * 待办事项
 *
 * @author maoko
 * @date 2020/3/11 14:27
 */
@Data
public class ToDoItem extends ArticleCategory {
    private String status = "待处理";
}

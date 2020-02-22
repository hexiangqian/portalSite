package zy.news.web.service;

import org.springframework.transaction.annotation.Transactional;
import zy.news.web.bean.ArticlAnnex;

import java.util.List;

/**
 * 附件处理接口
 *
 * @author maoko
 * @date 2020/2/22 22:04
 */
public interface IAnnex {
    /**
     * 批量添加附件
     *
     * @param records
     * @throws Exception
     */
    void adds(List<ArticlAnnex> records) throws Exception;

    /**
     * 批量更新附件
     *
     * @param records
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    void updates(List<ArticlAnnex> records) throws Exception;

    /**
     * 获取指定文章的附件列表
     *
     * @param articleid 文章id
     * @return
     */
    List<ArticlAnnex> getAnnexs(Long articleid) throws Exception;
}

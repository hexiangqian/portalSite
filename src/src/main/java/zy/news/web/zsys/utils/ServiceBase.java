package zy.news.web.zsys.utils;

import zy.news.web.bean.ArticlAnnex;
import zy.news.web.bean.SysFile;
import zy.news.web.service.IAnnex;
import zy.news.web.service.IFiles;

import java.util.ArrayList;
import java.util.List;

/**
 * @author maoko
 * @date 2020/3/3 11:30
 */
public class ServiceBase {
    protected final IFiles filesService;
    protected final IAnnex annexService;

    public ServiceBase(IAnnex annexService, IFiles filesService) {
        this.filesService = filesService;
        this.annexService = annexService;
    }

    protected void addAnnexs(Long id, List<ArticlAnnex> annexes) throws Exception {
        annexService.adds(id, annexes);
    }

    protected void updateAnnexs(Long id, List<ArticlAnnex> annexes) throws Exception {
        if (annexes == null || annexes.isEmpty()) {
            deleteAnnexs(id);
        }
        annexService.updates(id, annexes);
    }

    /**
     * 批量删除附件
     *
     * @param id 待删除附件关联文章id
     */
    public void deleteAnnexs(Long id) throws Exception {
        List<ArticlAnnex> annexes = annexService.getAnnexs(id);
        annexService.delete(id);
        //批量删除附件
        if (!annexes.isEmpty()) {
            List<SysFile> files = new ArrayList<>(annexes.size());
            for (ArticlAnnex annex : annexes) {
                SysFile file = new SysFile();
                file.setFid(annex.getFid());
                files.add(file);
            }
            filesService.deleteFiles(files);
        }
    }
}

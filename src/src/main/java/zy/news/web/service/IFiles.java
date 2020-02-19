package zy.news.web.service;

import org.springframework.web.multipart.MultipartFile;
import zy.news.web.bean.SysFile;
import zy.news.web.ui.param.ModuleType;

/**
 * @author maoko
 * @date 2020/2/19 15:30
 */
public interface IFiles {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传好的文件信息
     * @throws Exception
     */
    SysFile uploadFile(MultipartFile file) throws Exception;

    /**
     * 下载文件
     *
     * @param file
     * @throws Exception
     */
    void downLoadFile(SysFile file) throws Exception;
}

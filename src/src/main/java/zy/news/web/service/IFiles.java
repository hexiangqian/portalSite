package zy.news.web.service;

import maoko.common.exception.DataIsNullException;
import org.springframework.web.multipart.MultipartFile;
import zy.news.web.bean.SysFile;
import zy.news.web.ui.param.ModuleType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author maoko
 * @date 2020/2/19 15:30
 */
public interface IFiles {
    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传后的文件信息
     * @throws Exception
     */
    SysFile uploadFile(MultipartFile file) throws Exception;

    /**
     * 下载文件
     *
     * @param resp http返回
     * @param file 待下载的文件 1.可通过文件id下载; 2.通过文件名称和路径组合进行下载
     * @throws Exception
     */
    void downLoadFile(HttpServletResponse resp, SysFile file) throws IOException, DataIsNullException;
}

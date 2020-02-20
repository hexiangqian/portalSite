package zy.news.web.service.impl;

import maoko.common.StringUtil;
import maoko.common.exception.DataIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zy.news.web.UploadFilePathConfig;
import zy.news.web.bean.SysFile;
import zy.news.web.mapper.SysFileMapper;
import zy.news.web.service.IFiles;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件管理
 *
 * @author maoko
 * @date 2020/2/19 16:08
 */
@Service
public class SvrImpFiles implements IFiles {

    @Autowired
    private UploadFilePathConfig filePathConfig;

    @Autowired
    private SysFileMapper fileMapper;

    @Override
    public SysFile uploadFile(MultipartFile file) throws Exception {
        SysFile sysFile = FileUtils.upload(file, filePathConfig.getUploadPath());
        fileMapper.insert(sysFile);
        return sysFile;
    }

    @Override
    public void downLoadFile(HttpServletResponse resp, SysFile file) throws IOException, DataIsNullException {

        //1.验证和查询文件信息
        if (!StringUtil.isStrNullOrWhiteSpace(file.getName())
                && !StringUtil.isStrNullOrWhiteSpace(file.getPath())) {
            //not to do
        } else if (file.getFid() != null) {//查询文件信息
            file = fileMapper.selectByPrimaryKey(file.getFid());
            if (null == file) {
                throw new FileNotFoundException("此id文件不存在！");
            }
        } else {
            throw new DataIsNullException("文件下载信息不能为空。1.可通过文件id下载; 2.通过文件名称和路径组合进行下载！");
        }

        //2.下载
        FileUtils.download(resp, file, filePathConfig.getUploadPath());
    }
}

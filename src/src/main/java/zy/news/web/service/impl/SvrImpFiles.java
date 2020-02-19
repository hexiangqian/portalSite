package zy.news.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zy.news.amain.CusWebConfig;
import zy.news.web.bean.SysFile;
import zy.news.web.mapper.SysFileMapper;
import zy.news.web.service.IFiles;
import zy.news.web.ui.param.ModuleType;

/**
 * 文件管理
 *
 * @author maoko
 * @date 2020/2/19 16:08
 */
@Service
public class SvrImpFiles implements IFiles {

    @Autowired
    private CusWebConfig webConfig;

    @Autowired
    private SysFileMapper fileMapper;

    @Override
    public SysFile uploadFile(MultipartFile file) throws Exception {
        SysFile sysFile = FileUtils.upload(file, webConfig.getUploadPath());
        fileMapper.insert(sysFile);
        return sysFile;
    }
}

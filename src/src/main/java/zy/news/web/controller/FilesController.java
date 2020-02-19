package zy.news.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import zy.news.web.bean.SysFile;
import zy.news.web.service.IFiles;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcutePermission;

/**
 * 文件管理
 *
 * @author maoko
 * @date 2020/2/19 15:30
 */
@RestController
@RequestMapping("/files")
@ExcuteControllerDsrc("文件管理")
public class FilesController {
    @Autowired
    private IFiles fileService;

    @RequestMapping("/upload")
    @ResponseBody
    @ExcutePermission
    public SysFile upload(@RequestParam(value = "myfile") MultipartFile file) throws Exception {
        return fileService.uploadFile(file);
    }
}

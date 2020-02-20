package zy.news.web.controller;

import maoko.common.exception.DataIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zy.news.web.bean.SysFile;
import zy.news.web.service.IFiles;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcutePermission;
import zy.news.web.zsys.bean.ExcuteUserType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @PostMapping("/upload")
    @ResponseBody
    @ExcutePermission
    public SysFile upload(@RequestParam(value = "myfile") MultipartFile file) throws Exception {
        return fileService.uploadFile(file);
    }

    @PostMapping("/download")
    @ExcutePermission(userType = ExcuteUserType.游客)
    public void downloadFile(HttpServletResponse resp, @RequestBody SysFile file) throws IOException, DataIsNullException {
        fileService.downLoadFile(resp, file);
    }
}

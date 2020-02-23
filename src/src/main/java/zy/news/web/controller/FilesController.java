package zy.news.web.controller;

import maoko.common.exception.DataIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zy.news.web.bean.SysFile;
import zy.news.web.service.IFiles;
import zy.news.web.zsys.bean.ExcuteControllerDsrc;
import zy.news.web.zsys.bean.ExcuteInterfaceDsrc;
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
    private final IFiles fileService;

    @Autowired
    public FilesController(IFiles fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    @ResponseBody
    @ExcutePermission
    @ExcuteInterfaceDsrc("上传文件")
    public SysFile upload(@RequestParam(value = "myfile") MultipartFile file) throws Exception {
        return fileService.uploadFile(file);
    }

    @PostMapping("/download")
    @ExcutePermission(userType = ExcuteUserType.游客)
    @ExcuteInterfaceDsrc("下载文件")
    public void downloadFile(HttpServletResponse resp, @RequestBody SysFile file) throws IOException, DataIsNullException {
        fileService.downLoadFile(resp, file);
    }

    @PostMapping("/delete")
    @ExcutePermission
    @ExcuteInterfaceDsrc("删除文件")
    public void deleteFile(@RequestBody SysFile file) throws IOException, DataIsNullException {
        fileService.deleteFile(file);
    }
}

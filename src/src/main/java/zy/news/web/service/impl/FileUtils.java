package zy.news.web.service.impl;

import maoko.common.StringUtil;
import maoko.common.file.FileIDUtil;
import maoko.common.file.PathUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import zy.news.NewsConfiguration;
import zy.news.web.bean.SysFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传工具
 *
 * @author maoko
 * @date 2020/2/19 16:35
 */
public class FileUtils {

    /**
     * 上传文件
     *
     * @param file     文件
     * @param savepath 文件存放路径
     * @return
     */
    public static SysFile upload(MultipartFile file, String savepath) throws IOException {
        String simplePath = getUniquePath(NewsConfiguration.STATICFLAG, file.getOriginalFilename());
        String realPath = PathUtil.combinePath(savepath, simplePath);
        File dest = new File(realPath);

        //判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        //保存文件
        file.transferTo(dest);

        //生成系统文件信息
        SysFile sysFile = new SysFile();
        sysFile.setFid(FileIDUtil.getNextIdLong());
        sysFile.setName(file.getOriginalFilename());
        sysFile.setPath(simplePath.replace("\\", "/").replace("//", "/"));
        sysFile.setUploadtime(new Date());
        sysFile.setType(isImage(sysFile.getName()) ? new Byte((byte) 1) : new Byte((byte) 0));
        return sysFile;
    }

    /**
     * 下载文件
     *
     * @param response http返回
     * @param file     待下载文件
     * @throws IOException
     */
    public static void download(HttpServletResponse response, SysFile file, String savepath) throws IOException {
        String fileRealPath = PathUtil.combinePath(savepath, file.getPath());
        File downFile = new File(fileRealPath);
        if (!downFile.exists()) {
            throw new FileNotFoundException("文件" + file.getPath() + "已不存在，请确认路径后下载");
        }

        response.reset();
        response.setContentType("application/x-msdownload;charset=utf-8");
        //组装header,解决中文乱码问题
        try {
            StringBuilder fHeaderSb = new StringBuilder();
            fHeaderSb.append("attachment;filename=")
                    .append(new String(file.getName().getBytes("gbk"), "iso-8859-1"));
            response.setHeader("Content-Disposition", fHeaderSb.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        InputStream din = new FileInputStream(downFile);
        ServletOutputStream scout = response.getOutputStream();
        try {
            //copy的使用
            //copy能拷贝Integer.MAX_VALUE的字节数据，即2^31-1。
            //如果是很大的数据，那么可以选择用copyLarge方法，适合拷贝较大的数据流，比如2G以上
            IOUtils.copy(din, scout);
        } catch (final IOException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(din, scout);
        }
    }

    /**
     * 获取以日期分割的目录结构
     *
     * @return
     */
    private static String gerCurrentDateDir() {
        StringBuilder dirSb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        dirSb.append(File.separatorChar);
        dirSb.append(cal.get(Calendar.YEAR));
        dirSb.append(File.separatorChar);
        dirSb.append(cal.get(Calendar.HOUR_OF_DAY));
        dirSb.append(File.separatorChar);
        dirSb.append(cal.get(Calendar.MONTH) + 1);
        return dirSb.toString();
    }

    /**
     * 获取唯一性文件路径
     *
     * @param staticpath static匹配前缀
     * @param fileName   文件名
     * @return
     */
    private static String getUniquePath(String staticpath, String fileName) {
        StringBuilder fileNameSb = new StringBuilder();
        fileNameSb.append(File.separatorChar);
        fileNameSb.append(staticpath);
        fileNameSb.append(gerCurrentDateDir());
        fileNameSb.append(File.separatorChar);
        //获取文件后缀
        int endflagIndex = fileName.lastIndexOf(".");
        String endFileFlag = fileName.substring(endflagIndex, fileName.length());
        String urlFileName = new StringBuilder(getUUID()).append(endFileFlag).toString();
        fileNameSb.append(urlFileName);
        return fileNameSb.toString();
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 判断文件是否是图片
     */
    private static boolean isImage(String fileName) {
        BufferedImage image = null;
        try {
            //如果是spring中MultipartFile类型，则代码如下
            image = ImageIO.read(new File(fileName));
            if (image == null || image.getWidth() <= 0 || image.getHeight() <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package zy.news.web.service.impl;

import maoko.common.StrConUtil;
import maoko.common.file.FileIDUtil;
import org.springframework.web.multipart.MultipartFile;
import sun.swing.plaf.synth.DefaultSynthStyle;
import zy.news.web.bean.SysFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
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
     * @param file     文件
     * @param savepath 文件存放路径
     * @return
     */
    public static SysFile upload(MultipartFile file, String savepath) throws IOException {

        String simplePath = getUniquePath(file.getOriginalFilename());
        StringBuilder fpathSb = new StringBuilder(savepath);
        fpathSb.append(File.separatorChar);
        fpathSb.append(simplePath);
        String realPath = fpathSb.toString();
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
        sysFile.setPath(simplePath);
        sysFile.setUploadtime(new Date());
        sysFile.setType(isImage(sysFile.getName()) ? new Byte((byte) 1) : new Byte((byte) 0));
        return sysFile;
    }

    /**
     * 获取以日期分割的目录结构
     *
     * @return
     */
    private static String gerCurrentDateDir() {
        StringBuilder dirSb = new StringBuilder();
        Calendar cal = Calendar.getInstance();
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
     * @param fileName 文件名
     * @return
     */
    private static String getUniquePath(String fileName) {
        StringBuilder fileNameSb = new StringBuilder();
        fileNameSb.append(File.separatorChar);
        fileNameSb.append(gerCurrentDateDir());
        fileNameSb.append(File.separatorChar);
        fileNameSb.append(getUUID());
        fileNameSb.append("_");
        fileNameSb.append(fileName);
        return fileNameSb.toString();
    }

    public static String getUUID() {
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
            e.printStackTrace();
            return false;
        }
    }
}

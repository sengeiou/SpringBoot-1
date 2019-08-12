package com.onejane.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * @Auther: codewj
 * @Date: 2019/8/8 10:08
 * @Description:
 */
public class ImageUtils {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String IMG_PATH_PREFIX = "static/upload/imgs";

    public static File getImgDirFile(){

        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("src/main/resources/" + IMG_PATH_PREFIX);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

    public static void convertImageFile(File file) throws IOException {
        FileImageInputStream fiis=new FileImageInputStream(file);
        FileImageOutputStream fios=new FileImageOutputStream(
                new File(file.getParent()+"/"+file.getName().substring(0,file.getName().lastIndexOf("."))+".bmp"));

        ImageReader jpegReader = null;
        Iterator<ImageReader> it1 = ImageIO.getImageReadersByFormatName("jpg");
        if(it1.hasNext())
        {
            jpegReader = it1.next();
        }
        jpegReader.setInput(fiis);

        ImageWriter bmpWriter = null;
        Iterator<ImageWriter> it2 = ImageIO.getImageWritersByFormatName("bmp");
        if(it2.hasNext())
        {
            bmpWriter = it2.next();
        }
        bmpWriter.setOutput(fios);
        BufferedImage br = jpegReader.read(0);
        bmpWriter.write(br);
        fiis.close();
        fios.close();
        System.out.println("Jpg到bmp图片转换完成.");
    }
}

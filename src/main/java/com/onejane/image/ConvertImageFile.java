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
 * @Date: 2019/7/29 12:09
 * @Description:
 */
public class ConvertImageFile {
    public static void main(String[] args) throws IOException {
        FileImageInputStream fiis=new FileImageInputStream(new File("F:/test1.jpg"));
        FileImageOutputStream fios=new FileImageOutputStream(new File("F:/test1.bmp"));

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
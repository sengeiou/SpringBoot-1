package com.onejane.apose.pdftopicture;

import com.onejane.apose.util.CompactAlgorithm;
import com.onejane.apose.util.MergeImage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf 转图片
 */

public class PdfUtil {

    public static final float DEFAULT_DPI = 105;
    public static final String DEFAULT_FORMAT = "jpg";

    /**
     * 将pdf转成一张图片
     *
     * @param pdffile
     * @return
     * @throws IOException
     */
    public static String getPDFBinary(String pdffile) throws IOException {
        //文件流
        InputStream inputStream = new FileInputStream(pdffile);
        //文件 获取文件名字
        File file = new File(pdffile);
        String name = file.getName();
        //截取不带后缀名的字段
        String fileName = name.substring(0, name.lastIndexOf("."));

        //文件上传路径
        String parent = file.getParent();

        PDDocument doc = PDDocument.load(inputStream);
        //获取pdf文件 页数
        PDFRenderer renderer = new PDFRenderer(doc);

        List<BufferedImage> imageList = new ArrayList<BufferedImage>();
        BufferedImage imageResult = null;
        int width = 0;
        int shiftHeight = 0;
        // 保存一张图片中的RGB数据
        int[] singleImgRGB;
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        for (int i = 0, len = doc.getNumberOfPages(); i < len; i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);

            imageList.add(image);
            //合并一张
            imageResult = MergeImage.mergeImage(false, imageList);
        }
        doc.close();
        // 写图片
        boolean jpg = ImageIO.write(imageResult, DEFAULT_FORMAT, new File(parent + "/" + fileName + "."+DEFAULT_FORMAT));

        if (jpg == false) {
            return "转换失败";
        }

        //关闭流
        inputStream.close();
        return "转换成功";


    }


    // 将pdf 转化为 图片分页 压缩包
    public static String getPDFBinarys(String pdffile) throws IOException {
        //文件流
        InputStream inputStream = new FileInputStream(pdffile);
        PDDocument doc = PDDocument.load(inputStream);

        //文件 获取文件名字
        File file = new File(pdffile);
        String name = file.getName();
        //截取不带后缀名的字段
        String fileName = name.substring(0, name.lastIndexOf("."));
        //文件上传路径
        String parent = file.getParent();


        //创建同名文件夹
        new File(parent + "/" + fileName).mkdir();
        //获取pdf文件 页数
        PDFRenderer renderer = new PDFRenderer(doc);
        BufferedImage imageResult = null;
        int width = 0;
        int shiftHeight = 0;
        // 保存一张图片中的RGB数据
        int[] singleImgRGB;
        for (int i = 0, len = doc.getNumberOfPages(); i < len; i++) {
            BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI, ImageType.RGB);

            ImageIO.write(image, DEFAULT_FORMAT, new File(parent + "/" + fileName + "/" + "第" + i + "页" + fileName + "."+DEFAULT_FORMAT));
        }


        //压缩同名文件夹
        File f = new File(parent + "/" + fileName);
        new CompactAlgorithm(new File(parent + "/", f.getName() + ".zip")).zipFiles(f);

        //关闭流
        inputStream.close();


        return "转换成功" + doc.getNumberOfPages() + "页";

    }

    public static void main(String[] args) throws IOException {
//        getPDFBinarys("E:\\2019 Java好客租房\\day09-分布式消息系统RocketMQ的入门\\讲义\\讲义.pdf");
        getPDFBinary("E:\\2019 Java好客租房\\day09-分布式消息系统RocketMQ的入门\\讲义\\讲义.pdf");
    }


}

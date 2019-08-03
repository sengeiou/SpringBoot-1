package com.onejane.image;

import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * @Auther: codewj
 * @Date: 2019/8/2 14:40
 * @Description:
 */
public class Image2Binary {
    public static void main(String[] args) {
        //把图片用二进制字符串输出
        System.out.println(getImageBinary());
        //二进制字符串转成图片
        base64StringToImage(getImageBinary());
    }
    static String getImageBinary() {
        File f = new File("C://templates//1474338877870.jpg");

        BufferedImage bi;
        try {
            bi = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();

            return Base64.encodeBase64String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    static void base64StringToImage(String base64String) {
        try {
            byte[] bytes1 = Base64.decodeBase64(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("C://templates//QQ.bmp");// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

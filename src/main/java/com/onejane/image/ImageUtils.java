package com.onejane.image;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
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

    /**
     * 十六进制字符串转字节数组
     *
     * @param s
     * @return byte[]
     */
    public static byte[] fromHexString(String s) {
        int length = s.length() / 2;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) ((Character.digit(s.charAt(i * 2), 16) << 4) | Character.digit(s.charAt((i * 2) + 1), 16));
        }
        return bytes;
    }

    /**
     * 字节数组转十六进制字符串
     *
     * @param fieldData
     * @return String
     */
    public static String toHexString(byte[] fieldData) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldData.length; i++) {
            int v = (fieldData[i] & 0xFF);
            if (v <= 0xF) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    /**
     * 字节数组图片数据转存
     *
     * @param data
     * @param path
     *            void
     * @throws IOException
     */
    public static void byte2image(byte[] data, String path) throws IOException {
        if (data.length < 3 || path.equals("")) {
            return;
        }
        FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
        imageOutput.write(data, 0, data.length);
        imageOutput.close();
    }

    public static byte[] baseHexString(String s) {
        // 对字节数组字符串进行Base64解码并生成图片
        if (s == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        // Base64解码
        byte[] bytes = null;
        try {
            bytes = decoder.decodeBuffer(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    public static String getFileContentFromNetByUrl(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[10240];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            outStream.flush();
            byte[] buf = outStream.toByteArray();
            outStream.close();
            inStream.close();
            BASE64Encoder encoder = new BASE64Encoder();
            String str = encoder.encode(buf);
            str = str.replaceAll("[\\s*\t\n\r]", "");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getFileContentFromNetByUrl(String fileUrl,String fileName,Byte type) {
        File file = new File(fileName);//文件路径（路径+文件名）
        try {
            if (!file.exists()) {   //文件不存在则创建文件，先创建目录
                File dir = new File(file.getParent());
                dir.mkdirs();
                file.createNewFile();
                URL url = new URL(fileUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5 * 1000);
                InputStream inStream = conn.getInputStream();// 通过输入流获取图片数据
                FileOutputStream outStream = new FileOutputStream(file);
                byte[] buffer = new byte[10240];
                int len = 0;
                while ((len = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                outStream.flush();
                outStream.close();
                inStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(type == 0){
            return file.getPath();
        }else{
            return file.getParent();
        }

    }

    public static void main(String[] args) {
        try {
            String originImagePath = "companydemo.jpg";
            String updateImagePath = "companydemonew.jpg";
            FileInputStream fis = new FileInputStream(originImagePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = bis.read(buff)) != -1) {
                bos.write(buff, 0, len);
            }
            // 得到图片的字节数组
            byte[] result = bos.toByteArray();
            // 把字节数组转为十六进制字符串(方便持久化)
            String hexStringb = toHexString(result);
            System.out.println("转换后的结果：" + hexStringb);

            // 下面开始反转
            // 十六进制字符串转存字节数组
            byte[] newb = fromHexString(hexStringb);
            // 字节数组图片数据进行持久化
            byte2image(newb, updateImagePath);
            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

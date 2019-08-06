package com.onejane.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PanQiSaas {
    public static void main(String[] args) throws IOException {
        List<byte[]> pixelData = new ArrayList<>();
        final String reddata = "0111";
        final String blackdata = "0011";
        final String whitedata = "0000";
        File file = new File("F:\\1.bmp");


        BufferedImage bufImg = ImageIO.read(file);
        int height = bufImg.getHeight();
        int width = bufImg.getWidth();
        StringBuilder sb = new StringBuilder();
        for (int j = height - 1; j >= 0; j--) {
//            for (int i = 0; i < width; i++) {
            for (int i = width - 1; i >= 0; i-=2) {
//            for (int j = 0; j < height; j += 2) {
                for (int item = 0; item < 2; item++) {
                    String data = "";
                    int temp = (bufImg.getRGB(i - item, j ) & 0xff00) >> 8;
//                    int temp = (bufImg.getRGB(i, j + item) & 0xff00) >> 8;
                    if (temp <= 20) {
                        data = blackdata;
                    } else if (temp >= 235) {
                        data = whitedata;
                    } else {
                        data = reddata;
                    }
                    sb.append(data);
                }

            }
        }

        String dataString = sb.toString();
        for (int i = 0; i < dataString.length() / 8; i++) {
            byte[] bytes = dataString.substring(i * 8, i * 8 + 8).getBytes();
            pixelData.add(bytes);
        }
        System.out.println(pixelData);

    }


}
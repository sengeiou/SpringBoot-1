package com.onejane.image;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class OneTable2Image {
    BufferedImage image;
    void createImage(String fileLocation) {
        try {
            FileOutputStream fos = new FileOutputStream(fileLocation);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void graphicsGeneration() {
        //实际数据行数+标题+备注
        int totalrow = 9;
        int totalcol = 16;
        int imageWidth = 1024;
        int imageHeight = totalrow*40+20;
        int rowheight = 40;
        int startHeight = 10;
        int startWidth = 10;
        int colwidth = (int)((imageWidth-20)/totalcol);

        image = new BufferedImage(imageWidth, imageHeight,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, imageWidth, imageHeight);
        graphics.setColor(new Color(220,240,240));

        //画横线
        for(int j=0;j<totalrow-1;j++){
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth, startHeight+(j+1)*rowheight, imageWidth-5, startHeight+(j+1)*rowheight);
        }
        //末行
        graphics.setColor(Color.black);
        graphics.drawLine(startWidth, imageHeight-90, imageWidth-5, imageHeight-90);


        //画竖线
        for(int k=0;k<totalcol;k++){
            graphics.setColor(Color.black);
            graphics.drawLine(startWidth+k*colwidth, startHeight+rowheight, startWidth+k*colwidth, imageHeight-50);
        }
        //末列
        graphics.setColor(Color.black);
        graphics.drawLine(imageWidth-5, startHeight+rowheight,imageWidth-5, imageHeight-50);

        //设置字体
        Font font = new Font("华文楷体",Font.BOLD,20);
        graphics.setFont(font);

        //写标题
        String title = "标题写在这里";
        graphics.drawString(title, imageWidth/3+startWidth, startHeight+rowheight-10);

        font = new Font("华文楷体",Font.BOLD,18);
        graphics.setFont(font);

        //写入表头
        String[] headCells = {"编号","名称","年龄","性别","体重"};
        for(int m=0;m<headCells.length;m++){
            graphics.drawString(headCells[m].toString(), startWidth+colwidth*m+5, startHeight+rowheight*2-10);
        }

        //设置字体
        font = new Font("华文楷体",Font.PLAIN,16);
        graphics.setFont(font);
        String[][] cellsValue = {{"101","xiaozhang","13","M","55"},
                {"102","xiaowang","14","F","53"},
                {"103","xiaoli","15","M","58"}};
        //写入内容
        for(int n=0;n<cellsValue.length;n++){
            String[] arr = cellsValue[n];
            for(int l=0;l<arr.length;l++){
                graphics.drawString(cellsValue[n][l].toString(), startWidth+colwidth*l+5, startHeight+rowheight*(n+3)-10);
            }
        }

        font = new Font("华文楷体",Font.BOLD,18);
        graphics.setFont(font);
        graphics.setColor(Color.RED);

        //写备注
        String remark = "备注：备注写在这里。";
        graphics.drawString(remark, startWidth, imageHeight-30);

        createImage("F:\\1.bmp");
    }

    // 合并纵向有相同字段的单元格
    public static void mergeColumnTitleCell( Graphics graphics,int cellWidth,int cellHeight,String[][] dataArray) {

        int titleColCnt = 16;
        int titleFontSize = 14;
        for( int y=0; y< titleColCnt ; y++  ){

            int mergeBegin=0;
            int mergeEnd=0;
            for (int x = 1; x < dataArray.length; x++) {
                String prevCellVal = dataArray[x-1][y];
                String nowCellVal = dataArray[x][y];
                if(prevCellVal.equals(nowCellVal)){
                    mergeEnd++;
                    if( mergeEnd == dataArray.length-1 ){
                        graphics.setColor(Color.WHITE);
                        graphics.fillRect( y*cellWidth+1 , mergeBegin*cellHeight+1, cellWidth-1 , (mergeEnd-mergeBegin+1)*cellHeight-1);
                        graphics.setColor(new Color(220,240,240));
                        graphics.setFont(new Font("华文楷体",Font.PLAIN,16));
                        graphics.drawString(prevCellVal, y*cellWidth+(cellWidth-prevCellVal.length()*14)/2+10  , cellHeight*mergeBegin+ ((mergeEnd-mergeBegin+1)*cellHeight+titleFontSize)/2);
                        mergeBegin=x;
                        mergeEnd=x;
                    }
                }else if( mergeBegin == mergeEnd ){
                    mergeBegin=x;
                    mergeEnd=x;
                }else {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect( y*cellWidth+1 , mergeBegin*cellHeight+1, cellWidth-1 , (mergeEnd-mergeBegin+1)*cellHeight-1);
                    graphics.setColor(new Color(220,240,240));
                    graphics.setFont(new Font("华文楷体",Font.PLAIN,16));
                    graphics.drawString(prevCellVal, y*cellWidth+(cellWidth-prevCellVal.length()*14)/2+10  , cellHeight*mergeBegin+ ((mergeEnd-mergeBegin+1)*cellHeight+titleFontSize)/2);
                    mergeBegin=x;
                    mergeEnd=x;
                }
            }
        }

    }

    //  合并横向有相同字段的单元格
    public static void mergeRowTitleCell( Graphics graphics,int cellWidth,int cellHeight,String[][] dataArray) {

        int titleRowCnt = 16;
        int titleFontSize = 14;
        for( int i=0; i< titleRowCnt ; i++  ){
            String[] values = dataArray[i];
            int mergeBegin=0;
            int mergeEnd=0;
            for (int j = 1; j < values.length; j++) {
                String prevCellVal = values[j-1].toString();
                String nowCellVal = values[j].toString();
                if(prevCellVal.equals(nowCellVal)){
                    mergeEnd++;
                    if( mergeEnd == values.length-1){
                        graphics.setColor(Color.WHITE);
                        graphics.fillRect( mergeBegin*cellWidth+1 , i*cellHeight+1, (mergeEnd-mergeBegin+1)*cellWidth-1 , cellHeight-1);
                        graphics.setColor(new Color(220,240,240));
                        graphics.setFont(new Font("华文楷体",Font.PLAIN,16));
                        graphics.drawString(prevCellVal, mergeBegin*cellWidth+((mergeEnd-mergeBegin+1)*cellWidth-prevCellVal.length()*14)/2  , cellHeight*i+ (titleFontSize+cellHeight)/2);
                        mergeBegin=j;
                        mergeEnd=j;
                    }
                }else if( mergeBegin == mergeEnd ){
                    mergeBegin=j;
                    mergeEnd=j;
                }else {
                    graphics.setColor(Color.WHITE);
                    graphics.fillRect( mergeBegin*cellWidth+1 , i*cellHeight+1, (mergeEnd-mergeBegin+1)*cellWidth-1 , cellHeight-1);
                    graphics.setColor(new Color(220,240,240));
                    graphics.setFont(new Font("华文楷体",Font.PLAIN,16));
                    graphics.drawString(prevCellVal, mergeBegin*cellWidth+((mergeEnd-mergeBegin+1)*cellWidth-prevCellVal.length()*14)/2  , cellHeight*i+ (titleFontSize+cellHeight)/2);
                    mergeBegin=j;
                    mergeEnd=j;
                }
            }
        }

    }


    public static void main(String[] args) {
        OneTable2Image cg = new OneTable2Image();
        try {
            cg.graphicsGeneration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
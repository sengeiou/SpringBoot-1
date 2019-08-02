package com.onejane.image.mergetable;

import java.io.File;

/**
 * @Auther: codewj
 * @Date: 2019/7/29 16:49
 * @Description:
 */
public class GoodsLabel {
    public static void main(String[] args) {

        String[][] dataArray = new String[][]{
                { "物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌"},
                { "库"     , 	"1" ,  	"1"    ,	 "1",  	"架"  , 		"1" , 	"1"   ,     "1" ,     "层", 		"1", 	   "1",     "1",      "位", 	    "1", 	  "1", 	 "1"   },
                { "微机编号","微机编号","微机编号","微机编号",    "123",    "123",   "123" ,   "123" ,   "123" ,   "123",   "123"  ,   "123",   "123"  ,   "123",   "123" ,   "123"},
                { "物资名称","物资名称","物资名称","物资名称",    "盗贼",   "盗贼",    "盗贼",   "盗贼",    "盗贼",   "盗贼",    "盗贼",   "盗贼",    "盗贼",   "盗贼",    "盗贼",   "盗贼"},
                { "规格型号","规格型号","规格型号","规格型号",     "T1",     "T1",     "T1",     "T1",     "T1",     "T1",     "T1",    "T1",      "T1",    "T1",      "T1",   "T1"},
                { "图号材质","图号材质","图号材质","图号材质",  "塑料" ,   "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料"},
                { "计量单位","计量单位","计量单位","计量单位",     "斤",     "斤",      "斤",     "斤","计划单位","计划单位","计划单位","计划单位",   "元"   ,   "元"  ,   "元"  ,   "元"},
                { "储备定额","储备定额","储备定额","储备定额",   "最高",    "最高",	"100"  ,  "100" ,  	"100" ,  "100"  , "最低"   ,  "最低" , "10"    ,    "10" ,    "10" ,   "10"},
                { "备注"   ,   "备注" ,   "备注",    "备注","棒棒哒" , "棒棒哒" ,  "棒棒哒" ,"棒棒哒" ,"棒棒哒" ,  "棒棒哒" ,"棒棒哒" , "棒棒哒" , "棒棒哒" , "棒棒哒" , "棒棒哒" ,  "棒棒哒"}
        };
        //生成图片
        File file = new File("F:/test.png");
        try {

            //默认报表图片合并单元格对象
            ReportImageMergeCell reportImageMergeCell = new ReportImageMergeCellDefaultImp();
            //默认报表图片单元格动态颜色对象
            ReportImageDynamicColor reportImageDynamicColor = new ReportImageDynamicColorDefaultImp();
            //默认报表图片配置对象
            ReportImageConfig config = new ReportImageConfig(reportImageMergeCell,reportImageDynamicColor);
            //设置图片宽度
            config.setImgWidth(dataArray[0].length*80);
            //设置图片高度
            config.setImgHeight(dataArray.length*20);
            //设置标题行数
            config.setTitleRowCnt(9);


            //生成报表图片
            ReportImageCreator.createReportImage(config , dataArray ,file);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

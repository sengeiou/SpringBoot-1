package com.onejane.image.mergetable;

import com.onejane.image.ImageUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestMain {

	public static void main(String[] args) throws IOException {

		String[][] dataArray = new String[][]{
				{ "物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌","物资标牌"},
				{ "库"     , 	"1" ,  	"1"    ,	 "1",  	"架"  , 		"1" , 	"1"   ,     "1" ,     "层", 		"1", 	   "1",     "1",      "位", 	    "1", 	  "1", 	 "1"   },
				{ "微机编号","微机编号","微机编号","微机编号",    "123",    "123",   "123" ,   "123" ,   "123" ,   "123",   "123"  ,   "123",   "123"  ,   "123",   "123" ,   "123"},
				{ "物资名称","物资名称","物资名称","物资名称",    "猪头",   "猪头",    "猪头",   "猪头",    "猪头",   "猪头",    "猪头",   "猪头",    "猪头",   "猪头",    "猪头",   "猪头"},
				{ "规格型号","规格型号","规格型号","规格型号",     "T1",     "T1",     "T1",     "T1",     "T1",     "T1",     "T1",    "T1",      "T1",    "T1",      "T1",   "T1"},
				{ "图号材质","图号材质","图号材质","图号材质",  "塑料" ,   "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料" ,   "塑料" ,  "塑料"},
				{ "计量单位","计量单位","计量单位","计量单位",     "斤",     "斤",      "斤",     "斤","计划单位","计划单位","计划单位","计划单位",   "元"   ,   "元"  ,   "元"  ,   "元"},
				{ "储备定额","储备定额","储备定额","储备定额",   "最高",    "最高",	"100"  ,  "100" ,  	"100" ,  "100"  , "最低"   ,  "最低" , "10"    ,    "10" ,    "10" ,   "10"},
				{ "备注"   ,   "备注" ,   "备注",    "备注","棒棒哒" , "棒棒哒" ,  "棒棒哒" ,"棒棒哒" ,"棒棒哒" ,  "棒棒哒" ,"棒棒哒" , "棒棒哒" , "棒棒哒" , "棒棒哒" , "棒棒哒" ,  "棒棒哒"}
		};
		ArrayList<LinkedHashMap<String, String>> list = new ArrayList<LinkedHashMap<String, String>>();
		for (int i = 0; i < dataArray.length; i++) {
			//需有序集合对象，apache的框架所有sql结果集已是有序集合 sql查询出的列表集合的每一条记录都是有序集合
			LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
			for (int j = 0; j < dataArray[i].length; j++) {
				map.put("key_"+j, dataArray[i][j]);
			}
			list.add(map);
		}

		//生成图片
//		File file = new File("F:/1.jpg");
		//生成图片
		// 存放上传图片的文件夹
		File fileDir = ImageUtils.getImgDirFile();
		File file = new File(fileDir.getAbsolutePath() + File.separator + "1.jpg");
		File file2 = new File("F:/1.jpg");
		try {

			//默认报表图片合并单元格对象
			ReportImageMergeCell reportImageMergeCell = new ReportImageMergeCellDefaultImp();
			//默认报表图片单元格动态颜色对象
			ReportImageDynamicColor reportImageDynamicColor = new ReportImageDynamicColorDefaultImp();
			//默认报表图片配置对象
			ReportImageConfig config = new ReportImageConfig(reportImageMergeCell,reportImageDynamicColor);
			//设置图片宽度
//			config.setImgWidth(dataArray[0].length*80);
			config.setImgWidth(640);
			//设置图片高度
//			config.setImgHeight(dataArray.length*20);
			config.setImgHeight(384);
			//设置标题行数
			config.setTitleRowCnt(9);

			config.setTitleFontStyle(Font.PLAIN);
			config.setTitleFontSize(25);

			//生成报表图片
			ReportImageCreator.createReportImage(config , list ,file );

			//生成报表图片
			ReportImageCreator.createReportImage(config , dataArray ,file2 );
		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageUtils.convertImageFile(file);

	}
}

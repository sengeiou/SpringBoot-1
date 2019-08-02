package com.onejane.image.mergetable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 生成数据报表图片
 * @name ReportImageCreator
 * @author wangchao
 * @date Nov 21, 2013 9:38:02 AM
 * @demo
 *
 */
public class ReportImageCreator implements Serializable {


	/***/
	private static final long serialVersionUID = 1L;


	/**
	 * 生成数据报表图片
	 * @param config 图片属性配置对象
	 * @param dynamicColor 图片动态颜色接口
	 * @param dataList 图片报表的数据源
	 * @param imageFile 图片保存路径
	 * @return boolean
	 */
	public static boolean createReportImage(ReportImageConfig config,ArrayList<LinkedHashMap<String,String>> dataList,File imageFile) throws Exception {

		try {

			/**数据源为空时返回 */
			if(null == dataList || 0==dataList.size()){
				if(imageFile.exists()){
					imageFile.delete();
				}
				return imageFile.createNewFile();
			}

			/** 转换数据list为数组对象*/
			Object[] dataArray = dataList.toArray();

			/**获取图片单元格线条颜色*/
			Color cellLineColor = config.getCellLineColor();
			/**获取图片色彩质量类型*/
			int imgType = config.getImageType();
			/**获取图片宽度*/
			int imgWidth = config.getImgWidth();
			/**获取图片高度*/
			int imgHeight = config.getImgHeight();
			/**获取图片标题行数*/
			int titleRowCnt = config.getTitleRowCnt() ;
			/**获取图片标题列数*/
			int titleColCnt = config.getTitleColCnt() ;
			/**获取图片标题单元格字体名称*/
			String titleFontName = config.getTitleFontName();
			/**获取图片标题单元格字体样式*/
			int titleFontStyle = config.getTitleFontStyle();
			/**获取图片标题单元格字体大小*/
			int titleFontSize = config.getTitleFontSize();
			/**获取图片内容单元格字体名称*/
			String contentFontName = config.getContentFontName();
			/**获取图片内容单元格字体样式*/
			int contentFontStyle = config.getContentFontStyle();
			/**获取图片内容单元格字体大小*/
			int contentFontSize = config.getContentFontSize();
			/**获取配置对象中的图片文件格式*/
			String imageFileType = config.getImageFileType();
			/**获取配置对象中的图片单元格合并配置对象*/
			ReportImageMergeCell reportImageMergeCell = config.getReportImageMergeCell();
			/**获取配置对象中的图片单元格动态颜色配置对象*/
			ReportImageDynamicColor reportImageDynamicColor = config.getReportImageDynamicColor();

			/**生成图片总单元格行数*/
			int rowCnt = dataArray.length;
			/**生成图片总单元格列数*/
			int colCnt = ( (Map<?,?>) dataArray[0] ).values().size();
			/**生成图片单元格宽度*/
			int cellWidth = imgWidth/colCnt ;
			/**生成图片单元格高度*/
			int cellHeight = imgHeight/rowCnt ;

			/**创建图像区域*/
			BufferedImage bufferedImage = new BufferedImage(imgWidth,imgHeight,imgType);
			/**获取图像画笔*/
			Graphics graphics =bufferedImage.getGraphics();
			/**创建标题字体对象*/
			Font titleFont = new Font(titleFontName ,titleFontStyle , titleFontSize);
			/**创建内容字体对象*/
			Font contentFont = new Font(contentFontName ,contentFontStyle , contentFontSize);
			/**创建默认白色背景图层*/
			graphics.setColor(Color.white);
			/**填充白色背景图层*/
			graphics.fillRect(0, 0, imgWidth, imgHeight);

			/**上下左右居中写入内容*/
			for (int y = 0; y < dataArray.length; y++) {
				/**获取一行记录*/
				Object[] rowKeyData =( (Map<?,?>) dataArray[y] ).keySet().toArray();
				Object[] rowValueData =( (Map<?,?>) dataArray[y] ).values().toArray();
				for (int x=0 ; x < rowValueData.length; x++) {
					if( y< titleRowCnt || x< titleColCnt ){
						/**设置标题头单元格的背景颜色*/
						graphics.setColor(reportImageDynamicColor.getTitleCellBackgroundColor(config, dataList, y, rowKeyData[x].toString(), rowValueData[x].toString()));
						/**填充标题头单元格背景颜色*/
						graphics.fillRect(x*cellWidth, y*cellHeight, (x+1)*cellWidth, (y+1)*cellHeight);
						/**设置标题头单元格的字体*/
						graphics.setFont(titleFont);
						/**设置标题头单元格的字体颜色*/
						graphics.setColor(reportImageDynamicColor.getTitleFontColor(config, dataList, y, rowKeyData[x].toString(), rowValueData[x].toString()));
					}else{
						/**设置内容单元格的背景颜色*/
						graphics.setColor(reportImageDynamicColor.getContentCellBackgroundColor(config, dataList, y, rowKeyData[x].toString(), rowValueData[x].toString()));
						/**填充内容单元格背景颜色*/
						graphics.fillRect(x*cellWidth, y*cellHeight, (x+1)*cellWidth, (y+1)*cellHeight);
						/**设置内容单元格的字体*/
						graphics.setFont(contentFont);
						/**设置内容单元格的字体颜色*/
						graphics.setColor(reportImageDynamicColor.getContentFontColor(config, dataList, y, rowKeyData[x].toString(), rowValueData[x].toString()));
					}
					/**写入单元格内容*/
					graphics.drawString(rowValueData[x].toString() , cellWidth*x+(cellWidth-((isNumber(rowValueData[x].toString()))?(contentFontSize/2):contentFontSize)*(rowValueData[x].toString().length()))/2 , cellHeight*y +(cellHeight+((isNumber(rowValueData[x].toString()))?(contentFontSize-4):contentFontSize))/2);
				}
			}

			/**
			 * 	1.务必最后绘制单元格线条，因为提前绘制可能会被内容单元格覆盖掉
			 *  2.由于像素点坐标是从0开始，所以的宽度600的最大横向坐标是599，
			 *    所以在下边循环结束后需另外添加最右边和最下边的两条边线
			 * */

			/**设置绘制单元格线颜色，*/
			graphics.setColor(cellLineColor);
			/**绘制单元格线条*/
			for (int x = 0; x <= imgWidth; x+=cellWidth) {
				/**循环绘制单元格纵向线条*/
				graphics.drawLine(x , 0,  x, imgHeight);
				/**首次循环时绘制单元格横向线条*/
				for (int y = 0; x==0 && y <= imgHeight; y+=cellHeight) {
					graphics.drawLine(0, y, imgWidth, y);
				}
			}

			/**执行横向标题单元格合并*/
			reportImageMergeCell.mergeRowTitleCell(config, graphics, cellWidth, cellHeight, dataList);
			/**执行纵向标题单元格合并*/
			reportImageMergeCell.mergeColumnTitleCell(config, graphics, cellWidth, cellHeight, dataList);
			/**执行横向内容单元格合并*/
			reportImageMergeCell.mergeRowContentCell(config, graphics, cellWidth, cellHeight, dataList);
			/**执行纵向内容单元格合并*/
			reportImageMergeCell.mergeColumnContentCell(config, graphics, cellWidth, cellHeight, dataList);

			graphics.setColor(cellLineColor);
			/**添加最下边的边线*/
			graphics.drawLine(0 , imgHeight-1,  imgWidth-1, imgHeight-1);
			/**添加最右边的边线*/
			graphics.drawLine(imgWidth-1 , 0,  imgWidth-1, imgHeight-1);

			/**释放画笔和系统资源*/
			graphics.dispose();
			/**保存图片内容*/
			ImageIO.write(bufferedImage, imageFileType , imageFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}



	/**
	 * 生成数据报表图片
	 * @param config 图片属性配置对象
	 * @param dynamicColor 图片动态颜色接口
	 * @param dataArray 图片报表的数据源
	 * @param imageFile 图片保存路径
	 * @return boolean
	 */
	public static boolean createReportImage(ReportImageConfig config,String[][] dataArray,File imageFile) throws Exception {

		try {

			/**数据源为空时返回 */
			if(null == dataArray || 0==dataArray.length ){
				if(imageFile.exists()){
					imageFile.delete();
				}
				return imageFile.createNewFile();
			}

			/**获取图片单元格线条颜色*/
			Color cellLineColor = config.getCellLineColor();
			/**获取图片色彩质量类型*/
			int imgType = config.getImageType();
			/**获取图片宽度*/
			int imgWidth = config.getImgWidth();
			/**获取图片高度*/
			int imgHeight = config.getImgHeight();
			/**获取图片标题行数*/
			int titleRowCnt = config.getTitleRowCnt() ;
			/**获取图片标题列数*/
			int titleColCnt = config.getTitleColCnt() ;
			/**获取图片标题单元格字体名称*/
			String titleFontName = config.getTitleFontName();
			/**获取图片标题单元格字体样式*/
			int titleFontStyle = config.getTitleFontStyle();
			/**获取图片标题单元格字体大小*/
			int titleFontSize = config.getTitleFontSize();
			/**获取图片内容单元格字体名称*/
			String contentFontName = config.getContentFontName();
			/**获取图片内容单元格字体样式*/
			int contentFontStyle = config.getContentFontStyle();
			/**获取图片内容单元格字体大小*/
			int contentFontSize = config.getContentFontSize();
			/**获取配置对象中的图片文件格式*/
			String imageFileType = config.getImageFileType();
			/**获取配置对象中的图片单元格合并配置对象*/
			ReportImageMergeCell reportImageMergeCell = config.getReportImageMergeCell();
			/**获取配置对象中的图片单元格动态颜色配置对象*/
			ReportImageDynamicColor reportImageDynamicColor = config.getReportImageDynamicColor();

			/**生成图片总单元格行数*/
			int rowCnt = dataArray.length;
			/**生成图片总单元格列数*/
			int colCnt = dataArray[0].length;
			/**生成图片单元格宽度*/
			int cellWidth = imgWidth/colCnt ;
			/**生成图片单元格高度*/
			int cellHeight = imgHeight/rowCnt ;

			/**创建图像区域*/
			BufferedImage bufferedImage = new BufferedImage(imgWidth,imgHeight,imgType);
			/**获取图像画笔*/
			Graphics graphics =bufferedImage.getGraphics();
			/**创建标题字体对象*/
			Font titleFont = new Font(titleFontName ,titleFontStyle , titleFontSize);
			/**创建内容字体对象*/
			Font contentFont = new Font(contentFontName ,contentFontStyle , contentFontSize);
			/**创建默认白色背景图层*/
			graphics.setColor(Color.white);
			/**填充白色背景图层*/
			graphics.fillRect(0, 0, imgWidth, imgHeight);

			/**上下左右居中写入内容*/
			for (int y = 0; y < dataArray.length; y++) {
				/**获取一行记录*/
				Object[] rowData =dataArray[y];
				for (int x=0 ; x < rowData.length; x++) {
					if( y< titleRowCnt || x< titleColCnt ){
						/**设置标题头单元格的背景颜色*/
						graphics.setColor(reportImageDynamicColor.getTitleCellBackgroundColor(config, dataArray, y, x, rowData[x].toString()));
						/**填充标题头单元格背景颜色*/
						graphics.fillRect(x*cellWidth, y*cellHeight, (x+1)*cellWidth, (y+1)*cellHeight);
						/**设置标题头单元格的字体*/
						graphics.setFont(titleFont);
						/**设置标题头单元格的字体颜色*/
						graphics.setColor(reportImageDynamicColor.getTitleFontColor(config, dataArray, y, x, rowData[x].toString()));
					}else{
						/**设置内容单元格的背景颜色*/
						graphics.setColor(reportImageDynamicColor.getContentCellBackgroundColor(config, dataArray, y, x, rowData[x].toString()));
						/**填充内容单元格背景颜色*/
						graphics.fillRect(x*cellWidth, y*cellHeight, (x+1)*cellWidth, (y+1)*cellHeight);
						/**设置内容单元格的字体*/
						graphics.setFont(contentFont);
						/**设置内容单元格的字体颜色*/
						graphics.setColor(reportImageDynamicColor.getContentFontColor(config, dataArray,  y, x, rowData[x].toString()));
					}
					/**写入单元格内容*/
					graphics.drawString(rowData[x].toString() , cellWidth*x+(cellWidth-((isNumber(rowData[x].toString()))?(contentFontSize/2):contentFontSize)*(rowData[x].toString().length()))/2 , cellHeight*y +(cellHeight+( (isNumber(rowData[x].toString()))?(contentFontSize-4):contentFontSize))/2);
				}
			}

			/**
			 * 	1.务必最后绘制单元格线条，因为提前绘制可能会被内容单元格覆盖掉
			 *  2.由于像素点坐标是从0开始，所以的宽度600的最大横向坐标是599，
			 *    所以在下边循环结束后需另外添加最右边和最下边的两条边线
			 * */

			/**设置绘制单元格线颜色，*/
			graphics.setColor(cellLineColor);
			/**绘制单元格线条*/
			for (int x = 0; x <= imgWidth; x+=cellWidth) {
				/**循环绘制单元格纵向线条*/
				graphics.drawLine(x , 0,  x, imgHeight);
				/**首次循环时绘制单元格横向线条*/
				for (int y = 0; x==0 && y <= imgHeight; y+=cellHeight) {
					graphics.drawLine(0, y, imgWidth, y);
				}
			}

			/**执行横向标题单元格合并*/
			reportImageMergeCell.mergeRowTitleCell(config, graphics, cellWidth, cellHeight, dataArray);
			/**执行纵向标题单元格合并*/
			reportImageMergeCell.mergeColumnTitleCell(config, graphics, cellWidth, cellHeight, dataArray);
			/**执行横向内容单元格合并*/
			reportImageMergeCell.mergeRowContentCell(config, graphics, cellWidth, cellHeight, dataArray);
			/**执行纵向内容单元格合并*/
			reportImageMergeCell.mergeColumnContentCell(config, graphics, cellWidth, cellHeight, dataArray);

			graphics.setColor(cellLineColor);
			/**添加最下边的边线*/
			graphics.drawLine(0 , imgHeight-1,  imgWidth-1, imgHeight-1);
			/**添加最右边的边线*/
			graphics.drawLine(imgWidth-1 , 0,  imgWidth-1, imgHeight-1);

			/**释放画笔和系统资源*/
			graphics.dispose();
			/**保存图片内容*/
			ImageIO.write(bufferedImage, imageFileType , imageFile);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}


	/**
	 * 判断传入字符串是否是数字
	 * @method isNumber
	 * @date Dec 19, 2013 11:13:42 AM
	 * @return boolean
	 */
	public static boolean isNumber(String value){
		try{
			Double.parseDouble(value);
			return true;
		}catch (Exception e) {
			return false;
		}
	}


}

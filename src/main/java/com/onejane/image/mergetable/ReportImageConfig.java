package com.onejane.image.mergetable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * 报表图片属性配置信息
 * @name CreateReportImageConfig
 * @author wangchao
 * @date Nov 20, 2013 1:57:31 PM
 */
public class ReportImageConfig  implements Serializable{

	/***/
	private static final long serialVersionUID = 1L;

	/**报表图片的色彩质量类型，默认为 BufferedImage.TYPE_INT_RGB */
	public int imageType=BufferedImage.TYPE_INT_RGB;

	/**报表图片宽度，单位为px，默认为600px */
	public int imgWidth=600;

	/**报表图片高度，单位为px，默认为300px */
	public int imgHeight=300;

	/**报表横向标题行数，默认为1行 */
	public int titleRowCnt=1;

	/**报表纵向标题行数，默认为1列 */
	public int titleColCnt=1;

	/**报表标题字体名称，默认为“SansSerif”，字体可选范围为当前使用环境虚拟机内字体范围 */
	public String titleFontName = "SansSerif" ;

	/**报表标题字体样式，默认为BOLD，字体样式可选范围为当前使用环境虚拟机内字体样式范围 */
	public int titleFontStyle = Font.BOLD  ;

	/**报表标题字体大小，单位为px，默认为14px */
	public int titleFontSize = 14 ;

	/**报表内容字体名称，默认为“SansSerif”，字体可选范围为当前使用环境虚拟机内字体范围 */
	public String contentFontName = "SansSerif" ;

	/**报表内容字体样式，默认为PLAIN，字体样式可选范围为当前使用环境虚拟机内字体样式范围 */
	public int contentFontStyle = Font.PLAIN  ;

	/**报表内容字体大小，单位为px，默认为14px */
	public int contentFontSize = 14 ;

	/**报表标题单元格背景颜色，默认为RGB色(209,247,255) */
//	public Color titleCellBackgroundColor = new Color(209,247,255);
	public Color titleCellBackgroundColor = new Color(255,255,255);

	/**报表标题字体颜色，默认为RGB色(0,0,0) */
	public Color titleFontColor = new Color(0,0,0);

	/**报表内容单元格背景颜色，默认为RGB色(255,255,255) */
	public Color contentCellBackgroundColor = new Color(255,255,255);

	/**报表标题字体颜色，默认为RGB色(0,0,0) */
	public Color contentFontColor = new Color(0,0,0);

	/**报表单元格线条颜色，默认为RGB色(0,0,0) */
	public Color cellLineColor = new Color(0,0,0);

	/**报表图片格式，默认为存贮体积较小的JPG格式 */
	public String imageFileType = "JPG";

	/**报表图片合并单元格配置对象  */
	public ReportImageMergeCell reportImageMergeCell = new ReportImageMergeCellDefaultImp();

	/**报表图片动态颜色配置对象 */
	public ReportImageDynamicColor reportImageDynamicColor = new ReportImageDynamicColorDefaultImp();



	/**获取图片色彩质量类型
	 * @return the imageType
	 */
	public int getImageType() {
		return imageType;
	}
	/**设置图片色彩质量类型
	 * @param imageType the imageType to set
	 */
	public void setImageType(int imageType) {
		this.imageType = imageType;
	}
	/**获取图片宽度
	 * @return the imgWidth
	 */
	public int getImgWidth() {
		return imgWidth;
	}
	/**设置图片宽度
	 * @param imgWidth the imgWidth to set
	 */
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	/**获取图片高度
	 * @return the imgHeight
	 */
	public int getImgHeight() {
		return imgHeight;
	}
	/**设置图片高度
	 * @param imgHeight the imgHeight to set
	 */
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	/**获取图片报表标题行数
	 * @return the titleRowCnt
	 */
	public int getTitleRowCnt() {
		return titleRowCnt;
	}
	/**设置图片报表标题行数
	 * @param titleRowCnt the titleRowCnt to set
	 */
	public void setTitleRowCnt(int titleRowCnt) {
		this.titleRowCnt = titleRowCnt;
	}
	/**获取图片报表标题列数
	 * @return the titleColCnt
	 */
	public int getTitleColCnt() {
		return titleColCnt;
	}
	/**设置图片报表标题列数
	 * @param titleColCnt the titleColCnt to set
	 */
	public void setTitleColCnt(int titleColCnt) {
		this.titleColCnt = titleColCnt;
	}
	/**获取图片报表标题字体
	 * @return the titleFontName
	 */
	public String getTitleFontName() {
		return titleFontName;
	}
	/**设置图片报表标题字体
	 * @param titleFontName the titleFontName to set
	 */
	public void setTitleFontName(String titleFontName) {
		this.titleFontName = titleFontName;
	}
	/**获取图片报表标题字体样式
	 * @return the titleFontStyle
	 */
	public int getTitleFontStyle() {
		return titleFontStyle;
	}
	/**设置图片报表标题字体样式
	 * @param titleFontStyle the titleFontStyle to set
	 */
	public void setTitleFontStyle(int titleFontStyle) {
		this.titleFontStyle = titleFontStyle;
	}
	/**获取图片报表标题字体大小
	 * @return the titleFontSize
	 */
	public int getTitleFontSize() {
		return titleFontSize;
	}
	/**设置图片报表标题字体大小
	 * @param titleFontSize the titleFontSize to set
	 */
	public void setTitleFontSize(int titleFontSize) {
		this.titleFontSize = titleFontSize;
	}
	/**获取图片报表内容字体
	 * @return the contentFontName
	 */
	public String getContentFontName() {
		return contentFontName;
	}
	/**设置图片报表内容字体
	 * @param contentFontName the contentFontName to set
	 */
	public void setContentFontName(String contentFontName) {
		this.contentFontName = contentFontName;
	}
	/**获取图片报表内容字体样式
	 * @return the contentFontStyle
	 */
	public int getContentFontStyle() {
		return contentFontStyle;
	}
	/**设置图片报表内容字体样式
	 * @param contentFontStyle the contentFontStyle to set
	 */
	public void setContentFontStyle(int contentFontStyle) {
		this.contentFontStyle = contentFontStyle;
	}
	/**获取图片报表内容字体大小
	 * @return the contentFontSize
	 */
	public int getContentFontSize() {
		return contentFontSize;
	}
	/**设置图片报表内容字体大小
	 * @param contentFontSize the contentFontSize to set
	 */
	public void setContentFontSize(int contentFontSize) {
		this.contentFontSize = contentFontSize;
	}
	/**获取图片报表标题单元格背景颜色
	 * @return the titleCellBackgroundColor
	 */
	public Color getTitleCellBackgroundColor() {
		return titleCellBackgroundColor;
	}
	/**设置图片报表标题单元格背景颜色
	 * @param titleCellBackgroundColor the titleCellBackgroundColor to set
	 */
	public void setTitleCellBackgroundColor(Color titleCellBackgroundColor) {
		this.titleCellBackgroundColor = titleCellBackgroundColor;
	}
	/**获取图片报表标题单元格字体颜色
	 * @return the titleFontColor
	 */
	public Color getTitleFontColor() {
		return titleFontColor;
	}
	/**设置图片报表标题单元格字体颜色
	 * @param titleFontColor the titleFontColor to set
	 */
	public void setTitleFontColor(Color titleFontColor) {
		this.titleFontColor = titleFontColor;
	}
	/**获取图片报表内容单元格背景颜色
	 * @return the contentCellBackgroundColor
	 */
	public Color getContentCellBackgroundColor() {
		return contentCellBackgroundColor;
	}
	/**设置图片报表内容单元格背景颜色
	 * @param contentCellBackgroundColor the contentCellBackgroundColor to set
	 */
	public void setContentCellBackgroundColor(Color contentCellBackgroundColor) {
		this.contentCellBackgroundColor = contentCellBackgroundColor;
	}
	/**获取图片报表内容单元格字体颜色
	 * @return the contentFontColor
	 */
	public Color getContentFontColor() {
		return contentFontColor;
	}
	/**设置图片报表内容单元格字体颜色
	 * @param contentFontColor the contentFontColor to set
	 */
	public void setContentFontColor(Color contentFontColor) {
		this.contentFontColor = contentFontColor;
	}
	/**获取图片报表内容单元格线条颜色
	 * @return the cellLineColor
	 */
	public Color getCellLineColor() {
		return cellLineColor;
	}
	/**设置图片报表内容单元格线条颜色
	 * @param cellLineColor the cellLineColor to set
	 */
	public void setCellLineColor(Color cellLineColor) {
		this.cellLineColor = cellLineColor;
	}
	/**获取图片文件保存格式
	 * @return the imageFileType
	 */
	public String getImageFileType() {
		return imageFileType;
	}
	/**设置图片文件保存格式
	 * @param imageFileType the imageFileType to set
	 */
	public void setImageFileType(String imageFileType) {
		this.imageFileType = imageFileType;
	}
	/**获取图片单元格合并配置对象
	 * @return the reportImageMergeCell
	 */
	public ReportImageMergeCell getReportImageMergeCell() {
		return reportImageMergeCell;
	}
	/**设置图片单元格合并配置对象
	 * @param reportImageMergeCell the reportImageMergeCell to set
	 */
	public void setReportImageMergeCell(ReportImageMergeCell reportImageMergeCell) {
		this.reportImageMergeCell = reportImageMergeCell;
	}
	/**获取图片单元格动态颜色配置对象
	 * @return the reportImageDynamicColor
	 */
	public ReportImageDynamicColor getReportImageDynamicColor() {
		return reportImageDynamicColor;
	}
	/**设置图片单元格动态颜色配置对象
	 * @param reportImageDynamicColor the reportImageDynamicColor to set
	 */
	public void setReportImageDynamicColor(
			ReportImageDynamicColor reportImageDynamicColor) {
		this.reportImageDynamicColor = reportImageDynamicColor;
	}

	/** 无参构造
	 * @class CreateReportImageConfig
	 * @auther wangc
	 * @date 1:30:18 PM
	 */
	public ReportImageConfig() {
		super();
	}


	/** 有参构造
	 * @class CreateReportImageConfig
	 * @auther wangc
	 * @date 1:29:45 PM
	 */
	public ReportImageConfig(ReportImageMergeCell reportImageMergeCell,ReportImageDynamicColor reportImageDynamicColor) {
		super();
		this.reportImageMergeCell = reportImageMergeCell;
		this.reportImageDynamicColor = reportImageDynamicColor;
	}


	/** 有参构造
	 * @class CreateReportImageConfig
	 * @auther wangc
	 * @date 1:29:45 PM
	 */
	public ReportImageConfig(int imgWidth, int imgHeight,int titleRowCnt, int titleColCnt, String titleFontName,
							 int titleFontStyle, int titleFontSize, String contentFontName,int contentFontStyle, int contentFontSize,
							 Color titleCellBackgroundColor, Color titleFontColor,Color contentCellBackgroundColor, Color contentFontColor,
							 Color cellLineColor,String imageFileType) {
		super();
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.titleRowCnt = titleRowCnt;
		this.titleColCnt = titleColCnt;
		this.titleFontName = titleFontName;
		this.titleFontStyle = titleFontStyle;
		this.titleFontSize = titleFontSize;
		this.contentFontName = contentFontName;
		this.contentFontStyle = contentFontStyle;
		this.contentFontSize = contentFontSize;
		this.titleCellBackgroundColor = titleCellBackgroundColor;
		this.titleFontColor = titleFontColor;
		this.contentCellBackgroundColor = contentCellBackgroundColor;
		this.contentFontColor = contentFontColor;
		this.cellLineColor = cellLineColor;
		this.imageFileType = imageFileType;
	}

	/** 有参构造
	 * @class CreateReportImageConfig
	 * @auther wangc
	 * @date 1:29:45 PM
	 */
	public ReportImageConfig(int imgWidth, int imgHeight,int titleRowCnt, int titleColCnt, String titleFontName,
							 int titleFontStyle, int titleFontSize, String contentFontName,int contentFontStyle, int contentFontSize,
							 Color titleCellBackgroundColor, Color titleFontColor,Color contentCellBackgroundColor, Color contentFontColor,
							 Color cellLineColor,String imageFileType,ReportImageMergeCell reportImageMergeCell,ReportImageDynamicColor reportImageDynamicColor) {
		super();
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.titleRowCnt = titleRowCnt;
		this.titleColCnt = titleColCnt;
		this.titleFontName = titleFontName;
		this.titleFontStyle = titleFontStyle;
		this.titleFontSize = titleFontSize;
		this.contentFontName = contentFontName;
		this.contentFontStyle = contentFontStyle;
		this.contentFontSize = contentFontSize;
		this.titleCellBackgroundColor = titleCellBackgroundColor;
		this.titleFontColor = titleFontColor;
		this.contentCellBackgroundColor = contentCellBackgroundColor;
		this.contentFontColor = contentFontColor;
		this.cellLineColor = cellLineColor;
		this.imageFileType = imageFileType;
		this.reportImageMergeCell = reportImageMergeCell;
		this.reportImageDynamicColor = reportImageDynamicColor;
	}

}

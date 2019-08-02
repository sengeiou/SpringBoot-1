package com.onejane.image.mergetable;

import java.awt.*;
import java.util.List;

/**
 * 动态生成单元格背景和字体颜色接口定义
 * @name ReportImageDynamicColor
 * @author wangchao
 * @date Nov 20, 2013 1:57:31 PM
 */
public interface ReportImageDynamicColor {

	/**
	 * 动态返回标题单元格背景色
	 * @method getTitleCellBackgroundColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getTitleCellBackgroundColor(ReportImageConfig config, List<?> dataList, int x, String keyName, String value);


	/**
	 * 动态返回标题单元格背景色
	 * @method getTitleCellBackgroundColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getTitleCellBackgroundColor(ReportImageConfig config, String[][] dataArray, int x, int y, String value);


	/**
	 * 动态返回标题单元格字体颜色
	 * @method getTitleFontColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getTitleFontColor(ReportImageConfig config, List<?> dataList, int x, String keyName, String value);


	/**
	 * 动态返回标题单元格字体颜色
	 * @method getTitleFontColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getTitleFontColor(ReportImageConfig config, String[][] dataArray, int x, int y, String value);


	/**
	 * 动态返回内容单元格字体颜色
	 * @method getContentFontColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getContentFontColor(ReportImageConfig config, List<?> dataList, int x, String keyName, String value);


	/**
	 * 动态返回内容单元格字体颜色
	 * @method getContentFontColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getContentFontColor(ReportImageConfig config, String[][] dataArray, int x, int y, String value);


	/**
	 * 动态返回内容单元格背景色
	 * @method getContentCellBackgroundColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getContentCellBackgroundColor(ReportImageConfig config, List<?> dataList, int x, String keyName, String value);


	/**
	 * 动态返回内容单元格背景色
	 * @method getContentCellBackgroundColor
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public Color getContentCellBackgroundColor(ReportImageConfig config, String[][] dataArray, int x, int y, String value);


}

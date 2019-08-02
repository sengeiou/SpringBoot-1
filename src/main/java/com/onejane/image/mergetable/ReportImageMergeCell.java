package com.onejane.image.mergetable;

import java.awt.*;
import java.util.List;


/**
 * 数据报表图片单元格合并配置接口
 * @name ReportImageMergeCell
 * @author wangchao
 * @date Nov 21, 2013 9:38:02 AM
 * @demo
 *
 */
public interface ReportImageMergeCell{


	/**
	 * 实现合并横向标题单元格
	 * @method mergeRowTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowTitleCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, List<?> dataList) ;


	/**
	 * 实现合并横向标题单元格
	 * @method mergeRowTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowTitleCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, String[][] dataArray) ;


	/**
	 * 实现合并纵向标题单元格
	 * @method mergeColumnTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnTitleCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, List<?> dataList) ;


	/**
	 * 实现合并纵向标题单元格
	 * @method mergeColumnTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnTitleCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, String[][] dataArray) ;


	/**
	 * 实现合并横向标题单元格
	 * @method mergeRowContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowContentCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, List<?> dataList) ;


	/**
	 * 实现合并横向标题单元格
	 * @method mergeRowContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowContentCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, String[][] dataArray) ;


	/**
	 * 实现合并纵向内容单元格
	 * @method mergeColumnContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnContentCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, List<?> dataList) ;


	/**
	 * 实现合并纵向内容单元格
	 * @method mergeColumnContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnContentCell(ReportImageConfig config, Graphics graphics, int cellWidth, int cellHeight, String[][] dataArray) ;


}

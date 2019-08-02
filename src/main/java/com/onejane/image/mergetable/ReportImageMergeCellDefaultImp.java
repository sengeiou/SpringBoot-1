package com.onejane.image.mergetable;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 数据报表图片单元格合并配置默认实现类
 * @name ReportImageMergeCellDefaultImp
 * @author wangchao
 * @date Nov 21, 2013 9:38:02 AM
 * @demo
 *
 */
public class ReportImageMergeCellDefaultImp implements ReportImageMergeCell,Serializable{

	/***/
	private static final long serialVersionUID = 1L;



	/**
	 * 实现合并横向标题单元格，默认为合并横向相邻且内容相同的单元格
	 * @method mergeRowTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowTitleCell(ReportImageConfig config, Graphics graphics,int cellWidth,int cellHeight,List<?> dataList) {

		int titleRowCnt = config.getTitleRowCnt();
		int titleFontSize = config.getTitleFontSize();
		for( int i=0; i< titleRowCnt ; i++  ){
			Object[] keys = ((Map<?,?>) dataList.get(i)).keySet().toArray();
			Object[] values = ((Map<?,?>) dataList.get(i)).values().toArray();
			int mergeBegin=0;
			int mergeEnd=0;
			for (int j = 1; j < values.length; j++) {
				String prevCellVal = values[j-1].toString();
				String nowCellVal = values[j].toString();
				if(prevCellVal.equals(nowCellVal)){
					mergeEnd++;
					if( mergeEnd == values.length-1){
						graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataList, i, keys[mergeBegin].toString(), prevCellVal));
						graphics.fillRect( mergeBegin*cellWidth+1 , i*cellHeight+1, (mergeEnd-mergeBegin+1)*cellWidth-1 , cellHeight-1);
						graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataList, i, keys[mergeBegin].toString(), prevCellVal));
						graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
						graphics.drawString(prevCellVal, mergeBegin*cellWidth+((mergeEnd-mergeBegin+1)*cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*i+ (titleFontSize+cellHeight)/2);
						mergeBegin=j;
						mergeEnd=j;
					}

				}else if( mergeBegin == mergeEnd ){
					mergeBegin=j;
					mergeEnd=j;
				}else {
					graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataList, i, keys[mergeBegin].toString(), prevCellVal));
					graphics.fillRect( mergeBegin*cellWidth+1 , i*cellHeight+1, (mergeEnd-mergeBegin+1)*cellWidth-1 , cellHeight-1);
					graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataList, i, keys[mergeBegin].toString(), prevCellVal));
					graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
					graphics.drawString(prevCellVal, mergeBegin*cellWidth+((mergeEnd-mergeBegin+1)*cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*i+ (titleFontSize+cellHeight)/2);
					mergeBegin=j;
					mergeEnd=j;
				}
			}
		}

	}


	/**
	 * 实现合并横向标题单元格，默认为合并横向相邻且内容相同的单元格
	 * @method mergeRowTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowTitleCell(ReportImageConfig config, Graphics graphics,int cellWidth,int cellHeight,String[][] dataArray) {

		int titleRowCnt = config.getTitleRowCnt();
		int titleFontSize = config.getTitleFontSize();
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
						graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataArray, i, mergeBegin, prevCellVal));
						graphics.fillRect( mergeBegin*cellWidth+1 , i*cellHeight+1, (mergeEnd-mergeBegin+1)*cellWidth-1 , cellHeight-1);
						graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataArray, i, mergeBegin, prevCellVal));
						graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
						graphics.drawString(prevCellVal, mergeBegin*cellWidth+((mergeEnd-mergeBegin+1)*cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*i+ (titleFontSize+cellHeight)/2);
						mergeBegin=j;
						mergeEnd=j;
					}
				}else if( mergeBegin == mergeEnd ){
					mergeBegin=j;
					mergeEnd=j;
				}else {
					graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataArray, i, mergeBegin, prevCellVal));
					graphics.fillRect( mergeBegin*cellWidth+1 , i*cellHeight+1, (mergeEnd-mergeBegin+1)*cellWidth-1 , cellHeight-1);
					graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataArray, i, mergeBegin, prevCellVal));
					graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
					graphics.drawString(prevCellVal, mergeBegin*cellWidth+((mergeEnd-mergeBegin+1)*cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*i+ (titleFontSize+cellHeight)/2);
					mergeBegin=j;
					mergeEnd=j;
				}
			}
		}

	}


	/**
	 * 实现合并纵向标题单元格，默认为合并纵向相邻且内容相同的单元格
	 * @method mergeColumnTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnTitleCell(ReportImageConfig config, Graphics graphics,int cellWidth,int cellHeight,List<?> dataList) {

		int titleColCnt = config.getTitleColCnt();
		int titleFontSize = config.getTitleFontSize();
		for( int y=0; y< titleColCnt ; y++  ){

			int mergeBegin=0;
			int mergeEnd=0;
			String key = (((Map<?,?>) dataList.get(0)).keySet().toArray())[y].toString();
			for (int x = 1; x < dataList.size(); x++) {
				String prevCellVal = (((Map<?,?>) dataList.get(x-1)).values().toArray())[y].toString();
				String nowCellVal = (((Map<?,?>) dataList.get(x)).values().toArray())[y].toString();
				if(prevCellVal.equals(nowCellVal)){
					mergeEnd++;
					if( mergeEnd == dataList.size()-1){
						graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataList, x, key , prevCellVal));
						graphics.fillRect( y*cellWidth+1 , mergeBegin*cellHeight+1, cellWidth-1 , (mergeEnd-mergeBegin+1)*cellHeight-1);
						graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataList, x, key, prevCellVal));
						graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
						graphics.drawString(prevCellVal, y*cellWidth+(cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*mergeBegin+ ((mergeEnd-mergeBegin+1)*cellHeight+titleFontSize)/2);
						mergeBegin=x;
						mergeEnd=x;
					}
				}else if( mergeBegin == mergeEnd ){
					mergeBegin=x;
					mergeEnd=x;
				}else {
					graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataList, x, key , prevCellVal));
					graphics.fillRect( y*cellWidth+1 , mergeBegin*cellHeight+1, cellWidth-1 , (mergeEnd-mergeBegin+1)*cellHeight-1);
					graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataList, x, key, prevCellVal));
					graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
					graphics.drawString(prevCellVal, y*cellWidth+(cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*mergeBegin+ ((mergeEnd-mergeBegin+1)*cellHeight+titleFontSize)/2);
					mergeBegin=x;
					mergeEnd=x;
				}
			}
		}

	}


	/**
	 * 实现合并纵向标题单元格，默认为合并纵向相邻且内容相同的单元格
	 * @method mergeColumnTitleCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnTitleCell(ReportImageConfig config, Graphics graphics,int cellWidth,int cellHeight,String[][] dataArray) {

		int titleColCnt = config.getTitleColCnt();
		int titleFontSize = config.getTitleFontSize();
		for( int y=0; y< titleColCnt ; y++  ){

			int mergeBegin=0;
			int mergeEnd=0;
			for (int x = 1; x < dataArray.length; x++) {
				String prevCellVal = dataArray[x-1][y];
				String nowCellVal = dataArray[x][y];
				if(prevCellVal.equals(nowCellVal)){
					mergeEnd++;
					if( mergeEnd == dataArray.length-1 ){
						graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataArray, x, y , prevCellVal));
						graphics.fillRect( y*cellWidth+1 , mergeBegin*cellHeight+1, cellWidth-1 , (mergeEnd-mergeBegin+1)*cellHeight-1);
						graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataArray, x, y, prevCellVal));
						graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
						graphics.drawString(prevCellVal, y*cellWidth+(cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*mergeBegin+ ((mergeEnd-mergeBegin+1)*cellHeight+titleFontSize)/2);
						mergeBegin=x;
						mergeEnd=x;
					}
				}else if( mergeBegin == mergeEnd ){
					mergeBegin=x;
					mergeEnd=x;
				}else {
					graphics.setColor(config.getReportImageDynamicColor().getTitleCellBackgroundColor(config, dataArray, x, y , prevCellVal));
					graphics.fillRect( y*cellWidth+1 , mergeBegin*cellHeight+1, cellWidth-1 , (mergeEnd-mergeBegin+1)*cellHeight-1);
					graphics.setColor(config.getReportImageDynamicColor().getTitleFontColor(config, dataArray, x, y, prevCellVal));
					graphics.setFont(new Font(config.getTitleFontName() ,config.getTitleFontStyle() , config.getTitleFontSize()));
					graphics.drawString(prevCellVal, y*cellWidth+(cellWidth-prevCellVal.length()*config.getTitleFontSize())/2  , cellHeight*mergeBegin+ ((mergeEnd-mergeBegin+1)*cellHeight+titleFontSize)/2);
					mergeBegin=x;
					mergeEnd=x;
				}
			}
		}

	}


	/**
	 * 实现合并横向标题单元格，默认为无操作
	 * @method mergeRowContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowContentCell(ReportImageConfig config, Graphics graphics,int cellWidth,int cellHeight,List<?> dataList) {
		return;
	}


	/**
	 * 实现合并横向标题单元格，默认为无操作
	 * @method mergeRowContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeRowContentCell(ReportImageConfig config, Graphics graphics,int cellWidth,int cellHeight,String[][] dataArray) {
		return;
	}


	/**
	 * 实现合并纵向内容单元格，默认为无操作
	 * @method mergeColumnContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnContentCell(ReportImageConfig config,Graphics graphics,int cellWidth,int cellHeight,List<?> dataList) {
		return;
	}


	/**
	 * 实现合并纵向内容单元格，默认为无操作
	 * @method mergeColumnContentCell
	 * @date Nov 20, 2013 3:28:21 PM
	 * @return Color
	 */
	public void mergeColumnContentCell(ReportImageConfig config,Graphics graphics,int cellWidth,int cellHeight, String[][] dataArray) {
		return;
	}


}

package com.lanx.app.lbs.poi.inner;

import com.lanx.app.lbs.core.domain.LBSPoi;

/**
 * <p>LBS操作poi的内部执行的service</p>
 * 	 
 * @author Ramboo
 * @date 2013-09-27
 * */
public interface PoiInnerService {
	
	/**
	 * 将LBSPoi数据写入数据库或文件
	 * 
	 * @param poi
	 */
	public void savePoi(final LBSPoi poi);
	
	/**
	 * 将LBSPoi数据集合写入数据库或文件
	 * 
	 * @param pois
	 */
	public void savePoi(final Iterable<LBSPoi> pois);
	
	/**
	 * 根据设置的条件删除定位数据
	 * 
	 * @param pois
	 */
	public void deletePoi(final Iterable<LBSPoi> pois);

}

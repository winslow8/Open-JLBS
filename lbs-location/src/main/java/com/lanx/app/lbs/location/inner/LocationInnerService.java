package com.lanx.app.lbs.location.inner;

import com.lanx.app.lbs.core.domain.LBSCheckin;

/**
 * <p>LBS进行定位的内部执行的service</p>
 * 	 
 * @author Ramboo
 * @date 2013-09-16
 * */
public interface LocationInnerService{
	
	/**
	 * 根据location，将定位数据写入数据库或文件
	 * pid和经纬度数据等放在location实例中
	 * 因为是内部接口，因此使用和数据库对应的LBSCheckin
	 * 
	 * @param location
	 */
	public void saveLocation(final LBSCheckin location);
	
	/**
	 * 根据locations，将定位数据集合写入数据库或文件
	 * pid和经纬度数据等放在location集合实例中
	 * 因为是内部接口，因此使用和数据库对应的LBSCheckin
	 * 
	 * @param locations
	 */
	public void saveLocation(final Iterable<LBSCheckin> locations);
	
	/**
	 * 根据设置的条件删除定位数据
	 * 因为是内部接口，因此使用和数据库对应的LBSCheckin
	 * 
	 * @param deleteParams
	 */
	public void deleteLocations(final Iterable<LBSCheckin> locations);
}

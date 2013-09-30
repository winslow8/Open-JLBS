package com.lanx.app.lbs.core.service;

import java.util.List;

import com.lanx.app.lbs.core.domain.LBSPoi;

/**
 * <p>LBS的coreservice</p>
 * 提供一些公共的方法，例如经纬度和pid互相转换等
 * 
 * @author Ramboo
 * @since 2013-08-24
 * */
public interface LBSCoreService {
	/**
	 * 根据pid获得LBSCoreBean实体，主要是获得对应的经纬度
	 * 因为pid是唯一键，因此可以获得唯一LBSPoi值
	 * 
	 * @param pid
	 * @return
	 */
	public LBSPoi getLongLatByPid(String pid);
	
	/**
	 * 根据pid获得经纬度
	 * 因为pid是唯一键，因此可以获得唯一LBSPoi值
	 * 
	 * @param pid
	 * @return
	 */
	public String[] findLongLatByPid(String pid);
	
	/**
	 * 根据经纬度获得LBSPoi实体，主要是获得对应的pid标识
	 * 因为相同的经纬度可能获得多个poi，因此不能获得唯一LBSPoi值
	 * 此方法不用分页
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public List<LBSPoi> getPoiByLongLat(double longitude, double latitude);

	/**
	 * 根据经纬度以及精确度获得LBSPoi实体，主要是获得对应的pid标识
	 * 
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy
	 * @return
	 */
//	public List<LBSPoi> getPoiByLongLat(double longitude, double latitude, Accuracy accuracy);
}

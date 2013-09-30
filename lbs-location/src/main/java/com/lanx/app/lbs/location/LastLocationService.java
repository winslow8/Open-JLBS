package com.lanx.app.lbs.location;

import java.io.Serializable;

import com.lanx.app.lbs.core.domain.LBSLocation;

/**
 * <p></p>
 * 提供从数据库取得定位的数据的功能
 * 此类需要访问数据库的lbs_checkin表
 * 
 * 
 * @author Ramboo
 * @date 2013-09-09
 * */
public interface LastLocationService {
	
	/**
	 * 根据uid进行定位
	 * 
	 * @param uid
	 * @return
	 */
	public <UID extends Serializable> LBSLocation locateByUID(final UID uid);
	
	/**
	 * 根据设备id进行定位
	 * 
	 * @param deviceId
	 * @return
	 */
	public <DeviceID extends Serializable> LBSLocation locateByDeviceID(final DeviceID deviceId);
	
	/**
	 * 根据uid和设备id进行定位
	 * 
	 * @param uid
	 * @param deviceId
	 * @return
	 */
	public <UID extends Serializable, DeviceID extends Serializable> LBSLocation locate(final UID uid, final DeviceID deviceId);
}

package com.lanx.app.lbs.location;

import java.io.Serializable;
import java.util.Date;

import com.lanx.app.lbs.core.domain.LBSLocation;

/**
 * <p>LBS进行定位的service</p>
 * 
 * 定位操作理论上不应该有update,delete操作
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */
public interface LocationService {
		
	/**
	 * 根据uid，将接收到的经纬度数据写入数据库或文件
	 * 也就是设备id为null,poi没有给出的情况下
	 *  
	 * @param uid		用户uid
	 * @param longitude 经度
	 * @param latitude	纬度
	 */
	public <UID extends Serializable> void saveLocationByUID(final UID uid,final double longitude,final double latitude,final Date loctime);

	/**
	 * 根据uid，将接收到的poi数据写入数据库或文件
	 * 也就是设备标识ID为null，经纬度没有给出的情况下
	 *  
	 * @param uid		用户uid
	 * @param pid
	 * @param poiname
	 */
	public <UID extends Serializable> void saveLocationByUID(final UID uid,final String pid,final String poiname,final Date loctime);

	/**
	 * 根据uid，将接收到的pid和经纬度数据写入数据库或文件
	 * 也就是设备id为null的情况下
	 *  
	 * @param uid		用户uid
	 * @param pid
	 * @param poiname
	 * @param longitude 经度
	 * @param latitude	纬度
	 */
	public <UID extends Serializable> void saveLocationByUID(final UID uid,final String pid,final String poiname,final double longitude,final double latitude,final Date loctime);

	/**
	 * 根据设备标识id,将接收到的经纬度数据写入数据库或文件
	 * 也就是uid为null,poi没有给出的情况下
	 *  
	 * @param deviceId 设备标识ID
	 * @param longitude 经度
	 * @param latitude	纬度
	 */
	public <DeviceID extends Serializable> void saveLocationByDeviceID(final DeviceID deviceId,final double longitude,final double latitude,final Date loctime);

	/**
	 * 根据设备标识id,将接收到的poi数据写入数据库或文件
	 * 也就是uid为null,经纬度没有给出的情况下
	 *  
	 * @param deviceId 设备标识ID
	 * @param pid
	 * @param poiname
	 */
	public <DeviceID extends Serializable> void saveLocationByDeviceID(final DeviceID deviceId,final String pid,final String poiname,final Date loctime);

	/**
	 * 根据设备标识ID，将接收到的pid和经纬度数据写入数据库或文件
	 * 也就是uid为null的情况下
	 *  
	 * @param deviceId		设备标识ID
	 * @param pid
	 * @param poiname
	 * @param longitude 经度
	 * @param latitude	纬度
	 */
	public <DeviceID extends Serializable> void saveLocationByDeviceID(final DeviceID deviceId,final String pid,final String poiname,final double longitude,final double latitude,final Date loctime);
	
	/**
	 * 根据uid，设备标识id,将接收到的poi数据写入数据库或文件
	 * 也就是经纬度没有给出的情况下
	 *  
	 * @param uid		用户uid
	 * @param deviceId 设备标识ID
	 * @param pid
	 * @param poiname
	 */
	public <UID extends Serializable, DeviceID extends Serializable> void saveLocation(final UID uid,final DeviceID deviceId,final String pid,final String poiname,final Date loctime);

	/**
	 * 根据uid，设备标识id,将接收到的经纬度数据写入数据库或文件
	 * 也就是poi没有给出的情况下
	 *  
	 * @param uid		用户uid
	 * @param longitude 经度
	 * @param latitude	纬度
	 */
	public <UID extends Serializable, DeviceID extends Serializable> void saveLocation(final UID uid,final DeviceID deviceId,final double longitude,final double latitude,final Date loctime);

	/**
	 * 根据uid，设备标识id，将接收到的pid和经纬度数据写入数据库或文件
	 * 
	 * @param uid		用户uid
	 * @param deviceId 设备标识ID
	 * @param pid
	 * @param poiname
	 * @param longitude 经度
	 * @param latitude	纬度
	 */
	public <UID extends Serializable, DeviceID extends Serializable> void saveLocation(final UID uid, final DeviceID deviceId, final String pid,final String poiname,final double longitude, final double latitude,final Date loctime);
	
	/**
	 * 根据location定位数据写入数据库或文件
	 * 注意有些是必填项，如果check出错，这条数据会被抛弃
	 * 
	 * @param location
	 */
	public void saveLocation(final LBSLocation location);

	/**
	 * 根据locations定位数据集合异步写入数据库或文件
	 * 注意有些是必填项，如果check出错，这条数据会被抛弃
	 * 
	 * @param locations
	 */
	public void batchSaveLocation(final Iterable<LBSLocation> locations);

}

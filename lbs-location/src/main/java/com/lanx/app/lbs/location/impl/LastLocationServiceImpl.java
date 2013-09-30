package com.lanx.app.lbs.location.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.domain.LBSCheckin;
import com.lanx.app.lbs.core.domain.LBSLocation;
import com.lanx.app.lbs.location.LastLocationService;
import com.lanx.app.lbs.location.dao.LocateDao;
import com.lanx.app.lbs.util.LBSConstants.Pagination;
import com.lanx.app.lbs.util.LBSConstants.SortType;

/**
 * <p>LastLocationService的实现类</p>
 * 
 * @author Ramboo
 * @date 2013-09-10
 * */
public class LastLocationServiceImpl extends AbstractLocationService implements LastLocationService{

	private static final Log logger = LogFactory.getLog(LastLocationServiceImpl.class);
	
	@Autowired
	private LocateDao locateDao;
	
	@Override
	public <UID extends Serializable> LBSLocation locateByUID(UID uid) {
		if(uid == null)
			return null;
		
		long start = System.currentTimeMillis();
		
		String key = locationUID_KeyPrefix + uid;
		LBSCheckin lbsCheckin = syncCachedService.get(key);
		if(lbsCheckin == null){
			//List<LBSCheckin> locates = locateDao.locateByUID(uid);
			Page<LBSCheckin> pages = locateDao.findByUserId(uid, this.buildPageRequest(1, Pagination.DEFAULT_MIN_COUNT, SortType.CHECKIN_TIME_DESC));
			List<LBSCheckin> locates = pages.getContent();
			if(locates != null && locates.size() > 0){
				lbsCheckin = locates.get(0);
				
				//存入缓存
				syncCachedService.set(key, lbsCheckin);
			}
		}

		if(logger.isInfoEnabled())
			logger.info(LastLocationServiceImpl.class.getName() + ": UID -> " + uid + " locateByUID(UID uid) consume " + (System.currentTimeMillis() - start) + " ms");

		return lbsCheckin;
	}

	@Override
	public <DeviceID extends Serializable> LBSLocation locateByDeviceID(DeviceID deviceId) {
		if(deviceId == null)
			return null;
		
		long start = System.nanoTime();
		
		String key = locationDEVICE_KeyPrefix + deviceId;
		LBSCheckin lbsCheckin = syncCachedService.get(key);
		if(lbsCheckin == null){
			List<LBSCheckin> locates = locateDao.locateByDeviceID(deviceId);
			if(locates != null && locates.size() > 0){
				lbsCheckin = locates.get(0);
				
				//存入缓存
				syncCachedService.set(key, lbsCheckin);
			}
		}
		
		if(logger.isInfoEnabled())
			logger.info(LastLocationServiceImpl.class.getName() + ": DeviceID -> " + deviceId + 
					" locateByDeviceID(DeviceID deviceId) consume " + (System.nanoTime() - start) + " ms");
		
		return lbsCheckin;
	}
	
	@Override
	public <UID extends Serializable, DeviceID extends Serializable> LBSLocation locate(UID uid, DeviceID deviceId) {
		if(uid == null)
			return this.locateByDeviceID(deviceId);
		else if(deviceId == null)
			return this.locateByUID(uid);
		else if(uid != null && deviceId != null){
			long start = System.nanoTime();

			String key = locationUID_DEVICE_KeyPrefix + uid + "-" + deviceId;
			LBSCheckin lbsCheckin = syncCachedService.get(key);
			if(lbsCheckin == null){
				List<LBSCheckin> locates = locateDao.locate(uid, deviceId);
				if(locates != null && locates.size() > 0){
					lbsCheckin = locates.get(0);
					
					//存入缓存
					syncCachedService.set(key, lbsCheckin);
				}
			}
			
			if(logger.isInfoEnabled())
				logger.info(LastLocationServiceImpl.class + ": UID -> " + uid + ",DeviceID -> " + deviceId + 
						" locate(UID uid, DeviceID deviceId) consume " + (System.nanoTime() - start) + " ms");

			return lbsCheckin;
		}
		
		return null;
	}
}

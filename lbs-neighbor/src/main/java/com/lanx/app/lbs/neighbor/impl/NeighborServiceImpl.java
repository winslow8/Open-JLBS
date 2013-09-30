package com.lanx.app.lbs.neighbor.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.Accuracy;
import com.lanx.app.lbs.core.domain.LBSCheckin;
import com.lanx.app.lbs.neighbor.NeighborService;
import com.lanx.app.lbs.util.LBSConstants.LocType;
import com.lanx.app.lbs.util.LBSConstants.NearType;
import com.lanx.app.lbs.util.LBSConstants.Pagination;
import com.lanx.app.lbs.util.LBSConstants.PoiType;
import com.lanx.app.lbs.util.LBSConstants.Regex;
import com.lanx.app.lbs.util.LBSConstants.SortType;

/**
 * <p>LBS查找附近的service实现类</p>
 * 
 * @author Ramboo
 * @date 2013-09-23
 *
 */
public class NeighborServiceImpl extends AbstractNearService implements NeighborService{

	private static final Log logger = LogFactory.getLog(NeighborServiceImpl.class);
	
	@Override
	public Iterable<LBSCheckin> findNearCheckins(double longitude,double latitude) {
		return this.findNearCheckins(longitude, latitude, Accuracy.ONE_KM);
	}

	@Override
	public Iterable<LBSCheckin> findNearCheckins(double longitude,double latitude, Accuracy accuracy) {
		Page<LBSCheckin> checkins = this.findNearCheckins(longitude, latitude, Accuracy.ONE_KM, 1, Pagination.DEFAULT_MAX_COUNT, SortType.NONE, PoiType.NONE);
		return checkins == null ? null : checkins.getContent();
	}

	@Override
	public Page<LBSCheckin> findNearCheckins(double longitude, double latitude,Accuracy accuracy, int pageNo, int pageSize, SortType sortType,PoiType poiType) {
		return this.findNear(longitude, latitude, accuracy, pageNo, pageSize, sortType, poiType, LocType.NEAR_LONG_LAT,NearType.CHECKIN, LBSCheckin.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Long> findNearUids(double longitude, double latitude) {
		return this.findNearUids(longitude, latitude, Accuracy.ONE_KM);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Long> findNearUids(double longitude, double latitude, Accuracy accuracy) {
		Page<Long> uids = this.findNearUids(longitude, latitude, accuracy, 1, Pagination.DEFAULT_MAX_COUNT);
		return uids == null ? null : uids.getContent();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Long> findNearUids(double longitude,double latitude, Accuracy accuracy, int pageNo, int pageSize) {		
		//Accuracy.OTHER_KM精度的不缓存
		String keyPrefix = this.getNearKeyPrefix(LocType.NEAR_LONG_LAT, accuracy,NearType.CHECKIN);
		if(Accuracy.OTHER_KM.equals(accuracy) || keyPrefix == null){
			return this.findUids(getGeohash(longitude, latitude), accuracy, pageNo, pageSize);
		}else {
			String key = keyPrefix + longitude + Regex.LINE + latitude;
			
			Page<Long> nearUids = syncCachedService.get(key);		
			if(nearUids == null){
				nearUids = this.findUids(getGeohash(longitude, latitude), accuracy, pageNo, pageSize);
				
				if(nearUids != null){
					syncCachedService.set(key, nearUids);
				}
			}
			
			return nearUids;			
		}
	}
	
	@Override
	public Iterable<LBSCheckin> findNearCheckins(String pid) {
		return this.findNearCheckins(pid, Accuracy.ONE_KM);
	}

	@Override
	public Iterable<LBSCheckin> findNearCheckins(String pid, Accuracy accuracy) {
		Page<LBSCheckin> checkins = this.findNearCheckins(pid, accuracy, 1, Pagination.DEFAULT_MAX_COUNT, SortType.NONE, PoiType.NONE);
		return checkins == null ? null : checkins.getContent();
	}

	@Override
	public Page<LBSCheckin> findNearCheckins(String pid, Accuracy accuracy,int pageNo, int pageSize, SortType sortType, PoiType poiType) {
		String[] longLat = lbsCoreService.findLongLatByPid(pid);
		if(longLat == null){
			logger.error(NeighborServiceImpl.class + "No exists pid !");
			return null;
		}
		
		return this.findNear(Double.parseDouble(longLat[0]), Double.parseDouble(longLat[1]), accuracy, pageNo, pageSize, sortType, poiType, 
				LocType.NEAR_PID, NearType.CHECKIN, LBSCheckin.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Long> findNearUids(String pid) {
		return this.findNearUids(pid, Accuracy.ONE_KM);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<Long> findNearUids(String pid,Accuracy accuracy) {
		Page<Long> uids = this.findNearUids(pid, accuracy, 1, Pagination.DEFAULT_MAX_COUNT);
		return uids == null ? null : uids.getContent();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Long> findNearUids(String pid,Accuracy accuracy, int pageNo, int pageSize) {
		String[] longLat = lbsCoreService.findLongLatByPid(pid);
		if(longLat == null){
			logger.error(NeighborServiceImpl.class + "No exists pid !");
			return null;
		}
		
		//Accuracy.OTHER_KM精度的不缓存
		String keyPrefix = this.getNearKeyPrefix(LocType.NEAR_PID, accuracy,NearType.CHECKIN);
		if(Accuracy.OTHER_KM.equals(accuracy) || keyPrefix == null){
			return this.findNearUids(Double.parseDouble(longLat[0]), Double.parseDouble(longLat[1]), accuracy, pageNo, pageSize);
		}else {
			String key = keyPrefix + pid;
			
			Page<Long> nearUids = syncCachedService.get(key);		
			if(nearUids == null){
				nearUids = this.findNearUids(Double.parseDouble(longLat[0]), Double.parseDouble(longLat[1]), accuracy, pageNo, pageSize);
				
				if(nearUids != null){
					syncCachedService.set(key, nearUids);
				}
			}
			
			return nearUids;			
		}
	}
}

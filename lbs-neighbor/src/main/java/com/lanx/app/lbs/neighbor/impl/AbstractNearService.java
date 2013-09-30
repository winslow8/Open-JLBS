package com.lanx.app.lbs.neighbor.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.lanx.app.lbs.core.Accuracy;
import com.lanx.app.lbs.core.domain.LBSCheckin;
import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.core.persistence.DynamicSpecifications;
import com.lanx.app.lbs.core.persistence.SearchFilter;
import com.lanx.app.lbs.core.persistence.SearchFilter.Operator;
import com.lanx.app.lbs.core.service.LBSCoreService;
import com.lanx.app.lbs.core.service.impl.AbstractCoreService;
import com.lanx.app.lbs.neighbor.dao.NearCheckinDao;
import com.lanx.app.lbs.neighbor.dao.NearPoiDao;
import com.lanx.app.lbs.neighbor.dao.NeighborDao;
import com.lanx.app.lbs.util.Geohash;
import com.lanx.app.lbs.util.LBSConstants.CacheKeyPrefix;
import com.lanx.app.lbs.util.LBSConstants.LocType;
import com.lanx.app.lbs.util.LBSConstants.NearType;
import com.lanx.app.lbs.util.LBSConstants.PoiType;
import com.lanx.app.lbs.util.LBSConstants.Regex;
import com.lanx.app.lbs.util.LBSConstants.SortType;
import com.lanx.base.cache.service.SyncCachedService;

/**
 * <p>LBS查找附近的service抽象类</p>
 * 
 * @author Ramboo
 * @date 2013-09-23
 * */
public class AbstractNearService extends AbstractCoreService implements InitializingBean {

	private static final Log logger = LogFactory.getLog(AbstractNearService.class);

	@Autowired
	SyncCachedService syncCachedService;

	static String checkin_LongLat_KeyPrefix,checkin_Pid_KeyPrefix;
	
	static String poi_LongLat_KeyPrefix,poi_Pid_KeyPrefix;
	
	@Autowired
	protected LBSCoreService lbsCoreService;

	@Autowired
	NeighborDao neighborDao;
	
	@Autowired
	NearCheckinDao nearDao;
	
	@Autowired
	NearPoiDao nearPoiDao;

	protected String getGeohash(double longitude,double latitude) {
		String geohash = Geohash.encode(latitude, longitude);
		if(geohash == null || "".equals(geohash)){
			logger.error(AbstractNearService.class + " check your longitude and latitude ! ");
			return null;
		}
		
		if(logger.isInfoEnabled())
			logger.info(AbstractNearService.class + " geohash -> " + geohash);	
		
		return geohash;
	}

	protected Page<Long> findUids(String geohash, Accuracy accuracy,int pageNo, int pageSize){
		PageRequest pageRequest = this.buildPageRequest(pageNo, pageSize,null);

		return neighborDao.findNearUids(likeGeohash(geohash,accuracy),pageRequest);		
	}
	
	@SuppressWarnings("unchecked")
	protected <T> Page<T> find(String geohash, Accuracy accuracy,int pageNo, int pageSize,SortType sortType,PoiType poiType,Class<T> entityClazz){
		PageRequest pageRequest = this.buildPageRequest(pageNo, pageSize,sortType);
		
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("poiType", poiType);
		
		//if(LBSPoi.class.equals(entityClazz))
		//if(entityClazz == LBSPoi.class){
		if(entityClazz.isAssignableFrom(LBSPoi.class)){
			Specification<LBSPoi> spec = this.buildSpecification(searchParams,geohash, accuracy,LBSPoi.class);
			return (Page<T>)nearPoiDao.findAll(spec, pageRequest);			
		}else if(entityClazz == LBSCheckin.class){
			Specification<LBSCheckin> spec = this.buildSpecification(searchParams,geohash, accuracy,LBSCheckin.class);
			return (Page<T>)nearDao.findAll(spec, pageRequest);
		}
		return null;
	}
	
	protected <T> Specification<T> buildSpecification(Map<String, Object> searchParams,String geohash,Accuracy accuracy,Class<T> entityClazz) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("geohash", new SearchFilter("geohash", Operator.LIKE, likeGeohash(geohash,accuracy)));
		
		return DynamicSpecifications.bySearchFilter(filters.values(), entityClazz);
	}
	
	/**
	 * 根据精度获得geohash的like语句的值
	 * 
	 * @param geohash
	 * @param accuracy
	 * @return
	 */
	private String likeGeohash(String geohash,Accuracy accuracy) {
		if(geohash == null || "".equals(geohash))
			return null;
		
		if(Accuracy.ONE_KM.equals(accuracy)){
			return geohash.substring(0,geohash.length() - 3) + Regex.WILDCARD;
		}else if(Accuracy.FIVE_HUNDRED_METER.equals(accuracy)){
			return geohash.substring(0,geohash.length() - 2) + Regex.WILDCARD;
		}else if(Accuracy.ONE_HUNDRED_METER.equals(accuracy)){
			return geohash.substring(0,geohash.length() - 1) + Regex.WILDCARD;
		}else if(Accuracy.OTHER_KM.equals(accuracy)){
			return geohash.substring(0,geohash.length() - 4) + Regex.WILDCARD;
		}
		return null;
	}
	
	/**
	 * 根据Accuracy精度获得cachekey前缀
	 * 
	 * @return
	 */
	protected String getNearKeyPrefix(LocType locType,Accuracy accuracy,NearType nearType) {
		if(NearType.CHECKIN.equals(nearType)){
			if(Accuracy.ONE_KM.equals(accuracy)){
				return LocType.NEAR_LONG_LAT.equals(locType) ? checkin_LongLat_KeyPrefix + 1000 : checkin_Pid_KeyPrefix + 1000;
			}else if(Accuracy.FIVE_HUNDRED_METER.equals(accuracy)){
				return LocType.NEAR_LONG_LAT.equals(locType) ? checkin_LongLat_KeyPrefix + 500 : checkin_Pid_KeyPrefix + 500;
			}else if(Accuracy.ONE_HUNDRED_METER.equals(accuracy)){
				return LocType.NEAR_LONG_LAT.equals(locType) ? checkin_LongLat_KeyPrefix + 100 : checkin_Pid_KeyPrefix + 100;
			}else if(Accuracy.OTHER_KM.equals(accuracy)){
					
			}						
		}else if(NearType.POI.equals(nearType)){
			if(Accuracy.ONE_KM.equals(accuracy)){
				return LocType.NEAR_LONG_LAT.equals(locType) ? poi_LongLat_KeyPrefix + 1000 : poi_Pid_KeyPrefix + 1000;
			}else if(Accuracy.FIVE_HUNDRED_METER.equals(accuracy)){
				return LocType.NEAR_LONG_LAT.equals(locType) ? poi_LongLat_KeyPrefix + 500 : poi_Pid_KeyPrefix + 500;
			}else if(Accuracy.ONE_HUNDRED_METER.equals(accuracy)){
				return LocType.NEAR_LONG_LAT.equals(locType) ? poi_LongLat_KeyPrefix + 100 : poi_Pid_KeyPrefix + 100;
			}else if(Accuracy.OTHER_KM.equals(accuracy)){
					
			}
		}
		
		return null;
	}
	
	/**
	 * 值得缓存的分类
	 * 
	 * @param poiType
	 * @return
	 */
	protected boolean needCacheSort(PoiType poiType) {
		return PoiType.BANK.equals(poiType) || PoiType.EDU.equals(poiType) || PoiType.FOOD.equals(poiType) || PoiType.ENTERTAINMENT.equals(poiType);
	}
	
	/**
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy
	 * @param pageNo
	 * @param pageSize
	 * @param sortType 排序规则
	 * @param entityClazz
	 * @return
	 */
	protected <T> Page<T> findNear(double longitude, double latitude,Accuracy accuracy, int pageNo, int pageSize, SortType sortType,PoiType poiType,
			LocType locType,NearType nearType,Class<T> entityClazz) {
		//只缓存几种常用的分类,Accuracy.OTHER_KM精度的也不缓存
		if(!this.needCacheSort(poiType) || Accuracy.OTHER_KM.equals(accuracy)){
			return this.find(getGeohash(longitude, latitude), accuracy, pageNo, pageSize, sortType, poiType,entityClazz);	
		}else {
			String key = this.getNearKeyPrefix(locType/*LocType.NEAR_LONG_LAT*/, accuracy,nearType/*NearType.POI*/) + longitude + Regex.LINE + latitude + Regex.LINE + poiType ;			
			Page<T> nears = syncCachedService.get(key);		
			if(nears == null){
				nears = this.find(getGeohash(longitude, latitude), accuracy, pageNo, pageSize, sortType, poiType,entityClazz);
				
				if(nears != null){
					syncCachedService.set(key, nears);
				}
			}
			
			return nears;		
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info(AbstractNearService.class + " InitializingBean.afterPropertiesSet() running.");
		}
		
		if(checkin_LongLat_KeyPrefix == null || "".equals(checkin_LongLat_KeyPrefix))
			checkin_LongLat_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.NEAR_CHECKIN_LONGLAT);
		
		if(checkin_Pid_KeyPrefix == null || "".equals(checkin_Pid_KeyPrefix))
			checkin_Pid_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.NEAR_CHECKIN_PID);

		if(poi_LongLat_KeyPrefix == null || "".equals(poi_LongLat_KeyPrefix))
			poi_LongLat_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.NEAR_POI_LONGLAT);
		
		if(poi_Pid_KeyPrefix == null || "".equals(poi_Pid_KeyPrefix))
			poi_Pid_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.NEAR_POI_PID);
	}
}

package com.lanx.app.lbs.location.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.lanx.app.lbs.core.domain.LBSLocation;
import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.core.service.LBSCoreService;
import com.lanx.app.lbs.core.service.impl.AbstractCoreService;
import com.lanx.app.lbs.location.dao.LocatePlusDao;
import com.lanx.app.lbs.util.LBSConstants.CacheKeyPrefix;
import com.lanx.app.lbs.util.LBSConstants.SortType;
import com.lanx.base.cache.service.SyncCachedService;

/**
 * <p>LBS定位的service抽象类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */
public class AbstractLocationService extends AbstractCoreService implements InitializingBean{
	
	private static final Log logger = LogFactory.getLog(AbstractLocationService.class);
	
	@Autowired
	SyncCachedService syncCachedService;

	static String locationUID_KeyPrefix, locationDEVICE_KeyPrefix, locationUID_DEVICE_KeyPrefix;

	static String locationUID_List_KeyPrefix, locationDEVICE_List_KeyPrefix, locationUID_DEVICE_List_KeyPrefix;

	@Autowired
	protected LBSCoreService lbsCoreService;

	@Autowired
	LocatePlusDao locatePlusDao;
	
	protected void flushLBSLocation(LBSLocation lbsLocation,LBSPoi lbsPoi,Long uid, String deviceId, Date loctime) {
		if(lbsPoi == null || lbsLocation == null)
			return;
		
		lbsLocation.setUid(uid);
		lbsLocation.setDeviceId(deviceId);
		
		lbsLocation.setPid(lbsPoi.getPid());
		lbsLocation.setPoiname(lbsPoi.getPoiname());
		
		String longitude = lbsPoi.getLongitude();
		String latitude = lbsPoi.getLatitude();
		lbsLocation.setLongit(Double.parseDouble(longitude));
		lbsLocation.setLatit(Double.parseDouble(latitude));
		lbsLocation.setLongitude(longitude);
		lbsLocation.setLatitude(latitude);
		
		lbsLocation.setLoctime(loctime);		
	}
	
	protected Page<LBSLocation> findAll(Map<String, Object> searchParams, int pageNumber, int pageSize,SortType sortType) {
		PageRequest pageRequest = this.buildPageRequest(pageNumber, pageSize,sortType);
		Specification<LBSLocation> spec = this.buildSpecification(searchParams,LBSLocation.class);

		return locatePlusDao.findAll(spec, pageRequest);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(logger.isInfoEnabled()){
			logger.info(AbstractLocationService.class + " InitializingBean.afterPropertiesSet() running.");
		}
		
		if(locationUID_KeyPrefix == null || "".equals(locationUID_KeyPrefix))
			locationUID_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.DEFAULT_LOCATION_UID_NAME);
		
		if(locationDEVICE_KeyPrefix == null || "".equals(locationDEVICE_KeyPrefix))
			locationDEVICE_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.DEFAULT_LOCATION_DEVICE_NAME);
		
		if(locationUID_DEVICE_KeyPrefix == null || "".equals(locationUID_DEVICE_KeyPrefix))
			locationUID_DEVICE_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.DEFAULT_LOCATION_UID_DEVICE_NAME);
		
		if(locationUID_List_KeyPrefix == null || "".equals(locationUID_List_KeyPrefix))
			locationUID_List_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.DEFAULT_LOCATION_UID_NAME + CacheKeyPrefix.DEFAULT_LIST_APPEND);

		if(locationDEVICE_List_KeyPrefix == null || "".equals(locationDEVICE_List_KeyPrefix))
			locationDEVICE_List_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.DEFAULT_LOCATION_DEVICE_NAME + CacheKeyPrefix.DEFAULT_LIST_APPEND);
		
		if(locationUID_DEVICE_List_KeyPrefix == null || "".equals(locationUID_DEVICE_List_KeyPrefix))
			locationUID_DEVICE_List_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.DEFAULT_LOCATION_UID_DEVICE_NAME + CacheKeyPrefix.DEFAULT_LIST_APPEND);
	}
}

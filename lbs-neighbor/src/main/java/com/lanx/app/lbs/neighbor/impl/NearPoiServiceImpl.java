package com.lanx.app.lbs.neighbor.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.Accuracy;
import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.neighbor.NearPoiService;
import com.lanx.app.lbs.util.LBSConstants.LocType;
import com.lanx.app.lbs.util.LBSConstants.NearType;
import com.lanx.app.lbs.util.LBSConstants.Pagination;
import com.lanx.app.lbs.util.LBSConstants.PoiType;
import com.lanx.app.lbs.util.LBSConstants.SortType;

/**
 * <p>LBS进行查找附近poi的service实现类</p>
 * 考虑在列表中给出附近x公里，x米的功能，momo等都有这个功能
 * 
 * @author Ramboo
 * @date 2013-09-23
 */
public class NearPoiServiceImpl extends AbstractNearService implements NearPoiService{
	private static final Log logger = LogFactory.getLog(NearPoiServiceImpl.class);
	
	@Override
	public Iterable<LBSPoi> findNearPois(double longitude, double latitude) {
		return this.findNearPois(longitude, latitude, Accuracy.ONE_KM);
	}

	@Override
	public Iterable<LBSPoi> findNearPois(double longitude, double latitude,Accuracy accuracy) {
		Page<LBSPoi> pois = this.findNearPois(longitude, latitude, accuracy, 1, Pagination.DEFAULT_MAX_COUNT, SortType.NONE, PoiType.NONE);
		return pois == null ? null : pois.getContent();
	}

	@Override
	public Page<LBSPoi> findNearPois(double longitude, double latitude,Accuracy accuracy, int pageNo, int pageSize, SortType sortType,PoiType poiType) {
		return this.findNear(longitude, latitude, accuracy, pageNo, pageSize, sortType, poiType, LocType.NEAR_LONG_LAT,NearType.POI, LBSPoi.class);
	}

	@Override
	public Iterable<LBSPoi> findNearPois(String pid) {
		return this.findNearPois(pid, Accuracy.ONE_KM);
	}

	@Override
	public Iterable<LBSPoi> findNearPois(String pid, Accuracy accuracy) {
		Page<LBSPoi> pois =  this.findNearPois(pid, accuracy, 1, Pagination.DEFAULT_MAX_COUNT, SortType.NONE, PoiType.NONE);
		return pois == null ? null : pois.getContent();
	}

	@Override
	public Page<LBSPoi> findNearPois(String pid, Accuracy accuracy, int pageNo,int pageSize, SortType sortType, PoiType poiType) {
		String[] longLat = lbsCoreService.findLongLatByPid(pid);
		if(longLat == null){
			logger.error(NearPoiServiceImpl.class + "No exists pid !");
			return null;
		}

		return this.findNear(Double.parseDouble(longLat[0]), Double.parseDouble(longLat[1]), accuracy, pageNo, pageSize, sortType, poiType, 
				LocType.NEAR_PID, NearType.POI, LBSPoi.class);		
	}
}

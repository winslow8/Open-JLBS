package com.lanx.app.lbs.poi.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.core.service.impl.AbstractCoreService;
import com.lanx.app.lbs.poi.PoiService;
import com.lanx.app.lbs.poi.dao.PoiDao;
import com.lanx.app.lbs.poi.dao.PoiPageDao;
import com.lanx.app.lbs.util.LBSConstants.CacheKeyPrefix;
import com.lanx.app.lbs.util.LBSConstants.ID;
import com.lanx.app.lbs.util.LBSConstants.OperType;
import com.lanx.app.lbs.util.LBSConstants.Pagination;
import com.lanx.app.lbs.util.LBSConstants.PoiType;
import com.lanx.app.lbs.util.LBSConstants.Regex;
import com.lanx.app.lbs.util.LBSConstants.SortType;
import com.lanx.base.cache.CacheConstants.CacheExpire;
import com.lanx.base.cache.service.SyncCachedService;

/**
 * <p>LBS操作poi的service接口实现</p>
 * 
 * @author Ramboo
 * @date 2013-09-27
 * */
public class PoiServiceImpl extends AbstractCoreService implements PoiService,InitializingBean{
	
	private static final Log logger = LogFactory.getLog(PoiServiceImpl.class);

	@Autowired
	SyncCachedService syncCachedService;
	
	//poi的cache key前缀
	static String poi_unique_KeyPrefix;

	@Autowired
	PoiDao poiDao;

	@Autowired
	PoiPageDao poiPageDao;
	
	@Override
	public void savePoi(LBSPoi lbsPoi) {
		this.operatePoi(lbsPoi, OperType.SAVE);
	}

	@Override
	public void savePoi(Iterable<LBSPoi> lbsPois) {
		if(lbsPois == null)
    		return;

		long start = System.currentTimeMillis();

		this.sendJmsDto(lbsPois, ID.ALL,OperType.SAVE);
		
		if(logger.isInfoEnabled()){
			for(LBSPoi lbsPoi : lbsPois){
				logger.info(PoiServiceImpl.class + " PID -> " + lbsPoi.getPid() + " send");		
				
			}
			logger.info(PoiServiceImpl.class + " savePoi producer send consume " + (System.currentTimeMillis() - start) + " ms");		
		}
	}

	@Override
	public void deletePoi(LBSPoi lbsPoi) {
		this.operatePoi(lbsPoi, OperType.DELETE);	
	}
	
	private void operatePoi(LBSPoi lbsPoi,OperType operType){
    	if(lbsPoi == null)
    		return;

		long start = System.currentTimeMillis();

		List<LBSPoi> pois = new ArrayList<LBSPoi>(1);
		pois.add(lbsPoi);
		this.sendJmsDto(pois, ID.ALL,operType);//OperType.SAVE);
		
		if(logger.isInfoEnabled())
			logger.info(PoiServiceImpl.class + " PID -> " + lbsPoi.getPid() + " operatePoi producer send consume " + (System.currentTimeMillis() - start) + " ms");		
	}
	
	@Override
	public void deletePoi(Iterable<String> pids){
    	if(pids == null)
    		return;

		long start = System.currentTimeMillis();

		List<LBSPoi> pois = new ArrayList<LBSPoi>();
		LBSPoi poi = null;
		for(String pid : pids){
			poi = new LBSPoi();
			poi.setPid(pid);
			
			pois.add(poi);
		}
		this.sendJmsDto(pois, ID.ALL,OperType.DELETE);
		
		if(logger.isInfoEnabled()){
			for(String pid : pids){
				logger.info(PoiServiceImpl.class + " PID -> " + pid + " send");		
				
			}
			logger.info(PoiServiceImpl.class + " deletePoi producer send consume " + (System.currentTimeMillis() - start) + " ms");		
		}
	}
	
	@Override
	public <UNIQUE extends Serializable> LBSPoi findPoi(UNIQUE id, ID idType) {
		if(id == null){
			logger.error(PoiServiceImpl.class + " id cannot be null ! ");
			return null;
		}
		
		String key = poi_unique_KeyPrefix + idType + Regex.LINE + id;
		LBSPoi poi = syncCachedService.get(key);		
		if(poi == null){
			Map<String, Object> searchParams = new HashMap<String, Object>();
			if(ID.ID.equals(idType))
				searchParams.put("id", id);
			else if(ID.PID.equals(idType))
				searchParams.put("pid", id);
			else {
				logger.error(PoiServiceImpl.class + " Only id or pid allowed ! ");
				return null;
			}

			List<LBSPoi> pois = (List<LBSPoi>)this.findPois(searchParams); 
			if(pois != null && pois.size() > 0){
				poi = pois.get(0);
				
				//缓存一小时
				syncCachedService.set(key, poi,CacheExpire.HOUR);
			}			
		}
		
		return poi;
	}

	@Override
	public Iterable<LBSPoi> findPoi(final Iterable<Serializable> ids,final ID idType){
		if(ids == null){
			logger.error(PoiServiceImpl.class + " ids cannot be null ! ");
			return null;
		}
		
		return poiDao.findAll(ids);
	}
	
	@Override
	public Iterable<LBSPoi> findPois(Map<String, Object> searchParams) {		
		Page<LBSPoi> pages = this.findPois(searchParams, 1, Pagination.DEFAULT_MAX_COUNT, SortType.NONE);
		return pages == null ? null : pages.getContent();
	}

	@Override
	public Page<LBSPoi> findPois(Map<String, Object> searchParams,int pageNumber, int pageSize, SortType sortType) {
		return this.findPois(searchParams, pageNumber, pageSize, sortType, PoiType.NONE);
	}

	@Override
	public Page<LBSPoi> findPois(Map<String, Object> searchParams,int pageNumber, int pageSize, SortType sortType, PoiType poiType) {
		PageRequest pageRequest = this.buildPageRequest(pageNumber, pageSize,sortType);
		Specification<LBSPoi> spec = this.buildSpecification(searchParams,LBSPoi.class);

		return poiPageDao.findAll(spec, pageRequest);
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(logger.isInfoEnabled()){
			 logger.info(PoiServiceImpl.class + " InitializingBean.afterPropertiesSet() running.");
		}
		
		if(poi_unique_KeyPrefix == null || "".equals(poi_unique_KeyPrefix))
			poi_unique_KeyPrefix = syncCachedService.buildKey(CacheKeyPrefix.POI_UNIQUE);		
	}
}

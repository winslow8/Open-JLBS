package com.lanx.app.lbs.location.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.domain.LBSLocation;
import com.lanx.app.lbs.location.LocationPlusService;
import com.lanx.app.lbs.util.LBSConstants;
import com.lanx.app.lbs.util.LBSConstants.ID;
import com.lanx.app.lbs.util.LBSConstants.OperType;
import com.lanx.app.lbs.util.LBSConstants.Pagination;
import com.lanx.app.lbs.util.LBSConstants.SortType;
import com.lanx.app.lbs.util.ReflectUtils;

/**
 * <p>LBS进行定位的附加的service实现类</p>
 * 仅有少数几个常用方法做了cache处理
 * 
 * @author Ramboo
 * @date 2013-09-16
 * */
public class LocationPlusServiceImpl extends AbstractLocationService implements LocationPlusService{

    private static final Log logger = LogFactory.getLog(LocationPlusServiceImpl.class);

	@Override
	public <LocationID extends Serializable> void deleteLocation(LocationID id, ID idType) {
		if(id == null){
			logger.error(LocationPlusServiceImpl.class + " id cannot be null here ! ");			
			return ;			
		}

		List<LocationID> ids = new ArrayList<LocationID>(1);
		ids.add(id);
		
		this.deleteLocations(ids, idType);
	}

	@Override
	public <LocationID extends Serializable> void deleteLocations(Iterable<LocationID> ids, ID idType) {
		if(ids == null){
			logger.error(LocationPlusServiceImpl.class + " ids cannot be null here ! ");			
			return ;			
		}
		long start = System.currentTimeMillis();
		
		List<LBSLocation> locations = new ArrayList<LBSLocation>(1);
		LBSLocation location = null;
		if(ID.UID.equals(idType))
			for(LocationID id : ids){
				location = new LBSLocation();
				location.setUid((Long)id);
				
				locations.add(location);
			}
		else if(ID.DEVICE_ID.equals(idType))
			for(LocationID id : ids){
				location = new LBSLocation();
				location.setDeviceId((String)id);

				locations.add(location);
			}
		
		this.sendJmsDto(locations,idType,OperType.DELETE);

		if(logger.isInfoEnabled())
			logger.info(LocationPlusServiceImpl.class + ": deleteLocations send consume " + (System.currentTimeMillis() - start) + " ms");							
	}
	
	@Override
	public void deleteLocations(Map<String, Object> deleteParams) {
		if(deleteParams == null || (deleteParams.get(LBSConstants.Params.UID) == null && deleteParams.get(LBSConstants.Params.DEVICE_ID) == null)){
			logger.error(LocationPlusServiceImpl.class + " Both uid and deviceId are null ! ");			
			return ;
		}
		
		List<LBSLocation> locations = new ArrayList<LBSLocation>(1);
		LBSLocation location = new LBSLocation();
		ReflectUtils.flushObject(location, deleteParams);
		locations.add(location);
		
		ID idType = location.getUid() == null ? ID.DEVICE_ID : (location.getDeviceId() == null ? ID.UID : ID.ALL);
		this.sendJmsDto(locations, idType,OperType.DELETE);			
	}

	@Override
	public <LocationID extends Serializable> Iterable<LBSLocation> findLocationsByID(LocationID id, ID idType) {
		Page<LBSLocation> locations = this.findLocationsByID(id, idType, 1, Pagination.DEFAULT_MAX_COUNT, SortType.NONE);
		return locations == null ? null : locations.getContent();
	}

	@Override
	public <LocationID extends Serializable> Page<LBSLocation> findLocationsByID(LocationID id, ID idType, int pageNumber, int pageSize,SortType sortType) {
		if(ID.ALL.equals(idType)){
			logger.error(LocationPlusServiceImpl.class + ": idType is error ! Check and modify !");			
			return null;
		}
		
		String key = ID.UID.equals(idType) ? locationUID_List_KeyPrefix + id : locationDEVICE_List_KeyPrefix + id;		
		Page<LBSLocation> locations = syncCachedService.get(key);
		if(locations == null){
			Map<String, Object> searchParams = new HashMap<String, Object>();
			
			String paramkey = ID.UID.equals(idType) ? "eq_uid" : "eq_deviceId";
			//searchParams.put(Operator.EQ + "" + id, id);
			searchParams.put(paramkey, id);
			
			locations = this.findLocations(searchParams, pageNumber, pageSize, sortType);			
			if(locations != null && locations.getSize() > 0){
				//TODO 如果这个列表没有其他操作触发，可以考虑设置失效时间
				syncCachedService.set(key, locations);
			}
		}
		
		return locations;
	}

	@Override
	public Iterable<LBSLocation> findLocations(Map<String, Object> searchParams) {
		Page<LBSLocation> locations = this.findLocations(searchParams, 1, Pagination.DEFAULT_MAX_COUNT, SortType.NONE);
		return locations == null ? null : locations.getContent();
	}

	@Override
	public Page<LBSLocation> findLocations(Map<String, Object> searchParams,int pageNumber, int pageSize, SortType sortType) {
		long start = System.currentTimeMillis();
		
		Page<LBSLocation> pages = this.findAll(searchParams, pageNumber, pageSize, sortType);

		if(logger.isInfoEnabled())
			logger.info(LocationPlusServiceImpl.class + " findLocations consume " + (System.currentTimeMillis() - start) + " ms");
		
		return pages;
	}
}

package com.lanx.app.lbs.location.inner.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanx.app.lbs.core.domain.LBSCheckin;
import com.lanx.app.lbs.location.dao.LocateDao;
import com.lanx.app.lbs.location.inner.LocationInnerService;

/**
 * <p>LBS定位的service内部执行类</p>
 * 
 * @author Ramboo
 * @date 2013-09-16
 * */
public class LocationInnerServiceImpl extends AbstractLocationInnerService implements LocationInnerService{

	private static final Log logger = LogFactory.getLog(LocationInnerServiceImpl.class);

	@Autowired
	private LocateDao locateDao;
		
	@Override
	public void saveLocation(LBSCheckin location) {
		if(this.isCanSave(location))		
			locateDao.save(location);		
	}

	@Override
	public void saveLocation(Iterable<LBSCheckin> locations) {
		//只save符合条件的数据
		if(this.isCanSave((List<LBSCheckin>)locations))
			locateDao.save(locations);
	}
	
	private boolean isCanSave(LBSCheckin location) {
		if(location == null){
			logger.error("Nothing to save location!");
			return false;
		}
		
		Long uid = location.getUid();
		String deviceId = location.getDeviceId();
		String pid = location.getPid();
		
		String longitude = location.getLongitude();
		String latitude = location.getLatitude();
		
		Double longit = location.getLongit();
		Double latit = location.getLatit();
		
		boolean idFlag = uid == null && deviceId == null;
		
		//TODO 这里仔细测试一下
		boolean longlatFlag = (longitude == null || "".equals(longitude)) || (latitude == null || "".equals(latitude));
		boolean pidFlag = (pid == null || "".equals(pid)) && longlatFlag; 
				
		return idFlag && pidFlag;
	}
	
	private boolean isCanSave(List<LBSCheckin> locations) {
		if(locations == null){
			logger.error("Nothing to save location!");
			return false;
		}
		
		//如果发现不符合条件的数据，从列表中删除
		for(int i = 0;i<locations.size();i++) {
			LBSCheckin checkin = locations.get(i);
			if(!isCanSave(checkin)){
				locations.remove(i--);
			}
		}
		
		return locations.size() > 0 ? true : false;
	}

	@Override
	public void deleteLocations(Iterable<LBSCheckin> locations) {
		locateDao.delete(locations);		
	}
}

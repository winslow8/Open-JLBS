package com.lanx.app.lbs.location.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lanx.app.lbs.core.domain.LBSLocation;
import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.location.LocationService;

import static com.lanx.app.lbs.util.LBSConstants.ID;
import static com.lanx.app.lbs.util.LBSConstants.OperType;

/**
 * <p>LBS同步定位的service实现类</p>
 * 
 * @author Ramboo
 * @date 2013-08-24
 * */
public class LocationServiceImpl extends AbstractLocationService implements LocationService{
	
    private static final Log logger = LogFactory.getLog(LocationServiceImpl.class);

	@Override
	public <UID extends Serializable> void saveLocationByUID(UID uid, double longitude, double latitude,Date loctime) {
		this.saveLocation(uid, null, longitude, latitude,loctime);
	}

	@Override
	public <UID extends Serializable> void saveLocationByUID(UID uid, String pid, String poiname,Date loctime) {
		this.saveLocation(uid, null, pid, poiname,loctime);
	}

	@Override
	public <UID extends Serializable> void saveLocationByUID(UID uid, String pid, String poiname,double longitude, double latitude, Date loctime) {
		this.saveLocation(uid, null, pid,poiname, longitude, latitude,loctime);
	}

	@Override
	public <DeviceID extends Serializable> void saveLocationByDeviceID(DeviceID deviceId, double longitude,double latitude, Date loctime) {
		this.saveLocation(null, deviceId, longitude, latitude,loctime);
	}

	@Override
	public <DeviceID extends Serializable> void saveLocationByDeviceID(DeviceID deviceId, String pid,String poiname, Date loctime) {
		this.saveLocation(null, deviceId, pid, poiname,loctime);
	}

	@Override
	public <DeviceID extends Serializable> void saveLocationByDeviceID(DeviceID deviceId, String pid,String poiname, double longitude, double latitude, Date loctime) {
		this.saveLocation(null, deviceId, pid,poiname, longitude, latitude,loctime);	
	}

	@Override
	public <UID extends Serializable, DeviceID extends Serializable> void saveLocation(UID uid, DeviceID deviceId, String pid,String poiname, Date loctime) {
		LBSPoi lbsPoi = lbsCoreService.getLongLatByPid(pid);
		
		LBSLocation lbsLocation = new LBSLocation();
		this.flushLBSLocation(lbsLocation, lbsPoi,(Long)uid,(String)deviceId,loctime);
		
		this.saveLocation(lbsLocation);		
	}

	@Override
	public <UID extends Serializable, DeviceID extends Serializable> void saveLocation(UID uid, DeviceID deviceId, double longitude,double latitude, Date loctime) {
		List<LBSPoi> lbsPois = lbsCoreService.getPoiByLongLat(longitude, latitude);

		LBSLocation lbsLocation = new LBSLocation();
		this.flushLBSLocation(lbsLocation, lbsPois != null ? lbsPois.get(0) : null,(Long)uid,(String)deviceId,loctime);
		
		this.saveLocation(lbsLocation);		
	}
		
	@Override
	public <UID extends Serializable, DeviceID extends Serializable> void saveLocation(UID uid, DeviceID deviceId, String pid,String poiname, double longitude, double latitude, Date loctime) {
		LBSLocation lbsLocation = new LBSLocation();
		lbsLocation.setUid((Long)uid);
		lbsLocation.setDeviceId((String)deviceId);
		lbsLocation.setPid(pid);
		lbsLocation.setPoiname(poiname);
		
		lbsLocation.setLongit(longitude);
		lbsLocation.setLatit(latitude);
		lbsLocation.setLongitude(longitude + "");
		lbsLocation.setLatitude(latitude + "");
		
		lbsLocation.setLoctime(loctime);
		
		this.saveLocation(lbsLocation);
	}

	@Override
    public void saveLocation(final LBSLocation location){
    	if(location == null)
    		return;

		long start = System.currentTimeMillis();

		List<LBSLocation> locations = new ArrayList<LBSLocation>(1);
		locations.add(location);
		this.sendJmsDto(locations, ID.ALL,OperType.SAVE);
		
		if(logger.isInfoEnabled())
			logger.info(LocationServiceImpl.class + "UID -> " + location.getUid() + " and DeviceID -> " + location.getDeviceId() + 
					" saveLocation producer send consume " + (System.currentTimeMillis() - start) + " ms");
    }
    
	@Override
	public void batchSaveLocation(Iterable<LBSLocation> locations){
		if(locations == null)
    		return;

		long start = System.currentTimeMillis();

		this.sendJmsDto(locations, ID.ALL,OperType.SAVE);
		
		if(logger.isInfoEnabled()){
			for(LBSLocation location : locations){
				logger.info(LocationServiceImpl.class + "UID -> " + location.getUid() + " and DeviceID -> " + location.getDeviceId() + " send");		
				
			}
			logger.info(LocationServiceImpl.class + " batchSaveLocation producer send consume " + (System.currentTimeMillis() - start) + " ms");		
		}
	}
	
}

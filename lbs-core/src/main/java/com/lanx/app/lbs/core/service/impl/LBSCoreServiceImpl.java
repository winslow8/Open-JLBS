package com.lanx.app.lbs.core.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.core.service.LBSCoreService;

/**
 * <p>LBS的coreservice的实现类</p>
 * 提供一些公共的方法，例如经纬度和pid互相转换等
 * 
 * @author Ramboo
 * @date 2013-09-09
 * */
public class LBSCoreServiceImpl implements LBSCoreService{
	
	private static final Log logger = LogFactory.getLog(LBSCoreServiceImpl.class);
	
	@Override
	public LBSPoi getLongLatByPid(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] findLongLatByPid(String pid) {
		LBSPoi poi = this.getLongLatByPid(pid);
		return poi == null ? null : new String[]{poi.getLongitude(),poi.getLatitude()};
	}

	@Override
	public List<LBSPoi> getPoiByLongLat(double longitude, double latitude) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<LBSPoi> getPoiByLongLat(double longitude, double latitude,Accuracy accuracy) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

package com.lanx.app.lbs.poi.inner.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.poi.dao.PoiDao;
import com.lanx.app.lbs.poi.inner.PoiInnerService;

/**
 * <p>LBS操作poi的内部执行的service实现类</p>
 * 	 
 * @author Ramboo
 * @date 2013-09-27
 * */
public class PoiInnerServiceImpl implements PoiInnerService {
	private static final Log logger = LogFactory.getLog(PoiInnerServiceImpl.class);
	
	@Autowired
	PoiDao poiDao;
	
	@Override
	public void savePoi(LBSPoi poi) {
		if(poi == null)
			logger.error(PoiInnerServiceImpl.class + " savePoi -> Not found poi!");
		
		poiDao.save(poi);		
	}

	@Override
	public void savePoi(Iterable<LBSPoi> pois) {
		if(pois == null)
			logger.error(PoiInnerServiceImpl.class + " savePoi -> Not found pois!");
		
		poiDao.save(pois);		
	}

	@Override
	public void deletePoi(Iterable<LBSPoi> pois) {
		if(pois == null)
			logger.error(PoiInnerServiceImpl.class + " deletePoi -> Not found pois!");

		poiDao.delete(pois);
	}
}

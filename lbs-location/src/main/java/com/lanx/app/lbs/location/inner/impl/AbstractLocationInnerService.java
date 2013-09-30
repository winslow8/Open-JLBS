package com.lanx.app.lbs.location.inner.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanx.app.lbs.core.service.LBSCoreService;
import com.lanx.base.cache.service.SyncCachedService;

/**
 * <p>LBS定位的service抽象类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */
public class AbstractLocationInnerService implements InitializingBean{
	
	private static final Log logger = LogFactory.getLog(AbstractLocationInnerService.class);
	
	@Autowired
	SyncCachedService syncCachedService;

	@Override
	public void afterPropertiesSet() throws Exception {
		if(logger.isInfoEnabled()){
			 logger.info(AbstractLocationInnerService.class + " InitializingBean.afterPropertiesSet() running.");
		}		
	}
}

package com.lanx.app.lbs.location.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanx.app.lbs.core.domain.LBSCheckin;
import com.lanx.app.lbs.core.domain.LBSLocation;
import com.lanx.app.lbs.core.jms.JmsDto;
import com.lanx.app.lbs.location.inner.LocationInnerService;
import com.lanx.app.lbs.util.LBSConstants.OperType;

/**
 * 定位save，delete队列执行操作的helper类
 * 
 * @author Ramboo
 * @date 2013-09-16
 */
public class JmsHelper {
    private static final Log logger = LogFactory.getLog(JmsHelper.class);

	@Autowired
	LocationInnerService locationInnerService;
	
	public void process(ObjectMessage objMsg) throws JMSException {
		@SuppressWarnings("unchecked")
		JmsDto<LBSLocation> jmsDto = (JmsDto<LBSLocation>)objMsg.getObject();
		if(jmsDto != null){
			List<LBSLocation> lbsLocations = (List<LBSLocation>)jmsDto.getLbs();
			if(lbsLocations == null || lbsLocations.size() == 0){
				logger.error(JmsHelper.class + " Not found Iterable<LBSLocation> !");
				return;
			}
			
			List<LBSCheckin> checkins = new ArrayList<LBSCheckin>(lbsLocations.size());
			this.flushLBSCheckin(checkins, lbsLocations);
			
			OperType operType = jmsDto.getOperType();
			if(OperType.SAVE.equals(operType))
				locationInnerService.saveLocation(checkins);
			else if(OperType.DELETE.equals(operType))
				locationInnerService.deleteLocations(checkins);
			
		}else {
			logger.error(JmsHelper.class + " Not found jmsDto!");
		}		
	}
	
	private void flushLBSCheckin(List<LBSCheckin> checkins,List<LBSLocation> lbsLocations) {
		LBSCheckin checkin = null;
		for(LBSLocation location : lbsLocations){
			checkin = new LBSCheckin();
			checkin.setUid(location.getUid());
			checkin.setDeviceId(location.getDeviceId());			
			checkin.setPid(location.getPid());
			checkin.setPoiname(location.getPoiname());
			
			checkin.setLongitude(location.getLongitude());
			checkin.setLatitude(location.getLatitude());
			checkin.setLongit(location.getLongit());
			checkin.setLatit(location.getLatit());
			
			checkin.setCitycode(location.getCitycode());
			checkin.setProvincecode(location.getProvincecode());
			checkin.setCountrycode(location.getCountrycode());
			
			checkin.setLoctime(location.getLoctime());
			
			//这几个是定位信息固定的数据
			checkin.setSource(1);
			checkin.setUpdatetime(location.getUpdatetime());
			//checkin.setLocateType(locateType);
			//checkin.setPrivacy(privacy);

			checkins.add(checkin);
		}
	}	
}

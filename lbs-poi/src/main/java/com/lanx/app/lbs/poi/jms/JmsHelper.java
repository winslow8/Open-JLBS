package com.lanx.app.lbs.poi.jms;

import java.util.Date;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.core.jms.JmsDto;
import com.lanx.app.lbs.poi.inner.PoiInnerService;
import com.lanx.app.lbs.util.Geohash;
import com.lanx.app.lbs.util.LBSConstants.OperType;

/**
 * poi的save，delete队列执行操作的helper类
 * 
 * @author Ramboo
 * @date 2013-09-28
 */
public class JmsHelper {
    private static final Log logger = LogFactory.getLog(JmsHelper.class);

	@Autowired
	PoiInnerService poiInnerService;
	
	public void process(ObjectMessage objMsg) throws JMSException {
		@SuppressWarnings("unchecked")
		JmsDto<LBSPoi> jmsDto = (JmsDto<LBSPoi>)objMsg.getObject();
		if(jmsDto != null){
			List<LBSPoi> pois = (List<LBSPoi>)jmsDto.getLbs();
			if(pois == null || pois.size() == 0){
				logger.error(JmsHelper.class + " Not found Iterable<LBSPoi> !");
				return;
			}
			
			OperType operType = jmsDto.getOperType();
			if(OperType.SAVE.equals(operType)){
				this.flushLBSPoi(pois);				
				poiInnerService.savePoi(pois);
			}else if(OperType.DELETE.equals(operType))
				poiInnerService.deletePoi(pois);
			
		}else {
			logger.error(JmsHelper.class + " Not found jmsDto!");
		}		
	}
	
	/**
	 * 在flush LBSPoi的时候就将没有经纬度的数据剔除了
	 * 
	 * @param pois
	 */
	private void flushLBSPoi(List<LBSPoi> pois) {
		for(int i = 0 ; i< pois.size();i++){
			LBSPoi poi = pois.get(i);
			
			String longitude = poi.getLongitude();
			String latitude = poi.getLatitude();
			Double longit = poi.getLongit();
			Double latit = poi.getLatit();
			if(!existLongLat(longitude,latitude,longit,latit)){
				pois.remove(i--);
				continue;
			}

			poi.setGeohash(Geohash.encode(latit, longit));
			
			Date loctime = poi.getLoctime();
			poi.setLoctime(loctime == null ? new Date() : loctime);
			
			//这几个是poi信息固定的数据
			poi.setLocateType(1);
			poi.setAddtime(new Date());
			poi.setUpdatetime(poi.getUpdatetime());
		}
	}	
	
	//TODO 这个判断今后还得修订
	private boolean existLongLat(String longitude,String latitude,double longit,double latit){
		if(longitude == null || "".equals(longitude) || latitude == null || "".equals(latitude) || longit == 0 || latit == 0)
			return false;
		
		return true;
	}
}

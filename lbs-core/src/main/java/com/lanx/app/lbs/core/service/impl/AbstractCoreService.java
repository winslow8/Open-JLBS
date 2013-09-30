package com.lanx.app.lbs.core.service.impl;

import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.lanx.app.lbs.core.domain.LBSEntity;
import com.lanx.app.lbs.core.jms.JmsDto;
import com.lanx.app.lbs.core.persistence.DynamicSpecifications;
import com.lanx.app.lbs.core.persistence.SearchFilter;
import com.lanx.base.jms.activemq.ActiveMQJMSProducer;

import static com.lanx.app.lbs.util.LBSConstants.ID;
import static com.lanx.app.lbs.util.LBSConstants.OperType;
import static com.lanx.app.lbs.util.LBSConstants.SortType;

public abstract class AbstractCoreService {
	private static final Log logger = LogFactory.getLog(AbstractCoreService.class);
	
	@Autowired
	ActiveMQJMSProducer producer;
	
	/**
	 * 创建分页请求.
	 */
	protected PageRequest buildPageRequest(int pageNumber, int pagzSize,SortType sortType) {
		//TODO not finished yet
		Sort sort = null;
		if(sortType != null && !SortType.NONE.equals(sortType)){
			if (SortType.CHECKIN_TIME_DESC.equals(sortType)) {//lbs_checkin专用
				sort = new Sort(Direction.DESC, "checkintime");
			}else if (SortType.CHECKIN_TIME_ASC.equals(sortType)) {//lbs_checkin专用
				sort = new Sort(Direction.ASC, "checkintime");
			}else if (SortType.LOC_TIME_ASC.equals(sortType)) {//lbs_poi专用
				sort = new Sort(Direction.ASC, "loctime");
			}else if (SortType.LOC_TIME_DESC.equals(sortType)) {//lbs_poi专用
				sort = new Sort(Direction.ASC, "loctime");
			}
			else if ("title".equals(sortType)) {
				sort = new Sort(Direction.ASC, "title");
			} 			
		}
		
		return SortType.NONE.equals(sortType) ? new PageRequest(pageNumber - 1, pagzSize) : new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	protected <T> Specification<T> buildSpecification(Map<String, Object> searchParams,Class<T> entityClazz) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		
//		if(uid != null && !"".equals(uid))
//			filters.put("uid", new SearchFilter("uid", Operator.EQ, uid));
//		
//		if(deviceId != null && !"".equals(deviceId))
//			filters.put("deviceid", new SearchFilter("deviceid", Operator.EQ, deviceId));
		
		return DynamicSpecifications.bySearchFilter(filters.values(), entityClazz);
	}

	//TODO not finished yet，有两个枚举的判断条件，好像只做了前面的idtype，后来发现不用根据idtype判断怎么设置值，直接全部设置即可	
	protected <T extends LBSEntity> void sendJmsDto(Iterable<T> lbsLocations,ID idType,OperType operType) {
		JmsDto<T> jmsDto = new JmsDto<T>();
		
		jmsDto.setLbs(lbsLocations);
		jmsDto.setIdType(idType);
		jmsDto.setOperType(operType);
		
		jmsDto.setTime(new Date());
		
		producer.send(jmsDto);
		
		if(logger.isInfoEnabled())
			logger.info(AbstractCoreService.class + "send jmsDto ! ");
	}

}

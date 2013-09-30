package com.lanx.app.lbs.location;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.domain.LBSLocation;
import com.lanx.app.lbs.util.LBSConstants.ID;
import com.lanx.app.lbs.util.LBSConstants.SortType;

/**
 * <p>LBS进行定位的附加的service</p>
 * 
 * @author Ramboo
 * @date 2013-09-16
 * */
public interface LocationPlusService {
	
	/**
	 * 根据标识id删除定位数据，可能是批量删除
	 * 
	 * @param id
	 * @param idType
	 */
	public <LocationID extends Serializable> void deleteLocation(final LocationID id, final ID idType);
	
	/**
	 * 根据设置的条件删除定位数据
	 * 由于这个方法可以设置条件一次删除多条数据，同时还无法定位uid，deviceId，因此不建议使用
	 * 
	 * @param deleteParams
	 */
	@Deprecated
	public void deleteLocations(final Map<String, Object> deleteParams);

	/**
	 * 根据标识id集合删除定位数据，是批量删除
	 * 
	 * @param ids
	 * @param idType
	 */
	public <LocationID extends Serializable> void deleteLocations(final Iterable<LocationID> ids, final ID idType);
	
	/**
	 * 根据标识id查找定位数据集合，注意是定位数据，并不是checkin数据
	 * 
	 * @param id
	 * @param idType
	 * @return
	 */
	public <LocationID extends Serializable> Iterable<LBSLocation> findLocationsByID(final LocationID id, final ID idType);
	
	/**
	 * 根据标识id分页查找定位数据集合，注意是定位数据，并不是checkin数据
	 * 
	 * @param id
	 * @param idType
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public <LocationID extends Serializable> Page<LBSLocation> findLocationsByID(final LocationID id,final ID idType,final int pageNumber,final int pageSize,final SortType sortType);
	
	/**
	 * 根据设置的条件查找定位数据集合的分页数据，注意是定位数据，并不是checkin数据
	 * 
	 * @param searchParams
	 * @return
	 */
	public Iterable<LBSLocation> findLocations(final Map<String, Object> searchParams);
	
	/**
	 * 根据设置的条件分页查找定位数据集合的分页数据，注意是定位数据，并不是checkin数据
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<LBSLocation> findLocations(final Map<String, Object> searchParams,final int pageNumber,final int pageSize,final SortType sortType);	
}


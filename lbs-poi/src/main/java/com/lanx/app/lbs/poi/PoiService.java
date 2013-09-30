package com.lanx.app.lbs.poi;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.domain.LBSPoi;
import com.lanx.app.lbs.util.LBSConstants.ID;
import com.lanx.app.lbs.util.LBSConstants.PoiType;
import com.lanx.app.lbs.util.LBSConstants.SortType;

/**
 * <p>LBS操作poi的service接口</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */
public interface PoiService {
	/**
	 * 添加或者修改单条LBSPoi数据
	 * 经纬度必须设置
	 * 
	 * @param lbsPoi
	 */
	public void savePoi(final LBSPoi lbsPoi);
	
	/**
	 * 批量添加或者修改LBSPoi数据
	 * 经纬度必须设置
	 * 
	 * @param lbsPois
	 */
	public void savePoi(final Iterable<LBSPoi> lbsPois);
	
	/**
	 * 根据条件删除LBSPoi数据，不一定只删除单条数据
	 * 
	 * @param lbsPoi
	 */
	public void deletePoi(final LBSPoi lbsPoi);
	
	/**
	 * 根据pid唯一标识集合批量删除LBSPoi数据
	 * 
	 * @param pids
	 */
	public void deletePoi(final Iterable<String> pids);
	
	/**
	 * 根据主键或者唯一标识查找LBSPoi数据
	 * 
	 * @param id 
	 * @param idType 指出前面输入的是何种唯一标识
	 * @return
	 */
	public <UNIQUE extends Serializable> LBSPoi findPoi(UNIQUE id, ID idType);
	
	/**
	 * 根据主键或者唯一标识查找LBSPoi数据集合
	 * 
	 * @param id
	 * @param idType
	 * @return
	 */
	public Iterable<LBSPoi> findPoi(final Iterable<Serializable> ids,final ID idType);
	
	/**
	 * 根据条件查找LBSPoi数据集合，注意这不是查找附近，此方法不做缓存
	 * 
	 * @param searchParams
	 * @return
	 */
	public Iterable<LBSPoi> findPois(final Map<String, Object> searchParams);
	
	/**
	 * 根据条件分页查找LBSPoi数据集合，注意这不是查找附近，此方法不做缓存
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<LBSPoi> findPois(final Map<String, Object> searchParams,final int pageNumber,final int pageSize,final SortType sortType);
	
	/**
	 * 根据条件分页查找分类LBSPoi数据集合，注意这不是查找附近，此方法不做缓存
	 * 
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType 排序规则
	 * @param poiType 
	 * @return
	 */
	public Page<LBSPoi> findPois(final Map<String, Object> searchParams,final int pageNumber,final int pageSize,final SortType sortType,final PoiType poiType);
}


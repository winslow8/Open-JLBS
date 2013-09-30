package com.lanx.app.lbs.neighbor;

import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.Accuracy;
import com.lanx.app.lbs.core.domain.LBSPoi;

import static com.lanx.app.lbs.util.LBSConstants.PoiType;
import static com.lanx.app.lbs.util.LBSConstants.SortType;

/**
 * <p>LBS进行查找附近poi的service</p>
 * 考虑在列表中给出附近x公里，x米的功能，momo等都有这个功能
 * 
 * @author Ramboo
 * @date 2013-09-23
 */
public interface NearPoiService {

	/**
	 * 根据经纬度查找附近的poi数据集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public Iterable<LBSPoi> findNearPois(final double longitude,final double latitude);

	/**
	 * 根据经纬度查找附近的poi数据集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy 精度，可选择半径1千米，500米，100米等几种选项
	 * @return
	 */
	public Iterable<LBSPoi> findNearPois(final double longitude,final double latitude,final Accuracy accuracy);

	/**
	 * 根据经纬度分页查找附近的poi数据集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy
	 * @param pageNo
	 * @param pageSize
	 * @param sortType	结果集排序方式
	 * @param poiType   
	 * @return
	 */
	public Page<LBSPoi> findNearPois(final double longitude,final double latitude,final Accuracy accuracy,
			final int pageNo,final int pageSize,final SortType sortType,final PoiType poiType);
	
	/**
	 * 根据pid查找附近的poi集合
	 * 
	 * @param pid
	 * @return
	 */
	public Iterable<LBSPoi> findNearPois(final String pid);
	
	/**
	 * 根据pid查找附近的poi集合
	 * 
	 * @param pid
	 * @param accuracy
	 * @return
	 */
	public Iterable<LBSPoi> findNearPois(final String pid,final Accuracy accuracy);
	
	/**
	 * 根据pid页查找附近的poi数据集合
	 * 
	 * @param pid
	 * @param accuracy
	 * @param pageNo
	 * @param pageSize
	 * @param sortType	结果集排序方式
	 * @param poiType   
	 * @return
	 */
	public Page<LBSPoi> findNearPois(final String pid,final Accuracy accuracy,final int pageNo,final int pageSize,final SortType sortType,final PoiType poiType);
}


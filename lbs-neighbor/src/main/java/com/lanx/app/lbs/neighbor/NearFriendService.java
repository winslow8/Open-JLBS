package com.lanx.app.lbs.neighbor;

import java.io.Serializable;

import org.hsqldb.rights.User;
import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.Accuracy;
import com.lanx.app.lbs.util.LBSConstants.SortType;

/**
 * <p>LBS查找附近好友的service</p>
 * 
 * @author Ramboo
 * @date 2013-09-23
 */
public interface NearFriendService {

	/**
	 * 根据经纬度查找附近的好友uid集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(final double longitude,final double latitude);
	
	/**
	 * 根据经纬度查找附近的好友uid集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy  精度，可选择半径1千米，500米，100米等几种选项
	 * @return
	 */
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(final double longitude,final double latitude,final Accuracy accuracy);

	/**
	 * 根据经纬度分页查找附近的好友uid集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <UID extends Serializable> Page<UID> findNearFriendUids(final double longitude,final double latitude,final Accuracy accuracy,
			final int pageNo,final int pageSize);
	
	/**
	 * 根据pid查找附近的好友uid集合
	 * 
	 * @param pid
	 * @return
	 */
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(final String pid);
	
	/**
	 * 根据pid查找附近的好友uid集合
	 * 
	 * @param pid
	 * @param accuracy  精度，可选择半径1千米，500米，100米等几种选项
	 * @return
	 */
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(final String pid,final Accuracy accuracy);
	
	/**
	 * 根据pid分页查找附近的好友uid集合
	 * 
	 * @param pid
	 * @param accuracy
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public <UID extends Serializable> Page<UID> findNearFriendUids(final String pid,final Accuracy accuracy,final int pageNo,final int pageSize);

	/**
	 * 根据经纬度查找附近的好友User类集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @return
	 */
	public Iterable<User> findNearFriends(final double longitude,final double latitude);
	
	/**
	 * 根据经纬度查找附近的好友User类集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy  精度，可选择半径1千米，500米，100米等几种选项
	 * @return
	 */
	public Iterable<User> findNearFriends(final double longitude,final double latitude,final Accuracy accuracy);

	/**
	 * 根据经纬度分页查找附近的好友User集合
	 * 
	 * @param longitude
	 * @param latitude
	 * @param accuracy
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<User> findNearFriends(final double longitude,final double latitude,final Accuracy accuracy,
			final int pageNo,final int pageSize,final SortType sortType);

	/**
	 * 根据pid查找附近的好友User集合
	 * 
	 * @param pid
	 * @return
	 */
	public Iterable<User> findNearFriends(final String pid);

	/**
	 * 根据pid查找附近的好友User集合
	 * 
	 * @param pid
	 * @param accuracy
	 * @return
	 */
	public Iterable<User> findNearFriends(final String pid,final Accuracy accuracy);

	/**
	 * 根据pid分页查找附近的好友User集合
	 * 
	 * @param pid
	 * @param accuracy
	 * @param pageNo
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<User> findNearFriends(final String pid,final Accuracy accuracy,final int pageNo,final int pageSize,final SortType sortType);
}


package com.lanx.app.lbs.location.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lanx.app.lbs.core.domain.LBSLocation;

/**
 * <p>LBSLocation的Dao接口</p>
 * sql语句基本都使用分表查询
 * 
 * @author Ramboo
 * @date 2013-09-12
 *
 */
public interface LocatePlusDao extends PagingAndSortingRepository<LBSLocation, Serializable>, JpaSpecificationExecutor<LBSLocation> {
	static final String delete_sql = "delete from lbs_checkin ";

	static final String select_sql = "SELECT a.id,a.uid,a.deviceid,a.pid,a.poiname,a.checkintime,a.longitude,a.latitude,a.locate_type,a.gossip,a.countrycode,a.provincecode," +
			"a.citycode,a.updatetime FROM lbs_checkin a INNER JOIN (SELECT id FROM lbs_checkin ";
	
	static final String select_suffix = " order by checkintime desc limit 2) AS x USING(id)";
	
    @Modifying
    @Query(delete_sql + " WHERE uid = ?1 AND locate_type = 1")
    public void deleteLocationByUID(Serializable uid);

    @Modifying
    @Query(delete_sql + " WHERE uid = ?1 AND pid = ?2 AND locate_type = 1")
    public void deleteLocationByUID(Serializable uid, String pid);

    @Modifying
    @Query(delete_sql + " WHERE deviceid = ?1 AND locate_type = 1")
    public void deleteLocationByDeviceID(Serializable deviceId);
    
    @Modifying
    @Query(delete_sql + " WHERE deviceid = ?1 AND pid = ?2 AND locate_type = 1")
    public void deleteLocationByDeviceID(Serializable deviceId, String pid);
    
    @Modifying
    @Query(delete_sql + " WHERE uid = ?1 AND deviceid = ?2 AND locate_type = 1")
    public void deleteLocation(Serializable uid,Serializable deviceId);

    @Modifying
    @Query(delete_sql + " WHERE uid = ?1 AND deviceid = ?2 AND pid = ?3 AND locate_type = 1")
    public void deleteLocation(Serializable uid,Serializable deviceId, String pid);

}

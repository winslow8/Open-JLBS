package com.lanx.app.lbs.location.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lanx.app.lbs.core.domain.LBSCheckin;

/**
 * <p>LBSCheckin的Dao接口</p>
 * sql语句基本都使用分表查询
 * 
 * @author Ramboo
 * @date 2013-09-12
 *
 */
//@DAO
public interface LocateDao extends PagingAndSortingRepository<LBSCheckin, Serializable> {
	static final String select_sql = "SELECT a.id,a.uid,a.deviceid,a.pid,a.poiname,a.checkintime,a.longitude,a.latitude,a.locate_type,a.gossip,a.countrycode,a.provincecode," +
			"a.citycode,a.updatetime FROM lbs_checkin a INNER JOIN (SELECT id FROM lbs_checkin ";
	
	static final String insert_sql = "insert ignore into lbs_checkin ";
	
	static final String select_suffix = " order by checkintime desc limit 2) AS x USING(id)";
	
    //@Modifying
    @Query(select_sql + " WHERE uid = ?1 AND deviceid = ?2 AND locate_type = 1" + select_suffix)
    public List<LBSCheckin> locate(Serializable uid, Serializable deviceId);

    @Query(select_sql + " WHERE uid = ?1 AND locate_type = 1" + select_suffix)
    public List<LBSCheckin> locateByUID(Serializable uid);

    @Query(select_sql + " WHERE deviceid = ?1 AND locate_type = 1" + select_suffix)
    public List<LBSCheckin> locateByDeviceID(Serializable deviceId);

    
    //TODO 缺少向POINT字段insert
    @Modifying
    @Query(insert_sql + "(uid,pid,poiname,longitude,latitude,checkintime,source,locate_type,updatetime) values(?1,?2,?3,?4,?5,?6,1,1,now())")
    public int saveLocationByUID(Serializable uid, String pid, String poiname,String longitude, String latitude, Date loctime);
    
    @Modifying
    @Query(insert_sql + "(uid,pid,poiname,longitude,latitude,checkintime,countrycode,provincecode,citycode,source,locate_type,updatetime) " +
    		"values(?1,?2,?3,?4,?5,?6,?7,?8,?9,1,1,now())")
    public int saveLocationByUID(Serializable uid, String pid, String poiname,String longitude, String latitude, Date loctime,
    		String countrycode,String provincecode,String citycode);
        
    @Modifying
    @Query(insert_sql + "(deviceid,pid,poiname,longitude,latitude,checkintime,source,locate_type,updatetime) values(?1,?2,?3,?4,?5,?6,1,1,now())")
    public int saveLocationByDeviceID(Serializable deviceId, String pid, String poiname,String longitude, String latitude, Date loctime);
    
    @Modifying
    @Query(insert_sql + "(deviceid,pid,poiname,longitude,latitude,checkintime,countrycode,provincecode,citycode,source,locate_type,updatetime) " +
    		"values(?1,?2,?3,?4,?5,?6,?7,?8,?9,1,1,now())")
    public int saveLocationByDeviceID(Serializable deviceId, String pid, String poiname,String longitude, String latitude, Date loctime,
    		String countrycode,String provincecode,String citycode);
    
    @Modifying    
    @Query(insert_sql + "(uid,deviceid,pid,poiname,longitude,latitude,checkintime,source,locate_type,updatetime) values(?1,?2,?3,?4,?5,?6,?7,1,1,now())")
    public int saveLocation(Serializable uid,Serializable deviceId, String pid, String poiname,String longitude, String latitude, Date loctime);
    
    @Modifying
    @Query(insert_sql + "(uid,deviceid,pid,poiname,longitude,latitude,checkintime,countrycode,provincecode,citycode,source,locate_type,updatetime) " +
    		"values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,1,1,now())")
    public int saveLocation(Serializable uid,Serializable deviceId, String pid, String poiname,String longitude, String latitude, Date loctime,
    		String countrycode,String provincecode,String citycode);
    
    
    Page<LBSCheckin> findByUserId(Serializable id, Pageable pageRequest);

}

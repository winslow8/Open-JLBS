package com.lanx.app.lbs.neighbor.dao;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lanx.app.lbs.core.domain.LBSCheckin;

/**
 * <p>查找附近用户的Dao接口</p>
 * sql语句基本都使用分表查询
 * 
 * @author Ramboo
 * @date 2013-09-24
 *
 */
public interface NeighborDao extends PagingAndSortingRepository<LBSCheckin, Serializable>, JpaSpecificationExecutor<LBSCheckin> { 
	
	@Query("select uid from lbs_checkin checkin where checkin.geohash like ?1") 
	Page<Long> findNearUids(String geohash, Pageable pageable);
	
//	@Query("select a from AccountInfo a where a.balance > ?1") 
//	 public Page<AccountInfo> findByBalanceGreaterThan( 
//	 Integer balance,Pageable pageable); 
//	 } 
}

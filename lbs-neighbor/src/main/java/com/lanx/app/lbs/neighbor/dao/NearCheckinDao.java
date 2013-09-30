package com.lanx.app.lbs.neighbor.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lanx.app.lbs.core.domain.LBSCheckin;
/**
 * <p>查找附近LBSCheckin的Dao接口</p>
 * sql语句基本都使用分表查询
 * 
 * @author Ramboo
 * @date 2013-09-24
 *
 */
public interface NearCheckinDao extends PagingAndSortingRepository<LBSCheckin, Serializable>, JpaSpecificationExecutor<LBSCheckin> { 

}

package com.lanx.app.lbs.poi.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lanx.app.lbs.core.domain.LBSPoi;

/**
 * <p>LBS操作poi的dao接口</p>
 * 
 * @author Ramboo
 * @date 2013-09-27
 * */
public interface PoiPageDao extends PagingAndSortingRepository<LBSPoi, Serializable>, JpaSpecificationExecutor<LBSPoi> { 

}


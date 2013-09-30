package com.lanx.app.lbs.poi.dao;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.lanx.app.lbs.core.domain.LBSPoi;

/**
 * <p>LBS操作poi的dao接口</p>
 * 
 * @author Ramboo
 * @date 2013-09-27
 * */
public interface PoiDao extends CrudRepository<LBSPoi, Serializable> { 

}


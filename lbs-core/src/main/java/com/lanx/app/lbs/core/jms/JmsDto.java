package com.lanx.app.lbs.core.jms;

import java.io.Serializable;
import java.util.Date;

import com.lanx.app.lbs.core.domain.LBSEntity;
import com.lanx.app.lbs.util.LBSConstants.ID;
import com.lanx.app.lbs.util.LBSConstants.OperType;

/**
 * <p>Jms的传输类</p>
 * 
 * @author Ramboo
 * @date 2013-09-15
 * */
public class JmsDto<T extends LBSEntity> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2269853391679735773L;
	
//	private Long uid;
//	private String deviceId;
	private Iterable<T> lbs;
	
	private ID idType;
	private OperType operType;
	
	private Date time;
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public OperType getOperType() {
		return operType;
	}
	public void setOperType(OperType operType) {
		this.operType = operType;
	}
	public ID getIdType() {
		return idType;
	}
	public void setIdType(ID idType) {
		this.idType = idType;
	}
	public Iterable<T> getLbs() {
		return lbs;
	}
	public void setLbs(Iterable<T> lbs) {
		this.lbs = lbs;
	}
}

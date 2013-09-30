package com.lanx.app.lbs.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>LBS的base实体类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */
public class LBSEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5187058889489919460L;
	
	private int id;
	private Date updatetime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}	
}

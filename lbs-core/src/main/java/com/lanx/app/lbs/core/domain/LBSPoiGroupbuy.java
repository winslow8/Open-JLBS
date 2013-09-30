package com.lanx.app.lbs.core.domain;

import java.util.Date;

/**
 * <p>lbs_poi_groupbuy的实体类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */

public class LBSPoiGroupbuy extends LBSEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 109480925853292753L;
	
	private String pid;
	private String poiname;
	
	/**
	 * 定位或者签到时间
	 */
	private Date loctime;

	/**
	 * 用字符串表示的经度
	 */
	private String longitude;
	/**
	 * 用字符串表示的维度
	 */
	private String latitude;
	
	/**
	 * 用双精度double表示的经度
	 */
	private Double longit;
	/**
	 * 用双精度double表示的维度
	 */
	private Double latit;
		
	private String activityname;
	private String activityurl;
	private int activitytype;
	private int origintype;
	
	private String intro;
	private String photourl;
	private Date starttime;
	private Date expiretime;
	
	private int adduid;
	private Date updatetime;
	private int isdeleted;
	//long_lat		point NOT NULL
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPoiname() {
		return poiname;
	}
	public void setPoiname(String poiname) {
		this.poiname = poiname;
	}
	public Date getLoctime() {
		return loctime;
	}
	public void setLoctime(Date loctime) {
		this.loctime = loctime;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public Double getLongit() {
		return longit;
	}
	public void setLongit(Double longit) {
		this.longit = longit;
	}
	public Double getLatit() {
		return latit;
	}
	public void setLatit(Double latit) {
		this.latit = latit;
	}
	public String getActivityname() {
		return activityname;
	}
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}
	public String getActivityurl() {
		return activityurl;
	}
	public void setActivityurl(String activityurl) {
		this.activityurl = activityurl;
	}
	public int getActivitytype() {
		return activitytype;
	}
	public void setActivitytype(int activitytype) {
		this.activitytype = activitytype;
	}
	public int getOrigintype() {
		return origintype;
	}
	public void setOrigintype(int origintype) {
		this.origintype = origintype;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getExpiretime() {
		return expiretime;
	}
	public void setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
	}
	public int getAdduid() {
		return adduid;
	}
	public void setAdduid(int adduid) {
		this.adduid = adduid;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	

}

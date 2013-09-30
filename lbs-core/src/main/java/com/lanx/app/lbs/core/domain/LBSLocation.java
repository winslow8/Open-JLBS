package com.lanx.app.lbs.core.domain;

import java.util.Date;

/**
 * <p>LBSLocation定位的实体类</p>
 * 不对应表
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */

public class LBSLocation extends LBSEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6385561760023644786L;
	
	private Long uid;
	private String deviceId;
	
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
		
	private String countrycode;
	private String provincecode;
	private String citycode;
	
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
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
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getProvincecode() {
		return provincecode;
	}
	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

}

package com.lanx.app.lbs.core.domain;


/**
 * <p>lbs_checkin的实体类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */

public class LBSCheckin extends LBSLocation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4870088566881520896L;

	private int privacy;
	//private Date checkintime;
	
	private int source;
	private int locateType;
	private String appid;
	private String gossip;
	private String geohash;
	//long_lat		point NOT NULL

	public String getGeohash() {
		return geohash;
	}
	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
//	public Date getCheckintime() {
//		return checkintime;
//	}
//	public void setCheckintime(Date checkintime) {
//		this.checkintime = checkintime;
//	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getLocateType() {
		return locateType;
	}
	public void setLocateType(int locateType) {
		this.locateType = locateType;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getGossip() {
		return gossip;
	}
	public void setGossip(String gossip) {
		this.gossip = gossip;
	}
}

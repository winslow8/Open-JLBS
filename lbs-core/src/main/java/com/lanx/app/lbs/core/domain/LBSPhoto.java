package com.lanx.app.lbs.core.domain;

/**
 * <p>lbs_photo的实体类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */

public class LBSPhoto extends LBSEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4095518868559745003L;
	
	private int checkinId;
	private int uid;
	private String deviceId;
	private String pid;
	private String photoname;
	
	private String sourceurl;
	private String photourl;
	private String suffix;
	private String photosize;
	private String intro;	
	private int isdeleted;
	
	public int getCheckinId() {
		return checkinId;
	}
	public void setCheckinId(int checkinId) {
		this.checkinId = checkinId;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
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
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public String getSourceurl() {
		return sourceurl;
	}
	public void setSourceurl(String sourceurl) {
		this.sourceurl = sourceurl;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getPhotosize() {
		return photosize;
	}
	public void setPhotosize(String photosize) {
		this.photosize = photosize;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

}


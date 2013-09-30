package com.lanx.app.lbs.core.domain;

/**
 * <p>lbs_flow的实体类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */

public class LBSFlow extends LBSEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4307076321966194375L;
	
	private int recordeduid;
	private String deviceId;
	private String appid;
	private String intro;
	
	public int getRecordeduid() {
		return recordeduid;
	}
	public void setRecordeduid(int recordeduid) {
		this.recordeduid = recordeduid;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}

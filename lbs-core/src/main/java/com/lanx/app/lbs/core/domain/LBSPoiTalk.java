package com.lanx.app.lbs.core.domain;

/**
 * <p>lbs_poi_talk的实体类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */
public class LBSPoiTalk extends LBSEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1494308010016223396L;
	
	private String pid;
	private String discuss;
	
	private int adduid;
	private int isdeleted;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDiscuss() {
		return discuss;
	}
	public void setDiscuss(String discuss) {
		this.discuss = discuss;
	}
	public int getAdduid() {
		return adduid;
	}
	public void setAdduid(int adduid) {
		this.adduid = adduid;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}

}

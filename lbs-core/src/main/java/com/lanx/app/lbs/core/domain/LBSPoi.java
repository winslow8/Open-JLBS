package com.lanx.app.lbs.core.domain;

import java.util.Date;

/**
 * <p>lbs_poi的实体类</p>
 * 
 * @author Ramboo
 * @date 2013-09-02
 * */

public class LBSPoi extends LBSEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1970555783026491066L;
	
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
		
	private String reviseLongitude;
	private String reviseLatitude;
	private Date reviseTime;

	private String address;
	private String phone;
	private String country;
	private String countrycode;
	private String province;
	private String provincecode;
	private String city;
	private String citycode;
	
	private String district;
	private String street;
	private String note;
	private int poiType;
	private int locateType;
	private int source;
	
	private Date addtime;
	private int adduid;
	private int candidate;
	private String geohash;
	private int isdeleted;
	
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
	public String getReviseLongitude() {
		return reviseLongitude;
	}
	public void setReviseLongitude(String reviseLongitude) {
		this.reviseLongitude = reviseLongitude;
	}
	public String getReviseLatitude() {
		return reviseLatitude;
	}
	public void setReviseLatitude(String reviseLatitude) {
		this.reviseLatitude = reviseLatitude;
	}
	public Date getReviseTime() {
		return reviseTime;
	}
	public void setReviseTime(Date reviseTime) {
		this.reviseTime = reviseTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountrycode() {
		return countrycode;
	}
	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvincecode() {
		return provincecode;
	}
	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getPoiType() {
		return poiType;
	}
	public void setPoiType(int poiType) {
		this.poiType = poiType;
	}
	public int getLocateType() {
		return locateType;
	}
	public void setLocateType(int locateType) {
		this.locateType = locateType;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public int getAdduid() {
		return adduid;
	}
	public void setAdduid(int adduid) {
		this.adduid = adduid;
	}
	public int getCandidate() {
		return candidate;
	}
	public void setCandidate(int candidate) {
		this.candidate = candidate;
	}
	public String getGeohash() {
		return geohash;
	}
	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}
	public int getIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(int isdeleted) {
		this.isdeleted = isdeleted;
	}
	
}


package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BaArea {

	@Id
	@Column(name="area_id")
	private String areaId;
	@Column(name="area_name")
	private String areaName;
	@Column(name="simple_name")
	private String simpleName;
	@Column(name="parent_id")
	private String parentId;
	@Column(name="pre_pin_yin")
	private String prePinYin;
	@Column(name="level")
	private Integer level;
	@Column(name="pin_yin")
	private String pinYin;
	@Column(name="simple_py")
	private String simplePy;
	@Column(name="area_code")
	private String areaCode;
	@Column(name="lon")
	private String lon;
	@Column(name="lat")
	private String lat;
	@Column(name="remark")
	private String remark;
	@Column(name="zip_code")
	private String zipCode;


	public BaArea() {
	}

	public BaArea(String areaId, String areaName, String parentId, Integer level) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.parentId = parentId;
		this.level = level;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPrePinYin() {
		return prePinYin;
	}

	public void setPrePinYin(String prePinYin) {
		this.prePinYin = prePinYin;
	}

	public String getPinYin() {
		return pinYin;
	}

	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSimplePy() {
		return simplePy;
	}

	public void setSimplePy(String simplePy) {
		this.simplePy = simplePy;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}

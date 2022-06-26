package com.FrameWrkFullPrject.model;

public class DesignCode {
	String dsgnCode, dsgnStyle, ornament;
	double crpt;
	Long ornmntDataId, ornmntId;

	public Long getOrnmntDataId() {
		return ornmntDataId;
	}

	public void setOrnmntDataId(Long ornmntDataId) {
		this.ornmntDataId = ornmntDataId;
	}

	public Long getOrnmntId() {
		return ornmntId;
	}

	public void setOrnmntId(Long ornmntId) {
		this.ornmntId = ornmntId;
	}

	public String getDsgnCode() {
		return dsgnCode;
	}

	public void setDsgnCode(String dsgnCode) {
		this.dsgnCode = dsgnCode;
	}

	public String getDsgnStyle() {
		System.out.println("===dsgnStyle==Gtr===="+dsgnStyle);
		return dsgnStyle;
	}

	public void setDsgnStyle(String dsgnStyle) {
		this.dsgnStyle = dsgnStyle;
	}

	public String getOrnament() {
		return ornament;
	}

	public void setOrnament(String ornament) {
		this.ornament = ornament;
	}

	public double getCrpt() {
		return crpt;
	}

	public void setCrpt(double crpt) {
		this.crpt = crpt;
	}
}

package com.ispan.project;

public class Project1DataBean {

	private Integer id;
	private String DataYear;
	private Integer MW;
	private Double powerBackUp;

	public Project1DataBean() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataYear() {
		return DataYear;
	}

	public void setDataYear(String DataYear) {
		this.DataYear = DataYear;
	}

	public Integer getMW() {
		return MW;
	}

	public void setMW(Integer mW) {
		this.MW = mW;
	}

	public Double getPowerBackUp() {
		return powerBackUp;
	}

	public void setPowerBackUp(Double powerBackUp) {
		this.powerBackUp = powerBackUp;
	}

}

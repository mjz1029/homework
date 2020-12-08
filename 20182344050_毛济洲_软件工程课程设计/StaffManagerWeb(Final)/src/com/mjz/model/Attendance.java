package com.mjz.model;
/**
 * ¿¼ÇÚ±í
 * @author mjz
 *
 */
public class Attendance {
	private int id;
	private int dutyId;
	private int staffId;
	private String type;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDutyId() {
		return dutyId;
	}
	public void setDutyId(int dutyId) {
		this.dutyId = dutyId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}

package com.mjz.model;
/**
 * 
 * @author mjz
 *班种实体表
 */
public class Duty {
	private int id;
	private String name;
	private int staff1Id;
	private String dutyDate;
	private int selectedNum = 0;//已选人数
	private int maxNum = 50;//班种最大选课人数
	private String info;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStaff1Id() {
		return staff1Id;
	}
	public void setStaff1Id(int staff1Id) {
		this.staff1Id = staff1Id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(String dutyDate) {
		this.dutyDate = dutyDate;
	}
	public int getSelectedNum() {
		return selectedNum;
	}
	public void setSelectedNum(int selectedNum) {
		this.selectedNum = selectedNum;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}
}

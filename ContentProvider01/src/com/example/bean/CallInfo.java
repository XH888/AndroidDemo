package com.example.bean;

public class CallInfo {
	private int id;
	private String phone;
	private String date;
	private String type;
	
	public CallInfo(int id, String phone, String date, String type) {
		this.id = id;
		this.phone = phone;
		this.date = date;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

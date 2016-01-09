package com.example.bean;

public class Feed {
	private int id;
	private int oid;
	private String category;
	private FeedData data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public FeedData getData() {
		return data;
	}
	public void setData(FeedData data) {
		this.data = data;
	}
	
	
}

package com.example.bean;

public class CbkData {

	/**
	 * {
    "success": true,
    "error_msg": null,
    "top_list": [],
    "all_count": 11,
    "all_list": [
        {
            "id": 78257,
            "summary": "由于涉嫌毁绿，执法大队将限期整改的通知书贴在了102室的墙上，要求户主立刻停止施工，恢复原状并在第二天前往执法大队接受处理。",
            "user_name": "夏女士",
            "subtime": "2015-10-09 14:42:53",
            "status": 1,
            "title": "开铲机毁绿拆房 这个邻居想干啥？",
            "target": "南京鼓楼区牡丹里小区一栋102住户",
            "comment_count": 0,
            "is_hot": false,
            "unit_name": "江苏城市频道《零距离》",
            "unit_logo": "http://hudongstatic.jstv.com/Act315/unitLogo/20139914475645082093.jpg"
        },
	 */
	private int id;
	private int status;
	private int comment_count;
	private String summary;
	private String user_name;
	private String title;
	private String target;
	private String unit_name;
	private String unit_logo;
	private boolean is_hot;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	public String getUnit_logo() {
		return unit_logo;
	}
	public void setUnit_logo(String unit_logo) {
		this.unit_logo = unit_logo;
	}
	public boolean isIs_hot() {
		return is_hot;
	}
	public void setIs_hot(boolean is_hot) {
		this.is_hot = is_hot;
	}
	
}

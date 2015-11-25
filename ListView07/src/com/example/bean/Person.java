package com.example.bean;

public class Person {
	private String name;
	private String type;	//标识标题还是内容 data 或者 title
	private String sex;
	private int age;
	private String tel;
	
	
	public Person(String name, String type, String sex, int age, String tel) {
		this.name = name;
		this.type = type;
		this.sex = sex;
		this.age = age;
		this.tel = tel;
	}


	public static int getTypeCount(){
		return 2;
	}
	
	public static final int TYPE_TITLE=0;
	public static final int TYPE_DATA=1;
	
	public int getTypeID() {
		if("title".equals( getType() )){
			return TYPE_TITLE;
		}else if("data".equals( getType() )) {
			return TYPE_DATA;
		}
		return -1;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}

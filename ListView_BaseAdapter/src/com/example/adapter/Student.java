package com.example.adapter;

import java.util.List;

import com.example.listview_baseadapter.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Student extends BaseAdapter {
	private Context context;
	private List<Student> list;
			
	private String name;
	private int age;
	private String sex;
	private String imageUri;
	private String id;
	
	
	public String getId() {
		return id;
	}
	public Context getContext() {
		return context;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getImageUri() {
		return imageUri;
	}
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	
	public Student(Context context,List list) {
		this.context=context;
		this.list=list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong(list.get(position).getId());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Student stu=list.get(position);
		View itemView=LayoutInflater.from(getContext()).inflate(R.layout.item_student,null);
		ImageView image=(ImageView) itemView.findViewById(R.id.imageUriView);
		TextView ageView=(TextView) itemView.findViewById(R.id.ageView);
		TextView nameView=(TextView) itemView.findViewById(R.id.nameView);
		TextView sexView=(TextView) itemView.findViewById(R.id.sexView);
		
		return itemView;
	}

}

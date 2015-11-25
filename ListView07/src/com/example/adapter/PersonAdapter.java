package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bean.Person;
import com.example.listview07.R;

public class PersonAdapter extends BaseAdapter {
	private Context context;
	private List<Person> datas;
	
	public PersonAdapter(Context context, List<Person> datas) {
		this.context = context;
		this.datas = datas;
	}

	
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 返回对象数据类型的个数
	 */
	@Override
	public int getViewTypeCount() {
		return Person.getTypeCount();
	}

	/**
	 * 返回指定位置的数据类型的ID
	 */
	@Override
	public int getItemViewType(int position) {
		return datas.get(position).getTypeID();
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Person p=datas.get(position);
		ViewHolder viewHolder=null;
		if(getItemViewType(position)==Person.TYPE_TITLE){
			if(convertView==null){
				viewHolder=new ViewHolder();
				convertView=LayoutInflater.from(context).inflate(R.layout.item_person_title, null);
				viewHolder.nameView=(TextView) convertView.findViewById(R.id.titleId);
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder)convertView.getTag();
			}
			viewHolder.nameView.setText(p.getName());
		}else{
			if(convertView==null){
				viewHolder=new ViewHolder();
				convertView=LayoutInflater.from(context).inflate(R.layout.item_person_data, null);
				viewHolder.nameView=(TextView) convertView.findViewById(R.id.nameId);
				viewHolder. sexView=(TextView) convertView.findViewById(R.id.sexId);
				viewHolder. ageView=(TextView) convertView.findViewById(R.id.ageId);
				viewHolder. telView=(TextView) convertView.findViewById(R.id.telId);
				convertView.setTag(viewHolder);
			}else{
				viewHolder=(ViewHolder) convertView.getTag();
			}
			viewHolder.nameView.setText(p.getName());
			viewHolder.sexView.setText(p.getSex());
			viewHolder.ageView.setText(String.valueOf(p.getAge()));
			viewHolder.telView.setText(p.getTel());
		}
		convertView.setClickable(false);
		return convertView;
	}
	
	class ViewHolder{
		TextView nameView,sexView,ageView,telView;
	}
}

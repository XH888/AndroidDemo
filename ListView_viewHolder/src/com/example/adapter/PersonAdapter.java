package com.example.adapter;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONStringer;

import com.example.bean.Person;
import com.example.listview_viewholder.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
	private List<Person> datas;
	private Context context;
	
	
	public PersonAdapter(List<Person> datas, Context context) {
		this.datas = datas;
		this.context = context;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vHolder=null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_person, null);
			vHolder=new ViewHolder();
			vHolder.nameView=(TextView) convertView.findViewById(R.id.nameid);
			vHolder.ageView=(TextView) convertView.findViewById(R.id.ageid);
			convertView.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) convertView.getTag();
		}
		//TextView nameTv=(TextView) convertView.findViewById(R.id.nameid);
		//TextView ageTv=(TextView) convertView.findViewById(R.id.ageid);
		
		vHolder.nameView.setText(datas.get(position).getName());
		vHolder.ageView.setText(String.valueOf(datas.get(position).getAge()));
		
		return convertView;
	}
	
	//封装Item View的子控件；在复用时候，用来减少View中的findViewById的使用。
	class ViewHolder{
		TextView nameView;
		TextView ageView;
	}

}

package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Person;
import com.example.listview02_person.R;
import com.example.utils.ImageTask;
import com.example.utils.Urls;

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
		return datas.get(position).getId();
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		ViewHolder vholder=null;
		if(convertView!=null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_person, null);
			
			//实例化vholder
			vholder=new ViewHolder();
			vholder.nametv=(TextView) convertView.findViewById(R.id.nameId);
			vholder.agetv=(TextView) convertView.findViewById(R.id.ageId);
			vholder.imageView =(ImageView) convertView.findViewById(R.id.imageId);
			
			convertView.setTag(vholder);	//存入到convertView的附加数据中
		}else{
			vholder=(ViewHolder) convertView.getTag();	//从convertView中取出
			//当图片未下载完成显示默认图片
			vholder.imageView.setImageResource(R.drawable.ic_launcher);
		}
		convertView.findViewById(R.id.nameId);
		
		vholder.nametv.setText(datas.get(position).getName());
		vholder.agetv.setText(datas.get(position).getAge());
		
		vholder.imageView.setTag(Urls.BASE_URL+datas.get(position).getImgUrl()+position);
		
		new ImageTask(new ImageTask.CallBack() {
			@Override
			public void response(String url, Bitmap bitmap) {
				ImageView imageview=(ImageView)parent.findViewWithTag(url+position);
				if(imageview!=null){
					imageview.setImageBitmap(bitmap);
				}
			}

			@Override
			public boolean isCancel(String url) {
				return parent.findViewWithTag(url+position)==null;
			}
		}).execute(Urls.BASE_URL+datas.get(position).getImgUrl());
		
		return convertView;
	}
	
	class ViewHolder{
		TextView nametv,agetv;
		ImageView imageView;
	}
	
}

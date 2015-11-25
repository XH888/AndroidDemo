package com.example.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class AbsAdapter<T> extends BaseAdapter {
	private Context context;
	private List<T> datas;
	private int layoutRes;
	public AbsAdapter(Context context,int layoutRes, List<T> datas) {
		this.context = context;
		this.datas = datas;
		this.layoutRes = layoutRes;
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
		ViewHolder viewHolder=null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(layoutRes,null);
			viewHolder=new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}else{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		showData(viewHolder, datas.get(position));
		return convertView;
	}

	//将存放的子布局的传过去给调用者， 填充的类型都由调用者决定
	public abstract void showData(ViewHolder<T> viewHolder,T data); 
	
	public class ViewHolder<T>{
		private Map<Integer,View> views;	//定义ViewHolder中的对象键值对；
		private View itemView;

		public ViewHolder(View itemView) {	//传入布局的子布局LayoutView
			this.itemView=itemView;
			views=new HashMap<Integer,View>();
		}
		public View getViews(int resourceId) {	//根据ID取得子布局中的对象
			View view=views.get(resourceId);
			if(view==null){
				view=itemView.findViewById(resourceId);
				views.put(resourceId, view);
			}
			return view;
		}
	}
}

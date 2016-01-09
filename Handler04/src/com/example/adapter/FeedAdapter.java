package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bean.Feed;
import com.example.handler04.R;
import com.example.utils.AUtil;
import com.example.utils.URLs;

public class FeedAdapter extends BaseAdapter {
	private Context context;
	private List<Feed> datas;
	
	private AUtil autil;
	public FeedAdapter(Context context,List<Feed> datas,AUtil autil){
		this.context=context;
		this.datas=datas;
		this.autil=autil;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//对ListItem进行迭代保存
		ViewHodler vHodler = null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_feed, null);
			
			vHodler=new ViewHodler();
			vHodler.coverView=(ImageView) convertView.findViewById(R.id.coverId);
			vHodler.summaryView=(TextView) convertView.findViewById(R.id.summaryId);
			vHodler.subjectView=(TextView) convertView.findViewById(R.id.subjectId);
		
			convertView.setTag(vHodler);
		}else{
			vHodler=(ViewHodler) convertView.getTag();
			vHodler.coverView.setImageResource(R.drawable.ic_launcher);
		}
		vHodler.summaryView.setText(datas.get(position).getData().getSummary());
		vHodler.subjectView.setText(datas.get(position).getData().getSubject());
		
		String imgURL=URLs.BASE_URI+datas.get(position).getData().getCover();

		vHodler.coverView.setTag(imgURL);
		
		autil.getAsy(AUtil.TYPE_IMG, imgURL) ;	//显示Item的时候调用线程下载图片
		
		return convertView;
	}
	
	class ViewHodler{
		TextView summaryView,subjectView;
		ImageView coverView;
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
}

package com.example.adapter;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.bean.Feed;
import com.example.listview_news.R;
import com.example.utils.ImageTask;
import com.example.utils.Urls;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FeedAdapter extends BaseAdapter {
	private Context context;
	private List<Feed> feedlist;
	
	//定义线程池；
	private ExecutorService executor=Executors.newFixedThreadPool(3) ;
	
	//定义缓存
	private Map<String,SoftReference<Bitmap>> imgCaches=new HashMap<String, SoftReference<Bitmap>>();
	
	public FeedAdapter(Context context,List<Feed> list){
		this.context=context;
		this.feedlist=list;
	}
	
	@Override
	public int getCount() {
		return feedlist.size();
	}

	@Override
	public Object getItem(int position) {
		return feedlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return feedlist.get(position).getOid();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View itemView=LayoutInflater.from(context).inflate(R.layout.item_feed, null);
		
		TextView subjectView=(TextView) itemView.findViewById(R.id.subjectView);
		TextView summaryView=(TextView) itemView.findViewById(R.id.summaryView);
		
		final ImageView coverView=(ImageView) itemView.findViewById(R.id.coverId);
		subjectView.setText(feedlist.get(position).getSubject());
		summaryView.setText(feedlist.get(position).getSummary());

		final String imgUrl=Urls.BASE_URL+feedlist.get(position).getCover();
		
		SoftReference<Bitmap> sr=imgCaches.get(imgUrl);
		if(sr!=null){
			Bitmap bm=sr.get();
			if(bm!=null){
				coverView.setImageBitmap(bm);
			}else{
				getImage(coverView, imgUrl);
			}
		}else{
			getImage(coverView, imgUrl);
		}
		return itemView;
	}

	private void getImage(final ImageView coverView, final String imgUrl) {
		//下载图片
		new ImageTask(new ImageTask.CallBack(){
			@Override
			public void response(String url, Bitmap bitmap) {
				coverView.setImageBitmap(bitmap);
				imgCaches.put(url, new SoftReference<Bitmap>(bitmap));
			}
		}).executeOnExecutor(executor,imgUrl);
	}
	
	
	
	
}

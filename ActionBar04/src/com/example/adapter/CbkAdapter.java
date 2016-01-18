package com.example.adapter;

import java.util.List;

import com.example.actionbar04.R;
import com.example.bean.CbkData;
import com.example.util.AUtils;
import com.example.util.ImgUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CbkAdapter extends BaseAdapter {
	private Context context;
	private List<CbkData> datas;
	
	public CbkAdapter(Context context,List<CbkData> datas){
		this.context=context;
		this.datas=datas;
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
	public View getView(int position, View convertView, final ViewGroup parent) {
		
		ViewHolder vHolder=null;

		if(convertView==null){
			convertView = LayoutInflater.from(context).inflate(R.layout.item_content,null);
			vHolder=new ViewHolder();
			vHolder.unitLogoImgView=(ImageView) convertView.findViewById(R.id.unitLogoId);
			vHolder.titleView=(TextView) convertView.findViewById(R.id.titleId);
			vHolder.summaryView=(TextView) convertView.findViewById(R.id.summaryId);
			
			vHolder.unitLogoImgView=(ImageView) convertView.findViewById(R.id.unitLogoId);
			
			convertView.setTag(vHolder);
		}else{
			vHolder=(ViewHolder) convertView.getTag();
			vHolder.unitLogoImgView.setImageResource(R.drawable.ic_launcher);
		}
		CbkData data= datas.get(position);
		
		vHolder.titleView.setText(data.getUser_name()+"："+data.getTitle());
		vHolder.summaryView.setText(data.getSummary());
		
		String imgPath=data.getUnit_logo();
		
		if(imgPath.length()>0){
			vHolder.unitLogoImgView.setTag(imgPath);
			//从本地读取图片文件
			Bitmap bitmap=ImgUtil.getImage(imgPath);
			
			if(bitmap!=null){
				vHolder.unitLogoImgView.setImageBitmap(bitmap);
			}else{
				AUtils.getAsy(AUtils.TYPE_IMG, imgPath,new AUtils.AbsCallBack() {

					@Override
					public boolean isCancelled(String url) {
						return parent.findViewWithTag(url)==null;
					}

					@Override
					public void respImg(String url, Bitmap bitmap) {
						//查找是否存在复用的ImageView
						ImageView imageView=(ImageView) parent.findViewWithTag(url);
						if(imageView!=null){
							imageView.setImageBitmap(bitmap);
						}
					}
				});

			}
		}
		
		return convertView;
	}

	class ViewHolder{
		ImageView unitLogoImgView;
		TextView titleView,summaryView;
	}
}

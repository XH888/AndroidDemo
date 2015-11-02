package com.example.fragmentdemo.ui;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.constant.Constant;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageText extends LinearLayout {
	private Context context;
	private ImageView imageView;
	private TextView textView;
	private final static int DEFAULT_IMAGE_WIDTH = 64;
	private final static int DEFAULT_IMAGE_HEIGHT = 64;
	private int CHECKED_COLOR = Color.rgb(29, 118, 199); //选中蓝色
	private int UNCHECKED_COLOR = Color.GRAY;   //自然灰色
	public ImageText(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context=context;
		LayoutInflater inflater=LayoutInflater.from(context);
		View parentView = inflater.inflate(R.layout.image_text_layout, this, true);
		textView=(TextView) findViewById(R.id.text_iamge_text);
		imageView=(ImageView) findViewById(R.id.image_iamge_text);
	}
	
	public void setImage(int resId){
		if(imageView!=null){
			imageView.setImageResource(resId);
			setImageSize(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT);
		}
	}
	public void setText(String txt){
		if(textView!=null){
			textView.setText(txt);
			textView.setBackgroundColor(UNCHECKED_COLOR);
		}
	}
	public void setImageSize(int w,int h){
		if(imageView!=null){
			ViewGroup.LayoutParams params=imageView.getLayoutParams();
			params.width=w;
			params.height=h;
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return true;
	}
	
	public void setChecked(int itemID){
		if(textView!=null){
			textView.setBackgroundColor(CHECKED_COLOR);
		}
		int checkDrawableId=-1;
		switch (itemID) {
		case Constant.BTN_FLAG_CONTACTS:
			checkDrawableId=R.drawable.contacts_selected;
			break;
		case Constant.BTN_FLAG_MESSAGE:
			checkDrawableId=R.drawable.message_selected;
			break;
		case Constant.BTN_FLAG_NEW:
			checkDrawableId=R.drawable.news_selected;
			break;
		case Constant.BTN_FLAG_SETTING:
			checkDrawableId=R.drawable.setting_selected;
			break;
		default:
			break;
		}
		if(imageView!=null){
			imageView.setImageResource(checkDrawableId);
		}
	}
}

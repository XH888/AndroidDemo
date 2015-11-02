package com.example.fragmentdemo.ui;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.constant.Constant;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;
//ͷ
public class HeadControlPanel extends RelativeLayout {
	private Context context;
	private TextView rightTitle;
	private TextView middleTitle;
	private static final float middle_title_size=20f;
	private static final float right_title_size=17f;
	private static final int default_background_color=Color.rgb(23, 124, 202);
	public HeadControlPanel(Context context) {
		super(context);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		middleTitle=(TextView) findViewById(R.id.midle_title);
		rightTitle=(TextView) findViewById(R.id.right_title);
		setBackgroundColor(default_background_color);
	}
	
	public void initHeadPanel(){
		if(middleTitle!=null){
			setMiddleTitle(Constant.FRAGMENT_FLAG_MESSAGE);
		}
	}
	
	public void setMiddleTitle(String s ){
		middleTitle.setText(s);
		middleTitle.setTextSize(middle_title_size);
	}
}

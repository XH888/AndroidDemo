package com.example.control;

import com.example.excisedemo.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher.ViewFactory;

public class UsingImageSwicher extends Activity {
	private ImageSwitcher imageSwitcher;
	private boolean flag=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_use_imageswitcher);
		imageSwitcher=(ImageSwitcher) findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(new ViewFactory() {			//创建构建替换单元的View
			@Override
			public View makeView() {
				return new ImageView(UsingImageSwicher.this);
			}
		});

		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(UsingImageSwicher.this,android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(UsingImageSwicher.this,android.R.anim.fade_out));
		
		imageSwitcher.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				flag=!flag;
				if(flag){
					imageSwitcher.setImageResource(R.drawable.img1);
				}else{
					imageSwitcher.setImageResource(R.drawable.img2);
				}
			}
		});
		imageSwitcher.setImageResource(R.drawable.img1);
	}
}

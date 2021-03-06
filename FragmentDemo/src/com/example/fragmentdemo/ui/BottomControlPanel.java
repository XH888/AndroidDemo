package com.example.fragmentdemo.ui;

import java.util.ArrayList;
import java.util.List;

import com.example.fragmentdemo.R;
import com.example.fragmentdemo.constant.Constant;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
//β
public class BottomControlPanel extends RelativeLayout implements OnClickListener{
	private Context context;
	private ImageText msgBtn;
	private ImageText newBtn;
	private ImageText settingBtn;
	private ImageText contactsBtn;
	private int DEFALUT_BACKGROUND_COLOR = Color.rgb(243, 243, 243); //Color.rgb(192, 192, 192)  
	private BottomPanelCallback bottomCallback;  
	private List<ImageText> viewList=new ArrayList<ImageText>();
	
	public BottomControlPanel(Context context) {
		super(context);
	}
	
	public interface BottomPanelCallback{
		public void onBottomPanelClick(int itemID);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		msgBtn=(ImageText) findViewById(R.id.btn_message);
		newBtn=(ImageText) findViewById(R.id.btn_news);
		settingBtn=(ImageText) findViewById(R.id.btn_setting);
		contactsBtn=(ImageText) findViewById(R.id.btn_contacts);
		viewList.add(msgBtn);
		viewList.add(newBtn);
		viewList.add(settingBtn);
		viewList.add(contactsBtn);
		setBackgroundColor(DEFALUT_BACKGROUND_COLOR);
	}
	public void initBottomPanel(){
		if(msgBtn!=null){
			msgBtn.setImage(R.drawable.message_unselected);
			msgBtn.setText(Constant.FRAGMENT_FLAG_MESSAGE);
		}
		if(newBtn!=null){
			newBtn.setImage(R.drawable.news_unselected);
			newBtn.setText(Constant.FRAGMENT_FLAG_NEW);
		}
		if(settingBtn!=null){
			settingBtn.setImage(R.drawable.setting_unselected);
			settingBtn.setText(Constant.FRAGMENT_FLAG_SETTING);
		}
		if(contactsBtn!=null){
			contactsBtn.setImage(R.drawable.contacts_unselected);
			contactsBtn.setText(Constant.FRAGMENT_FLAG_CONTACTS);
		}
		setBtnListener();
	}
	
	private void setBtnListener(){
		int num=this.getChildCount();
		for(int i=0;i<num;i++){
			View v=getChildAt(i);
			if(v!=null){
				v.setOnClickListener(this);
			}
		}
	}
	
	public void setBottomCallback(BottomPanelCallback bottomCallback){
		this.bottomCallback=bottomCallback;
	}
	
	@Override
	public void onClick(View v) {
		initBottomPanel();
		int index=-1;
		switch (v.getId()) {
		case R.id.btn_contacts:
			index=Constant.BTN_FLAG_CONTACTS;
			contactsBtn.setChecked(index);
			break;
		case R.id.btn_message:
			index=Constant.BTN_FLAG_MESSAGE;
			msgBtn.setChecked(index);
			break;
		case R.id.btn_setting:
			index=Constant.BTN_FLAG_SETTING;
			settingBtn.setChecked(index);
			break;
		case R.id.btn_news:
			index=Constant.BTN_FLAG_NEW;
			newBtn.setChecked(index);
			break;
		default:
			break;
		}
		if(bottomCallback!=null){
			bottomCallback.onBottomPanelClick(index);
		}
	}
	
	public void defaultBtnChecked(){
		if(msgBtn!=null){
			msgBtn.setChecked(Constant.BTN_FLAG_MESSAGE);
		}
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		layoutItems(l, t, r, b);
	}
	
   private void layoutItems(int left, int top, int right, int bottom){
	   int n=getChildCount();
	   if(n==0){
		   return ;
	   }
	   int paddingLeft=getPaddingLeft();
	   int paddingRight=getPaddingRight();
	   int width=right-left;
	   int height=bottom-top;
	   
	   int allViewWidth=0;
	   for(int i=0;i<n;i++){
		   View v=getChildAt(i);
		   allViewWidth+=v.getWidth();
	   }
	   
	   int blankWidth=(width-allViewWidth-paddingLeft-paddingRight)/(n-1);
	   LayoutParams params1=(LayoutParams) viewList.get(1).getLayoutParams();
	   params1.leftMargin=blankWidth;
	   viewList.get(1).setLayoutParams(params1);
	   
	   LayoutParams params2=(LayoutParams) viewList.get(2).getLayoutParams();
	   params2.leftMargin=blankWidth;
	   viewList.get(1).setLayoutParams(params2);
	   
   }

}

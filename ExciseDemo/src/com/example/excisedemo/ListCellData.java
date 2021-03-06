package com.example.excisedemo;

import android.content.Context;
import android.content.Intent;

public class ListCellData {
	public ListCellData(String controlName, Context context,Intent relatedIntent) {
		super();
		this.controlName = controlName;
		this.context = context;
		this.relatedIntent = relatedIntent;
	}
	private String controlName;
	private Context context;
	private Intent relatedIntent;
	public String getControlName() {
		return controlName;
	}
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public Intent getRelatedIntent() {
		return relatedIntent;
	}
	public void setRelatedIntent(Intent relatedIntent) {
		this.relatedIntent = relatedIntent;
	}
	public void startActivity(){
		getContext().startActivity(getRelatedIntent());
	}
	
	@Override
	public String toString() {
		return this.controlName;
	}
}

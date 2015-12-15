package com.example.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;


/**
 * 
 * @author XH
 * 创建方式：onCreateView():创建一个
 */
public class EditDialogFragment extends DialogFragment {
	
	private DialogInterface.OnClickListener listener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof DialogInterface.OnClickListener){
			listener=(OnClickListener) activity;
		}
	}
	
	public static EditDialogFragment newInstance(Bundle args){
		EditDialogFragment f=new EditDialogFragment();
		f.setArguments(args);
		return f;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setMessage(getArguments().getString("msg"))
				.setPositiveButton("确定", listener)
				.setNegativeButton("取消", null)
				.setTitle(getArguments().getString("title"))
				.setIcon(android.R.drawable.ic_media_play)
				.create();
	}
}

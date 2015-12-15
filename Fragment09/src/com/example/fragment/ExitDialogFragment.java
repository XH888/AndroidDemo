package com.example.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fragment09.R;

public class ExitDialogFragment extends DialogFragment implements View.OnClickListener{
	private Button btn;
	//只能在初始化时候设置dialog标题
	@Override
	public void onStart() {
		super.onStart();
		if(getDialog()!=null)
			getDialog().setTitle("标题");
	}
	
	//给对话框实例化
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_exitdialog, null);
		btn=(Button) view.findViewById(R.id.btnId);
		btn.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		getActivity().finish();
	}
}

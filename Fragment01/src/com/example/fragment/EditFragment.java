package com.example.fragment;

import com.example.fragment01.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class EditFragment extends Fragment {
	private EditText nameEt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.fragment_edit, null);
		nameEt = (EditText) v.findViewById(R.id.nameId);
		return v;
	}
}

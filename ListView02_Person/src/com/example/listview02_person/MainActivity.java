package com.example.listview02_person;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.adapter.PersonAdapter;
import com.example.bean.Person;
import com.example.utils.JsonArrayTask;
import com.example.utils.Urls;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView lv;
	private List<Person> datas;
	private PersonAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv=(ListView) findViewById(R.id.lvId);
		datas=new ArrayList<Person>();
		adapter=new PersonAdapter(getApplicationContext(), datas);
		lv.setAdapter(adapter);
		loadData();
	}

	private void loadData() {
		new JsonArrayTask<Person>(Person.class, new JsonArrayTask.CallBack<Person>() {
			@Override
			public String parsePart(String json) {
				try {
					JSONArray jsonarray=new JSONObject(json).getJSONArray("data");
					return jsonarray.toString();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public void response(List<Person> result) {
				datas.addAll(result);
			}
		}).execute(Urls.PESON_URL);
	}
}

package com.example.processdialog01;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView imgView;
	private ProgressDialog progressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imgView=(ImageView) findViewById(R.id.imgView);
		initDialog();
	}

	private void initDialog() {
		progressDialog=new ProgressDialog(this);
		progressDialog.setTitle("下载提示");
		progressDialog.setIcon(android.R.drawable.ic_menu_add);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setCanceledOnTouchOutside(false);
	}

	public void showImg(View view){
		new ImageTask().execute("http://avatar.csdn.net/1/4/F/1_yanzi1225627.jpg");
	}
	
	class ImageTask extends AsyncTask<String, Void, Bitmap>{
		@Override
		protected void onPreExecute() {
			progressDialog.show();
			progressDialog.setProgress(0);
		}
		
		@Override
		protected Bitmap doInBackground(String... params) {
			try {
				URL url=new URL(params[0]);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				if(conn.getResponseCode()==200){
					InputStream stream = conn.getInputStream();	//正式连接（若文件过大则 自动会准换成GZIPInputStream） 造成获  getContentLength=-1
					
					byte[] buff=new byte[stream.available()];
					int len=-1;
					int currlen=0;
					int maxlen=conn.getContentLength();
					
					ByteArrayOutputStream baos=new ByteArrayOutputStream();	//内存流
					while ((len=stream.read())!=-1) {
						baos.write(buff,0,len);
						currlen+=len;
						int progress = currlen*100/maxlen;
						progressDialog.setProgress(progress);
						Thread.sleep(100);
					}
					Log.i("info", " msg："+baos.toByteArray().length);
					return BitmapFactory.decodeByteArray(baos.toByteArray(),0,baos.toByteArray().length);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Bitmap result){
			progressDialog.dismiss();
			if(result!=null){
				imgView.setImageBitmap(result);
			}
		}
	}
}

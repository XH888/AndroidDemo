package com.example.util;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;

public class FileUtils {
	// 保存图片的缓存路径
	public static final String CACHE_DIR = Environment.getDataDirectory() + "/Storage03/imgcache/";

	public static final int FORMAT_JPEG=1;
	public static final int FORMAT_PNG=2;
	
	public static boolean isMounted() {
		//返回扩展卡是否挂载
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	//保存为字节流图片
	public static void saveImage(String url, byte[] bytes) throws IOException {
		if(!isMounted()) return ;
		File dir=new File(CACHE_DIR);
		if(!dir.exists()) dir.mkdirs();	//判断文件路径
		FileOutputStream fos=new FileOutputStream(new File(dir,getFileName(url)));
		fos.write(bytes);
		fos.close();
	}

	//保存为Bitmap图片
	public static void saveImage(String url, Bitmap bitmap,int format) throws IOException {
		if(!isMounted()) return ;
		File dir=new File(CACHE_DIR);
		if(!dir.isDirectory()) dir.mkdirs();	//判断文件路径
		FileOutputStream fos=new FileOutputStream(new File(dir,getFileName(url)));
		bitmap.compress(format==FORMAT_JPEG?CompressFormat.JPEG:CompressFormat.PNG, 100, fos);		//将输入流转换成BitMap流
	}
	
	public static Bitmap readImage(String url) {
		if(!isMounted()) return null;
		File imageFile=new File(CACHE_DIR,getFileName(url));
		if(imageFile.exists()){
			return BitmapFactory.decodeFile(imageFile.getAbsolutePath());
		}
		return null;
	}
	
	public static String getFileName(String url){
		return url.substring(url.lastIndexOf("/")+1);
	}
}

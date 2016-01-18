package com.example.util;

import java.io.File;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class ImgUtil {
	public static String cacheURL=Environment.getDataDirectory()+"/gp06/cbk/images/";
	
	public static boolean isMounted(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	public static String getFileName(String url){
		return url.substring(url.lastIndexOf("/"));
	}
	
	public static void saveImage(String url,byte[] bytes ) throws Exception{
		if(isMounted()) return ;
		File dir=new File(cacheURL);
		if(dir.exists()) dir.mkdirs();
		
		FileOutputStream fos=new FileOutputStream(new File(dir,getFileName(url)));
		fos.write(bytes);
		fos.close();
	}
	
	public static Bitmap getImage(String url){
		if(isMounted()) return null;
		File imgfile=new File(cacheURL,getFileName(url));
		
		if(imgfile.exists()){
			return BitmapFactory.decodeFile(imgfile.getAbsolutePath());
		}
		return null;
	}
}

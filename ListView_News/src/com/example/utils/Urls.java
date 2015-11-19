package com.example.utils;

public class Urls {
	public static final String BASE_URL="http://litchiapi.jstv.com/";

	//分类
	public static final String CATEGORY_URL=BASE_URL+"api/GetColumns?client=android&val=B52F2195EB64517ABC31C550BBFC5AEC";

	//列表信息
	public static final String  LIST_URL=BASE_URL+"api/GetFeeds?column=%d&PageSize=20&pageIndex=%d&val=100511D3BE5301280E0992C73A9DEC41";
	
}

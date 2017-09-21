package com.guojianghao.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseUtil<T> {
	
	private ResponseUtil() {}
	
	@SuppressWarnings("rawtypes")
	public static final ResponseUtil INSTANCE = new ResponseUtil();
	
	public Map<String,Object> response(Object rows,int total){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", rows);
		map.put("total", total);
		return map;
	}
	
	public String response(int result){
		return result > 0 ? "success" : "fail";
	}
	
	public Map<String,Object> response(Object ...objects){
		Map<String,Object> map = new HashMap<String,Object>();
		for(int i = 0,m = 1; i < objects.length; i++,m++){
			map.put("data"+m, objects[i]);
		}
		return map;
	}
	
	
	
	
	
	
	
	
	
	
}

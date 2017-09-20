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
}

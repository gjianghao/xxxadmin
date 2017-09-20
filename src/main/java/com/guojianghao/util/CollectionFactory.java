package com.guojianghao.util;

import java.util.HashMap;
import java.util.Map;

import com.guojianghao.entity.page.Page;

/**
 * @Description:<p>带分页的参数封装map</p>
 * @author king
 * @date 2017年9月20日 下午3:04:36
 */
public class CollectionFactory {

	public static Map<String,Object> getParamMapInstance(int page,int rows){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", new Page(page, rows));
		return map;
	}
}

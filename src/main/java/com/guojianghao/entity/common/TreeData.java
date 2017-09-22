package com.guojianghao.entity.common;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * @Description:<p>树形数据结构</p>
 * @author king
 * @date 2017年9月22日 上午9:34:28
 */
public class TreeData {
	
	private int id;
	private String text;
	private String state;
	private boolean checked;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<TreeData> getChildren() {
		return children;
	}
	public void setChildren(List<TreeData> children) {
		this.children = children;
	}
	private List<TreeData> children = new ArrayList<TreeData>();
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

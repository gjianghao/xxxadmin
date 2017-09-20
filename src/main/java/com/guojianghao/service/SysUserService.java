package com.guojianghao.service;

import java.util.List;
import java.util.Map;

import com.guojianghao.entity.sys.SysUser;

public interface SysUserService{
	
	public SysUser queryUsernameAndPassword(String username);

	public List<SysUser> getSysUserList(Map<String, Object> map);

	public int getSysUserCount(Map<String, Object> map);

}

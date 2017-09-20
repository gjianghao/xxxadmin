package com.guojianghao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guojianghao.entity.sys.SysUser;
import com.guojianghao.entity.sys.SysUserExample;
import com.guojianghao.entity.sys.SysUserExample.Criteria;
import com.guojianghao.mapper.sys.SysUserMapper;
import com.guojianghao.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService{
	
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public SysUser queryUsernameAndPassword(String username) {
		SysUser sysUser = sysUserMapper.queryUsernameAndPassword(username);
		return sysUser;
	}

	@Override
	public List<SysUser> getSysUserList(Map<String, Object> map) {
		
		return sysUserMapper.getSysUserList(map);
	}

	@Override
	public int getSysUserCount(Map<String, Object> map) {
		return sysUserMapper.getSysUserCount(map);
	}

}

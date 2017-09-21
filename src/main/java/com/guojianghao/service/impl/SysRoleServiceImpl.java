package com.guojianghao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guojianghao.entity.sys.SysRole;
import com.guojianghao.mapper.sys.SysRoleMapper;
import com.guojianghao.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService{
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRole> getAllRoleList() {
		return sysRoleMapper.selectByExample(null);
	}

	@Override
	public List<SysRole> getRoleListByUserId(int userId) {
		return sysRoleMapper.getRoleListByUserId(userId);
	}

	@Override
	public int deleteRoleByUserId(Integer id) {
		return sysRoleMapper.deleteRoleByUserid(id);
	}

	@Override
	public int saveRole(Integer id, int[] roles) {
		int result = 0;
		for(int i = 0; i < roles.length; i++){
			result += sysRoleMapper.saveRole(id,roles[i]);
		}
		return result;
	}

	@Override
	public int getSysRoleCount(Map<String, Object> map) {
		return sysRoleMapper.getSysRoleCount(map);
	}

	@Override
	public List<SysRole> getSysRoleList(Map<String, Object> map) {
		return sysRoleMapper.getSysRoleList(map);
	}

	@Override
	public int saveSysRole(SysRole role) {
		return sysRoleMapper.insertSelective(role);
	}

	@Override
	public int deleteSysRole(int id) {
		return sysRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateSysRole(SysRole role) {
		return sysRoleMapper.updateByPrimaryKeySelective(role);
	}

}

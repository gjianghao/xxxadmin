package com.guojianghao.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	@Override
	public int deleteSysResourceByRoleId(int roleId) {
		return sysRoleMapper.deleteSysResourceByRoleId(roleId);
	}

	@Override
	public int assignSysResources(int roleId, int[] resources) {
		List<Integer> list1 = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < resources.length; i++){
			list1.add(resources[i]);
			set.add(resources[i]);
		}
		List<SysRole> list = sysRoleMapper.queryParentIdByResourceId(list1);
		for(int i = 0; i < list.size(); i++){
			set.add(list.get(i).getId());
		}
		int result = 0;
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()){
			result += sysRoleMapper.saveRoleResource(roleId,it.next());
		}
		return result;
	}
	

}

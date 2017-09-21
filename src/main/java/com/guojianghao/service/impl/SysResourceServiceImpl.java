package com.guojianghao.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guojianghao.entity.sys.SysResource;
import com.guojianghao.entity.sys.SysRole;
import com.guojianghao.mapper.sys.SysResourceMapper;
import com.guojianghao.service.SysResourceService;

@Service
public class SysResourceServiceImpl implements SysResourceService{
	
	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Override
	public List<SysResource> getSysParentResourcesListByUserId(int id) {
		
		List<SysResource> list = sysResourceMapper.getSysParentResourcesListByUserId(id);
		return list;
	}
	
	@Override
	public List<SysResource> getSysChildResourcesListByUserId(int id) {
		
		List<SysResource> list = sysResourceMapper.getSysChildResourcesListByUserId(id);
		return list;
	}

	@Override
	public int getsysResourceCount(Map<String, Object> map) {
		return sysResourceMapper.getsysResourceCount(map);
	}

	@Override
	public List<SysRole> getsysResourceList(Map<String, Object> map) {
		return sysResourceMapper.getsysResourceList(map);
	}

	@Override
	public int saveSysResource(SysResource resource) {
		return sysResourceMapper.insertSelective(resource);
	}

	@Override
	public int deleteSysResource(int id) {
		return sysResourceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateSysResource(SysResource sysResource) {
		return sysResourceMapper.updateByPrimaryKeySelective(sysResource);
	}

	@Override
	public List<SysResource> getAllResourceList() {
		return sysResourceMapper.getAllResourceList();
	}

	@Override
	public List<SysResource> getSelfResourceList(int roleId) {
		return sysResourceMapper.getSelfResourceList(roleId);
	}
	
}

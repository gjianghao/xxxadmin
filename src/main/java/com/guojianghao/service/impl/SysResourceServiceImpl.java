package com.guojianghao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guojianghao.entity.sys.SysResource;
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
	
}

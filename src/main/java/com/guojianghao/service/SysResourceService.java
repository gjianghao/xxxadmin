package com.guojianghao.service;

import java.util.List;

import com.guojianghao.entity.sys.SysResource;

public interface SysResourceService {

	List<SysResource> getSysChildResourcesListByUserId(int id);
	
	List<SysResource> getSysParentResourcesListByUserId(int id);
}

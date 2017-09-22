package com.guojianghao.service;

import java.util.List;
import java.util.Map;

import com.guojianghao.entity.sys.SysResource;
import com.guojianghao.entity.sys.SysRole;

public interface SysResourceService {

	List<SysResource> getSysChildResourcesListByUserId(int id);
	
	List<SysResource> getSysParentResourcesListByUserId(int id);

	int getsysResourceCount(Map<String, Object> map);

	List<SysRole> getsysResourceList(Map<String, Object> map);

	int saveSysResource(SysResource resource);

	int deleteSysResource(int id);

	int updateSysResource(SysResource sysResource);

	List<SysResource> getAllResourceList();

	List<SysResource> getSelfResourceList(int roleId);

}

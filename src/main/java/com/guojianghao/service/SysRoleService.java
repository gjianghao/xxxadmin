package com.guojianghao.service;

import java.util.List;
import java.util.Map;

import com.guojianghao.entity.sys.SysRole;

public interface SysRoleService {

	List<SysRole> getAllRoleList();

	List<SysRole> getRoleListByUserId(int userId);

	int deleteRoleByUserId(Integer id);

	int saveRole(Integer id, int[] roles);

	int getSysRoleCount(Map<String, Object> map);

	List<SysRole> getSysRoleList(Map<String, Object> map);

	int saveSysRole(SysRole role);

	int deleteSysRole(int id);

	int updateSysRole(SysRole role);

}

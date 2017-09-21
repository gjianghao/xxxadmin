package com.guojianghao.controller.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guojianghao.entity.sys.SysResource;
import com.guojianghao.entity.sys.SysRole;
import com.guojianghao.service.SysResourceService;
import com.guojianghao.service.SysRoleService;
import com.guojianghao.util.CollectionFactory;
import com.guojianghao.util.ResponseUtil;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysResourceService sysResourceService;

	@RequestMapping("/toView")
	public String toView(HttpServletRequest request){
		return "sys/roleList";
	}
	
	@RequestMapping("/datagrid")
	@ResponseBody
	public Object datagrid(@RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "roleName", required = false) String roleName) throws Exception{
		
		Map<String,Object> map = CollectionFactory.getParamMapInstance(page,rows);
		if(!StringUtils.isBlank(roleName)){
			map.put("roleName", roleName);
		}
		
		int total = sysRoleService.getSysRoleCount(map);
		List<SysRole> list = null;
		if(total > 0){
			list = sysRoleService.getSysRoleList(map);
		}
		return ResponseUtil.INSTANCE.response(list, total);
	}
	
	@RequestMapping("/saveRole")
	@ResponseBody
	public Object saveSysRole(SysRole role){
		
		int result = sysRoleService.saveSysRole(role);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("/deleteRole")
	@ResponseBody
	public Object deleteSysRole(int id){
		int result = sysRoleService.deleteSysRole(id);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("updateRole")
	@ResponseBody
	public Object updateSysRole(SysRole role){
		int result = sysRoleService.updateSysRole(role);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("/getResources")
	@ResponseBody
	public Object getSysResources(int roleId){
		
		List<SysResource> allResourceList = sysResourceService.getAllResourceList();
		List<SysResource> selfResourceList = sysResourceService.getSelfResourceList(roleId);
		
		return ResponseUtil.INSTANCE.response(allResourceList, selfResourceList);
	}
	
	
	
	
}

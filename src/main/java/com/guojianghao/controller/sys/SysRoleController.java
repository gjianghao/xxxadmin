package com.guojianghao.controller.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guojianghao.entity.common.TreeData;
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
	
	@RequestMapping(value = "/datagrid",method = RequestMethod.POST)
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
		
		List<TreeData> list = handlerTree(allResourceList,selfResourceList);
		return list;
	}
	
	@RequestMapping("/assignResources")
	@ResponseBody
	public Object assignSysResources(@RequestParam(value = "resources[]") int[] resources,
			@RequestParam(value = "roleId") int roleId){
		
		int result = 0;
		result = sysRoleService.deleteSysResourceByRoleId(roleId);
		if(resources != null && resources.length > 0){
			result = sysRoleService.assignSysResources(roleId,resources);
		}
		return ResponseUtil.INSTANCE.response(result);
	}

	private List<TreeData> handlerTree(List<SysResource> allResourceList, List<SysResource> selfResourceList) {
		
		List<TreeData> list = new ArrayList<TreeData>();
		for(SysResource r1 : allResourceList){
			if(r1.getParentId() == null){
				TreeData tree = new TreeData();
				tree.setId(r1.getId());
				tree.setText(r1.getName());
				List<TreeData> children = new ArrayList<TreeData>();
				for(SysResource r2 : allResourceList){
					if(r1.getId() == r2.getParentId()){
						TreeData tree2 = new TreeData();
						tree2.setId(r2.getId());
						tree2.setText(r2.getName());
						for(SysResource r3 : selfResourceList){
							if(r3.getId() == r2.getId()){
								tree2.setState("open");
								tree2.setChecked(true);
								break;
							}
							tree2.setState("close");
							tree2.setChecked(false);
						}
						children.add(tree2);
					}
				}
				tree.setChildren(children);
				list.add(tree);
			}
		}
		return list;
	}
	
	
	
	
}

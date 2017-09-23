package com.guojianghao.controller.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guojianghao.entity.sys.SysResource;
import com.guojianghao.entity.sys.SysRole;
import com.guojianghao.service.SysResourceService;
import com.guojianghao.util.CollectionFactory;
import com.guojianghao.util.ResponseUtil;

@Controller
@RequestMapping("/sysResource")
public class SysResourceController {

	@Autowired
	private SysResourceService sysResourceService;

	@RequestMapping("/toView")
	public String toView(Model model){
		List<SysResource> list = sysResourceService.getSysParentResourcesList();
		model.addAttribute("list", list);
		return "sys/resourceList";
	}
	
	@RequestMapping(value = "/datagrid",method = RequestMethod.POST)
	@ResponseBody
	public Object datagrid(@RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "resourceName", required = false) String resourceName) throws Exception{
		
		Map<String,Object> map = CollectionFactory.getParamMapInstance(page,rows);
		if(!StringUtils.isBlank(resourceName)){
			map.put("resourceName", resourceName);
		}
		
		int total = sysResourceService.getsysResourceCount(map);
		List<SysRole> list = null;
		if(total > 0){
			list = sysResourceService.getsysResourceList(map);
		}
		return ResponseUtil.INSTANCE.response(list, total);
	}
	
	@RequestMapping("/saveResource")
	@ResponseBody
	public Object saveSysResource(SysResource resource){
		int result = sysResourceService.saveSysResource(resource);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("/deleteResource")
	@ResponseBody
	public Object deleteSysResource(int id){
		int result = sysResourceService.deleteSysResource(id);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("/updateResource")
	@ResponseBody
	public Object updateSysResource(SysResource sysResource){
		int result = sysResourceService.updateSysResource(sysResource);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("/getParentResourcesList")
	@ResponseBody
	public Object getSysParentResourcesList(){
		List<SysResource> list = sysResourceService.getSysParentResourcesList();
		return ResponseUtil.INSTANCE.response(list);
	}
	
	
	
}

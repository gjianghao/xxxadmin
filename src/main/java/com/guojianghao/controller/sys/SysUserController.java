package com.guojianghao.controller.sys;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guojianghao.entity.sys.SysRole;
import com.guojianghao.entity.sys.SysUser;
import com.guojianghao.service.SysRoleService;
import com.guojianghao.service.SysUserService;
import com.guojianghao.util.CollectionFactory;
import com.guojianghao.util.Constant;
import com.guojianghao.util.ResponseUtil;

/**
 * @Description:<p>用户管理</p>
 * @author king
 * @date 2017年9月20日 下午3:05:44
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("/toView")
	public String toView(HttpServletRequest request){
		return "sys/userList";
	}
	
	@RequestMapping("/datagrid")
	@ResponseBody
	public Object datagrid(@RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "realName", required = false) String realName) throws Exception{
		
		Map<String,Object> map = CollectionFactory.getParamMapInstance(page,rows);
		if(!StringUtils.isBlank(username)){
			map.put("username", username);
		}
		if(!StringUtils.isBlank(realName)){
			map.put("realName",realName);
		}
		
		int total = sysUserService.getSysUserCount(map);
		List<SysUser> list = null;
		if(total > 0){
			list = sysUserService.getSysUserList(map);
		}
		return ResponseUtil.INSTANCE.response(list, total);
	}
	
	@RequestMapping("/saveUser")
	@ResponseBody
	public Object saveSysUser(SysUser sysUser){
		
		String pwd = DigestUtils.md5Hex(sysUser.getPassword() + Constant.Security.SALT);
		sysUser.setPassword(pwd);
		int result = sysUserService.saveSysUser(sysUser);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("/deleteUser")
	@ResponseBody
	public Object deleteSysUser(int id){
		
		int result = sysUserService.deleteSysUser(id);
		return ResponseUtil.INSTANCE.response(result);
	}
	
	@RequestMapping("/getRoles")
	@ResponseBody
	public Object assignRoles(int userId){
		
		List<SysRole> allRoleList = sysRoleService.getAllRoleList();
		List<SysRole> selfRoleList = sysRoleService.getRoleListByUserId(userId);
		
		return ResponseUtil.INSTANCE.response(allRoleList,selfRoleList);
	}
	
	@RequestMapping("/assignRoles")
	@ResponseBody
	public Object assignRoles(HttpServletRequest request,
			@RequestParam(value = "roles[]") int[] roles,
			@RequestParam(value = "userId") int userId){
		
		sysRoleService.deleteRoleByUserId(userId);
		int saveResult = sysRoleService.saveRole(userId,roles);
		return ResponseUtil.INSTANCE.response(saveResult);
	}

	
	
	
	
	
	
	
	
	
	
	
}

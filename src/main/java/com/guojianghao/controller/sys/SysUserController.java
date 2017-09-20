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

import com.guojianghao.entity.sys.SysUser;
import com.guojianghao.service.SysUserService;
import com.guojianghao.util.CollectionFactory;
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

	@RequestMapping("/toView")
	public String toView(HttpServletRequest request){
		return "sys/userList";
	}
	
	@RequestMapping("/datagrid")
	@ResponseBody
	public Object datagrid(@RequestParam(value = "page", required = false) int page,
            @RequestParam(value = "rows", required = false) int rows,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "realName", required = false) String realName){
		
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
}

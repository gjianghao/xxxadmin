package com.guojianghao.controller.sys;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guojianghao.entity.sys.SysResource;
import com.guojianghao.entity.sys.SysUser;
import com.guojianghao.service.SysResourceService;
import com.guojianghao.service.SysUserService;
import com.guojianghao.util.Constant;

/**
 * 
 * @Description:<p>后台登录</p>
 * @author 17050049
 * @date 2017年9月20日 下午2:50:41
 */
@Controller
public class LoginController {
	
	@Autowired
	private SysResourceService sysResourceService;
	
	@Autowired
	private SysUserService sysUserService;

	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(String username,String password,HttpServletRequest request){
		
		String message = "";
		SysUser sysUser = sysUserService.queryUsernameAndPassword(username);
		if(sysUser != null){
			String saltPwd = DigestUtils.md5Hex(password + Constant.Security.SALT);
			if(!saltPwd.equals(sysUser.getPassword())){
				message = "账号或密码不正确";
			}else{
				request.getSession().setAttribute("user", sysUser);
				message = "ok";
			}
		}else{
			message = "账号不存在";
		}
		return message;
	}
	
	@RequestMapping("/main")
	public String toView(Model model,HttpServletRequest request){
		SysUser user = (SysUser)request.getSession().getAttribute("user");
		int id = user.getId();
		List<SysResource> listParentResource = sysResourceService.getSysParentResourcesListByUserId(id);
		List<SysResource> listChildResource = sysResourceService.getSysChildResourcesListByUserId(id);
		model.addAttribute("listParentResource", listParentResource);
		model.addAttribute("listChildResource", listChildResource);
		model.addAttribute("username", user.getRealName());
		return "main";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "redirect:/index";
	}
	
	
/*	public static void main(String[] args) {
		
		String message = DigestUtils.md5Hex("123456"+Constant.Security.SALT);
		
		System.out.println(message);
	}*/
}

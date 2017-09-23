package com.guojianghao.controller.sys;

import java.util.ArrayList;
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
 * @author king
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
		List<SysResource> list = handleResourceList(listParentResource,listChildResource);
		model.addAttribute("list", list);
		model.addAttribute("username", user.getRealName());
		return "main";
	}
	
	private List<SysResource> handleResourceList(List<SysResource> listParentResource, List<SysResource> listChildResource) {
		
		if(listParentResource != null && listParentResource.size() > 0){
			for(SysResource r1 : listParentResource){
				List<SysResource> list = new ArrayList<SysResource>();
				if(listChildResource != null && listChildResource.size() > 0){
					for(SysResource r2 : listChildResource){
						if(r1.getId() == r2.getParentId()){
							list.add(r2);
						}
					}
				}
				r1.setList(list);
			}
		}
		return listParentResource;
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
	
	/*
	Navicat MySQL Data Transfer



	Source Server         : localhost_3306
	Source Server Version : 50717
	Source Host           : localhost:3306
	Source Database       : sn-admin

	Target Server Type    : MYSQL
	Target Server Version : 50717
	File Encoding         : 65001

	Date: 2017-09-20 18:02:48
	*/

	/*SET FOREIGN_KEY_CHECKS=0;

	-- ----------------------------
	-- Table structure for `sys_resource`
	-- ----------------------------
	DROP TABLE IF EXISTS `sys_resource`;
	CREATE TABLE `sys_resource` (
	  `id` int(10) NOT NULL AUTO_INCREMENT,
	  `name` varchar(20) DEFAULT NULL,
	  `parent_id` int(10) DEFAULT NULL,
	  `href` varchar(100) DEFAULT NULL,
	  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	  `status` char(1) NOT NULL DEFAULT '0',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

	-- ----------------------------
	-- Records of sys_resource
	-- ----------------------------
	INSERT INTO `sys_resource` VALUES ('1', '系统管理', null, null, '2017-09-20 10:19:53', '0');
	INSERT INTO `sys_resource` VALUES ('2', '用户管理', '1', 'sysUser/toView', '2017-09-20 15:53:31', '0');
	INSERT INTO `sys_resource` VALUES ('3', '角色管理', '1', 'sysRole/toView', '2017-09-20 15:53:33', '0');
	INSERT INTO `sys_resource` VALUES ('4', '资源管理', '1', 'sysResource/toView', '2017-09-20 15:53:36', '0');

	-- ----------------------------
	-- Table structure for `sys_role`
	-- ----------------------------
	DROP TABLE IF EXISTS `sys_role`;
	CREATE TABLE `sys_role` (
	  `id` int(10) NOT NULL AUTO_INCREMENT,
	  `name` varchar(20) NOT NULL,
	  `status` char(1) NOT NULL DEFAULT '0',
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

	-- ----------------------------
	-- Records of sys_role
	-- ----------------------------
	INSERT INTO `sys_role` VALUES ('1', 'admin', '0');

	-- ----------------------------
	-- Table structure for `sys_role_resource`
	-- ----------------------------
	DROP TABLE IF EXISTS `sys_role_resource`;
	CREATE TABLE `sys_role_resource` (
	  `id` int(10) NOT NULL AUTO_INCREMENT,
	  `role_id` int(10) DEFAULT NULL,
	  `resource_id` int(10) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

	-- ----------------------------
	-- Records of sys_role_resource
	-- ----------------------------
	INSERT INTO `sys_role_resource` VALUES ('1', '1', '1');
	INSERT INTO `sys_role_resource` VALUES ('2', '1', '2');
	INSERT INTO `sys_role_resource` VALUES ('3', '1', '3');
	INSERT INTO `sys_role_resource` VALUES ('4', '1', '4');

	-- ----------------------------
	-- Table structure for `sys_user`
	-- ----------------------------
	DROP TABLE IF EXISTS `sys_user`;
	CREATE TABLE `sys_user` (
	  `id` int(10) NOT NULL AUTO_INCREMENT,
	  `real_name` varchar(20) DEFAULT NULL,
	  `username` varchar(20) DEFAULT NULL,
	  `password` varchar(32) DEFAULT NULL,
	  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

	-- ----------------------------
	-- Records of sys_user
	-- ----------------------------
	INSERT INTO `sys_user` VALUES ('1', 'admin', 'admin', '20d8f66e87a46e21de78ea4efd661069', '2017-09-20 09:58:44');

	-- ----------------------------
	-- Table structure for `sys_user_role`
	-- ----------------------------
	DROP TABLE IF EXISTS `sys_user_role`;
	CREATE TABLE `sys_user_role` (
	  `id` int(10) NOT NULL AUTO_INCREMENT,
	  `user_id` int(10) DEFAULT NULL,
	  `role_id` int(10) DEFAULT NULL,
	  PRIMARY KEY (`id`)
	) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

	-- ----------------------------
	-- Records of sys_user_role
	-- ----------------------------
	INSERT INTO `sys_user_role` VALUES ('1', '1', '1');*/

	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.guojianghao.mapper.sys;

import com.guojianghao.entity.sys.SysUser;
import com.guojianghao.entity.sys.SysUserExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SysUserMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	long countByExample(SysUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int deleteByExample(SysUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int insert(SysUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int insertSelective(SysUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	List<SysUser> selectByExample(SysUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	SysUser selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int updateByPrimaryKeySelective(SysUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table sys_user
	 * @mbg.generated  Tue Sep 19 17:13:33 SGT 2017
	 */
	int updateByPrimaryKey(SysUser record);

	SysUser queryUsernameAndPassword(String username);

	List<SysUser> getSysUserList(Map<String, Object> map);

	int getSysUserCount(Map<String, Object> map);

}

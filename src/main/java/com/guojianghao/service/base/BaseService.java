package com.guojianghao.service.base;

import java.util.List;

import com.guojianghao.entity.sys.SysUser;

public interface BaseService<T,V> {

	long countByExample(V example);

	int deleteByExample(V example);

	int deleteByPrimaryKey(Integer id);

	int insert(T record);

	int insertSelective(T record);

	List<SysUser> selectByExample(V example);

	SysUser selectByPrimaryKey(Integer id);

	int updateByExampleSelective(T record, V example);

	int updateByExample(T record, V example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(SysUser record);
}

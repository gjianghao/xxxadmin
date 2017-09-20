package com.guojianghao.service.base;

import java.util.List;

import com.guojianghao.entity.sys.SysUser;

//@Service
public class BaseServiceImpl<T,V> implements BaseService<T, V> {

	@Override
	public long countByExample(V example) {
		return 0;
	}

	@Override
	public int deleteByExample(V example) {
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(T record) {
		return 0;
	}

	@Override
	public int insertSelective(T record) {
		return 0;
	}

	@Override
	public List<SysUser> selectByExample(V example) {
		return null;
	}

	@Override
	public SysUser selectByPrimaryKey(Integer id) {
		return null;
	}

	@Override
	public int updateByExampleSelective(T record, V example) {
		return 0;
	}

	@Override
	public int updateByExample(T record, V example) {
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(T record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SysUser record) {
		return 0;
	}

}

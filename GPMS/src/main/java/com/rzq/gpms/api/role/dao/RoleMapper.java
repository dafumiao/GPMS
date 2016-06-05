package com.rzq.gpms.api.role.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.role.domain.RoleCriteria;
import com.rzq.gpms.api.tree.domain.Tree;

public interface RoleMapper {

	int countByExample(RoleCriteria example);

	int deleteByExample(RoleCriteria example);

	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	int insertSelective(Role record);

	List<Role> selectByExampleWithRowbounds(RoleCriteria example,
			RowBounds rowBounds);

	List<Role> selectByExample(RoleCriteria example);

	Role selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Role record,
			@Param("example") RoleCriteria example);

	int updateByExample(@Param("record") Role record,
			@Param("example") RoleCriteria example);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<Tree> getRoleTree(int roleid);

	List<Role> getUserRole(Integer userid);
}
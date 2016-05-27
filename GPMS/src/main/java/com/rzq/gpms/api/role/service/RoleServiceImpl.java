package com.rzq.gpms.api.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rzq.gpms.api.role.dao.RoleMapper;
import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.tree.domain.Tree;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleDao;

	@Override
	public List<Tree> getRoleTree(Role role) {

		List<Tree> list = roleDao.getRoleTree(role.getId());

		return list;
	}

}

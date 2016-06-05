package com.rzq.gpms.api.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rzq.gpms.api.role.dao.RoleMapper;
import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.tree.domain.Tree;
import com.rzq.gpms.api.user.domain.User;

@Service
@Transactional
public class RoleServiceImpl {

	@Autowired
	private RoleMapper roleDao;

	// @Override
	public List<Tree> getRoleTree(Role role) {

		List<Tree> list = roleDao.getRoleTree(role.getId());

		return list;
	}

	// @Override
	public List<Role> getUserRole(User user) {
		// System.out.println("current user is" + user);
		// int id = user.getId();
		List<Role> list = roleDao.getUserRole(user.getId());
		// System.out.println("!!");
		return list;
	}
}

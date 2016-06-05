package com.rzq.gpms.api.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.rzq.gpms.api.role.dao.RoleMapper;
import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.role.domain.RoleCriteria;
import com.rzq.gpms.api.user.dao.UserMapper;
import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.user.domain.UserCriteria;

@Service
@Transactional
public class UserServiceImpl {

	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserMapper userDao1;
	@Autowired
	private RoleMapper roleDao;

	// @Override
	// public List<Role> getUserRole(User user) {
	// System.out.println("current user is" + user);
	// List<Role> list = userDao1.getUserRole(user.getId());
	// System.out.println("!!");
	// return list;
	// }

	// @Override
	public User findUserById(int id) {
		Assert.notNull(id);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.or().andIdEqualTo(id);
		User user = userDao.selectByPrimaryKey(id);
		if (user != null) {
			return user;
		} else {
			System.out.println("id不存在");
		}
		return null;
	}

	// @Override
	public User findUserByName(String name) {
		Assert.notNull(name);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.or().andNameEqualTo(name);
		List<User> userList = userDao.selectByExample(userCriteria);
		if (userList != null) {
			return userList.get(0);
		} else {
			System.out.println("姓名不存在");
			return null;
		}
	}

	// @Override
	public User findUserByNumber(int number) {
		Assert.notNull(number);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.or().andNumberEqualTo(number);
		List<User> userList = userDao.selectByExample(userCriteria);
		if (userList != null) {
			return userList.get(0);
		} else {
			System.out.println("学号或职工号不存在");
			return null;
		}

	}

	// @Override
	public User findUserByNumberAndRole(int number, String roleName) {
		Assert.notNull(number);
		RoleCriteria roleCriteria = new RoleCriteria();
		roleCriteria.or().andNameEqualTo(roleName);
		List<Role> role = roleDao.selectByExample(roleCriteria);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.or().andNumberEqualTo(number)
				.andRoleidEqualTo(role.get(0).getId());
		List<User> userList = userDao.selectByExample(userCriteria);
		// List<Role> list = userDao.getUserRole(userList.get(0).getId());
		if (userList != null) {
			return userList.get(0);
		} else {
			System.out.println("学号或职工号不存在");
			return null;
		}
	}
}

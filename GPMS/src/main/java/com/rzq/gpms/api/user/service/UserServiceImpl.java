package com.rzq.gpms.api.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.rzq.gpms.api.user.dao.UserMapper;
import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.user.domain.UserCriteria;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;

	@Override
	public User findUserById(int id) {
		Assert.notNull(id);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.or().andIdEqualTo(id);
		User user = userDao.selectByPrimaryKey(id);
		return user;
	}

	@Override
	public User findUserByName(String name) {
		Assert.notNull(name);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.or().andNameEqualTo(name);
		List<User> userList = userDao.selectByExample(userCriteria);
		return userList.get(0);
	}

	@Override
	public User findUserByNumber(int number) {
		Assert.notNull(number);
		UserCriteria userCriteria = new UserCriteria();
		userCriteria.or().andNumberEqualTo(number);
		List<User> userList = userDao.selectByExample(userCriteria);
		return userList.get(0);
	}
}

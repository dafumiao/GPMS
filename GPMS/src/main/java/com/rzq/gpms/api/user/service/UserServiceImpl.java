package com.rzq.gpms.api.user.service;

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
}

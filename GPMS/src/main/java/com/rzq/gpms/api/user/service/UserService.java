package com.rzq.gpms.api.user.service;

import com.rzq.gpms.api.user.domain.User;

public interface UserService {

	User findUserById(int id);

	User findUserByName(String name);

	User findUserByNumber(int number);

}

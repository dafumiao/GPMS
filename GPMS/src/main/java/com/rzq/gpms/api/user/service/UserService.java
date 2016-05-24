package com.rzq.gpms.api.user.service;

import java.util.List;

import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.user.domain.User;

public interface UserService {

	User findUserById(int id);

	User findUserByName(String name);

	User findUserByNumber(int number);

	List<Role> getUserRole(User user);

}

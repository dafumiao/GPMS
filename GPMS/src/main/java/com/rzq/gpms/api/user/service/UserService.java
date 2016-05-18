package com.rzq.gpms.api.user.service;

import org.springframework.stereotype.Service;

import com.rzq.gpms.api.user.domain.User;

public interface UserService {

	User findUserById(int id);

}

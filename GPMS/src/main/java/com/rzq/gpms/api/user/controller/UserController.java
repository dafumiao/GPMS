package com.rzq.gpms.api.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.user.service.UserService;

@Controller
public class UserController {

	private final Logger log = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/hello")
	public ModelAndView hello(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		User user = userService.findUserById(id);
		if (user != null) {
			modelAndView.addObject("name", user.getName());
		}
		return modelAndView;
	}

	@RequestMapping("/")
	public String success() {
		System.out.println(SUCCESS);
		return "login";
	}
}

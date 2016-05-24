package com.rzq.gpms.api.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.user.service.UserService;
import com.rzq.gpms.util.MD5Util;

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

	@ResponseBody
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public void login(@RequestParam("number") int number,
			@RequestParam("password") String password,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			User user = userService.findUserByNumber(number);
			if (user != null
					&& user.getPassword().equals(
							MD5Util.MD5ForPassword(password))) {
				user.setPassword(null);
				request.getSession().setAttribute("user", user);
				System.out.println("成功");
				out.print("ok");
			} else {
				System.out.println("失败");
				out.print("error");
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/userName", method = RequestMethod.GET)
	public void dispUserName(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		User user = null;
		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
			out.println(user.getName());
		} else {
			out.println("error");
		}
		out.flush();
	}

	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public void exit(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		request.getSession().invalidate();
		String retStr = "ok";
		out.println(retStr);
		out.flush();
	}

	@RequestMapping("/")
	public String success() {
		System.out.println(SUCCESS);
		return "login";
	}

}

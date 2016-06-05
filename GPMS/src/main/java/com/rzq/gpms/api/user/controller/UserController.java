package com.rzq.gpms.api.user.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzq.gpms.api.Result;
import com.rzq.gpms.api.user.dao.UserMapper;
import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.user.domain.UserCriteria;
import com.rzq.gpms.api.user.service.UserServiceImpl;
import com.rzq.gpms.util.MD5Util;

@Controller
public class UserController {

	private static final String SUCCESS = "success";

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	UserMapper userDao;

	@ResponseBody
	@RequestMapping(value = "/userLogin")
	public String login(@RequestParam("number") int number,
			@RequestParam("password") String password,
			@RequestParam("roleName") String roleName,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(number + " " + password + " " + roleName);
		User user = userService.findUserByNumberAndRole(number, roleName);
		System.out.println("mgy");
		if (user != null
				&& user.getPassword().equals(MD5Util.MD5ForPassword(password))) {
			user.setPassword(null);
			request.getSession().setAttribute("user", user);
			System.out.println("成功");
			return "ok";
		} else {
			System.out.println("失败");
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/userName", method = RequestMethod.GET)
	public String dispUserName(HttpServletRequest request,
			HttpServletResponse response) {
		User user = null;
		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
			return user.getName();
		} else {
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	public String getInfo(HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		if (request.getSession().getAttribute("user") != null) {
			User user = (User) request.getSession().getAttribute("user");
			// 设置日期格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mapper.setDateFormat(sdf);
			String retStr = mapper.writeValueAsString(user);
			// 打印用户信息
			System.out.println(retStr);
			return retStr;
		} else {
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getUser_android", method = RequestMethod.GET)
	public User getUser_android(@RequestParam("number") int number,
			HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {
		UserCriteria example = new UserCriteria();
		example.or().andNumberEqualTo(number).andRoleidEqualTo(2);
		List<User> list = userDao.selectByExample(example);
		return list.get(0);
	}

	@ResponseBody
	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public Result newUser(@RequestParam("regRoleid") int roleid,
			@RequestParam("regNumber") int number,
			@RequestParam("regName") String name,
			@RequestParam("regPassword") String password,
			@RequestParam("regBirthday") String birthday,
			@RequestParam("regDepartment") String tel,
			@RequestParam("regSex") String sex,
			@RequestParam("regDepartment") String department,
			@RequestParam("regEmail") String email, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException,
			ParseException {
		System.out.println(birthday);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String dateStr = birthday;
		Date birthdayDate = sdf.parse(dateStr);
		User user = new User();
		user.setRoleid(roleid);
		user.setNumber(number);
		user.setName(name);
		user.setPassword(MD5Util.MD5ForPassword(password));
		user.setBirthday(birthdayDate);
		user.setSex(sex);
		user.setDepartment(department);
		user.setTel(tel);
		user.setEmail(email);
		userDao.insert(user);
		Result result = new Result();
		result.setSuccess(true);
		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	public Result updateInfo(@RequestParam("id") int id,
			@RequestParam("roleid") int roleid,
			@RequestParam("number") int number,
			@RequestParam("name") String name,
			@RequestParam("password") String password,
			@RequestParam("birthday") String birthday,
			@RequestParam("tel") String tel, @RequestParam("sex") String sex,
			@RequestParam("department") String department,
			@RequestParam("email") String email, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException,
			ParseException {
		System.out.println(id + " " + roleid + " " + name + " " + password
				+ " " + birthday + " " + tel + " " + email);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = birthday;
		Date birthdayDate = sdf.parse(dateStr);
		User user = new User();
		user.setId(id);
		user.setRoleid(roleid);
		user.setNumber(number);
		user.setName(name);
		if (password != "") {
			user.setPassword(MD5Util.MD5ForPassword(password));
		} else {
			User preUser = userDao.selectByPrimaryKey(id);
			user.setPassword(preUser.getPassword());
		}
		user.setBirthday(birthdayDate);
		user.setSex(sex);
		user.setDepartment(department);
		user.setTel(tel);
		user.setEmail(email);
		userDao.updateByPrimaryKey(user);
		Result result = new Result();
		result.setSuccess(true);
		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/exit", method = RequestMethod.GET)
	public String exit(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		String retStr = "ok";
		return retStr;
	}

	@RequestMapping("/")
	public String success() {
		System.out.println(SUCCESS);
		return "login";
	}

}

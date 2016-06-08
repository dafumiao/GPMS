package com.rzq.gpms.api.theme.controller;

import java.io.IOException;
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
import com.rzq.gpms.api.DataGrid;
import com.rzq.gpms.api.Result;
import com.rzq.gpms.api.role.dao.RoleMapper;
import com.rzq.gpms.api.theme.dao.ThemeMapper;
import com.rzq.gpms.api.theme.domain.Theme;
import com.rzq.gpms.api.theme.domain.ThemeCriteria;
import com.rzq.gpms.api.user.dao.UserMapper;
import com.rzq.gpms.api.user.domain.User;

@Controller
public class ThemeController {

	@Autowired
	private RoleMapper roleDao;
	@Autowired
	private ThemeMapper themeDao;
	@Autowired
	private UserMapper userDao;

	@ResponseBody
	@RequestMapping(value = "/themeList", method = RequestMethod.POST)
	public DataGrid themeList() throws IOException {
		DataGrid dg = new DataGrid();
		ThemeCriteria example = new ThemeCriteria();
		List<Theme> themes = themeDao.selectByExample(example);
		for (int i = 0; i < themes.size(); i++) {
			themes.get(i).setTeacher(
					userDao.selectByPrimaryKey(themes.get(i).getTeacherid())
							.getName());
			if (themes.get(i).getStudentid() != null) {
				themes.get(i)
						.setStudent(
								userDao.selectByPrimaryKey(
										themes.get(i).getStudentid()).getName());
			}

		}
		dg.setTotal(themes.size());
		dg.setRows(themes);
		return dg;
	}

	@ResponseBody
	@RequestMapping(value = "/teacherThemeList", method = RequestMethod.POST)
	public String teacherThemeList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		DataGrid dg = new DataGrid();
		ThemeCriteria example = new ThemeCriteria();
		User user = (User) request.getSession().getAttribute("user");
		example.or().andTeacheridEqualTo(user.getId());
		List<Theme> themes = themeDao.selectByExample(example);
		for (int i = 0; i < themes.size(); i++) {
			themes.get(i).setTeacher(
					userDao.selectByPrimaryKey(themes.get(i).getTeacherid())
							.getName());
			if (themes.get(i).getStudentid() != null) {
				themes.get(i)
						.setStudent(
								userDao.selectByPrimaryKey(
										themes.get(i).getStudentid()).getName());
			}
		}
		dg.setTotal(themes.size());
		dg.setRows(themes);
		String retStr = mapper.writeValueAsString(dg);
		return retStr;
	}

	@ResponseBody
	@RequestMapping(value = "/studentsList", method = RequestMethod.POST)
	public DataGrid studentsList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DataGrid dg = new DataGrid();
		ThemeCriteria example = new ThemeCriteria();
		User user = (User) request.getSession().getAttribute("user");
		example.or().andTeacheridEqualTo(user.getId()).andStudentidIsNotNull();
		List<Theme> themes = themeDao.selectByExample(example);
		for (int i = 0; i < themes.size(); i++) {
			themes.get(i).setTeacher(
					userDao.selectByPrimaryKey(themes.get(i).getTeacherid())
							.getName());
			if (themes.get(i).getStudentid() != null) {
				themes.get(i)
						.setStudent(
								userDao.selectByPrimaryKey(
										themes.get(i).getStudentid()).getName());
			}
		}
		dg.setTotal(themes.size());
		dg.setRows(themes);
		return dg;
	}

	@ResponseBody
	@RequestMapping(value = "/themeUpload", method = RequestMethod.POST)
	public Result themeUpload(@RequestParam("name") String name,
			@RequestParam("content") String content,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		Theme theme = new Theme();
		theme.setTeacherid(user.getId());
		theme.setName(name);
		theme.setContent(content);
		theme.setStatus("等待选题");
		themeDao.insert(theme);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/themeDelete", method = RequestMethod.POST)
	public Result themeDelete(@RequestParam("id") int id)
			throws JsonProcessingException {
		System.out.println(id);
		themeDao.deleteByPrimaryKey(id);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getThemeInfo", method = RequestMethod.POST)
	public Theme getThemeInfo(@RequestParam("id") int id) {
		System.out.println("题目是：" + id);
		Theme theme = themeDao.selectByPrimaryKey(id);
		theme.setShowContent(theme.getContent());
		return theme;
	}

	@ResponseBody
	@RequestMapping(value = "/agreeStudent", method = RequestMethod.POST)
	public String agreeStudent(@RequestParam("id") int id)
			throws JsonProcessingException {
		System.out.println("id为" + id);
		Theme theme = themeDao.selectByPrimaryKey(id);
		ThemeCriteria example = new ThemeCriteria();
		example.or().andStudentidEqualTo(theme.getStudentid())
				.andStatusEqualTo("已选题");
		List<Theme> list = themeDao.selectByExample(example);
		if (list.isEmpty()) {
			theme.setStatus("已选题");
			themeDao.updateByPrimaryKey(theme);
			return "ok";
		} else {
			System.out.println("该生已选题！");
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/themeChoose", method = RequestMethod.GET)
	public String themeChoose(@RequestParam("id") int id,
			HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {
		System.out.println(id);
		Theme theme = themeDao.selectByPrimaryKey(id);
		User user = (User) request.getSession().getAttribute("user");
		ThemeCriteria example = new ThemeCriteria();
		example.or().andStudentidEqualTo(user.getId()).andStatusEqualTo("已选题");
		List<Theme> list = themeDao.selectByExample(example);
		if (list.isEmpty()) {
			theme.setStudentid(user.getId());
			theme.setStatus("待审理");
			themeDao.updateByPrimaryKey(theme);
			return "ok";
		} else {
			System.out.println("您已选题！");
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/themeChoose_android", method = RequestMethod.GET)
	public String themeChoose_android(@RequestParam("id") int id,
			@RequestParam("userid") int userid, HttpServletRequest request,
			HttpServletResponse response) throws JsonProcessingException {
		System.out.println(id);
		Theme theme = themeDao.selectByPrimaryKey(id);
		User user = userDao.selectByPrimaryKey(userid);
		ThemeCriteria example = new ThemeCriteria();
		example.or().andStudentidEqualTo(user.getId()).andStatusEqualTo("已选题");
		List<Theme> list = themeDao.selectByExample(example);
		if (list.isEmpty()) {
			theme.setStudentid(user.getId());
			theme.setStatus("待审理");
			themeDao.updateByPrimaryKey(theme);
			return "ok";
		} else {
			System.out.println("您已选题！");
			return "error";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/teacherList_android", method = RequestMethod.GET)
	public Theme teacherList_android(@RequestParam("userid") int userid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ThemeCriteria example = new ThemeCriteria();
		User user = userDao.selectByPrimaryKey(userid);
		example.or().andStudentidEqualTo(user.getId()).andStatusEqualTo("已选题");
		List<Theme> themes = themeDao.selectByExample(example);
		if (!themes.isEmpty()) {
			themes.get(0).setContacts(
					userDao.selectByPrimaryKey(themes.get(0).getTeacherid())
							.getName());
			themes.get(0).setTeacher(
					userDao.selectByPrimaryKey(themes.get(0).getTeacherid())
							.getName());
			return themes.get(0);
		} else {
			return null;
		}

	}
}

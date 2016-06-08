package com.rzq.gpms.api.message.controller;

import java.io.IOException;
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
import com.rzq.gpms.api.DataGrid;
import com.rzq.gpms.api.Result;
import com.rzq.gpms.api.message.dao.MessageMapper;
import com.rzq.gpms.api.message.domain.Message;
import com.rzq.gpms.api.message.domain.MessageCriteria;
import com.rzq.gpms.api.theme.dao.ThemeMapper;
import com.rzq.gpms.api.theme.domain.Theme;
import com.rzq.gpms.api.theme.domain.ThemeCriteria;
import com.rzq.gpms.api.user.dao.UserMapper;
import com.rzq.gpms.api.user.domain.User;

@Controller
public class MessageController {

	@Autowired
	private MessageMapper messageDao;
	@Autowired
	private ThemeMapper themeDao;
	@Autowired
	private UserMapper userDao;

	@ResponseBody
	@RequestMapping(value = "/contactsList", method = RequestMethod.POST)
	public DataGrid contactsList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DataGrid dg = new DataGrid();
		ThemeCriteria example = new ThemeCriteria();
		User user = (User) request.getSession().getAttribute("user");
		if (user.getRoleid() == 1) {
			example.or().andTeacheridEqualTo(user.getId())
					.andStatusEqualTo("已选题");
			List<Theme> themes = themeDao.selectByExample(example);
			for (int i = 0; i < themes.size(); i++) {
				if (themes.get(i).getStudentid() != null) {
					themes.get(i).setContacts(
							userDao.selectByPrimaryKey(
									themes.get(i).getStudentid()).getName());
					themes.get(i).setContactid(themes.get(i).getStudentid());
					themes.get(i).setStudent(
							userDao.selectByPrimaryKey(
									themes.get(i).getStudentid()).getName());
				}
			}
			dg.setTotal(themes.size());
			dg.setRows(themes);
		} else {
			example.or().andStudentidEqualTo(user.getId())
					.andStatusEqualTo("已选题");
			List<Theme> themes = themeDao.selectByExample(example);
			for (int i = 0; i < themes.size(); i++) {
				themes.get(i)
						.setContacts(
								userDao.selectByPrimaryKey(
										themes.get(i).getTeacherid()).getName());
				themes.get(i).setContactid(themes.get(i).getTeacherid());
			}
			dg.setTotal(themes.size());
			dg.setRows(themes);
		}
		return dg;
	}

	@ResponseBody
	@RequestMapping(value = "/contactsList_android", method = RequestMethod.GET)
	public DataGrid contactsList_android(@RequestParam("userid") int userid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		DataGrid dg = new DataGrid();
		ThemeCriteria example = new ThemeCriteria();
		User user = userDao.selectByPrimaryKey(userid);
		if (user.getRoleid() == 1) {
			example.or().andTeacheridEqualTo(user.getId())
					.andStatusEqualTo("已选题");
			List<Theme> themes = themeDao.selectByExample(example);
			for (int i = 0; i < themes.size(); i++) {
				if (themes.get(i).getStudentid() != null) {
					themes.get(i).setContacts(
							userDao.selectByPrimaryKey(
									themes.get(i).getStudentid()).getName());
					themes.get(i).setContactid(themes.get(i).getStudentid());
				}
			}
			dg.setTotal(themes.size());
			dg.setRows(themes);
		} else {
			example.or().andStudentidEqualTo(user.getId())
					.andStatusEqualTo("已选题");
			List<Theme> themes = themeDao.selectByExample(example);
			for (int i = 0; i < themes.size(); i++) {
				themes.get(i)
						.setContacts(
								userDao.selectByPrimaryKey(
										themes.get(i).getTeacherid()).getName());
				themes.get(i).setContactid(themes.get(i).getTeacherid());
			}
			dg.setTotal(themes.size());
			dg.setRows(themes);
		}
		return dg;
	}

	@ResponseBody
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public Result sendMessage(@RequestParam("content") String content,
			@RequestParam("receiverid") int receiverid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = (User) request.getSession().getAttribute("user");
		Message message = new Message();
		message.setSenderid(user.getId());
		message.setContent(content);
		message.setReceiverid(receiverid);
		message.setCurrentdate(new Date());
		messageDao.insert(message);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/sendMessage_android", method = RequestMethod.GET)
	public Result sendMessage_android(@RequestParam("content") String content,
			@RequestParam("receiverid") int receiverid,
			@RequestParam("userid") int userid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = userDao.selectByPrimaryKey(userid);
		Message message = new Message();
		message.setSenderid(user.getId());
		message.setContent(content);
		message.setReceiverid(receiverid);
		message.setCurrentdate(new Date());
		messageDao.insert(message);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/messagesReceiveList", method = RequestMethod.POST)
	public String messagesReceiveList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DataGrid dg = new DataGrid();
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(sdf);
		MessageCriteria example = new MessageCriteria();
		User user = (User) request.getSession().getAttribute("user");
		example.or().andReceiveridEqualTo(user.getId());
		example.setOrderByClause("currentdate");
		List<Message> messages = messageDao.selectByExample(example);
		if (!messages.isEmpty()) {
			for (int i = 0; i < messages.size(); i++) {
				messages.get(i).setSender(
						userDao.selectByPrimaryKey(
								messages.get(i).getSenderid()).getName());
			}
			dg.setTotal(messages.size());
			dg.setRows(messages);
			String retStr = mapper.writeValueAsString(dg);
			return retStr;
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/messagesReceiveList_android", method = RequestMethod.GET)
	public String messagesReceiveList_android(
			@RequestParam("userid") int userid, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DataGrid dg = new DataGrid();
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(sdf);
		MessageCriteria example = new MessageCriteria();
		User user = userDao.selectByPrimaryKey(userid);
		example.or().andReceiveridEqualTo(user.getId());
		example.setOrderByClause("currentdate");
		List<Message> messages = messageDao.selectByExample(example);
		if (!messages.isEmpty()) {
			for (int i = 0; i < messages.size(); i++) {
				messages.get(i).setSender(
						userDao.selectByPrimaryKey(
								messages.get(i).getSenderid()).getName());
			}
			dg.setTotal(messages.size());
			dg.setRows(messages);
			String retStr = mapper.writeValueAsString(dg);
			return retStr;
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/messagesSendList", method = RequestMethod.POST)
	public String messagesSendList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		DataGrid dg = new DataGrid();
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(sdf);
		MessageCriteria example = new MessageCriteria();
		User user = (User) request.getSession().getAttribute("user");
		example.or().andSenderidEqualTo(user.getId());
		example.setOrderByClause("currentdate");
		List<Message> messages = messageDao.selectByExample(example);
		if (!messages.isEmpty()) {
			for (int i = 0; i < messages.size(); i++) {
				messages.get(i).setReceiver(
						userDao.selectByPrimaryKey(
								messages.get(i).getReceiverid()).getName());
			}
			dg.setTotal(messages.size());
			dg.setRows(messages);
			String retStr = mapper.writeValueAsString(dg);
			return retStr;
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/messagesSendList_android", method = RequestMethod.GET)
	public String messagesSendList_android(@RequestParam("userid") int userid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		DataGrid dg = new DataGrid();
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(sdf);
		MessageCriteria example = new MessageCriteria();
		User user = userDao.selectByPrimaryKey(userid);
		example.or().andSenderidEqualTo(user.getId());
		example.setOrderByClause("currentdate");
		List<Message> messages = messageDao.selectByExample(example);
		if (!messages.isEmpty()) {
			for (int i = 0; i < messages.size(); i++) {
				messages.get(i).setReceiver(
						userDao.selectByPrimaryKey(
								messages.get(i).getReceiverid()).getName());
			}
			dg.setTotal(messages.size());
			dg.setRows(messages);
			String retStr = mapper.writeValueAsString(dg);
			return retStr;
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deleteMessage", method = RequestMethod.POST)
	public Result deleteMessage(@RequestParam("id") int id)
			throws JsonProcessingException {
		messageDao.deleteByPrimaryKey(id);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}
}

package com.rzq.gpms.api.calendar.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzq.gpms.api.Result;
import com.rzq.gpms.api.calendar.dao.CalendarMapper;
import com.rzq.gpms.api.calendar.domain.Calendar;
import com.rzq.gpms.api.calendar.domain.CalendarCriteria;
import com.rzq.gpms.api.role.dao.RoleMapper;
import com.rzq.gpms.api.user.dao.UserMapper;
import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.userFile.dao.UserFileMapper;

@Controller
public class CalendarController {

	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserFileMapper fileDao;
	@Autowired
	private RoleMapper roleDao;
	@Autowired
	private CalendarMapper calendarDao;

	@ResponseBody
	@RequestMapping(value = "/getCalendar", method = RequestMethod.POST)
	public String getCalendar(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(sdf);
		User user = (User) request.getSession().getAttribute("user");
		CalendarCriteria example = new CalendarCriteria();
		example.or().andOwneridEqualTo(user.getId());
		List<Calendar> list = calendarDao.selectByExample(example);
		if (!list.isEmpty()) {
			String retStr = mapper.writeValueAsString(list);
			return retStr;
		} else
			return null;
	}

	@ResponseBody
	@RequestMapping(value = "/getStudentCalendar")
	public String getStudentCalendars(@RequestParam("studentid") int studentid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mapper.setDateFormat(sdf);
		CalendarCriteria example = new CalendarCriteria();
		example.or().andOwneridEqualTo(studentid);
		List<Calendar> list = calendarDao.selectByExample(example);
		if (!list.isEmpty()) {
			String retStr = mapper.writeValueAsString(list);
			return retStr;
		} else
			return null;
	}

	@RequestMapping(value = "/studentSchedule_android")
	public String studentSchedule_android(@RequestParam("userid") int userid,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		User user = userDao.selectByPrimaryKey(userid);
		request.getSession().setAttribute("user", user);
		return "studentSchedule";
	}

	@ResponseBody
	@RequestMapping(value = "/getEventDate", method = RequestMethod.POST)
	public Calendar getEventDate(@RequestParam("selDate") String selDate,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println(selDate);
		Calendar calendar = new Calendar();
		calendar.setStart(selDate);
		return calendar;
	}

	@ResponseBody
	@RequestMapping(value = "/agreeEvent", method = RequestMethod.POST)
	public Result agreeEvent(@RequestParam("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
		Calendar calendar = calendarDao.selectByPrimaryKey(id);
		if (calendar.getStatus().equals("签到待批准")) {
			calendar.setStatus("批准签到");
			calendar.setTitle("【成功签到】" + calendar.getTitle().substring(7));
		} else {
			calendar.setStatus("批准请假");
			calendar.setTitle("【成功请假】" + calendar.getTitle().substring(7));
		}
		calendar.setColor("green");
		calendarDao.updateByPrimaryKey(calendar);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/deleteEvent", method = RequestMethod.POST)
	public Result deleteEvent(@RequestParam("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
		calendarDao.deleteByPrimaryKey(id);
		Result result = new Result();
		result.setSuccess(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/newEvent", method = RequestMethod.POST)
	public Result newEvent(@RequestParam("start") String start,
			@RequestParam("end") String end,
			@RequestParam("title") String title,
			@RequestParam("status") String status, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println(start + " " + end + " " + title + " " + status);
		User user = (User) request.getSession().getAttribute("user");
		CalendarCriteria example = new CalendarCriteria();
		example.or().andStartEqualTo(start).andStatusIsNotNull();
		List<Calendar> list = calendarDao.selectByExample(example);
		Result result = new Result();
		result.setSuccess(false);
		if (list.isEmpty()) {
			Calendar calendar = new Calendar();
			calendar.setStart(start);
			calendar.setEnd(end);
			calendar.setTitle(title);
			calendar.setOwnerid(user.getId());
			if (status != "") {
				if (status.equals("签到待批准")) {
					calendar.setStatus("签到待批准");
					calendar.setTitle("【签到待批准】" + title);
				} else if (status.equals("请假待批准")) {
					calendar.setStatus("请假待批准");
					calendar.setTitle("【请假待批准】" + title);
				}
				calendar.setColor("black");
			}
			calendarDao.insert(calendar);
			result.setSuccess(true);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/newEventStatus_android")
	public Result newEventStatus_android(@RequestParam("userid") int userid,
			@RequestParam("location") String location,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println(location);
		User user = userDao.selectByPrimaryKey(userid);
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String start = format.format(date);
		CalendarCriteria example = new CalendarCriteria();
		example.or().andStartEqualTo(start).andStatusIsNotNull();
		List<Calendar> list = calendarDao.selectByExample(example);
		Result result = new Result();
		result.setSuccess(false);
		if (list.isEmpty()) {
			Calendar calendar = new Calendar();
			calendar.setOwnerid(user.getId());
			calendar.setStart(start);
			calendar.setColor("black");
			calendar.setTitle("【签到待批准】【" + location + "】");
			calendarDao.insert(calendar);
			result.setSuccess(true);
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getTodayStatus_android")
	public String getTodayStatus_android(@RequestParam("userid") int userid,
			HttpServletRequest request, HttpServletResponse response) {
		User user = userDao.selectByPrimaryKey(userid);
		if (user != null) {
			CalendarCriteria example = new CalendarCriteria();
			example.or().andOwneridEqualTo(user.getId());
			List<Calendar> list = calendarDao.selectByExample(example);
			if (list.isEmpty()) {
				return null;
			} else {
				for (int i = 0; i < list.size(); i++) {
					String status = list.get(i).getStatus();
					if (status != null && status != "") {
						if (status.equals("批准签到")) {
							if (list.get(i).getTitle().substring(6) != null
									&& list.get(i).getTitle().substring(6) != "") {
								String str = list.get(i).getTitle()
										.substring(6);
								return "批准签到，" + str;
							} else
								return "批准签到，" + "";

						} else {
							if (list.get(i).getTitle().substring(7) != null
									&& list.get(i).getTitle().substring(7) != "") {
								String str = list.get(i).getTitle()
										.substring(7);
								return "签到待批准，" + str;
							} else
								return "签到待批准，" + "";
						}
					} else
						continue;
				}
			}
		} else
			System.out.println("用户未登录");
		return null;
	}
}

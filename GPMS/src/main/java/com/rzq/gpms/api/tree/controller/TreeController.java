package com.rzq.gpms.api.tree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzq.gpms.api.tree.domain.TreeSession;
import com.rzq.gpms.api.tree.service.TreeService;
import com.rzq.gpms.api.user.domain.User;

@Controller
public class TreeController {

	private final Logger log = LoggerFactory.getLogger(TreeController.class);
	private static final String SUCCESS = "success";

	@Autowired
	private TreeService treeService;

	@RequestMapping(value = "/getTree")
	public void getTree(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ClassNotFoundException, SQLException {
		PrintWriter out = response.getWriter();
		// jackson
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
			// 打印用户信息
			System.out.println(user);
			TreeSession treeSession = treeService.getTreeSession(user);
			String retStr = mapper
					.writeValueAsString(treeSession.getChildren());
			out.println(retStr);
		}
		out.flush();
	}

}

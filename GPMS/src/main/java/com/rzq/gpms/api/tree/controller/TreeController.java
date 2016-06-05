package com.rzq.gpms.api.tree.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzq.gpms.api.tree.domain.TreeSession;
import com.rzq.gpms.api.tree.service.TreeServiceImpl;
import com.rzq.gpms.api.user.domain.User;

@Controller
public class TreeController {

	@Autowired
	private TreeServiceImpl treeService;

	@ResponseBody
	@RequestMapping(value = "/getTree")
	public String getTree(HttpServletRequest request,
			HttpServletResponse response) throws ClassNotFoundException,
			SQLException, IOException {
		// jackson
		ObjectMapper mapper = new ObjectMapper();
		User user = null;
		String retStr = "";
		if (request.getSession().getAttribute("user") != null) {
			user = (User) request.getSession().getAttribute("user");
			TreeSession treeSession = treeService.getTreeSession(user);
			retStr = mapper.writeValueAsString(treeSession.getChildren());
		}
		return retStr;
	}

}

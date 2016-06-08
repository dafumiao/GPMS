package com.rzq.gpms.api.userFile.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rzq.gpms.api.DataGrid;
import com.rzq.gpms.api.role.dao.RoleMapper;
import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.userFile.dao.UserFileMapper;
import com.rzq.gpms.api.userFile.domain.UserFile;
import com.rzq.gpms.api.userFile.domain.UserFileCriteria;

@Controller
public class FileController {

	@Autowired
	private UserFileMapper fileDao;
	@Autowired
	private RoleMapper roleDao;

	@ResponseBody
	@RequestMapping(value = "/fileList", method = RequestMethod.POST)
	public String fileList() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(sdf);
		DataGrid dg = new DataGrid();
		UserFileCriteria example = new UserFileCriteria();
		List<UserFile> files = fileDao.selectByExample(example);
		for (int i = 0; i < files.size(); i++) {
			files.get(i).setOwner(
					roleDao.selectByPrimaryKey(files.get(i).getOwnerid())
							.getName());
		}
		dg.setTotal(files.size());
		dg.setRows(files);
		String retStr = mapper.writeValueAsString(dg);
		return retStr;
	}

	@ResponseBody
	@RequestMapping(value = "/fileDownload")
	public void fileDownload(@RequestParam("id") int id,
			HttpServletRequest request, HttpServletResponse response) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream fos = null;
		InputStream fis = null;
		UserFile userFile = fileDao.selectByPrimaryKey(id);
		if (userFile != null) {
			File downFile = new File(userFile.getPath());
			if (!downFile.exists()) {
				return;
			}
			try {
				fis = new FileInputStream(downFile);
				bis = new BufferedInputStream(fis);
				fos = response.getOutputStream();
				bos = new BufferedOutputStream(fos);
				response.setCharacterEncoding("utf-8");
				response.setContentType("multipart/form-data");
				response.setHeader("Content-Disposition",
						"attachment;fileName=" + userFile.getName());
				int byteRead = 0;
				byte[] buffer = new byte[8192];
				while ((byteRead = bis.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, byteRead);
				}
				bos.flush();
				fis.close();
				bis.close();
				fos.close();
				bos.close();
			} catch (Exception e) {
			}
		}
	}

	@ResponseBody
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(MultipartFile file, HttpServletRequest request)
			throws IOException {
		UserFile uFile = new UserFile();
		User user = (User) request.getSession().getAttribute("user");
		System.out.println(user.getName() + "上传文件:"
				+ file.getOriginalFilename());
		System.out.println();
		if (!file.getOriginalFilename().isEmpty()) {
			uFile.setCurrentdate(new Date());
			uFile.setOwnerid(user.getId());
			uFile.setPath("d:/毕业论文库/" + file.getOriginalFilename());
			uFile.setName(file.getOriginalFilename());
			System.out.println(uFile);
			fileDao.insert(uFile);
			FileUtils.writeByteArrayToFile(
					new File("d:/毕业论文库/" + file.getOriginalFilename()),
					file.getBytes());

			return "";
		}
		System.out.println("文件名为空！！！");
		return "";
	}
}

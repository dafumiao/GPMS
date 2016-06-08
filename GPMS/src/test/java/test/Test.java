package test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rzq.gpms.Application;
import com.rzq.gpms.api.role.dao.RoleMapper;
import com.rzq.gpms.api.user.dao.UserMapper;
import com.rzq.gpms.api.userFile.dao.UserFileMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class Test {

	@Autowired
	private RoleMapper dao;
	@Autowired
	private UserMapper userDao;
	@Autowired
	private UserFileMapper fileDao;

	@org.junit.Test
	public void test1() {
	}
}

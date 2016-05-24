package test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rzq.gpms.Application;
import com.rzq.gpms.api.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class Test {

	@Autowired
	private UserService userService;

	@org.junit.Test
	public void test1() {
		System.out.println(userService.findUserByNumber(1));

	}
}

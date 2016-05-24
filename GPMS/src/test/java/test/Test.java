package test;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rzq.gpms.Application;
import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.role.service.RoleService;
import com.rzq.gpms.api.tree.domain.Tree;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class Test {

	@Autowired
	private RoleService service;

	@org.junit.Test
	public void test1() {

		Role role = new Role();
		role.setId(1);
		List<Tree> list = service.getRoleTree(role);
		System.out.println(list.get(0));
		System.out.println(list.get(1));
	}
}

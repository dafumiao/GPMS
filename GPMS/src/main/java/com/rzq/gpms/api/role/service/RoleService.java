package com.rzq.gpms.api.role.service;

import java.util.List;

import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.tree.domain.Tree;

public interface RoleService {

	List<Tree> getRoleTree(Role role);

}

package com.rzq.gpms.api.tree.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rzq.gpms.api.role.domain.Role;
import com.rzq.gpms.api.role.service.RoleService;
import com.rzq.gpms.api.role.service.RoleServiceImpl;
import com.rzq.gpms.api.tree.dao.TreeMapper;
import com.rzq.gpms.api.tree.domain.Tree;
import com.rzq.gpms.api.tree.domain.TreeCriteria;
import com.rzq.gpms.api.tree.domain.TreeSession;
import com.rzq.gpms.api.tree.domain.TreeSessionAttributes;
import com.rzq.gpms.api.user.domain.User;
import com.rzq.gpms.api.user.service.UserService;
import com.rzq.gpms.api.user.service.UserServiceImpl;

@Service
@Transactional
public class TreeServiceImpl implements TreeService {

	@Autowired
	private TreeMapper treeDao;

	public TreeSession getTreeSession(User user) throws ClassNotFoundException,
			SQLException, IOException {

		TreeSession treeSession = new TreeSession();

		// 根节点
		Tree rootTree = new Tree();
		rootTree.setId(0);
		if (user.getId() == 1) {
			Tree tree = this.getTree(rootTree); // 返回所有节点，管理员
			treeSession = this.getTreeSession(tree, treeSession, 1); // 生成前台节点
		} else {
			Map<Integer, List<Tree>> mMap = this.getTree(rootTree, user); // 普通用户，返回权限集合
			treeSession = this.getTreeSession(mMap, treeSession, 1); // 生成前台节点
		}
		return treeSession;
	}

	public Tree getTree(Tree tree) {
		tree.setChildren(new ArrayList<Tree>());
		TreeCriteria treeCriteria = new TreeCriteria();
		treeCriteria.or().andPidEqualTo(tree.getId())
				.andEnableEqualTo(tree.getEnable());
		treeCriteria.setOrderByClause("orderid");
		List<Tree> treeList = treeDao.selectByExample(treeCriteria);
		Tree ctree = treeList.get(0);
		tree.getChildren().add(ctree);
		if (!Boolean.parseBoolean(ctree.getIsleaf())) {
			getTree(ctree);
		}
		return tree;
	}

	public Map<Integer, List<Tree>> getTree(Tree tree, User user) {
		UserService userService = new UserServiceImpl();
		RoleService roleService = new RoleServiceImpl();
		Set<Integer> kSet = new HashSet<Integer>();
		Map<Integer, List<Tree>> mMap = new HashMap<Integer, List<Tree>>();

		List<Role> rlist = userService.getUserRole(user);
		for (int i = 0; i < rlist.size(); i++) {
			List<Tree> tlist = roleService.getRoleTree(rlist.get(i));
			for (int j = 0; j < tlist.size(); j++) {
				Tree ctree = tlist.get(j);
				if (!kSet.contains(ctree.getId())) {
					kSet.add(ctree.getId());
					if (!mMap.containsKey(ctree.getPid())) {
						List<Tree> vlist = new ArrayList<Tree>();
						vlist.add(ctree);
						mMap.put(ctree.getPid(), vlist);
					} else {
						List<Tree> vlist = (List<Tree>) mMap
								.get(ctree.getPid());
						vlist.add(ctree);
					}
				}
			}
		}

		return mMap;
	}

	public TreeSession getTreeSession(Tree tree, TreeSession treeSession,
			int level) {

		List<TreeSession> treeSessionChildren = new ArrayList<TreeSession>();
		treeSession.setChildren(treeSessionChildren);

		List<Tree> treeChildren = tree.getChildren();
		for (int i = 0; i < treeChildren.size(); i++) {
			Tree ctree = treeChildren.get(i);
			TreeSession cTreeSession = new TreeSession();
			cTreeSession.setId(ctree.getId());
			cTreeSession.setText(ctree.getName());
			if (level == 1 && !Boolean.parseBoolean(ctree.getIsleaf()))
				cTreeSession.setState("closed");
			else
				cTreeSession.setState("open");
			TreeSessionAttributes treeSessionAttributes = new TreeSessionAttributes();
			treeSessionAttributes.setUrl(ctree.getLink());
			cTreeSession.setAttributes(treeSessionAttributes);
			if (!Boolean.parseBoolean(ctree.getIsleaf())) {
				level++;
				getTreeSession(ctree, cTreeSession, level);
				level--;
			}
			treeSession.getChildren().add(cTreeSession);

		}
		return treeSession;
	}

	public TreeSession getTreeSession(Map<Integer, List<Tree>> mMap,
			TreeSession treeSession, int level) {

		if (!mMap.containsKey(treeSession.getId()))
			return treeSession;

		List<TreeSession> treeSessionChildren = new ArrayList<TreeSession>();
		treeSession.setChildren(treeSessionChildren);

		List<Tree> lt = (List<Tree>) mMap.get(treeSession.getId());

		for (int i = 0; i < lt.size(); i++) {
			Tree ctree = lt.get(i);
			TreeSession cTreeSession = new TreeSession();
			cTreeSession.setId(ctree.getId());
			cTreeSession.setText(ctree.getName());
			if (level == 1 && mMap.containsKey(ctree.getId()))
				cTreeSession.setState("closed");
			else
				cTreeSession.setState("open");
			TreeSessionAttributes treeSessionAttributes = new TreeSessionAttributes();
			treeSessionAttributes.setUrl(ctree.getLink());
			cTreeSession.setAttributes(treeSessionAttributes);
			if (mMap.containsKey(ctree.getId())) {
				level++;
				getTreeSession(mMap, cTreeSession, level);
				level--;
			}
			treeSession.getChildren().add(cTreeSession);
		}
		return treeSession;
	}
}

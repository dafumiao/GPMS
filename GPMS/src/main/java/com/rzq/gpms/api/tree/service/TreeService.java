package com.rzq.gpms.api.tree.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rzq.gpms.api.tree.domain.Tree;
import com.rzq.gpms.api.tree.domain.TreeSession;
import com.rzq.gpms.api.user.domain.User;

public interface TreeService {

	public TreeSession getTreeSession(User user) throws ClassNotFoundException,
			SQLException, IOException;

	public Tree getTree(Tree tree);

	public Map<Integer, List<Tree>> getTree(Tree tree, User user);

	public TreeSession getTreeSession(Tree tree, TreeSession treeSession,
			int level);

	public TreeSession getTreeSession(Map<Integer, List<Tree>> mMap,
			TreeSession treeSession, int level);

}

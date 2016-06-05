package com.rzq.gpms.api.tree.domain;

import java.util.List;

public class TreeSession {
	private int id;
	private String text;
	private String state;
	private boolean checked;
	private TreeSessionAttributes attributes;
	private List<TreeSession> children;

	public TreeSession() {
		super();
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TreeSessionAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(TreeSessionAttributes attributes) {
		this.attributes = attributes;
	}

	public List<TreeSession> getChildren() {
		return children;
	}

	public void setChildren(List<TreeSession> children) {
		this.children = children;
	}
}

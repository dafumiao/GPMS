package com.rzq.gpms.api.message.domain;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

	private Integer id;

	private String transforttype;

	private Integer receiverid;

	private Integer senderid;

	private String content;

	private Date currentdate;

	private String sender;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTransforttype() {
		return transforttype;
	}

	public void setTransforttype(String transforttype) {
		this.transforttype = transforttype == null ? null : transforttype
				.trim();
	}

	public Integer getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(Integer receiverid) {
		this.receiverid = receiverid;
	}

	public Integer getSenderid() {
		return senderid;
	}

	public void setSenderid(Integer senderid) {
		this.senderid = senderid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getCurrentdate() {
		return currentdate;
	}

	public void setCurrentdate(Date currentdate) {
		this.currentdate = currentdate;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", transforttype=").append(transforttype);
		sb.append(", receiverid=").append(receiverid);
		sb.append(", senderid=").append(senderid);
		sb.append(", content=").append(content);
		sb.append(", currentdate=").append(currentdate);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}
package com.kks.txtest.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Post {
	public String title;
	public long no;
	public String createBy;
	public String updateBy;
	public String createTmstp;
	public String updateTmstp;
	public String content;
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateTmstp() {
		return createTmstp;
	}
	public void setCreateTmstp(String createTmstp) {
		this.createTmstp = createTmstp;
	}
	public String getUpdateTmstp() {
		return updateTmstp;
	}
	public void setUpdateTmstp(String updateTmstp) {
		this.updateTmstp = updateTmstp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}

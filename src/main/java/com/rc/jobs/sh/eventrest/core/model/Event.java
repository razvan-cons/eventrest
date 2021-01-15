package com.rc.jobs.sh.eventrest.core.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;

	public int id;
	public String title;
	public String description;
	public Date kickOffTime;
	public int categoryId;

	public Event() {
		super();
	}

	public Event(int id, String title, String description, Date kickOffTime, int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.kickOffTime = kickOffTime;
		this.categoryId = categoryId;
	}

	public Event(String title, String description, Date kickOffTime, int categoryId) {
		super();
		this.title = title;
		this.description = description;
		this.kickOffTime = kickOffTime;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getKickOffTime() {
		return kickOffTime;
	}

	public void setKickOffTime(Date kickOffTime) {
		this.kickOffTime = kickOffTime;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	@JsonIgnore
	public List<Object> getValues() {
		return Arrays.asList(new Object[] { 
				this.title,
				this.description,
				this.kickOffTime,
				this.categoryId });
	}
}
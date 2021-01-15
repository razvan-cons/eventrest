package com.rc.jobs.sh.eventrest.core.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	public int id;
	public String name;
	public String description;
	public Integer parentCategoryId;
	public Integer categoryLevel;

	public Category() {
		super();
	}

	public Category(int id, String name, String description, Integer parentCategoryId, Integer categoryLevel) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentCategoryId = parentCategoryId != null && parentCategoryId > 0 ? parentCategoryId : null;
		this.categoryLevel = categoryLevel;
	}
	
	public Category(int id, String name, String description, Integer parentCategoryId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.parentCategoryId = parentCategoryId != null && parentCategoryId > 0 ? parentCategoryId : null;
	}

	public Category(String name, String description, Integer parentCategoryId) {
		super();
		this.name = name;
		this.description = description;
		this.parentCategoryId = parentCategoryId != null && parentCategoryId > 0 ? parentCategoryId : null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}
	
	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	@JsonIgnore
	public List<Object> getValues() {
		return Arrays.asList(new Object[] { 
				this.name,
				this.description,
				this.parentCategoryId});
	}
}
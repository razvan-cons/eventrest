package com.rc.jobs.sh.eventrest.repository;

import java.util.List;

import com.rc.jobs.sh.eventrest.core.exceptions.RepositoryException;
import com.rc.jobs.sh.eventrest.core.model.Category;

public interface CategoryRepository {

	Category get(int categoryId) throws RepositoryException;

	List<Category> getAll() throws RepositoryException;

	int add(Category category) throws RepositoryException;

	void update(Category category) throws RepositoryException;

	void remove(int categoryId) throws RepositoryException;
}
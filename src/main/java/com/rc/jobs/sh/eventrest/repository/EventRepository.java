package com.rc.jobs.sh.eventrest.repository;

import java.util.List;

import com.rc.jobs.sh.eventrest.core.exceptions.RepositoryException;
import com.rc.jobs.sh.eventrest.core.model.Category;
import com.rc.jobs.sh.eventrest.core.model.Event;

public interface EventRepository {

	Event get(int eventId) throws RepositoryException;

	List<Event> getAll() throws RepositoryException;

	List<Event> getByCategory(int categoryId) throws RepositoryException;
	
	List<Category> getBreadcrumb(int eventId) throws RepositoryException;

	int add(Event event) throws RepositoryException;

	void update(Event event) throws RepositoryException;

	void remove(int eventId) throws RepositoryException;
}
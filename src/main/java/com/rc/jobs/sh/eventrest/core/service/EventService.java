package com.rc.jobs.sh.eventrest.core.service;

import java.util.List;

import com.rc.jobs.sh.eventrest.core.exceptions.EventRestApiException;
import com.rc.jobs.sh.eventrest.core.exceptions.RepositoryException;
import com.rc.jobs.sh.eventrest.core.model.Category;
import com.rc.jobs.sh.eventrest.core.model.Event;
import com.rc.jobs.sh.eventrest.repository.EventRepository;

public class EventService {

	private EventRepository eventRepository;

	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	public Event get(int id) throws EventRestApiException {
		try {
			return eventRepository.get(id);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}

	public List<Event> getAll() throws EventRestApiException {
		try {
			return eventRepository.getAll();
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}

	public List<Event> getByCategoryId(int categoryId) throws EventRestApiException {
		try {
			return eventRepository.getByCategory(categoryId);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
	
	public List<Category> getBreadcrumb(int eventId) throws EventRestApiException {
		try {
			return eventRepository.getBreadcrumb(eventId);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
	
	public void remove(int eventId) throws EventRestApiException {
		try {
			eventRepository.remove(eventId);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
	
	public int add(Event event) throws EventRestApiException {
		try {
			return eventRepository.add(event);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
	
	public void update(Event event) throws EventRestApiException {
		try {
			eventRepository.update(event);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
}
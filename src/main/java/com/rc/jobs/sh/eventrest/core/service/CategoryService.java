package com.rc.jobs.sh.eventrest.core.service;

import java.util.List;

import com.rc.jobs.sh.eventrest.core.exceptions.EventRestApiException;
import com.rc.jobs.sh.eventrest.core.exceptions.RepositoryException;
import com.rc.jobs.sh.eventrest.core.model.Category;
import com.rc.jobs.sh.eventrest.repository.CategoryRepository;

public class CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category get(int id) throws EventRestApiException {
		try {
			return categoryRepository.get(id);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}

	public List<Category> getAll() throws EventRestApiException {
		try {
			return categoryRepository.getAll();
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}

	public void remove(int categoryId) throws EventRestApiException {
		try {
			categoryRepository.remove(categoryId);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
	
	public int add(Category category) throws EventRestApiException {
		try {
			return categoryRepository.add(category);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
	
	public void update(Category category) throws EventRestApiException {
		try {
			categoryRepository.update(category);
		} catch (RepositoryException repositoryException) {
			throw new EventRestApiException(repositoryException);
		}
	}
}
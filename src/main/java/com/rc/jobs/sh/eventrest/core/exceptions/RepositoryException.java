package com.rc.jobs.sh.eventrest.core.exceptions;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public RepositoryException(Exception exception) {
		super(exception);
	}
	
	public RepositoryException(String message) {
		super(message);
	}
}
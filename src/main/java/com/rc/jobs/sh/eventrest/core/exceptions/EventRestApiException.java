package com.rc.jobs.sh.eventrest.core.exceptions;

public class EventRestApiException extends Exception {

	private static final long serialVersionUID = 1L;

	public EventRestApiException(Exception exception) {
		super(exception);
	}
	
	public EventRestApiException(String message) {
		super(message);
	}
}
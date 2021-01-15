package com.rc.jobs.sh.eventrest.controllers;

import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.rc.jobs.sh.eventrest.core.exceptions.EventRestApiException;
import com.rc.jobs.sh.eventrest.core.model.Category;
import com.rc.jobs.sh.eventrest.core.model.Event;
import com.rc.jobs.sh.eventrest.core.service.EventService;

@Path("/events")
public class EventController {

	private EventService eventService;

	public EventController(EventService eventService) throws NamingException {
		this.eventService = eventService;
	}

	/**
	 * URL: http://localhost:8080/eventrest/api/events
	 * 
	 * @return Response list Events
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEvents() {
		try {
			List<Event> events = eventService.getAll();
			return Response.ok(events).build();
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}

	/**
	 * URL: http://localhost:8080/eventrest/api/events/1
	 * 
	 * @param id Integer
	 * @return Response Event
	 */
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventById(@PathParam("id") int id) {
		try {
			Event event = eventService.get(id);
			if (event == null) {
				return Response.status(Status.NO_CONTENT).entity("Event not found").build();
			} else {
				return Response.ok(event).build();
			}
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}		
	}
	
	/**
	 * URL: http://localhost:8080/eventrest/api/events/1
	 * 
	 * @param id Integer
	 * @return Response Event
	 */
	@GET
	@Path("/{id}/breadcrumb")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventBreadcrumb(@PathParam("id") int id) {
		try {
			List<Category> breadcrumbCategories = eventService.getBreadcrumb(id);
			if (breadcrumbCategories == null) {
				return Response.status(Status.NO_CONTENT).entity("Event breadcrumb not found").build();
			} else {
				return Response.ok(breadcrumbCategories).build();
			}
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}		
	}

	/**
	 * URL: http://localhost:8080/eventrest/api/events/category/1
	 * 
	 * @param id Integer
	 * @return Response list Events by category ID
	 */
	@GET
	@Path("/category/{categoryId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEventsByCategoryId(@PathParam("categoryId") int categoryId) {
		try {
			List<Event> events = eventService.getByCategoryId(categoryId);
			return Response.ok(events).build();
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	/**
	 * URL: http://localhost:8080/eventrest/api/events
	 * 
	 * @param event Event
	 * @return newEventId Integer
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEvent(Event event) {
		try {
			if (event == null) {
				return Response.status(Status.NO_CONTENT).entity("Event not found").build();
			} else {
				int newEventId = eventService.add(event);
				return Response.ok(newEventId).build();
			}
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	/**
	 * URL: http://localhost:8080/eventrest/api/categories
	 * 
	 * @param event Event
	 * @return newEventId Integer
	 */
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEvent(Event event) {
		try {
			if (event == null) {
				return Response.status(Status.NO_CONTENT).entity("Event not found").build();
			} else {
				eventService.update(event);
				return Response.ok().build();
			}
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	/**
	 * URL: http://localhost:8080/eventrest/api/categories/1
	 * 
	 * @param id Integer
	 * @return Response Category
	 */
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeEvent(@PathParam("id") int id) {
		try {
			eventService.remove(id);
			return Response.ok().build();
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}
}
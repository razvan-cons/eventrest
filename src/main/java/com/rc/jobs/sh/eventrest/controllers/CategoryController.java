package com.rc.jobs.sh.eventrest.controllers;

import java.util.List;

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
import com.rc.jobs.sh.eventrest.core.service.CategoryService;

@Path("/categories")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * URL: http://localhost:8080/eventrest/api/categories
	 * 
	 * @return Response list Categories
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCategories() {
		try {
			List<Category> categories = categoryService.getAll();
			return Response.ok(categories).build();
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
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoryById(@PathParam("id") int id) {
		try {
			Category category = categoryService.get(id);
			if (category == null) {
				return Response.status(Status.NO_CONTENT).entity("Category not found").build();
			} else {
				return Response.ok(category).build();
			}
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	/**
	 * URL: http://localhost:8080/eventrest/api/categories
	 * 
	 * @param category Category
	 * @return newCategoryId Integer
	 */
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategory(Category category) {
		try {
			if (category == null) {
				return Response.status(Status.NO_CONTENT).entity("Category not found").build();
			} else {
				int newCategoryId = categoryService.add(category);
				return Response.ok(newCategoryId).build();
			}
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	/**
	 * URL: http://localhost:8080/eventrest/api/categories
	 * 
	 * @param category Category
	 * @return newCategoryId Integer
	 */
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCategory(Category category) {
		try {
			if (category == null) {
				return Response.status(Status.NO_CONTENT).entity("Category not found").build();
			} else {
				categoryService.update(category);
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
	public Response removeCategory(@PathParam("id") int id) {
		try {
			categoryService.remove(id);
			return Response.ok().build();
		} catch (EventRestApiException exception) {
			exception.printStackTrace();
			return Response.serverError().build();
		}
	}
}
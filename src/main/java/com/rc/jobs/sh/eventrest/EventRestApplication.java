package com.rc.jobs.sh.eventrest;

import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.rc.jobs.sh.eventrest.controllers.CategoryController;
import com.rc.jobs.sh.eventrest.controllers.EventController;
import com.rc.jobs.sh.eventrest.core.service.CategoryService;
import com.rc.jobs.sh.eventrest.core.service.EventService;
import com.rc.jobs.sh.eventrest.repository.CategoryRepository;
import com.rc.jobs.sh.eventrest.repository.EventRepository;
import com.rc.jobs.sh.eventrest.repository.connectors.DatabaseConnector;
import com.rc.jobs.sh.eventrest.repository.connectors.JdbcAwsRdsConnector;
import com.rc.jobs.sh.eventrest.repository.implementations.sql.AwsRdsCategoryRepository;
import com.rc.jobs.sh.eventrest.repository.implementations.sql.AwsRdsEventRepository;

@ApplicationPath("/api")
public class EventRestApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public EventRestApplication() throws NamingException {
		DatabaseConnector databaseConnector = new JdbcAwsRdsConnector();
		
		CategoryRepository categoryRepository = new AwsRdsCategoryRepository(databaseConnector);
		CategoryService categoryService = new CategoryService(categoryRepository);
		
		EventRepository eventRepository = new AwsRdsEventRepository(databaseConnector);
		EventService eventService = new EventService(eventRepository);
		
        singletons.add(new CategoryController(categoryService));
		singletons.add(new EventController(eventService));
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
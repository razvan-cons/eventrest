package com.rc.jobs.sh.eventrest.repository.implementations.sql;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.naming.NamingException;

import com.rc.jobs.sh.eventrest.core.exceptions.RepositoryException;
import com.rc.jobs.sh.eventrest.core.model.Category;
import com.rc.jobs.sh.eventrest.core.model.Event;
import com.rc.jobs.sh.eventrest.repository.EventRepository;
import com.rc.jobs.sh.eventrest.repository.connectors.DatabaseConnector;

public final class AwsRdsEventRepository implements EventRepository {

	private DatabaseConnector connector;

	public AwsRdsEventRepository(DatabaseConnector connector) throws NamingException {
		this.connector = connector;
	}

	@Override
	public Event get(int eventId) throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getSelectFiltered(EventMapper.tableName, EventMapper.id,
					String.valueOf(eventId));
			List<Event> events = connector.sqlQuery(sqlQuery, new EventMapper());
			return events.size() > 0 ? events.stream().findFirst().get() : null;

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public List<Event> getAll() throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getSelect(EventMapper.tableName);
			List<Event> events = connector.sqlQuery(sqlQuery, new EventMapper());
			return events;

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public List<Event> getByCategory(int categoryId) throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getSelectFiltered(EventMapper.tableName, EventMapper.categoryId,
					String.valueOf(categoryId));
			List<Event> events = connector.sqlQuery(sqlQuery, new EventMapper());
			return events;

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}
	
	@Override
	public List<Category> getBreadcrumb(int eventId) throws RepositoryException {
		try {
			List<Category> breadcrumbCategories = connector.storedProcedure(EventMapper.getEventBreadcrumbSp, Arrays.asList(eventId), new CategoryMapper());
			return breadcrumbCategories;
			
		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public int add(Event event) throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getInsert(EventMapper.tableName, EventMapper.getColumnNames(), event.getValues());
			List<Integer> newEventIds = connector.sqlQuery(sqlQuery, new IntResultMapper());
			return newEventIds.get(0);

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public void update(Event event) throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getUpdate(EventMapper.tableName, EventMapper.id, event.id, EventMapper.getColumnNames(), event.getValues());
			connector.sqlQuery(sqlQuery, null);

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public void remove(int eventId) throws RepositoryException {
		String sqlQuery = SqlQueryBuilder.getDeleteFiltered(EventMapper.tableName, EventMapper.id,
				String.valueOf(eventId));
		try {
			connector.sqlQuery(sqlQuery, new EventMapper());
		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}
}

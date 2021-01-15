package com.rc.jobs.sh.eventrest.repository.implementations.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.rc.jobs.sh.eventrest.core.model.Event;
import com.rc.jobs.sh.eventrest.repository.RowMapper;

public class EventMapper implements RowMapper<Event> {

	public static final String tableName = SqlQueryBuilder.getFullDboTableName("event");
	public static final String getEventBreadcrumbSp = "get_event_breadcrumb";
	
	public static final String id = "idEvent";
	public static final String title = "title";
	public static final String description = "description";
	public static final String kickOffTime = "kickOffTime";
	public static final String categoryId = "categoryId";

	@Override
	public Event mapRow(ResultSet resultSet, int rownumber) throws SQLException {
		return new Event(resultSet.getInt(id), resultSet.getString(title), resultSet.getString(description),
				resultSet.getDate(kickOffTime), resultSet.getInt(categoryId));
	}

	public static List<String> getColumnNames() {
		return Arrays.asList(new String[] { title, description, kickOffTime, categoryId });
	}
}
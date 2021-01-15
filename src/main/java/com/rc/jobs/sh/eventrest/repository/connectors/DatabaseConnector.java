package com.rc.jobs.sh.eventrest.repository.connectors;

import java.sql.SQLException;
import java.util.List;

import com.rc.jobs.sh.eventrest.repository.RowMapper;

public interface DatabaseConnector {

	<T> List<T> sqlQuery(String queryString, RowMapper<T> rowMapper) throws SQLException;
	<T> List<T> storedProcedure(String storedProcedureName, List<Object> params, RowMapper<T> rowMapper) throws SQLException;
}
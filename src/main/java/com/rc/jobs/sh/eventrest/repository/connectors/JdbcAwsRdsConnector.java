package com.rc.jobs.sh.eventrest.repository.connectors;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.rc.jobs.sh.eventrest.repository.RowMapper;
import com.rc.jobs.sh.eventrest.repository.implementations.sql.SqlQueryBuilder;

public final class JdbcAwsRdsConnector implements DatabaseConnector {

	private static final String contextResource = "java:comp/env";
	private static final String jdbcAwsRdsContextResource = "jdbc/aws/rds/events";

	private DataSource datasource = null;

	public JdbcAwsRdsConnector() throws NamingException {
		Context context = new InitialContext();
		context = (Context) context.lookup(contextResource);
		datasource = (DataSource) context.lookup(jdbcAwsRdsContextResource);
	}

	@Override
	public <T> List<T> sqlQuery(String queryString, RowMapper<T> rowMapper) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		List<T> resultList = new ArrayList<T>();

		try {

			connection = datasource.getConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(queryString);

			if (rowMapper == null) return resultList;
			
			while (resultSet.next()) {
				resultList.add(rowMapper.mapRow(resultSet, resultSet.getRow()));
			}

		} catch (SQLException sqlEx) {
			throw sqlEx;
		} finally {
			if (statement != null && !statement.isClosed())
				statement.close();
			if (connection != null && !connection.isClosed())
				connection.close();
		}

		return resultList;
	}
	
	@Override
	public <T> List<T> storedProcedure(String storedProcedureName, List<Object> params, RowMapper<T> rowMapper) throws SQLException {
		Connection connection = null;
		CallableStatement statement = null;
		List<T> resultList = new ArrayList<T>();

		try {

			connection = datasource.getConnection();
			String storedProcedure = SqlQueryBuilder.getCallStoredProc(storedProcedureName, params.size());
			statement = connection.prepareCall(storedProcedure);
			
			for(int i = 0; i < params.size(); i++) {
				if(params.get(i) instanceof Integer) statement.setInt(i+1, (Integer)params.get(i));
				else statement.setString(i+1, String.valueOf(params.get(i)));
			}
			
			statement.execute();
			ResultSet resultSet = statement.getResultSet();

			if (rowMapper == null) return resultList;
			
			while (resultSet.next()) {
				resultList.add(rowMapper.mapRow(resultSet, resultSet.getRow()));
			}

		} catch (SQLException sqlEx) {
			throw sqlEx;
		} finally {
			if (statement != null && !statement.isClosed())
				statement.close();
			if (connection != null && !connection.isClosed())
				connection.close();
		}

		return resultList;
	}
}
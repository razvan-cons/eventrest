package com.rc.jobs.sh.eventrest.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
	T mapRow(ResultSet resultSet, int rowNum) throws SQLException;
}
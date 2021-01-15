package com.rc.jobs.sh.eventrest.repository.implementations.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rc.jobs.sh.eventrest.repository.RowMapper;

public class IntResultMapper implements RowMapper<Integer> {

	@Override
	public Integer mapRow(ResultSet resultSet, int rownumber) throws SQLException {
		return resultSet.getInt(0);
	}
}
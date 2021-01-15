package com.rc.jobs.sh.eventrest.repository.implementations.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.rc.jobs.sh.eventrest.core.model.Category;
import com.rc.jobs.sh.eventrest.repository.RowMapper;

public class CategoryMapper implements RowMapper<Category> {

	public static final String tableName = SqlQueryBuilder.getFullDboTableName("category");

	public static final String id = "idCategory";
	public static final String name = "name";
	public static final String description = "description";
	public static final String parentCategoryId = "parentCategoryId";
	public static final String categoryLevel = "categoryLevel";

	@Override
	public Category mapRow(ResultSet resultSet, int rownumber) throws SQLException {
		return new Category(resultSet.getInt(id), resultSet.getString(name), resultSet.getString(description),
				resultSet.getInt(parentCategoryId), getCategoryLevelFromResultSet(resultSet));
	}

	public static List<String> getColumnNames() {
		return Arrays.asList(new String[] { name, description, parentCategoryId });
	}
	
	private Integer getCategoryLevelFromResultSet(ResultSet resultSet) {
		try {
			if (resultSet.findColumn(categoryLevel) >= 0) return resultSet.getInt(categoryLevel);
		} catch (Exception ex) {}
		
		return null;
	}
}
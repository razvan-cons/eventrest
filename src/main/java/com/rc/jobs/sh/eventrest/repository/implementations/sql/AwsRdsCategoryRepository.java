package com.rc.jobs.sh.eventrest.repository.implementations.sql;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.rc.jobs.sh.eventrest.core.exceptions.RepositoryException;
import com.rc.jobs.sh.eventrest.core.model.Category;
import com.rc.jobs.sh.eventrest.repository.CategoryRepository;
import com.rc.jobs.sh.eventrest.repository.connectors.DatabaseConnector;

public final class AwsRdsCategoryRepository implements CategoryRepository {

	private DatabaseConnector connector;

	public AwsRdsCategoryRepository(DatabaseConnector connector) throws NamingException {
		this.connector = connector;
	}

	@Override
	public Category get(int categoryId) throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getSelectFiltered(CategoryMapper.tableName, CategoryMapper.id,
					String.valueOf(categoryId));
			List<Category> categories = connector.sqlQuery(sqlQuery, new CategoryMapper());
			return categories.size() > 0 ? categories.stream().findFirst().get() : null;

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public List<Category> getAll() throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getSelect(CategoryMapper.tableName);
			List<Category> categories = connector.sqlQuery(sqlQuery, new CategoryMapper());
			return categories;

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public int add(Category category) throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getInsert(CategoryMapper.tableName, CategoryMapper.getColumnNames(), category.getValues());
			List<Integer> newCategoryIds = connector.sqlQuery(sqlQuery, new IntResultMapper());
			return newCategoryIds.get(0);

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public void update(Category category) throws RepositoryException {
		try {
			String sqlQuery = SqlQueryBuilder.getUpdate(CategoryMapper.tableName, CategoryMapper.id, category.id, CategoryMapper.getColumnNames(), category.getValues());
			connector.sqlQuery(sqlQuery, null);

		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}

	@Override
	public void remove(int categoryId) throws RepositoryException {
		String sqlQuery = SqlQueryBuilder.getDeleteFiltered(CategoryMapper.tableName, CategoryMapper.id,
				String.valueOf(categoryId));
		try {
			connector.sqlQuery(sqlQuery, new EventMapper());
		} catch (SQLException sqlException) {
			throw new RepositoryException(sqlException);
		}
	}
}

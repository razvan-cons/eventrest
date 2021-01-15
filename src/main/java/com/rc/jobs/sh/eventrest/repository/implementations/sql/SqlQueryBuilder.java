package com.rc.jobs.sh.eventrest.repository.implementations.sql;

import java.util.ArrayList;
import java.util.List;

public class SqlQueryBuilder {
	
	private static final String mainDbSchema = "dbo";
	
	public static String getFullDboTableName(String tableName) {
		return String.format("%1$s.%2$s", mainDbSchema, tableName);
	}
	
	public static String getSelect(String tableName) {
		return String.format("SELECT * FROM %1$s", tableName);
	}
	
	public static String getSelectFiltered(String tableName, String columnName, Object filterValue) {
		return String.format("SELECT * FROM %1$s WHERE %2$s = %3$s", tableName, columnName, filterValue);
	}
	
	public static String getDeleteFiltered(String tableName, String columnName, Object filterValue) {
		return String.format("DELETE FROM %1$s WHERE %2$s = %3$s", tableName, columnName, filterValue);
	}
	
	public static String getInsert(String tableName, List<String> columnNames, List<Object> values) {
		return String.format("INSERT INTO %1$s (%2$s) VALUES(%3$s)", tableName, getColumnCommaSeparatedList(columnNames), getCommaSeparatedList(values, true));
	}
	
	public static String getUpdate(String tableName, String columnName, Object filterValue, List<String> columnNames, List<Object> values) {
		return String.format("UPDATE %1$s SET %2$s WHERE %3$s = %4$s", tableName, pairColumnsAndValues(columnNames, values), columnName, filterValue);
	}
	
	public static String getCallStoredProc(String name, int paramCount) {
		List<String> paramQuestionMarks = new ArrayList<String>();
		for (int i = 0; i < paramCount; i++) {
			paramQuestionMarks.add("?");
		}
		return String.format("CALL %1$s.%2$s (%3$s)", mainDbSchema, name, String.join(", ", paramQuestionMarks));
	}
	
	public static String getCommaSeparatedList(List<Object> values, boolean singleQuotationStrings) {
		final String delimiter = ", ";
		String valuesStr = "";
		String quotation = "";
		
		for(Object v : values) {
			if (singleQuotationStrings && !(v instanceof Integer)) quotation = "'";
			else quotation = "";
			valuesStr += quotation + String.valueOf(v) + quotation;
			if(values.indexOf(v) < (values.size() -1)) valuesStr += delimiter;  
		}
		
		return valuesStr;
	}
	
	public static String getColumnCommaSeparatedList(List<String> columns) {
		return getCommaSeparatedList(new ArrayList<Object>(columns), false);
	}
	
	public static String pairColumnsAndValues(List<String> columnNames, List<Object> values) {
		final String delimiter = ", ";
		String pairedString = "";
		String quotation = "";
		
		for (int i = 0; i < columnNames.size(); i++) {
			if (!(values.get(i) instanceof Integer)) quotation = "'";
			else quotation = "";
			pairedString += columnNames.get(i) + "=" + quotation + values.get(i) + quotation + ",";
			if(i < (values.size() -1)) pairedString += delimiter;
		}
		
		return pairedString;
	}
}

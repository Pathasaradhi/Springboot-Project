package com.weshopify.ecom.mysql;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableMysqlDao implements Condition {

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		/*
		 * String dbType = System.getProperty("dbType");
		 * System.out.println("db type is:\t"+dbType); return dbType != null &&
		 * dbType.contentEquals("MYSQL");
		 */
		boolean enableMysql = false;
		try {
			Class<?> mysqlDriver = Class.forName("com.mysql.cj.jdbc.Driver");
			if(mysqlDriver != null) {
				System.out.println("Driver evalutaing by the Conditional is:\t"+mysqlDriver.getName());
				enableMysql = true;
			}
		} catch (ClassNotFoundException e) {
			enableMysql=false;
		}
		return enableMysql;
	}

}

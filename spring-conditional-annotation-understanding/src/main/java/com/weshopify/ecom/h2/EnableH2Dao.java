package com.weshopify.ecom.h2;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableH2Dao implements Condition {

	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		/*
		 * String dbType = System.getProperty("dbType");
		 * System.out.println("db type is:\t"+dbType); return dbType != null &&
		 * dbType.contentEquals("H2");
		 */
		boolean enableH2 = false;
		try {
			Class<?> h2Driver = Class.forName("org.h2.Driver");
			if(h2Driver != null) {
				System.out.println("Driver evalutaing by the Conditional is:\t"+h2Driver.getName());
				enableH2 = true;
			}
		} catch (ClassNotFoundException e) {
			enableH2=false;
		}
		return enableH2;
	}

}

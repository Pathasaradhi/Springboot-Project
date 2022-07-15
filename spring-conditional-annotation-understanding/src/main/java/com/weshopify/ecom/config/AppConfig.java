package com.weshopify.ecom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.weshopify.ecom.h2.EnableH2Dao;
import com.weshopify.ecom.h2.H2UsersDao;
import com.weshopify.ecom.mysql.EnableMysqlDao;
import com.weshopify.ecom.mysql.MysqlUsersDao;

@Configuration
public class AppConfig {

	@Bean
	@Conditional(EnableMysqlDao.class)
	public UsersDao mysqlUsersDao() {
		return new MysqlUsersDao();
	}
	
	@Bean
	@Conditional(EnableH2Dao.class)
	public UsersDao h2UsersDao() {
		return new H2UsersDao();
	}
}

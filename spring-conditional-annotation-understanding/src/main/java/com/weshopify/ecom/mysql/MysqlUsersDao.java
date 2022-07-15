package com.weshopify.ecom.mysql;

import java.util.Arrays;
import java.util.List;

import com.weshopify.ecom.config.UsersDao;

public class MysqlUsersDao implements UsersDao {

	public List<String> getAllUserNames() {
		return Arrays.asList("mysql-user1","mysql-user2","mysql-user3");
	}

}

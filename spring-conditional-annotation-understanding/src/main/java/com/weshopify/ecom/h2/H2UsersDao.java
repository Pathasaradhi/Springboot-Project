package com.weshopify.ecom.h2;

import java.util.Arrays;
import java.util.List;

import com.weshopify.ecom.config.UsersDao;

public class H2UsersDao implements UsersDao {

	public List<String> getAllUserNames() {
		return Arrays.asList("h2-user1","h2-user2","h2-user3");
	}

}

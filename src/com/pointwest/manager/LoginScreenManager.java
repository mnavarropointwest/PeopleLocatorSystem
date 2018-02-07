package com.pointwest.manager;

import com.pointwest.bean.User;
import com.pointwest.dao.UserDao;
import com.pointwest.exception.LocatorException;

public class LoginScreenManager {
	public User retrieveUserByCred(String userName, String password) {
		User user = null;
		UserDao userDao = new UserDao();
		try {
		user = userDao.retrieveUserByCred(userName, password);
		}catch(LocatorException e) {
			throw e;
		}
		return user;
	}
}

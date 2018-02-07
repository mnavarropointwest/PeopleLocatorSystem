package com.pointwest.manager;

import com.pointwest.bean.Employee;
import com.pointwest.bean.User;
import com.pointwest.dao.EmployeeDao;
import com.pointwest.exception.LocatorException;

public class HomePageManager {
	public Employee retrieveEmployeeDetail(User userLogged) throws LocatorException {
		Employee employee = null;
		EmployeeDao empDao = new EmployeeDao();
		try {
			employee = empDao.retrieveEmployeeInfoByUser(userLogged);
		} catch (LocatorException e) {
			throw e;
		}
		return employee;
	}
}

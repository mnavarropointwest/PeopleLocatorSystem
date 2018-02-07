package com.pointwest.manager;

import java.util.List;

import com.pointwest.bean.Employee;
import com.pointwest.bean.Project;
import com.pointwest.dao.EmployeeDao;
import com.pointwest.dao.ProjectDao;

public class SearchPageManager {
	public List<Employee> retrieveListEmployeeById(String empId) {
		EmployeeDao empDao = new EmployeeDao();
		List<Employee> employeeList = empDao.retrieveEmployeeListById(empId);
		return employeeList;
	}

	//
	public List<Employee> retrieveListEmployeeByName(String name) {
		EmployeeDao empDao = new EmployeeDao();
		List<Employee> employeeList = empDao.retrieveEmployeeListByName(name);
		return employeeList;
	}

	//
	public List<Employee> retrieveListEmployeeByProjectName(String projectName) {
		EmployeeDao empDao = new EmployeeDao();
		List<Employee> employeeList = empDao.retrieveEmployeeListByProj(projectName);
		return employeeList;
	}

	//
	public List<Project> retrieveProjectChoices() {
		ProjectDao projectDao = new ProjectDao();
		List<Project> projectChoices = projectDao.retrieveProjectChoices();
		return projectChoices;
	}

}

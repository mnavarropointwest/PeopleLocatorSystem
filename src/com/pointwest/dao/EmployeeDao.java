package com.pointwest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pointwest.bean.Building;
import com.pointwest.bean.Employee;
import com.pointwest.bean.Seat;
import com.pointwest.bean.User;
import com.pointwest.constant.SqlConstant;
import com.pointwest.exception.LocatorException;

public class EmployeeDao extends BaseDao {
	public Employee retrieveEmployeeInfoByUser(User userLogged) throws LocatorException {
		Connection conn = null;
		Employee employeeMatch = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_EMPLOYEE_QUERY + SqlConstant.EMPLOYEE_USERNAME_WHERE_QUERY);
			ps.setString(1, userLogged.getUserName());
			rs = ps.executeQuery();
			if (rs.next()) {
				employeeMatch = new Employee();
				employeeMatch.setFirstName(rs.getString(SqlConstant.DB_COLUMN_EMPLOYEE_FIRST_NAME));
				employeeMatch.setLastName(rs.getString(SqlConstant.DB_COLUMN_EMPLOYEE_LAST_NAME));
				employeeMatch.setAppRole(rs.getString(SqlConstant.DB_COLUMN_EMPLOYEE_ROLE));
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database." + e.getMessage());
		} finally {
			clearResource(conn, ps, rs);
		}
		return employeeMatch;
	}

	private List<Employee> populateWithData(ResultSet rs) throws LocatorException {
		// LoggerUtil.logInfoStart("populateWithData");
		List<Employee> listOfEmployees = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee employeeMatch = new Employee();
				employeeMatch.setId(rs.getString("e." + SqlConstant.DB_COLUMN_EMPLOYEE_EMPLOYEE_ID));
				employeeMatch.setFirstName(rs.getString("e." + SqlConstant.DB_COLUMN_EMPLOYEE_FIRST_NAME));
				employeeMatch.setLastName(rs.getString("e." + SqlConstant.DB_COLUMN_EMPLOYEE_LAST_NAME));
				employeeMatch.setShift(rs.getString("e." + SqlConstant.DB_COLUMN_EMPLOYEE_SHIFT));
				Building seatBuilding = new Building();
				seatBuilding.setId(rs.getString("loc." + SqlConstant.DB_COLUMN_LOCATION_LOCATION_BLDG_ID));
				Seat employeeSeat = new Seat();
				employeeSeat.setBuilding(seatBuilding);
				employeeSeat.setFloorNumber(rs.getString("s." + SqlConstant.DB_COLUMN_SEAT_FLOOR_NUM));
				employeeSeat.setQuadrant(rs.getString("s." + SqlConstant.DB_COLUMN_SEAT_QUADRANT));
				employeeSeat.setRowNumber(rs.getString("s." + SqlConstant.DB_COLUMN_SEAT_ROW));
				employeeSeat.setColNumber(rs.getString("s." + SqlConstant.DB_COLUMN_SEAT_COLUMN));
				employeeSeat.setLoc(rs.getString("s." + SqlConstant.DB_COLUMN_SEAT_LOCAL));
				employeeMatch.setSeat(employeeSeat);
				employeeMatch.setProject(rs.getString("projects"));
				listOfEmployees.add(employeeMatch);
			}
		} catch (LocatorException le) {
			throw le;
		} catch (SQLException sqle) {
			// LoggerUtil.logError(sqle.getMessage());
			throw new LocatorException("Error occured while querying to the database.");
		}
		// LoggerUtil.logInfoEnd("populateWithData");
		return listOfEmployees;
	}

	public List<Employee> retrieveEmployeeListById(String empId) throws LocatorException {
		Connection conn = null;
		List<Employee> employeeMatches = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_EMPLOYEE_LIST_QUERY + SqlConstant.WHERE_EMPLOYEE_ID_QUERY
					+ SqlConstant.GROUP_BY_EMPLOYEE_ID_QUERY + SqlConstant.HAVING_MAX_EFFECT_DATE_QUERY
					+ SqlConstant.ORDER_BY_EMP_ID);
			ps.setString(1, empId);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				employeeMatches = new ArrayList<Employee>();
				employeeMatches = populateWithData(rs);
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database.");
		} finally {
			clearResource(conn, ps, rs);
		}
		return employeeMatches;
	}

	public List<Employee> retrieveEmployeeListByName(String name) throws LocatorException {
		Connection conn = null;
		List<Employee> employeeMatches = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_EMPLOYEE_LIST_QUERY
					+ SqlConstant.WHERE_EMPLOYEE_FIRST_NAME_OR_LAST_NAME_LIKE + SqlConstant.GROUP_BY_EMPLOYEE_ID_QUERY
					+ SqlConstant.HAVING_MAX_EFFECT_DATE_QUERY + SqlConstant.ORDER_BY_FIRST_NAME);
			ps.setString(1, "%" + name + "%");
			ps.setString(2, "%" + name + "%");
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				employeeMatches = new ArrayList<Employee>();
				employeeMatches = populateWithData(rs);
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database.");
		} finally {
			clearResource(conn, ps, rs);
		}
		return employeeMatches;
	}

	public List<Employee> retrieveEmployeeListByProj(String name) throws LocatorException {
		Connection conn = null;
		List<Employee> employeeMatches = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_EMPLOYEE_LIST_QUERY + SqlConstant.WHERE_PROJECT_NAME_QUERY
					+ SqlConstant.GROUP_BY_EMPLOYEE_ID_QUERY + SqlConstant.HAVING_MAX_EFFECT_DATE_QUERY
					+ SqlConstant.ORDER_BY_FIRST_NAME);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				employeeMatches = new ArrayList<Employee>();
				employeeMatches = populateWithData(rs);
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database." + e.getMessage());
		} finally {
			clearResource(conn, ps, rs);
		}
		return employeeMatches;
	}
}

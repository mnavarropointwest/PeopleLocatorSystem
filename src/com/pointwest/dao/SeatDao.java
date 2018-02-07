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
import com.pointwest.constant.SqlConstant;
import com.pointwest.exception.LocatorException;

public class SeatDao extends BaseDao {
	
	public List<String> retrieveQuadrantChoices() throws LocatorException {
		Connection conn = null;
		List<String> quadrants = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_DISTINCT_QUADRANT_QUERY);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				quadrants = new ArrayList<String>();
				while(rs.next()) {
					quadrants.add(rs.getString(SqlConstant.DB_COLUMN_SEAT_QUADRANT));
				}
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("There's an error in the server. Please contact your system administrator");
		} finally {
			clearResource(conn, ps, rs);
		}
		return quadrants;
	}
	public List<Employee> retrieveSeatPlanByLocationFloor(String location, String floor) throws LocatorException {
		Connection conn = null;
		List<Employee> employeeSeatPlan = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_SEAT_PLAN_LIST + SqlConstant.WHERE_FLOOR_AND_BUILDING_QUERY);
			ps.setString(1, floor);
			ps.setString(2, location);
			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				employeeSeatPlan = new ArrayList<Employee>();
				employeeSeatPlan = populateWithData(rs);
			}
		} catch (LocatorException e) {
			System.out.println(e.getMessage());
			throw e;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new LocatorException("There's an error in the server. Please contact your system administrator");
		} finally {
			clearResource(conn, ps, rs);
		}
		return employeeSeatPlan;
	}

	public List<Employee> retrieveSeatPlanByQuadrant(String quadrant) throws LocatorException {
		Connection conn = null;
		List<Employee> employeeSeatPlan = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_SEAT_PLAN_LIST + SqlConstant.WHERE_QUADRANT_QUERY);
			ps.setString(1, quadrant);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				employeeSeatPlan = new ArrayList<Employee>();
				employeeSeatPlan = populateWithData(rs);
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("There's an error in the server. Please contact your system administrator");
		} finally {
			clearResource(conn, ps, rs);
		}
		return employeeSeatPlan;
	}

	private List<Employee> populateWithData(ResultSet rs) throws LocatorException {
		// LoggerUtil.logInfoStart("populateWithData");
		List<Employee> listOfEmployees = new ArrayList<Employee>();
		try {
			while (rs.next()) {
				Employee employeeMatch = new Employee();
				employeeMatch.setFirstName(rs.getString("e." + SqlConstant.DB_COLUMN_EMPLOYEE_FIRST_NAME));
				employeeMatch.setLastName(rs.getString("e." + SqlConstant.DB_COLUMN_EMPLOYEE_LAST_NAME));
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
				listOfEmployees.add(employeeMatch);
			}
		} catch (LocatorException le) {
			throw le;
		} catch (SQLException sqle) {
			// LoggerUtil.logError(sqle.getMessage());
			System.out.println(sqle.getMessage());
			throw new LocatorException("Error occured while querying to the database.");
		}
		// LoggerUtil.logInfoEnd("populateWithData");
		return listOfEmployees;
	}
}

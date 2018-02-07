package com.pointwest.manager;

import java.util.List;

import com.pointwest.bean.Building;
import com.pointwest.bean.Employee;
import com.pointwest.bean.Seat;
import com.pointwest.dao.BuildingDao;
import com.pointwest.dao.SeatDao;

public class SeatPlanManager {
	public List<Building> retrieveListOfLocation() {
		BuildingDao buildDao = new BuildingDao();
		List<Building> locations = buildDao.retrieveAllLocation();
		return locations;
	}

	public List<Seat> retrieveFloorsByLocation(String location) {
		BuildingDao buildDao = new BuildingDao();
		List<Seat> seatFloors = buildDao.retrieveSeatFloorsByLocation(location);
		return seatFloors;
	}

	public List<Employee> retrieveSeatPlanByLocationFloor(String location, String floor) {
		SeatDao seatDao = new SeatDao();
		List<Employee> employeeSeatPlan = seatDao.retrieveSeatPlanByLocationFloor(location, floor);
		return employeeSeatPlan;
	}

	public List<String> retrieveAllQuadrants() {
		SeatDao seatDao = new SeatDao();
		List<String> quadrants = seatDao.retrieveQuadrantChoices();
		return quadrants;
	}
}

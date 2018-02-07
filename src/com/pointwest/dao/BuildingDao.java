package com.pointwest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pointwest.bean.Building;
import com.pointwest.bean.Seat;
import com.pointwest.constant.SqlConstant;
import com.pointwest.exception.LocatorException;

public class BuildingDao extends BaseDao {
	public List<Building> retrieveAllLocation() throws LocatorException {
		myLogger.trace("BuildingDao: retrieveAllLocation method");
		Connection conn = null;
		List<Building> locations = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_DISTINCT_LOCATION_QUERY);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				locations = new ArrayList<Building>();
				while (rs.next()) {
					Building location = new Building();
					location.setId(rs.getString(SqlConstant.DB_COLUMN_LOCATION_LOCATION_BLDG_ID));
					locations.add(location);
				}
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database." + e.getMessage());
		} finally {
			clearResource(conn, ps, rs);
		}
		myLogger.trace("BuildingDao:end retrieveAllLocation method");
		return locations;
	}

	public List<Seat> retrieveSeatFloorsByLocation(String location) throws LocatorException {
		Connection conn = null;
		List<Seat> seatFloors = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_DISTINCT_FLOOR_QUERY + SqlConstant.WHERE_BLDG_ID_QUERY);
			ps.setString(1, location);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				seatFloors = new ArrayList<Seat>();
				while (rs.next()) {
					Seat seat = new Seat();
					seat.setFloorNumber(rs.getString(SqlConstant.DB_COLUMN_SEAT_FLOOR_NUM));
					seatFloors.add(seat);
				}
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database." + e.getMessage());
		} finally {
			clearResource(conn, ps, rs);
		}
		return seatFloors;
	}

}

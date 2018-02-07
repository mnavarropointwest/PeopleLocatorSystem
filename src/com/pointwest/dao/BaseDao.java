package com.pointwest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import com.pointwest.constant.SqlConstant;
import com.pointwest.exception.LocatorException;

public abstract class BaseDao {
	Logger myLogger = Logger.getLogger(BaseDao.class);
	protected Connection getConnection() throws LocatorException {
		myLogger.trace("BaseDao:getConnection method");
		Connection conn = null;
		try {
			// Sets up the Database Driver
			Class.forName("com.mysql.jdbc.Driver");
			// Sets up the Database Connection
			conn = DriverManager.getConnection(SqlConstant.DB_URL, SqlConstant.DB_USERNAME, SqlConstant.DB_PASSWORD);
			// Catch Exception for Database Driver.
		} catch (ClassNotFoundException e) {
			throw new LocatorException("Please contact your Administrator, there's missing drivers on your computer.");
			// Catch Exception for SQL Connections.
		} catch (SQLException e) {
			throw new LocatorException("ou have no connection to the database. Please contact your Administrator.");
			// Catch Exception for other Exceptions.
		} catch (Exception e) {
			throw new LocatorException("You have no connection to the database. Please contact your Administrator.");
		}
		myLogger.trace("BaseDao:end getConnection method");
		return conn;
	}

	protected void clearResource(Connection conn, PreparedStatement ps, ResultSet rs) throws LocatorException {
		myLogger.trace("BaseDao: clearResource method");
		try {
			/*
			 * Closes resources for Connection PreparedStatement ResultSet for Memory
			 * Management.
			 */
			if (conn != null) {
				conn.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			// Catch Exception for SQL Connections.
		} catch (SQLException sqle) {
			// Throws an LocatorException to the invoker of the method.
			throw new LocatorException("You have no connection to the database. Please contact your Administrator.");
		}
		myLogger.trace("BaseDao:end clearResource method");
	}
}

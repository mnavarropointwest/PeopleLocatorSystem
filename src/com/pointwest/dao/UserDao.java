package com.pointwest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pointwest.bean.User;
import com.pointwest.constant.SqlConstant;
import com.pointwest.exception.LocatorException;

public class UserDao extends BaseDao {

	public User retrieveUserByCred(String userName, String password) throws LocatorException {
		Connection conn = null;
		User userMatch = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_USER_QUERY + SqlConstant.WHERE_USER_PASSWORD_QUERY);
			ps.setString(1, userName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				userMatch = new User();
				userMatch.setUserName(rs.getString(SqlConstant.DB_COLUMN_EMPLOYEE_USERNAME));
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database. " + e.getMessage());
		} finally {
			clearResource(conn, ps, rs);
		}
		return userMatch;
	}
}

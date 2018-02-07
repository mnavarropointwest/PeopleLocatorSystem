package com.pointwest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pointwest.bean.Project;
import com.pointwest.constant.SqlConstant;
import com.pointwest.exception.LocatorException;

public class ProjectDao extends BaseDao {
	public List<Project> retrieveProjectChoices() {
		Connection conn = null;
		List<Project> listOfProject = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstant.SELECT_PROJECT_QUERY);
			rs = ps.executeQuery();
			if (rs.isBeforeFirst()) {
				listOfProject = new ArrayList<Project>();
				while (rs.next()) {
					Project project = new Project();
					project.setName(rs.getString(SqlConstant.DB_COLUMN_PROJECT_PROJECT_NAME));
					listOfProject.add(project);
				}
			}
		} catch (LocatorException e) {
			throw e;
		} catch (SQLException e) {
			throw new LocatorException("Error on SQL Database." + e.getMessage());
		} finally {
			clearResource(conn, ps, rs);
		}
		return listOfProject;
	}
}

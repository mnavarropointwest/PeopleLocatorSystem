package com.pointwest.constant;

public class SqlConstant {
	// DB CONFIG
	public final static String DB_NAME = "plsdb";
	public final static String DB_PORT = "3306";
	public final static String DB_SERVER = "localhost";
	public final static String DB_USERNAME = "root";
	public final static String DB_PASSWORD = "root";
	public final static String DB_URL = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
	// EMPLOYEE TABLE
	public final static String DB_COLUMN_EMPLOYEE_EMPLOYEE_ID = "emp_id";
	public final static String DB_COLUMN_EMPLOYEE_USERNAME = "username";
	public final static String DB_COLUMN_EMPLOYEE_PASSWORD = "password";
	public final static String DB_COLUMN_EMPLOYEE_FIRST_NAME = "first_name";
	public final static String DB_COLUMN_EMPLOYEE_LAST_NAME = "last_name";
	public final static String DB_COLUMN_EMPLOYEE_ROLE = "role";
	public final static String DB_COLUMN_EMPLOYEE_SHIFT = "shift";
	// EMPLOYEE SEAT TABLE
	public final static String DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID = "emp_id";
	public final static String DB_COLUMN_EMPLOYEE_SEAT_SEAT_ID = "seat_id";
	public final static String DB_COLUMN_EMPLOYEE_SEAT_EFFECT_DATE = "effect_date";
	// PROJECT TABLE
	public final static String DB_COLUMN_PROJECT_PROJECT_NAME = "proj_name";
	public final static String DB_COLUMN_PROJECT_PROJECT_ALIAS = "proj_alias";
	// LOCATION TABLE
	public final static String DB_COLUMN_LOCATION_LOCATION_BLDG_ID = "bldg_id";
	// SEAT TABLE
	public final static String DB_COLUMN_SEAT_SEAT_ID = "seat_id";
	public final static String DB_COLUMN_SEAT_FLOOR_NUM = "floor_number";
	public final static String DB_COLUMN_SEAT_QUADRANT = "quadrant";
	public final static String DB_COLUMN_SEAT_ROW = "row_number";
	public final static String DB_COLUMN_SEAT_COLUMN = "column_number";
	public final static String DB_COLUMN_SEAT_LOCAL = "local_number";
	public final static String DB_COLUMN_SEAT_BLDG_ID = "bldg_id";
	// EMP PROJECT TABLE
	public final static String DB_COLUMN_EMPLOYEE_PROJECT_EMPLOYEE_ID = "employee_id";
	public final static String DB_COLUMN_EMPLOYEE_PROJECT_PROJECT_ALIAS = "proj_alias";

	// SELECT QUERIES
	public final static String SELECT_USER_QUERY = "SELECT " + DB_COLUMN_EMPLOYEE_USERNAME + " FROM employee";
	public final static String SELECT_EMPLOYEE_QUERY = "SELECT " + DB_COLUMN_EMPLOYEE_FIRST_NAME + ","
			+ DB_COLUMN_EMPLOYEE_LAST_NAME + "," + DB_COLUMN_EMPLOYEE_ROLE + " FROM employee";
	public final static String EMPLOYEE_USERNAME_WHERE_QUERY = " WHERE " + DB_COLUMN_EMPLOYEE_USERNAME + " = ?";

	public final static String SELECT_PROJECT_QUERY = "SELECT " + DB_COLUMN_PROJECT_PROJECT_NAME + " FROM project";
	public final static String SELECT_EMPLOYEE_LIST_QUERY = "SELECT e." + DB_COLUMN_EMPLOYEE_EMPLOYEE_ID + "" + ",e."
			+ DB_COLUMN_EMPLOYEE_FIRST_NAME + "" + ", e." + DB_COLUMN_EMPLOYEE_LAST_NAME + "" + ", loc."
			+ DB_COLUMN_LOCATION_LOCATION_BLDG_ID + "" + ", s." + DB_COLUMN_SEAT_FLOOR_NUM + "" + ", s."
			+ DB_COLUMN_SEAT_QUADRANT + "" + ",s." + DB_COLUMN_SEAT_ROW + "" + ",s." + DB_COLUMN_SEAT_COLUMN + ""
			+ ", e." + DB_COLUMN_EMPLOYEE_SHIFT + "" + ", s." + DB_COLUMN_SEAT_LOCAL + "" + ", GROUP_CONCAT(DISTINCT(p."
			+ DB_COLUMN_PROJECT_PROJECT_NAME + ") SEPARATOR ', ') projects" + " FROM employee e " + "LEFT JOIN "
			+ "employee_seat es ON es." + DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID + " = e." + DB_COLUMN_EMPLOYEE_EMPLOYEE_ID
			+ " " + "LEFT JOIN " + "seat s ON s." + DB_COLUMN_SEAT_SEAT_ID + " = es." + DB_COLUMN_EMPLOYEE_SEAT_SEAT_ID
			+ " " + "LEFT JOIN " + "location loc ON loc." + DB_COLUMN_LOCATION_LOCATION_BLDG_ID + " = s."
			+ DB_COLUMN_SEAT_BLDG_ID + " " + "LEFT JOIN " + "employee_project ep ON e." + DB_COLUMN_EMPLOYEE_EMPLOYEE_ID
			+ " = ep." + DB_COLUMN_EMPLOYEE_PROJECT_EMPLOYEE_ID + " " + "LEFT JOIN " + "project p ON p."
			+ DB_COLUMN_PROJECT_PROJECT_ALIAS + " = ep." + DB_COLUMN_EMPLOYEE_PROJECT_PROJECT_ALIAS + " ";

	public final static String SELECT_DISTINCT_LOCATION_QUERY = "SELECT DISTINCT(" + DB_COLUMN_LOCATION_LOCATION_BLDG_ID
			+ ") FROM location";
	public final static String SELECT_DISTINCT_FLOOR_QUERY = "SELECT DISTINCT(" + DB_COLUMN_SEAT_FLOOR_NUM
			+ ") FROM seat";
	public final static String SELECT_DISTINCT_QUADRANT_QUERY = "SELECT DISTINCT(" + DB_COLUMN_SEAT_QUADRANT
			+ ") FROM seat";
	public final static String SELECT_SEAT_PLAN_LIST = "SELECT loc." + DB_COLUMN_LOCATION_LOCATION_BLDG_ID + " " + ",s."
			+ DB_COLUMN_SEAT_FLOOR_NUM + "," + "s." + DB_COLUMN_SEAT_QUADRANT + "," + "s." + DB_COLUMN_SEAT_ROW + ","
			+ "s." + DB_COLUMN_SEAT_COLUMN + "," + "e." + DB_COLUMN_EMPLOYEE_FIRST_NAME + "," + "e."
			+ DB_COLUMN_EMPLOYEE_LAST_NAME + "," + "s." + DB_COLUMN_SEAT_LOCAL + " " + "FROM seat s" + " LEFT JOIN "
			+ "(SELECT s." + DB_COLUMN_SEAT_SEAT_ID + ", s." + DB_COLUMN_SEAT_BLDG_ID + ", esd."
			+ DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID + " FROM seat s " + "	LEFT JOIN ( " + "SELECT es."
			+ DB_COLUMN_EMPLOYEE_SEAT_SEAT_ID + ", es." + DB_COLUMN_EMPLOYEE_SEAT_EFFECT_DATE + ",es."
			+ DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID + " FROM employee_seat es " + "INNER JOIN ( " + "SELECT es."
			+ DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID + ", MAX(es." + DB_COLUMN_EMPLOYEE_SEAT_EFFECT_DATE
			+ ") as maxDate FROM employee_seat es " + "LEFT JOIN" + " seat s ON s." + DB_COLUMN_SEAT_SEAT_ID + " = es."
			+ DB_COLUMN_EMPLOYEE_SEAT_SEAT_ID + " GROUP BY es." + DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID + ", s."
			+ DB_COLUMN_SEAT_BLDG_ID + ") est" + " ON est.maxDate = es." + DB_COLUMN_EMPLOYEE_SEAT_EFFECT_DATE
			+ " AND est." + DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID + " = es." + DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID
			+ ") esd" + " ON esd." + DB_COLUMN_EMPLOYEE_SEAT_SEAT_ID + " = s." + DB_COLUMN_SEAT_SEAT_ID + " GROUP BY "
			+ DB_COLUMN_LOCATION_LOCATION_BLDG_ID + ", " + DB_COLUMN_EMPLOYEE_EMPLOYEE_ID + ")" + "esf ON esf."
			+ DB_COLUMN_EMPLOYEE_SEAT_SEAT_ID + " = s." + DB_COLUMN_SEAT_SEAT_ID + " " + "LEFT JOIN "
			+ "location loc ON loc." + DB_COLUMN_LOCATION_LOCATION_BLDG_ID + " = s." + DB_COLUMN_SEAT_BLDG_ID + " "
			+ "LEFT JOIN " + "employee e ON e." + DB_COLUMN_EMPLOYEE_EMPLOYEE_ID + " = esf."
			+ DB_COLUMN_EMPLOYEE_SEAT_EMPLOYEE_ID + " ";

	// WHERE QUERIES
	public final static String WHERE_USER_PASSWORD_QUERY = " WHERE " + DB_COLUMN_EMPLOYEE_USERNAME + " = ? AND "
			+ DB_COLUMN_EMPLOYEE_PASSWORD + " = ?";
	public final static String WHERE_EMPLOYEE_ID_QUERY = " WHERE e." + DB_COLUMN_EMPLOYEE_EMPLOYEE_ID + "= ? ";
	public final static String WHERE_EMPLOYEE_FIRST_NAME_LIKE = " WHERE e." + DB_COLUMN_EMPLOYEE_FIRST_NAME
			+ " LIKE ? ";
	public final static String WHERE_EMPLOYEE_FIRST_NAME_OR_LAST_NAME_LIKE = WHERE_EMPLOYEE_FIRST_NAME_LIKE + " OR e."
			+ DB_COLUMN_EMPLOYEE_LAST_NAME + " LIKE ?";
	public final static String WHERE_FLOOR_AND_BUILDING_QUERY = "WHERE s." + DB_COLUMN_SEAT_FLOOR_NUM + "= ? AND loc."
			+ DB_COLUMN_LOCATION_LOCATION_BLDG_ID + " = ?";
	public final static String WHERE_QUADRANT_QUERY = " WHERE " + DB_COLUMN_SEAT_QUADRANT + " = ?";

	public final static String WHERE_BLDG_ID_QUERY = " WHERE " + DB_COLUMN_SEAT_BLDG_ID + " = ? ";
	public final static String WHERE_PROJECT_NAME_QUERY = " WHERE p." + DB_COLUMN_PROJECT_PROJECT_NAME + " = ? ";

	// ORDER BY GROUP BY HAVING
	public final static String ORDER_BY_PROJECT_NAME = " ORDER BY p." + DB_COLUMN_PROJECT_PROJECT_NAME + " ";
	public final static String ORDER_BY_FIRST_NAME = " ORDER BY e."+DB_COLUMN_EMPLOYEE_FIRST_NAME;
	public final static String ORDER_BY_EMP_ID = " ORDER BY e."+DB_COLUMN_EMPLOYEE_EMPLOYEE_ID;
	public final static String ORDER_BY_BLDG_FLOOR_QUADRANT_ROW_COLUMN = "ORDER BY "
			+ DB_COLUMN_LOCATION_LOCATION_BLDG_ID + ", " + DB_COLUMN_SEAT_FLOOR_NUM + ", " + DB_COLUMN_SEAT_QUADRANT
			+ " ," + DB_COLUMN_SEAT_ROW + " ," + DB_COLUMN_SEAT_COLUMN;
	public final static String GROUP_BY_EMPLOYEE_ID_QUERY = " GROUP BY e." + DB_COLUMN_EMPLOYEE_EMPLOYEE_ID;
	public final static String HAVING_MAX_EFFECT_DATE_QUERY = " HAVING MAX(es." + DB_COLUMN_EMPLOYEE_SEAT_EFFECT_DATE
			+ ")";
}

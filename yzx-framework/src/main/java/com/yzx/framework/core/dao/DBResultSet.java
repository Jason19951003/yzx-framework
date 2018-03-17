package com.yzx.framework.core.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.yzx.framework.core.dao.impl.DBResultSetImpl;

public abstract class DBResultSet {
	private ResultSet rs 		   = null;	
	private Connection con         = null;
	private DataAccess da          = null;
	private int totalCount;
	
	public DBResultSet(ResultSet rs, int totalCount, DataAccess da)  {
		this.rs = rs;
		this.totalCount = totalCount;
		this.da = da;
	}
	
	public static DBResultSet createDBResultSet(ResultSet rs, int totalCount, DataAccess da) {
		return new DBResultSetImpl(rs, totalCount, da);
	}
	
	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void close() throws SQLException {
		if(con != null) {
			this.da.releaseConnection(con);			
		}			
		if(rs != null) {
			rs.close();		
		}
	}
	
	public int getTotalCount() throws SQLException {		
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getColumnCount() throws SQLException {
		return rs.getMetaData().getColumnCount();
	}
	
	public String getColumnName(int column) throws SQLException {
		return rs.getMetaData().getColumnName(column);
	}
	
	public boolean next() throws SQLException {
		return rs.next();				
	}
	
	public void beforeFirst() throws SQLException {		
		rs.beforeFirst();
	}
	
	public Object getArray(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getArray(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getBoolean(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getBoolean(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getBlob(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getBlob(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getClob(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getClob(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getDate(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getDate(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getDouble(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getDouble(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getFloat(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getFloat(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getInt(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getInt(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getLong(String param) throws SQLException {
		return rs.getArray(param);		
	}
	
	public Object getLong(int columnIndex) throws SQLException {
		return rs.getArray(columnIndex);		
	}
	
	public Object getObject(int columnIndex) throws SQLException {
		return rs.getObject(columnIndex);		
	}
	
	public Object getObject(String param) throws SQLException {
		return rs.getObject(param);		
	}
	
	public String getString(int columnIndex) throws SQLException {
		return rs.getString(columnIndex);		
	}
	
	public String getString(String param) throws SQLException {		
		return rs.getString(param);
	}
}

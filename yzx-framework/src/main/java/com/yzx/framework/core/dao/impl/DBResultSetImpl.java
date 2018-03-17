package com.yzx.framework.core.dao.impl;

import java.sql.ResultSet;

import com.yzx.framework.core.dao.DBResultSet;
import com.yzx.framework.core.dao.DataAccess;

public class DBResultSetImpl extends DBResultSet {
	public DBResultSetImpl(ResultSet rs, int totalCount, DataAccess da) {
		super(rs, totalCount, da);		
	}
}

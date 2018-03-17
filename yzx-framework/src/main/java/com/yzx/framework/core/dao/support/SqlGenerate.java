package com.yzx.framework.core.dao.support;

import java.util.Map;

public interface SqlGenerate {
	/**
	 * 
	 * @param sql
	 * @param map
	 * @return
	 */
	public GenerateResult generate(String sql, Map<?, ?> map);
	/**
	 * 
	 */
	public void close();
}

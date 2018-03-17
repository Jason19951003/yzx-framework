package com.yzx.framework.core.dao.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

@SuppressWarnings("all")
public class MybatisSqlGenerate implements SqlGenerate {
	private SqlSessionFactory sqlSessionFactory;

	public GenerateResult generate(String sql, Map<?, ?> paramMap) {
		final String rtnSql = getSql(paramMap, sql);
		final Object[] rtnObj = getParameters(sqlSessionFactory, sql, paramMap);
		return new GenerateResult() {
			public String getSql() {
				return rtnSql;
			}
			public Object[] getParams() {
				return rtnObj;
			}
		};
	}

	public void close() {
		this.sqlSessionFactory = null;		
	}
	
	private String getSql(Map<?, ?> map, String sqlId) {
		Configuration configuration = sqlSessionFactory.getConfiguration();
		MappedStatement ms = configuration.getMappedStatement(sqlId);
		BoundSql  boundsql = ms.getBoundSql(map);
		return boundsql.getSql();
	}
	
	private Object[] getParameters(SqlSessionFactory sqlSessionFactory, String sqlId, Map paraMap) {
		Configuration configuration = sqlSessionFactory.getConfiguration();

		ArrayList<Object> paraList = new ArrayList<Object>();

		MappedStatement ms = configuration.getMappedStatement(sqlId);
		BoundSql boundSql = ms.getBoundSql(paraMap);

		TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

		configuration.newParameterHandler(ms, paraMap, boundSql);

		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			MetaObject metaObject = paraMap == null ? null : configuration.newMetaObject(paraMap);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = (ParameterMapping) parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					Object value;
					if (paraMap == null) {
						value = null;
					} else {

						if (typeHandlerRegistry.hasTypeHandler(paraMap.getClass())) {
							value = paraMap;
						} else {

							if (boundSql.hasAdditionalParameter(propertyName)) {
								value = boundSql.getAdditionalParameter(propertyName);
							} else if ((propertyName.startsWith("__frch_"))
									&& (boundSql.hasAdditionalParameter(prop.getName()))) {
								value = boundSql.getAdditionalParameter(prop.getName());
								if (value != null) {
									value = configuration.newMetaObject(value)
											.getValue(propertyName.substring(prop.getName().length()));
								}
							} else {
								value = metaObject == null ? null : metaObject.getValue(propertyName);
							}
						}
					}					
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						System.out.println("Error");
					}
					paraList.add(value);
				}
			}
		}
		return paraList.toArray();
	}
	/**
	 * @return the sqlSessionFactory
	 */
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	/**
	 * @param sqlSessionFactory the sqlSessionFactory to set
	 */
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
}

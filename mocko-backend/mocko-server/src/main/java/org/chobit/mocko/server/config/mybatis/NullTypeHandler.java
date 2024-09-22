package org.chobit.mocko.server.config.mybatis;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.chobit.commons.constans.Symbol.EMPTY;

/**
 * @author robin
 */
@MappedTypes({String.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class NullTypeHandler extends BaseTypeHandler<String> {


	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		if (null == parameter) {
			parameter = EMPTY;
		}
		super.setParameter(ps, i, parameter, jdbcType);
	}


	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter);
	}


	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		return null == value ? EMPTY : value;
	}


	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		return null == value ? EMPTY : value;
	}


	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		return null == value ? EMPTY : value;
	}

}

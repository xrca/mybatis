package com.xrca.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author xrca
 * @description 自定义LocalDateTimeTypeHandler
 * @date 2020-08-08 15:25
 */
@MappedTypes(LocalDateTime.class)
public class MyLocalDateTimeTypeHandler extends BaseTypeHandler<LocalDateTime> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        if (localDateTime != null) {
            String str = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Timestamp timestamp = Timestamp.valueOf(str);
            preparedStatement.setTimestamp(i, timestamp);
        } else {
            preparedStatement.setTimestamp(i, null);
        }
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(s);
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTime;
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp timestamp = resultSet.getTimestamp(i);
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTime;
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp timestamp = callableStatement.getTimestamp(i);
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTime;
    }
}

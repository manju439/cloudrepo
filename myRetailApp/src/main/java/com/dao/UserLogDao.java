package com.dao;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserLogDao {
    @Autowired
    private Session session;

    private String keySpace;

    public UserLogDao(@Value("${cassandra.keyspace2}") String keySpace) {
        this.keySpace = keySpace;
    }

    /**
     * Table Name
     */
    private static final String USERLOG_TABLE = "userlog";

    /**
     * Column Names
     */
    private static final String USERLOG_DATE_COLUMN = "date";
    private static final String USERLOG_USERID_COLUMN = "user_id";
    private static final String USERLOG_TIMESTAMP_COLUMN = "ts";
    private static final String USERLOG_NAME_COLUMN = "name";

    public List<UserLog> findAll(String searchByDate) {
        ResultSet result = session.execute("SELECT * FROM " + keySpace + "." + USERLOG_TABLE + " where " + USERLOG_DATE_COLUMN + "='" + searchByDate + "'");
        return result.all()
                .stream()
                .map(this::userLogRowMapper)
                .collect(Collectors.toList());
    }

    public UserLog userLogRowMapper(Row row) {
        UserLog userLog = new UserLog();
        userLog.setDate(row.getString(USERLOG_DATE_COLUMN));
        userLog.setUserId(row.getString(USERLOG_USERID_COLUMN));
        userLog.setTime_utc(row.getString(USERLOG_TIMESTAMP_COLUMN));
        userLog.setName(row.getString(USERLOG_NAME_COLUMN));

        return userLog;
    }

}


package com.dao;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.model.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
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
    private static final String USERLOG_DATE_COLUMN = "utcdate";
    private static final String USERLOG_ADDRESS_COLUMN = "address";
    private static final String USERLOG_TIMESTAMP_COLUMN = "ts";
    private static final String USERLOG_NAME_COLUMN = "name";

    public List<UserLog> findAll(String searchByDate) {
        ResultSet result = session.execute("SELECT * FROM " + keySpace + "." + USERLOG_TABLE + " where " + USERLOG_DATE_COLUMN + "='" + searchByDate + "'");
        return result.all()
                .stream()
                .map(this::userLogRowMapper)
                .collect(Collectors.toList());
    }

    public List<UserLog> findAll(int start, int limit) {
        Statement statement = QueryBuilder.select().all().from(keySpace, USERLOG_TABLE);
        List<Row> rowsList = fetchRowsWithPage(statement, start, limit);
        return rowsList
                .stream()
                .map(this::userLogRowMapper)
                .collect(Collectors.toList());
    }

    public UserLog userLogRowMapper(Row row) {
        UserLog userLog = new UserLog();
        userLog.setDate(row.getString(USERLOG_DATE_COLUMN));
        userLog.setAddress(row.getString(USERLOG_ADDRESS_COLUMN));
        userLog.setTime_utc(row.getTimestamp(USERLOG_TIMESTAMP_COLUMN));
        userLog.setName(row.getString(USERLOG_NAME_COLUMN));

        return userLog;
    }

    public List<Row> fetchRowsWithPage(Statement statement, int start, int size) {
        ResultSet result = skipRows(statement, start, size);
        return getRows(result, start, size);
    }

    private ResultSet skipRows(Statement statement, int start, int size) {
        ResultSet result = null;
        int skippingPages = getPageNumber(start, size);
        String savingPageState = null;
        statement.setFetchSize(size);
        boolean isEnd = false;
        for (int i = 0; i < skippingPages; i++) {
            if (null != savingPageState) {
                statement = statement.setPagingState(PagingState
                        .fromString(savingPageState));
            }
            result = session.execute(statement);
            PagingState pagingState = result.getExecutionInfo()
                    .getPagingState();
            if (null != pagingState) {
                savingPageState = result.getExecutionInfo().getPagingState()
                        .toString();
            }

            if (result.isFullyFetched() && null == pagingState) {
                // if hit the end more than once, then nothing to return,
                // otherwise, mark the isEnd to 'true'
                if (true == isEnd) {
                    return null;
                } else {
                    isEnd = true;
                }
            }
        }
        return result;
    }

    private int getPageNumber(int start, int size) {
        if (start < 1) {
            throw new IllegalArgumentException(
                    "Starting row need to be larger than 1");
        }
        int page = 1;
        if (start > size) {
            page = (start - 1) / size + 1;
        }
        return page;
    }

    private List<Row> getRows(ResultSet result, int start, int size) {
        List<Row> rows = new ArrayList<>(size);
        if (null == result) {
            return rows;
        }
        int skippingRows = (start - 1) % size;
        int index = 0;
        for (Iterator<Row> iter = result.iterator(); iter.hasNext()
                && rows.size() < size; ) {
            Row row = iter.next();
            if (index >= skippingRows) {
                rows.add(row);
            }
            index++;
        }
        return rows;
    }

}


package by.itechart.library.dao.util.impl;


import by.itechart.library.dao.util.api.ResourceCloser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResourceCloserImpl implements ResourceCloser {


    @Override
    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            //log.error(e);
        }
    }


    @Override
    public void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            //log.error(e);
        }
    }
}

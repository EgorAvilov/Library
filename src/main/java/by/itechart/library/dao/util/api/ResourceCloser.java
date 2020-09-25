package by.itechart.library.dao.util.api;

import java.sql.ResultSet;
import java.sql.Statement;

public interface ResourceCloser {
    public void close(AutoCloseable resource);

}

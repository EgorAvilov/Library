package by.itechart.library.dao.pool;

import by.itechart.library.dao.exception.ConnectionPoolException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DBConnectionPool {
    private static final DBConnectionPool INSTANCE = new DBConnectionPool();

    private DBResourceBundle resourceBundle = DBResourceBundle.getINSTANCE();

    private DataSource dataSource;

    public static DBConnectionPool getInstance() {
        return INSTANCE;
    }

    public void init() {
        dataSource = new HikariDataSource(initConfig());
    }

    public void destroy() {
        dataSource = null;
    }

    public Connection getConnection() throws ConnectionPoolException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            log.error(e);
            throw new ConnectionPoolException(e);
        }
    }

    private HikariConfig initConfig() {
        HikariConfig hikariConfig = new HikariConfig() {{
            setDriverClassName(resourceBundle.getDBValue(DBConstant.DB_DRIVER));
            setJdbcUrl(resourceBundle.getDBValue(DBConstant.DB_URL));
            setUsername(resourceBundle.getDBValue(DBConstant.DB_USERNAME));
            setPassword(resourceBundle.getDBValue(DBConstant.DB_PASSWORD));
        }};

        return hikariConfig;
    }
}

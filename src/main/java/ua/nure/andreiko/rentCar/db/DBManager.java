package ua.nure.andreiko.rentCar.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import ua.nure.andreiko.rentCar.util.LoggerUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;

/**
 * DB manager. Works with MySQL DB.
 *
 * @author E.Andreiko
 */
public class DBManager {

    private static DBManager instance;

    private DataSource dataSource;

    private final Logger LOGGER = Logger.getLogger(DBManager.class);

    public DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext;
            envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/rent");
        } catch (NamingException e) {
            LOGGER.error(LoggerUtils.ERR_CANNOT_OBTAIN_DATA_SOURCE, e);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error(LoggerUtils.ERR_CANNOT_OBTAIN_CONNECTION);
            return null;
        }
    }

    public <T> T doTransaction(Supplier<T> function) {
        T result = null;
        Connection connection = getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            result = function.get();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            LOGGER.error(LoggerUtils.ERR_FAIL_TRANSACTION, e);
        } finally {
            close(connection);
        }
        return result;
    }

    /**
     * Rollbacks a connection.
     *
     * @param connection Connection to be roll backed.
     */
    public void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error("Cannot rollback transaction", e);
            }
        }
    }

    /**
     * Closes a connection.
     *
     * @param connection Connection to be closed.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Closes are connection and statement object.
     *
     * @param connection Connection to be closed.
     * @param statement Statement to be closed.
     */
    public static void close(Connection connection, Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(connection);
    }

    /**
     * Closes are connection, statement and result set object.
     *
     * @param connection Connection to be closed.
     * @param statement Statement to be closed.
     * @param resultSet Result set to be closed.
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close(connection, statement);
    }
}

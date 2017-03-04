package by.iba.calculator.dao.impl;

import by.iba.calculator.dao.PoolDAO;
import by.iba.calculator.dao.connectionmanager.ConnectionPool;
import by.iba.calculator.dao.exception.ConnectionPoolException;
import by.iba.calculator.dao.exception.DAOException;

/**
 * Provides a logic for the MySQL {@link ConnectionPool} in DAO layer.
 */
public class MySQLPoolDAO implements PoolDAO {

    /**
     * Initialize the {@link ConnectionPool}.
     *
     * @throws DAOException in case of init pool error
     */
    @Override
    public void init() throws DAOException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.init();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Cannot init a mySQL connection pool", e);
        }
    }

    /**
     * Destroys the {@link ConnectionPool}.
     *
     * @throws DAOException in case of destroy pool error
     */
    @Override
    public void destroy() throws DAOException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.destroy();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Cannot destroy a mySQL connection pool", e);
        }
    }
}

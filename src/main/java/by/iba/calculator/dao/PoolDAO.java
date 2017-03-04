package by.iba.calculator.dao;

import by.iba.calculator.dao.connectionmanager.ConnectionPool;
import by.iba.calculator.dao.exception.DAOException;

/**
 * Provides a logic for the {@link ConnectionPool} in DAO layer.
 */
public interface PoolDAO {

    /**
     * Initialize the {@link ConnectionPool}.
     *
     * @throws DAOException in case of init pool error
     */
    void init() throws DAOException;

    /**
     * Destroys the {@link ConnectionPool}.
     *
     * @throws DAOException in case of destroy pool error
     */
    void destroy() throws DAOException;
}

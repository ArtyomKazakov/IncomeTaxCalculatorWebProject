package by.iba.calculator.service.impl;

import by.iba.calculator.dao.exception.DAOException;
import by.iba.calculator.dao.transaction.TransactionManager;
import by.iba.calculator.dao.transaction.impl.TransactionManagerImpl;
import by.iba.calculator.service.exception.ServiceException;
import by.iba.calculator.util.function.Action;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Provides some useful methods for work with services.
 */
public abstract class Service {
    private static final Logger logger = Logger.getLogger(Service.class);

    /**
     * Performs given operations inside of {@link TransactionManager#doInTransaction(Callable)}
     * method.
     * @param exceptionMessage message, that setting like an message in case of exception
     * @param callable implementation of {@link Callable} interface, which
     *        contains all operations, that should be a single transaction
     * @param <T> a type of result
     * @return result of transaction
     * @throws ServiceException in case of error occurred with database
     */
    public final <T> T service(String exceptionMessage, Callable<T> callable) throws ServiceException {
        try {
            return TransactionManagerImpl.getInstance().doInTransaction(callable);
        } catch(DAOException e){
            logger.error(e);
            throw new ServiceException(exceptionMessage, e);
        }
    }

    /**
     * Performs given operations inside of {@link TransactionManager#doInTransaction(Callable)}
     * method.
     * @param exceptionMessage message, that setting like an message in case of exception
     * @param action implementation of {@link Action} interface, which
     *        contains all operations, that should be a single transaction
     * @throws ServiceException in case of error occurred with database
     */
    public final void service(String exceptionMessage, Action action) throws ServiceException {
        try {
            TransactionManagerImpl.getInstance().doInTransaction(action);
        } catch(DAOException e){
            logger.error(e);
            throw new ServiceException(exceptionMessage, e);
        }
    }
}

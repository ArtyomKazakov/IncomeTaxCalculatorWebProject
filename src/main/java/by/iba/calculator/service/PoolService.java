package by.iba.calculator.service;

import by.iba.calculator.service.exception.ServiceException;

/**
 * Provides work with a data source.
 */
public interface PoolService {

    /**
     * Initialize data source for work with it
     */
    void init() throws ServiceException;

    /**
     * Destroy held resources for work with data source
     */
    void destroy() throws ServiceException;
}

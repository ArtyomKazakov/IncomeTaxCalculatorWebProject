package by.iba.calculator.service;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.service.exception.ServiceException;

import java.util.List;

/**
 * Provides a business-logic with the {@link Calculation} entity and relate with it.
 */
public interface CalculationService {
    /**
     * Creates a calculation in a data source
     *
     * @param calculation a calculation object
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    void addCalculation(Calculation calculation) throws ServiceException;

    /**
     * Return limited calculations from a data source
     *
     * @param start    the number from which accounts will be returned
     * @param amount   of schedule records
     * @param language a language
     * @return a {@link List} of calculations
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    List<Calculation> getAllCalculationsLimited(int start, int amount, String language) throws ServiceException;
}

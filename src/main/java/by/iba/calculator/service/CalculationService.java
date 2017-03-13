package by.iba.calculator.service;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.service.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provides a business-logic with the {@link Calculation} entity and relate with it.
 */
public interface CalculationService {
    /**
     * Creates a calculation in a data source
     *
     * @param calculation a calculation object
     * @return a {@link BigDecimal} value of income tax
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    BigDecimal addCalculation(Calculation calculation) throws ServiceException;

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

    /**
     * Return limited calculations from a data source order by period
     *
     * @param start    the number from which accounts will be returned
     * @param amount   of schedule records
     * @param language a language
     * @return a {@link List} of calculations
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    List<Calculation> getAllCalculationsOrderByPeriodLimited(int start, int amount, String language) throws ServiceException;

    /**
     * Return limited calculations from a data source order by income tax
     *
     * @param start    the number from which accounts will be returned
     * @param amount   of schedule records
     * @param language a language
     * @return a {@link List} of calculations
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    List<Calculation> getAllCalculationsOrderByIncomeTaxLimited(int start, int amount, String language) throws ServiceException;

    /**
     * Return limited calculations from a data source order by revenue from sale
     *
     * @param start    the number from which accounts will be returned
     * @param amount   of schedule records
     * @param language a language
     * @return a {@link List} of calculations
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    List<Calculation> getAllCalculationsOrderByRevenueFromSaleLimited(int start, int amount, String language) throws ServiceException;

    /**
     * Return limited calculations from a data source order by sum of expenses on business activity
     *
     * @param start    the number from which accounts will be returned
     * @param amount   of schedule records
     * @param language a language
     * @return a {@link List} of calculations
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    List<Calculation> getAllCalculationsOrderBySumOfExpensesOnBusinessActivityLimited(int start, int amount, String language) throws ServiceException;

    /**
     * Returns number of calculations in data source.
     *
     * @return amount of calculations
     * @throws ServiceException if error occurred with data source
     */
    int getCalculationsCount() throws ServiceException;
}

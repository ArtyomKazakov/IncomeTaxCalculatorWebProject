package by.iba.calculator.service;

import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.service.exception.ServiceException;

import java.util.List;

/**
 * Provides a business-logic with the {@link Period} entity and relate with it.
 */
public interface PeriodService {
    /**
     * Return periods from a data source
     *
     * @param language a language
     * @return a {@link List} of periods
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    List<Period> getAllPeriods(String language) throws ServiceException;
}

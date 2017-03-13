package by.iba.calculator.service.impl;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.dao.CalculationDAO;
import by.iba.calculator.dao.factory.DAOFactory;
import by.iba.calculator.service.CalculationService;
import by.iba.calculator.service.exception.ServiceException;
import by.iba.calculator.service.util.Calculator;
import by.iba.calculator.service.util.Validator;

import java.math.BigDecimal;
import java.util.List;

/**
 * Provides a business-logic with the {@link Calculation} entity and relate with it.
 */
public class CalculationServiceImpl extends Service implements CalculationService {
    private final CalculationDAO calculationDAO = DAOFactory.getInstance().getCalculationDAO();

    /**
     * Creates a calculation in a data source
     *
     * @param calculation a calculation object
     * @return a {@link BigDecimal} value of income tax
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    @Override
    public BigDecimal addCalculation(Calculation calculation) throws ServiceException {
        if (!Validator.validateCalculation(calculation)) {
            throw new ServiceException("Wrong parameters for adding calculation");
        }

        BigDecimal incomeTax = Calculator.calculateIncomeTax(calculation);

        calculation.setIncomeTax(incomeTax);

        if (calculation.isHasMainJob()) {
            this.service("Service layer: cannot add a calculation",
                    () -> calculationDAO.insertWithMainJob(calculation)
            );
        } else {
            this.service("Service layer: cannot add a calculation",
                    () -> calculationDAO.insertWithoutMainJob(calculation)
            );
        }

        return incomeTax;
    }

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
    @Override
    public List<Calculation> getAllCalculationsLimited(int start, int amount, String language) throws ServiceException {
        return this.service("Service layer: cannot get all calculations limited",
                () -> calculationDAO.findAllLimited(start, amount, language)
        );
    }

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
    @Override
    public List<Calculation> getAllCalculationsOrderByPeriodLimited(int start, int amount, String language) throws ServiceException {
        return this.service("Service layer: cannot get all calculations limited order by period",
                () -> calculationDAO.findAllOrderByPeriodLimited(start, amount, language)
        );
    }

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
    @Override
    public List<Calculation> getAllCalculationsOrderByIncomeTaxLimited(int start, int amount, String language) throws ServiceException {
        return this.service("Service layer: cannot get all calculations limited order by income tax",
                () -> calculationDAO.findAllOrderByIncomeTaxLimited(start, amount, language)
        );
    }

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
    @Override
    public List<Calculation> getAllCalculationsOrderByRevenueFromSaleLimited(int start, int amount, String language) throws ServiceException {
        return this.service("Service layer: cannot get all calculations limited order by order by revenue from sale",
                () -> calculationDAO.findAllOrderByPeriodLimited(start, amount, language)
        );
    }

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
    @Override
    public List<Calculation> getAllCalculationsOrderBySumOfExpensesOnBusinessActivityLimited(int start, int amount, String language) throws ServiceException {
        return this.service("Service layer: cannot get all calculations limited order by sum of expenses on business activity",
                () -> calculationDAO.findAllOrderBySumOfExpensesOnBusinessActivityLimited(start, amount, language)
        );
    }

    /**
     * Returns number of calculations in data source.
     *
     * @return amount of calculations
     * @throws ServiceException if error occurred with data source
     */
    @Override
    public int getCalculationsCount() throws ServiceException {
        return this.service("Service layer: cannot get count of calculations",
                calculationDAO::selectCalculationCount
        );
    }
}

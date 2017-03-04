package by.iba.calculator.service.impl;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.dao.CalculationDAO;
import by.iba.calculator.dao.factory.DAOFactory;
import by.iba.calculator.service.CalculationService;
import by.iba.calculator.service.exception.ServiceException;
import by.iba.calculator.service.util.Validator;

import java.util.List;

/**
 * Provides a business-logic with the {@link Calculation} entity and relate with it.
 */
public class CalculationServiceImpl extends Service implements CalculationService{
    private final CalculationDAO calculationDAO = DAOFactory.getInstance().getCalculationDAO();

    /**
     * Creates a calculation in a data source
     *
     * @param calculation a calculation object
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    @Override
    public void addCalculation(Calculation calculation) throws ServiceException {
        if(!Validator.validateCalculation(calculation)){
            throw new ServiceException("Wrong parameters for adding discount");
        }

        this.service("Service layer: cannot add a calculation",
                () -> calculationDAO.insert(calculation)
        );

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
}

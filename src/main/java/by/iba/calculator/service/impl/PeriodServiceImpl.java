package by.iba.calculator.service.impl;

import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.dao.PeriodDAO;
import by.iba.calculator.dao.factory.DAOFactory;
import by.iba.calculator.service.PeriodService;
import by.iba.calculator.service.exception.ServiceException;

import java.util.List;

/**
 * Provides a business-logic with the {@link Period} entity and relate with it.
 */
public class PeriodServiceImpl extends Service implements PeriodService {
    private final PeriodDAO periodDAO = DAOFactory.getInstance().getPeriodDAO();

    /**
     * Return periods from a data source
     *
     * @param language a language
     * @return a {@link List} of periods
     * @throws ServiceException in case of error occurred with a data source
     *                          or validation of data
     */
    @Override
    public List<Period> getAllPeriods(String language) throws ServiceException {
        return this.service("Service layer: cannot get all periods",
                () -> periodDAO.findAll(language)
        );
    }
}

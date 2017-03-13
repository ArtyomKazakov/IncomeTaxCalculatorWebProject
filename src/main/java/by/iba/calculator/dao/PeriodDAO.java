package by.iba.calculator.dao;

import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.dao.exception.DAOException;

import java.util.List;

/**
 * Provides a DAO-logic for the {@link Period} entity.
 */
public interface PeriodDAO {

    /**
     * Gives a list of periods from a data source.
     *
     * @param language a language
     * @return a {@link List} of periods
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<Period> findAll(String language) throws DAOException;
}

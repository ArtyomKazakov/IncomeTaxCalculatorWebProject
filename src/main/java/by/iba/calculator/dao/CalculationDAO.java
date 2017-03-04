package by.iba.calculator.dao;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.dao.exception.DAOException;

import java.util.List;

/**
 * Provides a DAO-logic for the {@link Calculation} entity.
 */
public interface CalculationDAO {
    /**
     * Inserts a new calculation into a data source.
     *
     * @param calculation a calculation object for insertion
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    void insert(Calculation calculation) throws DAOException;

    /**
     * Gives a limited list of calculations from a data source.
     *
     * @param start a number from which entries will be returned
     * @param amount of entries
     * @param language a language
     * @return a {@link List} of calculations
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    List<Calculation> findAllLimited(int start, int amount, String language) throws DAOException;
}

package by.iba.calculator.dao.impl;

import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.dao.PeriodDAO;
import by.iba.calculator.dao.exception.DAOException;
import by.iba.calculator.dao.transaction.impl.TransactionManagerImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides a DAO-logic for the {@link Period} entity for the MySQL Database.
 */
public class MySQLPeriodDAO extends MySQLDAO implements PeriodDAO {

    private static final String SELECT_ALL_PERIODS_QUERY = "SELECT `p`.`value`, `p`.`name` FROM `periods` AS `p` " +
            "JOIN `languages` AS `l` ON `p`.`language_id` = `l`.`id` WHERE `l`.`name` = ? ";

    private DataSource dataSource = (DataSource) TransactionManagerImpl.getInstance();


    /**
     * Set a {@link DataSource} object, that will give a {@link Connection}
     * for all operation with the database.
     *
     * @param dataSource for setting
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Gives a list of periods from a database.
     *
     * @param language a language
     * @return a {@link List} of periods
     * @throws DAOException in case of some exception with
     *                      a database or a connection with it
     */
    @Override
    public List<Period> findAll(String language) throws DAOException {
        return select(dataSource, SELECT_ALL_PERIODS_QUERY, "DAO layer: cannot select all periods",
                preparedStatement -> {
                    preparedStatement.setString(1, language);
                },
                this::createPeriod
        );
    }

    /**
     * Creates a new {@link Period} object.
     *
     * @param resultSet a {@link ResultSet} object from which information will be extracted
     * @return a period object
     * @throws SQLException in cases of errors
     */
    private Period createPeriod(ResultSet resultSet) throws SQLException {
        Period period = new Period();
        period.setValue(resultSet.getInt(1));
        period.setName(resultSet.getString(2));
        return period;
    }
}

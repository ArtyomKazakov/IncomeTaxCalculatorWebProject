package by.iba.calculator.dao.impl;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.dao.CalculationDAO;
import by.iba.calculator.dao.exception.DAOException;
import by.iba.calculator.dao.transaction.impl.TransactionManagerImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides a DAO-logic for the {@link Calculation} entity for the MySQL Database.
 */
public class MySQLCalculationDAO extends MySQLDAO implements CalculationDAO {

    private static final String INSERT_CALCULATION_QUERY = "INSERT INTO `calculations` " +
            "(`period_id`, `revenue_from_sale`, `nonoperating_income`, `has_main_job`, `has_right_to_benefits`, " +
            "`is_widow_or_single_parent_or_trustee`, `number_of_children_under_eighteen`, " +
            "`number_of_disabled_children_under_eighteen`, `number_of_dependents`, " +
            "`sum_of_expenses_on_insurance_premiums`, `sum_of_expenses_on_first_paid_education_for_relative`, " +
            "`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `sum_of_expenses_on_business_activity`," +
            "`income_tax`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

        private static final String SELECT_ALL_CALCULATIONS_LIMITED_QUERY = "SELECT `c`.`id`, `c`.`period_id`, " +
                "`c`.`revenue_from_sale`, `c`.`nonoperating_income`, `c`.`has_main_job`, `c`.`has_right_to_benefits`," +
                "`c`.`is_widow_or_single_parent_or_trustee`, `c`.`number_of_children_under_eighteen`," +
                "`c`.`number_of_disabled_children_under_eighteen`, `c`.`number_of_dependents`, " +
                "`c`.`sum_of_expenses_on_insurance_premiums`, `c`.`sum_of_expenses_on_first_paid_education_for_relative`, " +
                "`c`.`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `c`.`sum_of_expenses_on_business_activity`, " +
                "`c`.`income_tax`, `p`.`name` " +
                "FROM `calculations` AS `c` " +
                "JOIN `periods` AS `p` ON `c`.`period_id` = `p`.`id` " +
                "JOIN `languages` AS `l` ON `p`.`language_id` = `l`.`id` " +
                "WHERE `l`.`name` = ? LIMIT ?, ?;";

    private DataSource dataSource = (DataSource) TransactionManagerImpl.getInstance();


    /**
     * Set a {@link DataSource} object, that will give a {@link Connection}
     * for all operation with the database.
     * @param dataSource for setting
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Inserts a new calculation into a database.
     *
     * @param calculation a calculation object for insertion
     * @throws DAOException in case of some exception with
     *                      a database or a connection with it
     */
    @Override
    public void insert(Calculation calculation) throws DAOException {
        doDataManipulation(dataSource, INSERT_CALCULATION_QUERY, "DAO layer: cannot insert calculation",
                preparedStatement -> {
                    preparedStatement.setInt(1, calculation.getPeriod().getId());
                    preparedStatement.setBigDecimal(2, calculation.getRevenueFromSale());
                    preparedStatement.setBigDecimal(3, calculation.getNonoperatingIncome());
                    preparedStatement.setBoolean(4, calculation.isHasMainJob());
                    preparedStatement.setBoolean(5, calculation.getHasRightToBenefits());
                    preparedStatement.setBoolean(6, calculation.getWidowOrSingleParentOrTrustee());
                    preparedStatement.setInt(7, calculation.getNumberOfChildrenUnderEighteen());
                    preparedStatement.setInt(8, calculation.getNumberOfDisabledChildrenUnderEighteen());
                    preparedStatement.setInt(9, calculation.getNumberOfDisabledChildrenUnderEighteen());
                    preparedStatement.setBigDecimal(10, calculation.getSumOfExpensesOnInsurancePremiums());
                    preparedStatement.setBigDecimal(11, calculation.getSumOfExpensesOnFirstPaidEducationForRelative());
                    preparedStatement.setBigDecimal(12, calculation.getSumOfExpensesOnConstrOrAcquisitionOfHousingForReq());
                    preparedStatement.setBigDecimal(13, calculation.getSumOfExpensesOnBusinessActivity());
                    preparedStatement.setBigDecimal(14, calculation.getIncomeTax());
                }
        );
    }

    /**
     * Gives a limited list of calculations from a database.
     *
     * @param start a number from which entries will be returned
     * @param amount of entries
     * @return a {@link List} of schedule records
     * @throws DAOException in case of some exception with
     *                      a database or a connection with it
     */
    @Override
    public List<Calculation> findAllLimited(int start, int amount, String language) throws DAOException {
        return select(dataSource, SELECT_ALL_CALCULATIONS_LIMITED_QUERY, "DAO layer: cannot select all limited calculations",
                preparedStatement -> {
                    preparedStatement.setString(1, language);
                    preparedStatement.setInt(2, start);
                    preparedStatement.setInt(3, amount);
                },
                this :: createCalculation
        );
    }

    /**
     * Creates a new {@link Calculation} object.
     * @param resultSet a {@link ResultSet} object from which information will be extracted
     * @return a calculation object
     * @throws SQLException in cases of errors
     */
    private Calculation createCalculation(ResultSet resultSet) throws SQLException {
        Calculation calculation = new Calculation();
        calculation.setId(resultSet.getInt(1));
        Period period = new Period();
        period.setId(resultSet.getInt(2));
        period.setName(resultSet.getString(16));
        calculation.setPeriod(period);
        calculation.setRevenueFromSale(resultSet.getBigDecimal(3));
        calculation.setNonoperatingIncome(resultSet.getBigDecimal(4));
        calculation.setHasMainJob(resultSet.getBoolean(5));
        calculation.setHasRightToBenefits(resultSet.getBoolean(6));
        calculation.setWidowOrSingleParentOrTrustee(resultSet.getBoolean(7));
        calculation.setNumberOfChildrenUnderEighteen(resultSet.getInt(8));
        calculation.setNumberOfDisabledChildrenUnderEighteen(resultSet.getInt(9));
        calculation.setNumberOfDependents(resultSet.getInt(10));
        calculation.setSumOfExpensesOnInsurancePremiums(resultSet.getBigDecimal(11));
        calculation.setSumOfExpensesOnFirstPaidEducationForRelative(resultSet.getBigDecimal(12));
        calculation.setSumOfExpensesOnConstrOrAcquisitionOfHousingForReq(resultSet.getBigDecimal(13));
        calculation.setSumOfExpensesOnBusinessActivity(resultSet.getBigDecimal(14));
        calculation.setIncomeTax(resultSet.getBigDecimal(15));
        return calculation;
    }
}

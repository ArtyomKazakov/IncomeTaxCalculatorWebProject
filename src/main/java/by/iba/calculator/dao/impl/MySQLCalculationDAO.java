package by.iba.calculator.dao.impl;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.dao.CalculationDAO;
import by.iba.calculator.dao.exception.DAOException;
import by.iba.calculator.dao.transaction.impl.TransactionManagerImpl;
import by.iba.calculator.dao.util.FieldMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides a DAO-logic for the {@link Calculation} entity for the MySQL Database.
 */
public class MySQLCalculationDAO extends MySQLDAO implements CalculationDAO {

    private static final String INSERT_CALCULATION_WITHOUT_MAIN_JOB_QUERY = "INSERT INTO `calculations` " +
            "(`period_value`, `revenue_from_sale`, `nonoperating_income`, `has_main_job`, `has_right_to_benefits`, " +
            "`is_widow_or_single_parent_or_trustee`, `number_of_children_under_eighteen`, " +
            "`number_of_disabled_children_under_eighteen`, `number_of_dependents`, " +
            "`sum_of_expenses_on_insurance_premiums`, `sum_of_expenses_on_first_paid_education_for_relative`, " +
            "`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `sum_of_expenses_on_business_activity`, " +
            "`income_tax`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String INSERT_CALCULATION_WITH_MAIN_JOB_QUERY = "INSERT INTO `calculations` " +
            "(`period_value`, `revenue_from_sale`, `nonoperating_income`, `has_main_job`, " +
            "`sum_of_expenses_on_business_activity`, `income_tax`) " +
            "VALUES (?, ?, ?, ?, ?, ?) ";

    private static final String SELECT_ALL_CALCULATIONS_LIMITED_QUERY = "SELECT `c`.`id`, `c`.`period_value`, " +
            "`c`.`revenue_from_sale`, `c`.`nonoperating_income`, `c`.`has_main_job`, `c`.`has_right_to_benefits`," +
            "`c`.`is_widow_or_single_parent_or_trustee`, `c`.`number_of_children_under_eighteen`," +
            "`c`.`number_of_disabled_children_under_eighteen`, `c`.`number_of_dependents`, " +
            "`c`.`sum_of_expenses_on_insurance_premiums`, `c`.`sum_of_expenses_on_first_paid_education_for_relative`, " +
            "`c`.`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `c`.`sum_of_expenses_on_business_activity`, " +
            "`c`.`income_tax`, `p`.`name` " +
            "FROM `calculations` AS `c` " +
            "JOIN `periods` AS `p` ON `c`.`period_value` = `p`.`value` " +
            "JOIN `languages` AS `l` ON `p`.`language_id` = `l`.`id` " +
            "WHERE `l`.`name` = ? ORDER BY `c`.`id` DESC LIMIT ?, ?;";

    private static final String SELECT_ALL_CALCULATIONS_ORDER_BY_PERIOD_LIMITED_QUERY = "SELECT `c`.`id`, `c`.`period_value`, " +
            "`c`.`revenue_from_sale`, `c`.`nonoperating_income`, `c`.`has_main_job`, `c`.`has_right_to_benefits`," +
            "`c`.`is_widow_or_single_parent_or_trustee`, `c`.`number_of_children_under_eighteen`," +
            "`c`.`number_of_disabled_children_under_eighteen`, `c`.`number_of_dependents`, " +
            "`c`.`sum_of_expenses_on_insurance_premiums`, `c`.`sum_of_expenses_on_first_paid_education_for_relative`, " +
            "`c`.`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `c`.`sum_of_expenses_on_business_activity`, " +
            "`c`.`income_tax`, `p`.`name` " +
            "FROM `calculations` AS `c` " +
            "JOIN `periods` AS `p` ON `c`.`period_value` = `p`.`value` " +
            "JOIN `languages` AS `l` ON `p`.`language_id` = `l`.`id` " +
            "WHERE `l`.`name` = ? ORDER BY `c`.`period_value` LIMIT ?, ?;";

    private static final String SELECT_ALL_CALCULATIONS_ORDER_BY_INCOME_TAX_LIMITED_QUERY =
            "SELECT `c`.`id`, `c`.`period_value`, " +
            "`c`.`revenue_from_sale`, `c`.`nonoperating_income`, `c`.`has_main_job`, `c`.`has_right_to_benefits`," +
            "`c`.`is_widow_or_single_parent_or_trustee`, `c`.`number_of_children_under_eighteen`," +
            "`c`.`number_of_disabled_children_under_eighteen`, `c`.`number_of_dependents`, " +
            "`c`.`sum_of_expenses_on_insurance_premiums`, `c`.`sum_of_expenses_on_first_paid_education_for_relative`, " +
            "`c`.`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `c`.`sum_of_expenses_on_business_activity`, " +
            "`c`.`income_tax`, `p`.`name` " +
            "FROM `calculations` AS `c` " +
            "JOIN `periods` AS `p` ON `c`.`period_value` = `p`.`value` " +
            "JOIN `languages` AS `l` ON `p`.`language_id` = `l`.`id` " +
            "WHERE `l`.`name` = ? ORDER BY `c`.`income_tax` LIMIT ?, ?;";

    private static final String SELECT_ALL_CALCULATIONS_ORDER_BY_REVENUE_FROM_SALE_LIMITED_QUERY =
            "SELECT `c`.`id`, `c`.`period_value`, " +
            "`c`.`revenue_from_sale`, `c`.`nonoperating_income`, `c`.`has_main_job`, `c`.`has_right_to_benefits`," +
            "`c`.`is_widow_or_single_parent_or_trustee`, `c`.`number_of_children_under_eighteen`," +
            "`c`.`number_of_disabled_children_under_eighteen`, `c`.`number_of_dependents`, " +
            "`c`.`sum_of_expenses_on_insurance_premiums`, `c`.`sum_of_expenses_on_first_paid_education_for_relative`, " +
            "`c`.`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `c`.`sum_of_expenses_on_business_activity`, " +
            "`c`.`income_tax`, `p`.`name` " +
            "FROM `calculations` AS `c` " +
            "JOIN `periods` AS `p` ON `c`.`period_value` = `p`.`value` " +
            "JOIN `languages` AS `l` ON `p`.`language_id` = `l`.`id` " +
            "WHERE `l`.`name` = ? ORDER BY `c`.`revenue_from_sale` LIMIT ?, ?;";

    private static final String SELECT_ALL_CALCULATIONS_ORDER_BY_SUM_OF_EXPENSES_ON_BUSINESS_ACTIVITY_LIMITED_QUERY =
            "SELECT `c`.`id`, `c`.`period_value`, " +
            "`c`.`revenue_from_sale`, `c`.`nonoperating_income`, `c`.`has_main_job`, `c`.`has_right_to_benefits`," +
            "`c`.`is_widow_or_single_parent_or_trustee`, `c`.`number_of_children_under_eighteen`," +
            "`c`.`number_of_disabled_children_under_eighteen`, `c`.`number_of_dependents`, " +
            "`c`.`sum_of_expenses_on_insurance_premiums`, `c`.`sum_of_expenses_on_first_paid_education_for_relative`, " +
            "`c`.`sum_of_expenses_on_constr_or_acquisition_of_housing_for_req`, `c`.`sum_of_expenses_on_business_activity`, " +
            "`c`.`income_tax`, `p`.`name` " +
            "FROM `calculations` AS `c` " +
            "JOIN `periods` AS `p` ON `c`.`period_value` = `p`.`value` " +
            "JOIN `languages` AS `l` ON `p`.`language_id` = `l`.`id` " +
            "WHERE `l`.`name` = ? ORDER BY `c`.`sum_of_expenses_on_business_activity` LIMIT ?, ?;";

    private static final String SELECT_COUNT_OF_CALCULATIONS = "SELECT COUNT(*) FROM `calculations`;";

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
     * Inserts a new calculation into a database.
     *
     * @param calculation a calculation object for insertion
     * @throws DAOException in case of some exception with
     *                      a database or a connection with it
     */
    @Override
    public void insertWithoutMainJob(Calculation calculation) throws DAOException {
        doDataManipulation(dataSource, INSERT_CALCULATION_WITHOUT_MAIN_JOB_QUERY, "DAO layer: cannot insert calculation",
                preparedStatement -> {
                    preparedStatement.setInt(1, calculation.getPeriod().getValue());
                    preparedStatement.setBigDecimal(2, calculation.getRevenueFromSale());
                    preparedStatement.setBigDecimal(3, calculation.getNonoperatingIncome());
                    preparedStatement.setBoolean(4, calculation.isHasMainJob());
                    preparedStatement.setBoolean(5, calculation.getHasRightToBenefits());
                    preparedStatement.setBoolean(6, calculation.getIsWidowOrSingleParentOrTrustee());
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
     * Inserts a new calculation into a database.
     *
     * @param calculation a calculation object for insertion
     * @throws DAOException in case of some exception with
     *                      a database or a connection with it
     */
    @Override
    public void insertWithMainJob(Calculation calculation) throws DAOException {
        doDataManipulation(dataSource, INSERT_CALCULATION_WITH_MAIN_JOB_QUERY, "DAO layer: cannot insert calculation",
                preparedStatement -> {
                    preparedStatement.setInt(1, calculation.getPeriod().getValue());
                    preparedStatement.setBigDecimal(2, calculation.getRevenueFromSale());
                    preparedStatement.setBigDecimal(3, calculation.getNonoperatingIncome());
                    preparedStatement.setBoolean(4, calculation.isHasMainJob());
                    preparedStatement.setBigDecimal(5, calculation.getSumOfExpensesOnBusinessActivity());
                    preparedStatement.setBigDecimal(6, calculation.getIncomeTax());
                }
        );
    }

    /**
     * Gives a limited list of calculations from a database.
     *
     * @param start    a number from which entries will be returned
     * @param amount   of entries
     * @param language a language
     * @return a {@link List} of calculations
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
                this::createCalculation
        );
    }

    /**
     * Gives a limited list of calculations from a database order by period.
     *
     * @param start    a number from which entries will be returned
     * @param amount   of entries
     * @param language a language
     * @return a {@link List} of calculations
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Calculation> findAllOrderByPeriodLimited(int start, int amount, String language) throws DAOException {
        return select(dataSource, SELECT_ALL_CALCULATIONS_ORDER_BY_PERIOD_LIMITED_QUERY,
                "DAO layer: cannot select all limited calculations order by period",
                preparedStatement -> {
                    preparedStatement.setString(1, language);
                    preparedStatement.setInt(2, start);
                    preparedStatement.setInt(3, amount);
                },
                this::createCalculation
        );
    }

    /**
     * Gives a limited list of calculations from a database order by income tax.
     *
     * @param start    a number from which entries will be returned
     * @param amount   of entries
     * @param language a language
     * @return a {@link List} of calculations
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Calculation> findAllOrderByIncomeTaxLimited(int start, int amount, String language) throws DAOException {
        return select(dataSource, SELECT_ALL_CALCULATIONS_ORDER_BY_INCOME_TAX_LIMITED_QUERY,
                "DAO layer: cannot select all limited calculations order by income tax",
                preparedStatement -> {
                    preparedStatement.setString(1, language);
                    preparedStatement.setInt(2, start);
                    preparedStatement.setInt(3, amount);
                },
                this::createCalculation
        );
    }

    /**
     * Gives a limited list of calculations from a database order by revenue from sale.
     *
     * @param start    a number from which entries will be returned
     * @param amount   of entries
     * @param language a language
     * @return a {@link List} of calculations
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Calculation> findAllOrderByRevenueFromSaleLimited(int start, int amount, String language) throws DAOException {
        return select(dataSource, SELECT_ALL_CALCULATIONS_ORDER_BY_REVENUE_FROM_SALE_LIMITED_QUERY,
                "DAO layer: cannot select all limited calculations by revenue from sale",
                preparedStatement -> {
                    preparedStatement.setString(1, language);
                    preparedStatement.setInt(2, start);
                    preparedStatement.setInt(3, amount);
                },
                this::createCalculation
        );
    }

    /**
     * Gives a limited list of calculations from a database order by sum of expenses on business activity.
     *
     * @param start    a number from which entries will be returned
     * @param amount   of entries
     * @param language a language
     * @return a {@link List} of calculations
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public List<Calculation> findAllOrderBySumOfExpensesOnBusinessActivityLimited(int start, int amount, String language) throws DAOException {
        return select(dataSource, SELECT_ALL_CALCULATIONS_ORDER_BY_SUM_OF_EXPENSES_ON_BUSINESS_ACTIVITY_LIMITED_QUERY,
                "DAO layer: cannot select all limited calculations by sum of expenses on business activity",
                preparedStatement -> {
                    preparedStatement.setString(1, language);
                    preparedStatement.setInt(2, start);
                    preparedStatement.setInt(3, amount);
                },
                this::createCalculation
        );
    }

    /**
     * Gives number of rooms in a database.
     *
     * @return count of calculations
     * @throws DAOException in case of some exception with
     *                      a data source or a connection with it
     */
    @Override
    public int selectCalculationCount() throws DAOException {
        return this.singleSelect(
                dataSource,
                SELECT_COUNT_OF_CALCULATIONS,
                "Can't get count of calculations",
                resultSet -> {
                    resultSet.next();
                    return resultSet.getInt(1);
                }
        );
    }

    /**
     * Creates a new {@link Calculation} object.
     *
     * @param resultSet a {@link ResultSet} object from which information will be extracted
     * @return a calculation object
     * @throws SQLException in cases of errors
     */
    private Calculation createCalculation(ResultSet resultSet) throws SQLException {
        Calculation calculation = new Calculation();
        calculation.setId(resultSet.getInt(1));
        Period period = new Period();
        period.setValue(resultSet.getInt(2));
        period.setName(resultSet.getString(16));
        calculation.setPeriod(period);
        calculation.setRevenueFromSale(resultSet.getBigDecimal(3));
        calculation.setNonoperatingIncome(resultSet.getBigDecimal(4));
        calculation.setHasMainJob(resultSet.getBoolean(5));
        calculation.setHasRightToBenefits((Boolean) resultSet.getObject(6));
        calculation.setWidowOrSingleParentOrTrustee((Boolean) resultSet.getObject(7));
        calculation.setNumberOfChildrenUnderEighteen((Integer) resultSet.getObject(8));
        calculation.setNumberOfDisabledChildrenUnderEighteen((Integer) resultSet.getObject(9));
        calculation.setNumberOfDependents((Integer) resultSet.getObject(10));
        calculation.setSumOfExpensesOnInsurancePremiums(resultSet.getBigDecimal(11));
        calculation.setSumOfExpensesOnFirstPaidEducationForRelative(resultSet.getBigDecimal(12));
        calculation.setSumOfExpensesOnConstrOrAcquisitionOfHousingForReq(resultSet.getBigDecimal(13));
        calculation.setSumOfExpensesOnBusinessActivity(resultSet.getBigDecimal(14));
        calculation.setIncomeTax(resultSet.getBigDecimal(15));
        return calculation;
    }
}

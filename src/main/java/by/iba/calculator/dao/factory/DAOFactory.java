package by.iba.calculator.dao.factory;

import by.iba.calculator.dao.*;
import by.iba.calculator.util.injection.Injectable;
import by.iba.calculator.util.injection.annotation.InjectBean;

/**
 * Provides a logic of instancing DAO objects.
 */
public class DAOFactory implements Injectable{
    private static final DAOFactory INSTANCE = new DAOFactory();

    @InjectBean(beanName = "calculationDAO")
    private CalculationDAO calculationDAO;

    @InjectBean(beanName = "periodDAO")
    private PeriodDAO periodDAO;

    @InjectBean(beanName = "poolDAO")
    private PoolDAO poolDAO;

    private DAOFactory(){}

    /**
     * Returns the instance of the DAOFactory.

     * @return the instance of the DAOFactory
     */
    public static DAOFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Gives {@link CalculationDAO} implementation.
     *
     * @return CalculationDAO implementation
     */
    public CalculationDAO getCalculationDAO() {
        return calculationDAO;
    }

    /**
     * Gives {@link PeriodDAO} implementation.
     *
     * @return PeriodDAO implementation
     */
    public PeriodDAO getPeriodDAO() {
        return periodDAO;
    }

    /**
     * Gives {@link PoolDAO} implementation.
     *
     * @return PoolDAO implementation
     */
    public PoolDAO getPoolDAO() {
        return poolDAO;
    }

}

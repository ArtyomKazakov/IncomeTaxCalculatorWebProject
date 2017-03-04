package by.iba.calculator.service.factory;

import by.iba.calculator.service.*;
import by.iba.calculator.util.injection.Injectable;
import by.iba.calculator.util.injection.annotation.InjectBean;

/**
 * Provides a logic of instancing Service objects.
 */
public class ServiceFactory implements Injectable {
	private static final ServiceFactory INSTANCE = new ServiceFactory();

	@InjectBean(beanName = "calculationService")
	private CalculationService calculationService;

	@InjectBean(beanName = "poolService")
	private PoolService poolService;


	private ServiceFactory(){}

	/**
	 * Gives {@link ServiceFactory} instance
	 * @return ServiceFactory
     */
	public static ServiceFactory getInstance(){
		return INSTANCE;
	}

	/**
	 * Gives {@link CalculationService} implementation
	 * @return CalculationService implementation
     */
	public CalculationService getCalculationService(){
		return calculationService;
	}

	/**
	 * Gives {@link PoolService} implementation
	 * @return PoolService implementation
	 */
	public PoolService getPoolService() {
		return poolService;
	}

}

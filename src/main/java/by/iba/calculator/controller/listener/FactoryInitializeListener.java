package by.iba.calculator.controller.listener;

import by.iba.calculator.dao.factory.DAOFactory;
import by.iba.calculator.service.factory.ServiceFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Listener will be inject dependency in @link ServiceFactory}
 * and {@link DAOFactory} on servlet initialize.
 */
public class FactoryInitializeListener implements ServletContextListener {
    private static final String SERVICE_FACTORY_CONFIGURATION = "/bean/servicebeans.xml";
    private static final String DAO_FACTORY_CONFIGURATION = "/bean/daobeans.xml";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DAOFactory.getInstance().inject(DAO_FACTORY_CONFIGURATION);
        ServiceFactory.getInstance().inject(SERVICE_FACTORY_CONFIGURATION);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

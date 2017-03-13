package by.iba.calculator.command.impl.calculation;

import by.iba.calculator.bean.entity.Calculation;
import by.iba.calculator.bean.entity.PagedList;
import by.iba.calculator.command.Command;
import by.iba.calculator.command.util.CommandHelper;
import by.iba.calculator.command.util.LanguageUtil;
import by.iba.calculator.command.util.QueryUtil;
import by.iba.calculator.service.CalculationService;
import by.iba.calculator.service.exception.ServiceException;
import by.iba.calculator.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Services request to the statistic page.
 */
public class ViewStatisticPageCommand implements Command {

    private final static Logger logger = Logger.getLogger(ViewStatisticPageCommand.class);

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/statistic.jsp";

    private static final String PAGE_PARAM = "page";
    private static final String SORT_BY_PARAM = "sortBy";

    private static final String PERIOD = "period";
    private static final String INCOME_TAX = "incomeTax";
    private static final String REVENUE_FROM_SALE = "revenueFromSale";
    private static final String SUM_OF_EXPENSES_ON_BUSINESS_ACTIVITY = "sumOfExpensesOnBusinessActivity";

    private static final String CALCULATIONS_ATTRIBUTE = "calculations";

    private static final String SERVICE_ERROR_REQUEST_ATTR = "serviceError";
    private static final String SELECTED_LANGUAGE_REQUEST_ATTR = "selectedLanguage";

    private static final int AMOUNT = 10;
    private static final int DEFAULT_PAGE = 1;


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryUtil.saveCurrentQueryToSession(request);
        String languageId = LanguageUtil.getLanguageId(request);
        request.setAttribute(SELECTED_LANGUAGE_REQUEST_ATTR, languageId);

        int page = CommandHelper.getInt(request.getParameter(PAGE_PARAM));
        if (page == -1) {
            page = DEFAULT_PAGE;
        }

        String sortBy = request.getParameter(SORT_BY_PARAM);
        try {
            CalculationService calculationService = ServiceFactory.getInstance().getCalculationService();
            List<Calculation> calculations;
            switch (sortBy){
                case PERIOD: {
                    calculations = calculationService.getAllCalculationsOrderByPeriodLimited((page - 1) * AMOUNT, AMOUNT, languageId);
                    break;
                }
                case INCOME_TAX: {
                    calculations = calculationService.getAllCalculationsOrderByIncomeTaxLimited((page - 1) * AMOUNT, AMOUNT, languageId);
                    break;
                }
                case REVENUE_FROM_SALE: {
                    calculations = calculationService.getAllCalculationsOrderByRevenueFromSaleLimited((page - 1) * AMOUNT, AMOUNT, languageId);
                    break;
                }
                case SUM_OF_EXPENSES_ON_BUSINESS_ACTIVITY: {
                    calculations = calculationService.getAllCalculationsOrderBySumOfExpensesOnBusinessActivityLimited((page - 1) * AMOUNT, AMOUNT, languageId);
                    break;
                }
                default: {
                    calculations = calculationService.getAllCalculationsLimited((page - 1) * AMOUNT, AMOUNT, languageId);
                    break;
                }
            }
            int count = calculationService.getCalculationsCount();

            PagedList<Calculation> pagedList = new PagedList<>();
            pagedList.setContent(calculations);
            pagedList.setLastPage((int) Math.ceil(count / (double) AMOUNT));
            pagedList.setCurrentPage(page);

            request.setAttribute(CALCULATIONS_ATTRIBUTE, pagedList);
        } catch (ServiceException e) {
            logger.warn(e);
            request.setAttribute(SERVICE_ERROR_REQUEST_ATTR, true);
        }
        request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);
    }
}


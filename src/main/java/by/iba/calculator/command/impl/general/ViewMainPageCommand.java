package by.iba.calculator.command.impl.general;

import by.iba.calculator.bean.entity.Period;
import by.iba.calculator.command.Command;
import by.iba.calculator.command.util.LanguageUtil;
import by.iba.calculator.command.util.QueryUtil;
import by.iba.calculator.service.PeriodService;
import by.iba.calculator.service.exception.ServiceException;
import by.iba.calculator.service.factory.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Services request to the main page.
 */
public class ViewMainPageCommand implements Command{
    private final static Logger logger = Logger.getLogger(ViewMainPageCommand.class);

    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/index.jsp";

    private static final String PERIODS_ATTRIBUTE = "periods";

    private static final String SELECTED_LANGUAGE_REQUEST_ATTR = "selectedLanguage";
    private static final String SERVICE_ERROR_REQUEST_ATTR = "serviceError";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryUtil.saveCurrentQueryToSession(request);
        String languageId = LanguageUtil.getLanguageId(request);
        request.setAttribute(SELECTED_LANGUAGE_REQUEST_ATTR, languageId);

        try {
            PeriodService periodService = ServiceFactory.getInstance().getPeriodService();
            List<Period> periods = periodService.getAllPeriods(languageId);

            request.setAttribute(PERIODS_ATTRIBUTE, periods);
        } catch (ServiceException e){
            logger.warn(e);
            request.setAttribute(SERVICE_ERROR_REQUEST_ATTR, true);
        }

        request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

    }
}

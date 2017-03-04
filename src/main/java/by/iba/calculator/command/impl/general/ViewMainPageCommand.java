package by.iba.calculator.command.impl.general;

import by.iba.calculator.command.Command;
import by.iba.calculator.command.util.LanguageUtil;
import by.iba.calculator.command.util.QueryUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Services request to the main page.
 */
public class ViewMainPageCommand implements Command{
    private static final String JSP_PAGE_PATH = "WEB-INF/jsp/index.jsp";

    private static final String SELECTED_LANGUAGE_REQUEST_ATTR = "selectedLanguage";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QueryUtil.saveCurrentQueryToSession(request);
        String languageId = LanguageUtil.getLanguageId(request);
        request.setAttribute(SELECTED_LANGUAGE_REQUEST_ATTR, languageId);

        request.getRequestDispatcher(JSP_PAGE_PATH).forward(request, response);

    }
}

package by.iba.calculator.controller;

import by.iba.calculator.command.Command;
import by.iba.calculator.command.CommandProvider;
import by.iba.calculator.controller.exception.CommandNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front-controller of the whole web-application.
 */
public final class Controller extends HttpServlet {

    private final CommandProvider provider = CommandProvider.getInstance();
    private static final String COMMAND = "command";

    private static final int PAGE_NOT_FOUND_ERROR = 404;

    /**
     * Services a GET-requests.
     *
     * @param request a request object
     * @param response a response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.process(request, response);
    }

    /**
     * Services a POST-requests.
     *
     * @param request a request object
     * @param response a response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        this.process(request, response);
    }

    /**
     * Services requests.
     * @param request a request object
     * @param response a response object
     * @throws ServletException
     * @throws IOException
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String commandName = request.getParameter(COMMAND);
            Command command = provider.getCommand(commandName);
            command.execute(request, response);
        } catch (CommandNotFoundException e){
            response.sendError(PAGE_NOT_FOUND_ERROR);
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
}

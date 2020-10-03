package by.itechart.library.controller;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.command.factory.CommandFactory;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;
import lombok.extern.log4j.Log4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@MultipartConfig
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = -8384500438307619158L;
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private PathCreator pathCreator = utilFactory.getPathCreator();
    private CommandFactory commandFactory = CommandFactory.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action = request.getParameter(ParameterName.COMMAND);
        Command command = commandFactory.createCommand(action);
        try {
            String path = command.execute(request, response);
            response.sendRedirect(path);
        } catch (CommandException e) {
            log.error(e);
            response.sendRedirect(pathCreator.getError());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter(ParameterName.COMMAND);
        Command command = commandFactory.createCommand(action);
        try {
            String path = command.execute(request, response);
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (CommandException e) {
            log.error(e);
            response.sendRedirect(pathCreator.getError());
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
        PathCreator pathCreator = utilFactory.getPathCreator();
        CommandFactory commandFactory = CommandFactory.getInstance();

        String action = request.getParameter(ParameterName.COMMAND);
        Command command = commandFactory.createCommand(action);
        try {
            String path = command.execute(request, response);
            response.sendRedirect(path);
        } catch (CommandException e) {
            log.error(e);
            response.sendRedirect(pathCreator.getError());
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
        PathCreator pathCreator = utilFactory.getPathCreator();
        CommandFactory commandFactory = CommandFactory.getInstance();

        String action = request.getParameter(ParameterName.COMMAND);
        Command command = commandFactory.createCommand(action);
        try {
            String path = command.execute(request, response);
            response.sendRedirect(path);
        } catch (CommandException e) {
            log.error(e);
            response.sendRedirect(pathCreator.getError());
        }
    }
}

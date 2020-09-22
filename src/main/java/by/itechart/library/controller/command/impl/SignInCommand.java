package by.itechart.library.controller.command.impl;


import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.AttributesInitializer;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();

        String path = pathCreator.getError();
        String username = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);

        HttpSession session = request.getSession();
        try {
            User user = commonService.signIn(username, password);
            session.setAttribute(ParameterName.USER, user);
            //  path = pathCreator.getForwardMainPage(request.getContextPath());
            path = "main-page";
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}

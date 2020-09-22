package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForwardEditProfileCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();

        HttpSession session = request.getSession();
        long userId = (int) session.getAttribute(ParameterName.USER_ID);
        User user;
        try {
            user = commonService.getProfile(userId);
            request.setAttribute(ParameterName.USER, user);
            path = pathCreator.getEditProfile();

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}

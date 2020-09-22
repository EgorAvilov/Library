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

public class EditProfileCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();

        long id = Long.parseLong(request.getParameter(ParameterName.USER_ID));
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String phoneNumber = request.getParameter(ParameterName.PHONE_NUMBER);
        String password = request.getParameter(ParameterName.PASSWORD);

        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        try {
            commonService.updateProfile(user);
            path = pathCreator.getUserPage();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return path;
    }
}

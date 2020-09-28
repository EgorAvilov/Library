package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.Role;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Log4j
public class SignUpCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();

        String username = request.getParameter(ParameterName.USERNAME);
        String password = request.getParameter(ParameterName.PASSWORD);
        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String lastName = request.getParameter(ParameterName.LAST_NAME);
        String email = request.getParameter(ParameterName.EMAIL);
        boolean gender = Boolean.parseBoolean(request.getParameter(ParameterName.GENDER));
        String phoneNumber = request.getParameter(ParameterName.PHONE_NUMBER);
        LocalDate dateOfRegistration = LocalDate.now();
        Role role;
        if (request.getSession().isNew()) {
            role = Role.USER;
        } else {//проверить на работоспособность
            role = Role.values()[(int) request.getSession()
                                              .getAttribute(ParameterName.ROLE)];
        }


        User user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setGender(gender);
        user.setPhoneNumber(phoneNumber);
        user.setRole(role);//сделать по проверке сессии, если админ то другое
        user.setDateOfRegistration(dateOfRegistration);

        HttpSession session = request.getSession();

        try {
            commonService.signUp(user);
            path = pathCreator.getSignIn();
            session.setAttribute(ParameterName.USER, user);
            path = pathCreator.getMainPage();
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }
}

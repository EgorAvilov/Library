package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.User;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Log4j
public class SignOutCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
    private PathCreator pathCreator = utilFactory.getPathCreator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String path;

        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole()
                       .getRoleId();
        if (valueChecker.isAnyUser(role)) {
            session.invalidate();
            log.info("signing out");

            return pathCreator.getSignIn();
        } else {
            path = pathCreator.getNoAccess();
            return path;
        }
    }
}

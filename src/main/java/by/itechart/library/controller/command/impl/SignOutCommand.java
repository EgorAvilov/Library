package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpSession session = request.getSession();
        String path;
        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole().getRoleId();
        if (valueChecker.isAnyUser(role)) {
            session.invalidate();
            return pathCreator.getSignIn();
        } else {
            path = pathCreator.getError();
            return path;
        }
    }
}

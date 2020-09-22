package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignOutCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PathCreator pathCreator = utilFactory.getPathCreator();
        HttpSession session = request.getSession();
        session.invalidate();
        return pathCreator.getSignIn();
    }
}

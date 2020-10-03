package by.itechart.library.controller.command;


import by.itechart.library.controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
     String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}

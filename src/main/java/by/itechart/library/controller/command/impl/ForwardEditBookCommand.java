package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.Book;
import by.itechart.library.entity.User;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Log4j
public class ForwardEditBookCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
    PathCreator pathCreator = utilFactory.getPathCreator();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();

        String path;

        long bookId = Long.parseLong(request.getParameter(ParameterName.BOOK_ID));
        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole().getRoleId();
        Book book;

        try {
            if (valueChecker.isAdmin(role)) {
                book = commonService.getBook(bookId);
                request.setAttribute(ParameterName.BOOK, book);
                path = pathCreator.getEditBook();
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }
}

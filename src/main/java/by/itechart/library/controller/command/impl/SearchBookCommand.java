package by.itechart.library.controller.command.impl;

import by.itechart.library.controller.command.Command;
import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.command.exception.CommandException;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.Book;
import by.itechart.library.service.ServiceFactory;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
@Log4j
public class SearchBookCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();

        String title=request.getParameter(ParameterName.SEARCH_TITLE);
        String authors=request.getParameter(ParameterName.SEARCH_AUTHORS);
        String genres=request.getParameter(ParameterName.SEARCH_GENRES);
        String description=request.getParameter(ParameterName.SEARCH_DESCRIPTION);

        List<Book> books;
        try {
            books = commonService.searchBooks(title,authors,genres,description);
            request.setAttribute(ParameterName.BOOKS, books);
            path = pathCreator.getBooksPage();
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }
}

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
public class ForwardToMainPageCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();
        int currentPage = Integer.parseInt(request.getParameter(ParameterName.CURRENT_PAGE));
        int recordsPerPage = Integer.parseInt(request.getParameter(ParameterName.RECORDS_PER_PAGE));
        List<Book> books;
        try {
            int numberOfRows = commonService.getNumberOfBookRows();
            int numberOfPages = numberOfRows / recordsPerPage;
            if (numberOfRows % recordsPerPage > 0) {
                numberOfPages++;
            }
            books = commonService.getAllBooks(currentPage, recordsPerPage);
            request.setAttribute(ParameterName.BOOKS, books);
            request.setAttribute(ParameterName.NUMBER_OF_PAGES, numberOfPages);
            request.setAttribute(ParameterName.CURRENT_PAGE, currentPage);
            request.setAttribute(ParameterName.RECORDS_PER_PAGE, recordsPerPage);
            path = pathCreator.getMainPage();
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }
}

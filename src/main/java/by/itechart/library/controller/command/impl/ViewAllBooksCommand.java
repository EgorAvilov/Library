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
public class ViewAllBooksCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private CommonService commonService = serviceFactory.getCommonService();

    //здесь убрать хардкод
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PathCreator pathCreator = utilFactory.getPathCreator();

        //int currentPage = Integer.parseInt(request.getParameter(ParameterName.CURRENT_PAGE));
        //int recordsPerPage = Integer.parseInt(request.getParameter(ParameterName.RECORDS_PER_PAGE));

        String path = pathCreator.getError();
        List<Book> books;
        try {
            int numberOfRows = commonService.getNumberOfBookRows();
//            int numberOfPages = numberOfRows / recordsPerPage;
//            if (numberOfRows % recordsPerPage > 0) {
//                numberOfPages++;
//            }
            books = commonService.getAllBooks(1, 10);
            request.setAttribute(ParameterName.BOOKS, books);
            request.setAttribute(ParameterName.NUMBER_OF_PAGES, 1);
            request.setAttribute(ParameterName.CURRENT_PAGE, 1);
            request.setAttribute(ParameterName.RECORDS_PER_PAGE, 10);
            path = pathCreator.getBooksPage();
        } catch (ServiceException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }
}

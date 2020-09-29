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
import by.itechart.library.service.api.AdminService;
import by.itechart.library.service.exception.ServiceException;
import lombok.extern.log4j.Log4j;
import sun.instrument.InstrumentationImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.time.LocalDate;

@Log4j
public class EditBookCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AdminService adminService = serviceFactory.getAdminService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
        HttpSession session = request.getSession();

        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();
        long bookId = Long.parseLong(request.getParameter(ParameterName.BOOK_ID));

        String title = request.getParameter(ParameterName.TITLE);
        String authors = request.getParameter(ParameterName.AUTHORS);
        String publisher = request.getParameter(ParameterName.PUBLISHER);
        LocalDate publishDate = LocalDate.parse(request.getParameter(ParameterName.PUBLISH_DATE));
        String genres = request.getParameter(ParameterName.GENRES);
        int pageCount = Integer.parseInt(request.getParameter(ParameterName.PAGE_COUNT));
        String ISBN = request.getParameter(ParameterName.ISBN);
        String description = request.getParameter(ParameterName.DESCRIPTION);
        int totalAmount = Integer.parseInt(request.getParameter(ParameterName.TOTAL_AMOUNT));
        int availableAmount = totalAmount;

        Book book = new Book();
        book.setId(bookId);
        book.setTitle(title);
        book.setAuthors(authors);
        book.setPublisher(publisher);
        book.setPublishDate(publishDate);
        book.setGenres(genres);
        book.setPageCount(pageCount);
        book.setISBN(ISBN);
        book.setDescription(description);
        book.setTotalAmount(totalAmount);

        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole().getRoleId();
        try {
            Part coverPart = request.getPart(ParameterName.COVER);
            InputStream cover = getInputStream(coverPart);
            book.setCover(cover.toString());

            if (valueChecker.isAdmin(role)) {
                adminService.updateBook(book);
                path = pathCreator.getBookPage(request.getContextPath(), bookId);
            } else {
                path = pathCreator.getError();
            }
        } catch (ServiceException | IOException | ServletException e) {
            log.error(e);
            throw new CommandException(e);
        }
        return path;
    }

    private InputStream getInputStream(Part coverPart) throws IOException {
        InputStream stream = null;
        if (coverPart != null){
            stream =  coverPart.getInputStream();
        }
        return stream;
    }
}

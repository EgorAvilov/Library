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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.io.FileUtils;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

@Log4j
public class AddBookCommand implements Command {
    private ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private AdminService adminService = serviceFactory.getAdminService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        ControllerValueChecker valueChecker = utilFactory.getControllerValueChecker();
        PathCreator pathCreator = utilFactory.getPathCreator();
        String path = pathCreator.getError();

        HttpSession session = request.getSession();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 1024 * 2);
        File image = new File(request.getParameter(ParameterName.COVER));
        byte[] cover = new byte[0];//сделать как у артема
        try {
            cover = FileUtils.readFileToByteArray(image);
        } catch (IOException e) {
            e.printStackTrace();
        }


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
        book.setCover("");
        book.setTitle(title);
        book.setAuthors(authors);
        book.setPublisher(publisher);
        book.setPublishDate(publishDate);
        book.setGenres(genres);
        book.setPageCount(pageCount);
        book.setISBN(ISBN);
        book.setDescription(description);
        book.setTotalAmount(totalAmount);
        book.setAvailableAmount(availableAmount);

        User user = (User) session.getAttribute(ParameterName.USER);
        int role = user.getRole().getRoleId();
        try {
            if (valueChecker.isAdmin(role)) {
                adminService.addBook(book);
                path = pathCreator.getForwardMainPage(request.getContextPath());
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

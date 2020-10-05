package by.itechart.library.controller.filter;

import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageSecurityFilter implements Filter {

    private static final String ADMIN = "admin";
    private static final String USER = "user";
    private static final String NOT_AUTHORIZED_USER = "no-authorized-user";

    private List<String> adminUrls;
    private List<String> userUrls;
    private List<String> authorizedUserUrls;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String c = request.getRequestURI();

        ControllerUtilFactory utilFactory = ControllerUtilFactory.getInstance();
        PathCreator pathCreator = utilFactory.getPathCreator();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ParameterName.USER);
        int roleId = 0;
        if (user != null) {
            roleId = user.getRole()
                         .getRoleId();
        }

        if (roleId == 2) {
            for (String url : adminUrls) {
                if (c.endsWith(url)) {
                    response.sendRedirect(pathCreator.getNoAccess());
                }
            }
        } else if (roleId == 1) {
            for (String url : userUrls) {
                if (c.endsWith(url)) {
                    response.sendRedirect(pathCreator.getNoAccess());
                }
            }
        } else {
            for (String url : authorizedUserUrls) {
                if (c.endsWith(url)) {
                    response.sendRedirect(pathCreator.getNoAccess());
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        adminUrls = new ArrayList<String>() {{
            add("add-book");
            add("change-book-deleted-status");
            add("change-user-deleted-status");
            add("edit-book");
            add("edit-borrow-record-by-admin");
            add("edit-profile");
            add("forward-edit-book");
            add("forward-edit-borrow-record");
            add("forward-edit-profile");
            add("forward-to-main-page");
            add("search-book");
            add("set-admin");
            add("sign-out");
            add("view-all-books");
            add("view-all-borrow-records-by-admin");
            add("view-all-users");
            add("view-book");
            add("view-profile");

        }};
        userUrls = new ArrayList<String>() {{
            add("add-borrow-record");
            add("edit-borrow-record-by-user");
            add("edit-profile");
            add("forward-edit-book");
            add("forward-edit-borrow-record");
            add("forward-edit-profile");
            add("forward-to-main-page");
            add("search-book");
            add("sign-out");
            add("view-all-books");
            add("view-all-borrow-records-by-user");
            add("view-book");
            add("view-profile");
            add("view-borrow-record");
        }};
        authorizedUserUrls = new ArrayList<String>() {{
            addAll(adminUrls);
            addAll(userUrls);
        }};
    }

}

package by.itechart.library.controller.filter;

import by.itechart.library.controller.command.ParameterName;
import by.itechart.library.controller.util.ControllerUtilFactory;
import by.itechart.library.controller.util.api.PathCreator;
import by.itechart.library.entity.Role;
import by.itechart.library.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class SecurityPageFilter implements javax.servlet.Filter {
    private ControllerUtilFactory utilFactory;
    private PathCreator pathCreator;

    private HashMap<String, Object> paths;


    public void destroy() {
        paths = null;
        utilFactory = null;
        pathCreator = null;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute(ParameterName.USER);

        Role role = null;
        if (user != null) {
            role = user.getRole();
        }
        Set<String> pathsSet = paths.keySet();//достает все пути из мапы
        for (String path : pathsSet) {
            if (path.equals(requestURI) && Objects.equals(role, paths.get(path))) {
                response.sendRedirect(requestURI);//тут дописать чтоб кидало туда что написал
            }
        }
        System.out.println(requestURI.endsWith("book-page"));
        if (role == null) {
            response.sendRedirect(pathCreator.getSignIn());
        } else {
            response.sendRedirect(request.getContextPath() + pathCreator.getBooksPage());
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        utilFactory = ControllerUtilFactory.getInstance();
        pathCreator = utilFactory.getPathCreator();
        if (paths == null) {
            paths = new HashMap<String, Object>() {
                {
                    put(pathCreator.getSignIn(), null);
                    put(pathCreator.getSignUp(), null);
                    put("fgfhfh", Role.ADMIN);
                    put("serghfgnh", Role.ADMIN);
                    put("srdf", Role.ADMIN);
                    put("sgdf", Role.USER);
                }
            };
        }
    }

}
package by.itechart.library.controller.util.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpRequestResponseKeeper {
    public void setAll(HttpServletRequest request, HttpServletResponse response);
    public HttpServletRequest getRequest();
    public HttpServletResponse getResponse();
}

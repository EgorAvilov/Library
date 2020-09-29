package by.itechart.library.controller.util.api;

import javax.servlet.http.Part;

public interface ControllerValueChecker {
    public boolean isNumber(String number);

    public boolean isAdmin(int statusId);

    public boolean isAnyUser(int statusId);

    public boolean isUser(int statusId);

    public boolean isPhoto(Part part);

    public boolean suitsSize(long fileSize);
}

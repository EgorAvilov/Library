package by.itechart.library.controller.util.api;

import javax.servlet.http.Part;

public interface ControllerValueChecker {

     boolean isAdmin(int statusId);

     boolean isAnyUser(int statusId);

     boolean isUser(int statusId);

     boolean isPhoto(Part part);

     boolean isSize(long fileSize);
}

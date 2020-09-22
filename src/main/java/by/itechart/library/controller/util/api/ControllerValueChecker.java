package by.itechart.library.controller.util.api;

public interface ControllerValueChecker {
    public boolean isNumber(String number);

    public boolean isAdmin(int statusId);

    public boolean isAnyUser(int statusId);

    public boolean isUser(int statusId);
}

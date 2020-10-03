package by.itechart.library.controller.util.impl;

import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.entity.Role;


import javax.servlet.http.Part;


public class ControllerValueCheckerImpl implements ControllerValueChecker {
    private final long MAX_FILE_SIZE = 2 * 1024 * 1024;

    @Override
    public boolean isAdmin(int statusId) {
        Role role = Role.values()[statusId - 1];
        return role == Role.ADMIN;
    }

    @Override
    public boolean isAnyUser(int statusId) {
        Role role = Role.values()[statusId - 1];
        return role == Role.ADMIN
                || role == Role.USER;
    }

    @Override
    public boolean isUser(int statusId) {
        Role role = Role.values()[statusId - 1];
        return role == Role.USER;
    }

    @Override
    public boolean isPhoto(Part part) {
        return part.getContentType().endsWith("jpg")
                || part.getContentType().endsWith("jpeg")
                || part.getContentType().endsWith("png");
    }

    @Override
    public boolean isSize(long fileSize) {
        return fileSize <= MAX_FILE_SIZE;
    }
}

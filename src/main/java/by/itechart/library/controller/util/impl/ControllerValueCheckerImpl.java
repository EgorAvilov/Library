package by.itechart.library.controller.util.impl;

import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.entity.Role;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;

public class ControllerValueCheckerImpl implements ControllerValueChecker {
    private final long MAX_FILE_SIZE = 2 * 1028 * 1024;

    @Override
    public boolean isNumber(String number) {
        return false;
    }

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
        File file = (File) part;//проверить работу
        String extension = FilenameUtils.getExtension(file.getName());
        return extension.equalsIgnoreCase("jpg")
                || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("png");
    }

    @Override
    public boolean suitsSize(long fileSize) {
        return fileSize <= MAX_FILE_SIZE;
    }
}

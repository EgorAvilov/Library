package by.itechart.library.controller.util.impl;

import by.itechart.library.controller.util.api.ControllerValueChecker;
import by.itechart.library.entity.Role;

public class ControllerValueCheckerImpl implements ControllerValueChecker {
    @Override
    public boolean isNumber(String number) {
        return false;
    }

    @Override
    public boolean isAdmin(int statusId) {
        Role role = Role.values()[statusId];
        return role == Role.ADMIN;
    }

    @Override
    public boolean isAnyUser(int statusId) {
        Role role = Role.values()[statusId];
        return role == Role.ADMIN
                || role == Role.USER;
    }

    @Override
    public boolean isUser(int statusId) {
        Role role = Role.values()[statusId];
        return role == Role.USER;
    }
}

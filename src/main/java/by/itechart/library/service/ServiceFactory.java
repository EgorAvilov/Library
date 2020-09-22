package by.itechart.library.service;

import by.itechart.library.service.api.AdminService;
import by.itechart.library.service.api.CommonService;
import by.itechart.library.service.api.UserService;
import by.itechart.library.service.impl.AdminServiceImpl;
import by.itechart.library.service.impl.CommonServiceImpl;
import by.itechart.library.service.impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceFactory {
    @Getter
    private static final ServiceFactory instance = new ServiceFactory();
    @Getter
    private UserService userService = new UserServiceImpl();
    @Getter
    private CommonService commonService = new CommonServiceImpl();
    @Getter
    private AdminService adminService = new AdminServiceImpl();



}

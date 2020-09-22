package by.itechart.library.controller.util;

import by.itechart.library.controller.util.api.*;
import by.itechart.library.controller.util.impl.*;
import lombok.Getter;

public class ControllerUtilFactory {
    @Getter
    private final static ControllerUtilFactory instance = new ControllerUtilFactory();
    @Getter
    private PathCreator pathCreator = new PathCreatorImpl();
    @Getter
    private AttributesInitializer attributesInitializer = new AttributesInitializerImpl();
    @Getter
    private HttpRequestResponseKeeper httpRequestResponseKeeper = new HttpRequestResponseKeeperImpl();
    @Getter
    private ControllerValueChecker controllerValueChecker=new ControllerValueCheckerImpl();
    @Getter
    private Pagination pagination=new PaginationImpl();
}

package by.itechart.library.dao.util;


import by.itechart.library.dao.util.api.ResourceCloser;
import by.itechart.library.dao.util.api.ResultCreator;
import by.itechart.library.dao.util.api.StatementInitializer;
import by.itechart.library.dao.util.impl.ResourceCloserImpl;
import by.itechart.library.dao.util.impl.ResultCreatorImpl;
import by.itechart.library.dao.util.impl.StatementInitializerImpl;
import lombok.Getter;

public class DAOUtilFactory {

    @Getter
    private static final DAOUtilFactory Instance = new DAOUtilFactory();

    @Getter
    private ResultCreator resultCreator = new ResultCreatorImpl();

    @Getter
    private ResourceCloser resourceCloser = new ResourceCloserImpl();

    @Getter
    private StatementInitializer statementInitializer = new StatementInitializerImpl();
}
